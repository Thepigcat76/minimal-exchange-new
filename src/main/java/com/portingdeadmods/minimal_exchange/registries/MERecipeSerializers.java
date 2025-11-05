package com.portingdeadmods.minimal_exchange.registries;

import com.portingdeadmods.minimal_exchange.MinimalExchange;
import com.portingdeadmods.minimal_exchange.content.recipes.AlchemicalFireTransmutationRecipe;
import com.portingdeadmods.minimal_exchange.content.recipes.ItemTransmutationRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public final class MERecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, MinimalExchange.MODID);

    public static final Supplier<SimpleCraftingRecipeSerializer<ItemTransmutationRecipe>> ITEM_TRANSMUTATION = RECIPE_SERIALIZERS.register("item_transmutation",
            () -> new SimpleCraftingRecipeSerializer<>(ItemTransmutationRecipe::new));
    public static final Supplier<RecipeSerializer<AlchemicalFireTransmutationRecipe>> ALCHEMICAL_FIRE_TRANSMUTATION = RECIPE_SERIALIZERS.register("alchemical_fire_transmutation",
            () -> AlchemicalFireTransmutationRecipe.SERIALIZER);
}
