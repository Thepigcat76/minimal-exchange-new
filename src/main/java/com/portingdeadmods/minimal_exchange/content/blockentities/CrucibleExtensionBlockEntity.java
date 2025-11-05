package com.portingdeadmods.minimal_exchange.content.blockentities;

import com.portingdeadmods.minimal_exchange.registries.MEBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class CrucibleExtensionBlockEntity extends BlockEntity {
    public CrucibleExtensionBlockEntity(BlockPos pos, BlockState blockState) {
        super(MEBlockEntityTypes.CRUCIBLE_EXTENSION.get(), pos, blockState);
    }
}
