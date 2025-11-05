package com.portingdeadmods.minimal_exchange.content.recipes;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.portingdeadmods.portingdeadlibs.api.recipes.IngredientWithCount;
import com.portingdeadmods.portingdeadlibs.api.recipes.PDLRecipe;
import com.portingdeadmods.portingdeadlibs.utils.RecipeUtils;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record AlchemicalFireTransmutationRecipe(IngredientWithCount input, ItemStack result) implements PDLRecipe<SingleRecipeInput> {
    public static final MapCodec<AlchemicalFireTransmutationRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            IngredientWithCount.CODEC.fieldOf("input").forGetter(AlchemicalFireTransmutationRecipe::input),
            ItemStack.CODEC.fieldOf("result").forGetter(AlchemicalFireTransmutationRecipe::result)
    ).apply(inst, AlchemicalFireTransmutationRecipe::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, AlchemicalFireTransmutationRecipe> STREAM_CODEC = StreamCodec.composite(
            IngredientWithCount.STREAM_CODEC,
            AlchemicalFireTransmutationRecipe::input,
            ItemStack.STREAM_CODEC,
            AlchemicalFireTransmutationRecipe::result,
            AlchemicalFireTransmutationRecipe::new
    );
    public static final RecipeSerializer<AlchemicalFireTransmutationRecipe> SERIALIZER = RecipeUtils.newRecipeSerializer(CODEC, STREAM_CODEC);
    public static final RecipeType<AlchemicalFireTransmutationRecipe> TYPE = RecipeUtils.newRecipeType("alchemical_fire_transmutation");

    @Override
    public boolean matches(SingleRecipeInput singleRecipeInput, Level level) {
        return RecipeUtils.compareItems(List.of(singleRecipeInput.item()), List.of(this.input()));
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.Provider provider) {
        return this.result();
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return TYPE;
    }
}
