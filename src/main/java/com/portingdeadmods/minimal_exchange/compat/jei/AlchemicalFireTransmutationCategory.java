package com.portingdeadmods.minimal_exchange.compat.jei;

import com.portingdeadmods.minimal_exchange.MinimalExchange;
import com.portingdeadmods.minimal_exchange.content.recipes.AlchemicalFireTransmutationRecipe;
import com.portingdeadmods.minimal_exchange.registries.MEItems;
import com.portingdeadmods.minimal_exchange.registries.METranslations;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.AbstractRecipeCategory;
import net.minecraft.network.chat.Component;

public class AlchemicalFireTransmutationCategory extends AbstractRecipeCategory<AlchemicalFireTransmutationRecipe> {
    public static final RecipeType<AlchemicalFireTransmutationRecipe> TYPE = RecipeType.create(MinimalExchange.MODID, "alchemical_fire_transmutation", AlchemicalFireTransmutationRecipe.class);

    public AlchemicalFireTransmutationCategory(IGuiHelper guiHelper) {
        super(
                TYPE,
                METranslations.ALCHEMICAL_FIRE_TRANSMUTATION_CATEGORY.component(),
                guiHelper.createDrawableItemLike(MEItems.ALCHEMICAL_FIRE),
                125,
                38
        );
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, AlchemicalFireTransmutationRecipe recipe, IFocusGroup focuses) {
        builder.addInputSlot(0, 0).addIngredients(recipe.input().toIngredientSaveCount());

        builder.addOutputSlot(64, 0).addItemStack(recipe.result());
    }
}
