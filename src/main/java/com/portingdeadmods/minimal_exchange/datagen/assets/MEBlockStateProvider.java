package com.portingdeadmods.minimal_exchange.datagen.assets;

import com.portingdeadmods.minimal_exchange.MinimalExchange;
import com.portingdeadmods.minimal_exchange.content.blocks.CrucibleBlock;
import com.portingdeadmods.minimal_exchange.content.blocks.CrucibleExtensionBlock;
import com.portingdeadmods.minimal_exchange.registries.MEBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.NotNull;

public class MEBlockStateProvider extends BlockStateProvider {
    public MEBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MinimalExchange.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(MEBlocks.ALCHEMICAL_STONE.get());

        crucibleModel(MEBlocks.CRUCIBLE);
        crucibleExtensionModel(MEBlocks.CRUCIBLE_EXTENSION);
    }

    private void crucibleExtensionModel(DeferredBlock<CrucibleExtensionBlock> block) {
        BlockModelBuilder crucibleModel = models().withExistingParent(key(block.get()).toString(), MinimalExchange.rl("block/crucible_extension_base"))
                .texture("0", "minimal_exchange:block/crucible_extension_side")
                .texture("1", "minimal_exchange:block/crucible_top")
                .texture("2", "minimal_exchange:block/crucible_extension_inside");
        BlockModelBuilder crucibleConnectedBottomModel = models().withExistingParent(key(block.get()).withSuffix("_connected_bottom").toString(), MinimalExchange.rl("block/crucible_extension_base"))
                .texture("0", "minimal_exchange:block/crucible_extension_connected_bottom")
                .texture("1", "minimal_exchange:block/crucible_top")
                .texture("2", "minimal_exchange:block/crucible_extension_inside");
        BlockModelBuilder crucibleConnectedTopModel = models().withExistingParent(key(block.get()).withSuffix("_connected_top").toString(), MinimalExchange.rl("block/crucible_extension_base"))
                .texture("0", "minimal_exchange:block/crucible_extension_connected_top")
                .texture("1", "minimal_exchange:block/crucible_top")
                .texture("2", "minimal_exchange:block/crucible_extension_inside");
        BlockModelBuilder crucibleConnectedBothModel = models().withExistingParent(key(block.get()).withSuffix("_connected_both").toString(), MinimalExchange.rl("block/crucible_extension_base"))
                .texture("0", "minimal_exchange:block/crucible_extension_connected_both")
                .texture("1", "minimal_exchange:block/crucible_top")
                .texture("2", "minimal_exchange:block/crucible_extension_inside");
        getVariantBuilder(block.get()).partialState().with(CrucibleBlock.CONNECTED_TOP, false).with(CrucibleBlock.CONNECTED_BOTTOM, false)
                .modelForState().modelFile(crucibleModel).addModel()
                .partialState().with(CrucibleBlock.CONNECTED_TOP, true).with(CrucibleBlock.CONNECTED_BOTTOM, false)
                .modelForState().modelFile(crucibleConnectedTopModel).addModel()
                .partialState().with(CrucibleBlock.CONNECTED_TOP, false).with(CrucibleBlock.CONNECTED_BOTTOM, true)
                .modelForState().modelFile(crucibleConnectedBottomModel).addModel()
                .partialState().with(CrucibleBlock.CONNECTED_TOP, true).with(CrucibleBlock.CONNECTED_BOTTOM, true)
                .modelForState().modelFile(crucibleConnectedBothModel).addModel();
    }

    private void crucibleModel(DeferredBlock<CrucibleBlock> block) {
        BlockModelBuilder crucibleModel = models().withExistingParent(key(block.get()).toString(), MinimalExchange.rl("block/crucible_base"))
                .texture("0", "minimal_exchange:block/crucible_new0")
                .texture("1", "minimal_exchange:block/crucible_top")
                .texture("2", "minimal_exchange:block/crucible_extras")
                .texture("3", "minimal_exchange:block/crucible_bottom")
                .texture("4", "minimal_exchange:block/crucible_inside")
                .texture("5", "minimal_exchange:block/crucible_top_inside");
        BlockModelBuilder crucibleConnectedModel = models().withExistingParent(key(block.get()).withSuffix("_connected").toString(), MinimalExchange.rl("block/crucible_base"))
                .texture("0", "minimal_exchange:block/crucible_connected")
                .texture("1", "minimal_exchange:block/crucible_top")
                .texture("2", "minimal_exchange:block/crucible_extras")
                .texture("3", "minimal_exchange:block/crucible_bottom")
                .texture("4", "minimal_exchange:block/crucible_inside")
                .texture("5", "minimal_exchange:block/crucible_top_inside");
        getVariantBuilder(block.get()).partialState().with(CrucibleBlock.CONNECTED_TOP, false)
                .modelForState().modelFile(crucibleModel).addModel()
                .partialState().with(CrucibleBlock.CONNECTED_TOP, true)
                .modelForState().modelFile(crucibleConnectedModel).addModel();
    }

    private static @NotNull ResourceLocation key(ItemLike item) {
        return BuiltInRegistries.ITEM.getKey(item.asItem());
    }

}
