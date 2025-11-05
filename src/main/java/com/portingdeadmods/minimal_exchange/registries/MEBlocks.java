package com.portingdeadmods.minimal_exchange.registries;

import com.portingdeadmods.minimal_exchange.MinimalExchange;
import com.portingdeadmods.minimal_exchange.content.blocks.AlchemicalFireBlock;
import com.portingdeadmods.minimal_exchange.content.blocks.CrucibleBlock;
import com.portingdeadmods.minimal_exchange.content.blocks.CrucibleExtensionBlock;
import com.portingdeadmods.portingdeadlibs.api.utils.PDLDeferredRegisterBlocks;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Set;
import java.util.function.Supplier;

public final class MEBlocks {
    public static final PDLDeferredRegisterBlocks BLOCKS = PDLDeferredRegisterBlocks.createBlocksRegister(MinimalExchange.MODID, MEItems.ITEMS);
    public static final BlockBehaviour.Properties ALCHEMICAL_STONE_PROPS = BlockBehaviour.Properties.ofFullCopy(Blocks.STONE);

    public static final DeferredBlock<Block> ALCHEMICAL_STONE = BLOCKS.registerBlockWithItem("alchemical_stone", Block::new, ALCHEMICAL_STONE_PROPS);
    public static final DeferredBlock<AlchemicalFireBlock> ALCHEMICAL_FIRE = BLOCKS.registerBlock("alchemical_fire", AlchemicalFireBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_FIRE));

    public static final DeferredBlock<CrucibleBlock> CRUCIBLE = BLOCKS.registerBlockWithItem("crucible", CrucibleBlock::new, ALCHEMICAL_STONE_PROPS);

    public static final DeferredBlock<CrucibleExtensionBlock> CRUCIBLE_EXTENSION = BLOCKS.registerBlockWithItem("crucible_extension", CrucibleExtensionBlock::new, ALCHEMICAL_STONE_PROPS);
}
