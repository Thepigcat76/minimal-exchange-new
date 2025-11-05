package com.portingdeadmods.minimal_exchange.datagen.data;

import com.portingdeadmods.minimal_exchange.MinimalExchange;
import com.portingdeadmods.minimal_exchange.content.recipes.AlchemicalFireTransmutationRecipe;
import com.portingdeadmods.minimal_exchange.content.recipes.ItemTransmutationRecipe;
import com.portingdeadmods.minimal_exchange.registries.MEBlocks;
import com.portingdeadmods.minimal_exchange.registries.MEItems;
import com.portingdeadmods.portingdeadlibs.api.recipes.IngredientWithCount;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;

import java.util.concurrent.CompletableFuture;

public class MERecipeProvider extends RecipeProvider {
    public MERecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
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

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MEItems.MATTER_SWORD.get())
                .pattern("M")
                .pattern("M")
                .pattern("A")
                .define('M', MEItems.MATTER.get())
                .define('A', MEItems.ASHEN_INGOT.get())
                .unlockedBy("has_matter", has(MEItems.MATTER.get()))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MEItems.MATTER_PICKAXE.get())
                .pattern("MMM")
                .pattern(" A ")
                .pattern(" A ")
                .define('M', MEItems.MATTER.get())
                .define('A', MEItems.ASHEN_INGOT.get())
                .unlockedBy("has_matter", has(MEItems.MATTER.get()))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MEItems.MATTER_AXE.get())
                .pattern("MM")
                .pattern("MA")
                .pattern(" A")
                .define('M', MEItems.MATTER.get())
                .define('A', MEItems.ASHEN_INGOT.get())
                .unlockedBy("has_matter", has(MEItems.MATTER.get()))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MEItems.MATTER_SHOVEL.get())
                .pattern("M")
                .pattern("A")
                .pattern("A")
                .define('M', MEItems.MATTER.get())
                .define('A', MEItems.ASHEN_INGOT.get())
                .unlockedBy("has_matter", has(MEItems.MATTER.get()))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MEItems.MATTER_HOE.get())
                .pattern("MM")
                .pattern(" A")
                .pattern(" A")
                .define('M', MEItems.MATTER.get())
                .define('A', MEItems.ASHEN_INGOT.get())
                .unlockedBy("has_matter", has(MEItems.MATTER.get()))
                .save(output);

        SpecialRecipeBuilder.special(ItemTransmutationRecipe::new)
                .save(output, MinimalExchange.rl("item_transmutation"));

        PDLRecipeBuilder.of(new AlchemicalFireTransmutationRecipe(IngredientWithCount.of(Items.REDSTONE), MEItems.ALCHEMICAL_ASHES.toStack()))
                .save(output);
        PDLRecipeBuilder.of(new AlchemicalFireTransmutationRecipe(IngredientWithCount.of(Tags.Items.STONES), MEBlocks.ALCHEMICAL_STONE.toStack()))
                .save(output);

    }
}
