package com.portingdeadmods.minimal_exchange.registries;

import com.portingdeadmods.minimal_exchange.MinimalExchange;
import com.portingdeadmods.portingdeadlibs.api.utils.PDLDeferredRegisterBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;

public final class MEBlocks {
    public static final PDLDeferredRegisterBlocks BLOCKS = PDLDeferredRegisterBlocks.createBlocksRegister(MinimalExchange.MODID, MEItems.ITEMS);

    public static final DeferredBlock<Block> ALCHEMICAL_STONE = BLOCKS.registerBlockWithItem("alchemical_stone", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));

}
