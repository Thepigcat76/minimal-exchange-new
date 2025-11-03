package com.portingdeadmods.minimal_exchange.datagen.data;

import com.portingdeadmods.minimal_exchange.registries.MEItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;

import java.util.concurrent.CompletableFuture;

public class EMRecipeProvider extends RecipeProvider {
    public EMRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {
        super.buildRecipes(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MEItems.TRANSMUTATION_STONE.get())
                .pattern("MMM")
                .pattern("MDM")
                .pattern("MMM")
                .define('M', MEItems.MINIUM_SHARD)
                .define('D', Items.DIAMOND)
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MEItems.IRON_BAND.get())
                .pattern(" I ")
                .pattern("I I")
                .pattern(" I ")
                .define('I', Tags.Items.INGOTS_IRON)
                .unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
                .save(output);
    }
}
