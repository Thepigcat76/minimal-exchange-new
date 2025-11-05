package com.portingdeadmods.minimal_exchange.content.items;

import com.portingdeadmods.minimal_exchange.registries.MEBlocks;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class AlchemicalFireBlockItem extends BlockItem {
    public AlchemicalFireBlockItem(Properties properties) {
        super(MEBlocks.ALCHEMICAL_FIRE.get(), properties);
    }

    @Override
    protected boolean canPlace(BlockPlaceContext context, BlockState state) {
        return false;
    }

    @Override
    public InteractionResult place(BlockPlaceContext context) {
        return InteractionResult.FAIL;
    }

}
