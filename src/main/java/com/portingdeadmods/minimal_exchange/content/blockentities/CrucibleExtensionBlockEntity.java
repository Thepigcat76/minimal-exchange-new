package com.portingdeadmods.minimal_exchange.content.blockentities;

import com.portingdeadmods.minimal_exchange.registries.MEBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CrucibleExtensionBlockEntity extends BlockEntity {
    private @Nullable BlockPos mainCruciblePos;

    public CrucibleExtensionBlockEntity(BlockPos pos, BlockState blockState) {
        super(MEBlockEntityTypes.CRUCIBLE_EXTENSION.get(), pos, blockState);
    }

    public void setMainCruciblePos(@Nullable BlockPos mainCruciblePos) {
        this.mainCruciblePos = mainCruciblePos;
        this.updateData();
    }

    public @Nullable BlockPos getMainCruciblePos() {
        return mainCruciblePos;
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        if (tag.contains("main_crucible_pos")) {
            this.mainCruciblePos = BlockPos.of(tag.getLong("main_crucible_pos"));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);

        if (this.mainCruciblePos != null) {
            tag.putLong("main_crucible_pos", this.mainCruciblePos.asLong());
        }
    }

    public void updateData() {
        setChanged();
        level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag(HolderLookup.Provider provider) {
        return this.saveWithoutMetadata(provider);
    }

}
