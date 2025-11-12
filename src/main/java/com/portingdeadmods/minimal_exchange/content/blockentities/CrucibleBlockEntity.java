package com.portingdeadmods.minimal_exchange.content.blockentities;

import com.portingdeadmods.minimal_exchange.registries.MEBlockEntityTypes;
import com.portingdeadmods.portingdeadlibs.api.blockentities.ContainerBlockEntity;
import com.portingdeadmods.portingdeadlibs.utils.BlockUtils;
import com.portingdeadmods.portingdeadlibs.utils.capabilities.HandlerUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;

import java.util.*;
import java.util.stream.Collectors;

public class CrucibleBlockEntity extends ContainerBlockEntity {
    private Set<BlockPos> extensionPositions;

    public CrucibleBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(MEBlockEntityTypes.CRUCIBLE.get(), blockPos, blockState);
        this.addItemHandler(HandlerUtils::newItemStackHandler, builder -> builder
                .slots(3)
                .onChange(this::onItemsChanged)
                .validator((slot, item) -> true)
        );
        this.extensionPositions = new HashSet<>();
    }

    public void addExtensionPos(BlockPos pos) {
        if (this.extensionPositions == null) {
            this.extensionPositions = new HashSet<>();
        }
        this.extensionPositions.add(pos);
        this.updateData();
    }

    public void removeExtensionPos(BlockPos pos) {
        if (this.extensionPositions != null) {
            this.extensionPositions.remove(pos);
            this.updateData();
        }
    }

    public Set<BlockPos> getExtensionPositions() {
        return this.extensionPositions;
    }

    private void onItemsChanged(int slot) {
        this.updateData();
    }

    public void recheckExtensions() {
        for (BlockPos extensionPos : this.extensionPositions) {
            CrucibleExtensionBlockEntity be = BlockUtils.getBE(CrucibleExtensionBlockEntity.class, level, extensionPos);
            if (be != null) {
                be.setMainCruciblePos(null);
            }
        }
        this.extensionPositions.clear();

        BlockPos.MutableBlockPos curPos = this.worldPosition.above().mutable();

        CrucibleExtensionBlockEntity extensionBe;
        while ((extensionBe = BlockUtils.getBE(CrucibleExtensionBlockEntity.class, level, curPos)) != null) {
            extensionBe.setMainCruciblePos(this.worldPosition);
            this.extensionPositions.add(curPos);
            curPos.move(Direction.UP, 1);
        }
        this.updateData();

    }

    @Override
    protected void loadData(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadData(tag, provider);

        if (tag.contains("extension_positions")) {
            this.extensionPositions = Arrays.stream(tag.getLongArray("extension_positions")).mapToObj(BlockPos::of).collect(Collectors.toSet());
        }
    }

    @Override
    protected void saveData(CompoundTag tag, HolderLookup.Provider provider) {
        super.saveData(tag, provider);

        if (this.extensionPositions != null) {
            tag.putLongArray("extension_positions", this.extensionPositions.stream().mapToLong(BlockPos::asLong).toArray());
        }
    }
}
