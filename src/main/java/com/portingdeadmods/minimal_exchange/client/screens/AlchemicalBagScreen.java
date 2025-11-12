package com.portingdeadmods.minimal_exchange.client.screens;

import com.portingdeadmods.minimal_exchange.MinimalExchange;
import com.portingdeadmods.minimal_exchange.content.menus.AlchemicalBagMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class AlchemicalBagScreen extends AbstractContainerScreen<AlchemicalBagMenu> {
    public static final ResourceLocation TEXTURE = MinimalExchange.rl("textures/gui/alchemical_bag.png");

    public AlchemicalBagScreen(AlchemicalBagMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageHeight = 114 + 6 * 18;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    protected void init() {
        super.init();
        this.topPos = (this.height - this.imageHeight) / 2;
    }

    public @NotNull ResourceLocation getBackgroundTexture() {
        return TEXTURE;
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pGuiGraphics, pMouseX, pMouseX, pPartialTick);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float delta, int mouseX, int mouseY) {
        guiGraphics.blit(getBackgroundTexture(), leftPos, topPos, 0, 0, imageWidth, imageHeight);
    }

}