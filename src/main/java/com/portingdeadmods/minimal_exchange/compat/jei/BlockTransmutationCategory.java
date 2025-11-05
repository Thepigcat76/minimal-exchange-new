package com.portingdeadmods.minimal_exchange.compat.jei;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.portingdeadmods.minimal_exchange.MinimalExchange;
import com.portingdeadmods.minimal_exchange.registries.MEItems;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.RenderTypeHelper;
import net.neoforged.neoforge.client.model.data.ModelData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlockTransmutationCategory implements IRecipeCategory<BlockTransmutationRecipe> {
    static final ResourceLocation BURN_PROGRESS_SPRITE = ResourceLocation.fromNamespaceAndPath(MinimalExchange.MODID, "container/furnace/empty_arrow");
    public static final RecipeType<BlockTransmutationRecipe> BLOCK_TRANSMUTATION_TYPE =
            new RecipeType<>(ResourceLocation.fromNamespaceAndPath(MinimalExchange.MODID, "block_transmutations"), BlockTransmutationRecipe.class);

    private final IDrawable icon;

    public BlockTransmutationCategory(IGuiHelper guiHelper) {
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(MEItems.TRANSMUTATION_STONE.get()));
    }

    @Override
    public @NotNull RecipeType<BlockTransmutationRecipe> getRecipeType() {
        return BLOCK_TRANSMUTATION_TYPE;
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.literal("Block Transmutations");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public int getWidth() {
        return 80;
    }

    @Override
    public int getHeight() {
        return 32;
    }

    @Override
    public void createRecipeExtras(IRecipeExtrasBuilder builder, BlockTransmutationRecipe recipe, IFocusGroup focuses) {
        builder.addRecipeArrow().setPosition(this.getWidth() / 2 - 12, 12);
        //builder.addText(Component.literal(recipe.result().matterCost() + " Matter").withStyle(ChatFormatting.GRAY), this.getWidth(), Minecraft.getInstance().font.lineHeight).setPosition(0, 0);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, BlockTransmutationRecipe recipe, IFocusGroup focuses) {
        Block input = recipe.input();
        if (BlockItem.BY_BLOCK.get(input) != null) {
            builder.addSlot(RecipeIngredientRole.INPUT, 0, 12)
                    .addIngredients(Ingredient.of(input));
        }
        Block result = recipe.result().result();
        if (BlockItem.BY_BLOCK.get(result) != null) {
            builder.addSlot(RecipeIngredientRole.OUTPUT, 64, 12)
                    .addIngredients(Ingredient.of(result));
        }
    }

    @Override
    public void draw(BlockTransmutationRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        Block input = recipe.input();
        // TODO: Cache block state
        if (BlockItem.BY_BLOCK.get(input) == null) {
            renderBlock(guiGraphics, input.defaultBlockState(), 0, 12);
        }

        Block result = recipe.result().result();
        if (BlockItem.BY_BLOCK.get(result) == null) {
            renderBlock(guiGraphics, result.defaultBlockState(), 64, 12);
        }

        PoseStack poseStack = guiGraphics.pose();

        poseStack.pushPose();
        {
            poseStack.translate(0, 0, 100);
            guiGraphics.drawString(Minecraft.getInstance().font, Component.literal(recipe.result().matterCost() + " Matter").withStyle(ChatFormatting.DARK_GRAY), 0, 0, -1, false);
        }
        poseStack.popPose();

    }

    private static void renderBlock(GuiGraphics guiGraphics, BlockState state, int x, int y) {
        MultiBufferSource.BufferSource bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();

        PoseStack poseStack = guiGraphics.pose();

        poseStack.pushPose();
        {
            poseStack.translate(x, y, 0);
            poseStack.scale(16, 16, 16);
            poseStack.translate(0.5, 0.5, 0.5);
            poseStack.mulPose(Axis.ZP.rotationDegrees(180));
            poseStack.translate(-0.5, -0.5, -0.5);
            Minecraft.getInstance().getBlockRenderer().renderSingleBlock(state, poseStack, bufferSource, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY, ModelData.EMPTY, RenderType.TRANSLUCENT);
        }
        poseStack.popPose();
    }
}