package com.portingdeadmods.minimal_exchange.datagen.assets;

import com.portingdeadmods.minimal_exchange.MinimalExchange;
import com.portingdeadmods.minimal_exchange.registries.MEBlocks;
import com.portingdeadmods.minimal_exchange.registries.MEItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class MEItemModelProvider extends ItemModelProvider {
    public MEItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MinimalExchange.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        MEBlocks.BLOCKS.getBlockItems().stream().map(Supplier::get).map(BlockItem::getBlock).forEach(this::simpleBlockItem);

        basicItem(MEItems.MINIUM_SHARD.get());
        basicItem(MEItems.IRON_BAND.get());
        basicItem(MEItems.ASHEN_INGOT.get());
        basicItem(MEItems.ALCHEMICAL_ASHES.get());
        basicItem(MEItems.MATTER.get());

        handheldItem(MEItems.MATTER_SWORD.get());
        handheldItem(MEItems.MATTER_PICKAXE.get());
        handheldItem(MEItems.MATTER_AXE.get());
        handheldItem(MEItems.MATTER_HOE.get());
        handheldItem(MEItems.MATTER_SHOVEL.get());

        basicItem(MEItems.DESTRUCTION_CATALYST.get());
        basicItem(MEItems.TRANSMUTATION_STONE.get());

        fireItem(MEItems.ALCHEMICAL_FIRE);

    }

    private ItemModelBuilder fireItem(ItemLike item) {
        ResourceLocation itemKey = key(item);
        return this.getBuilder(itemKey.toString()).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", itemKey.withPrefix("block/").withSuffix("_0"));
    }

    private static @NotNull ResourceLocation key(ItemLike item) {
        return BuiltInRegistries.ITEM.getKey(item.asItem());
    }

}
