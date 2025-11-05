package com.portingdeadmods.minimal_exchange.content.blockentities;

import com.portingdeadmods.minimal_exchange.registries.MEBlockEntityTypes;
import com.portingdeadmods.portingdeadlibs.api.blockentities.ContainerBlockEntity;
import com.portingdeadmods.portingdeadlibs.utils.capabilities.HandlerUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class CrucibleBlockEntity extends ContainerBlockEntity {
    public CrucibleBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(MEBlockEntityTypes.CRUCIBLE.get(), blockPos, blockState);
        this.addItemHandler(HandlerUtils::newItemStackHandler, builder -> builder
                .slots(3)
                .onChange(this::onItemsChanged)
                .validator((slot, item) -> true)
        );
    }

    private void onItemsChanged(int slot) {
        this.updateData();
    }

}
