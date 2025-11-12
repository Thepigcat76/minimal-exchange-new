package com.portingdeadmods.minimal_exchange.content.menus;

import com.portingdeadmods.minimal_exchange.registries.MEMenuTypes;
import com.portingdeadmods.portingdeadlibs.api.gui.menus.PDLAbstractContainerMenu;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class AlchemicalBagMenu extends AbstractContainerMenu {
    public static final int COLUMNS = 9;
    public static final int ROWS = 6;
    private final ItemStack itemStack;
    private final Inventory inventory;
    private final IItemHandler itemHandler;

    public AlchemicalBagMenu(int containerId, Inventory inventory, ItemStack itemStack, int slot) {
        super(MEMenuTypes.ALCHEMICAL_BAG.get(), containerId);
        this.itemStack = itemStack;
        this.inventory = inventory;
        this.itemHandler = itemStack.getCapability(Capabilities.ItemHandler.ITEM);

        int y = 18;
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                this.addSlot(new SlotItemHandler(itemHandler, column + row * 9, 8 + column * 18, y + row * 18));
            }
        }

        addPlayerInventory(inventory, 140);
        for (int i = 0; i < 9; ++i) {
            if (i != slot) {
                this.addSlot(new Slot(inventory, i, 8 + i * 18, 198));
            } else {
                this.addSlot(new NonInteractiveSlot(inventory, i, 8 + i * 18, 198));
            }
        }
    }

    public AlchemicalBagMenu(int containerId, Inventory inventory, RegistryFriendlyByteBuf byteBuf) {
        this(containerId, inventory, ItemStack.STREAM_CODEC.decode(byteBuf), byteBuf.readInt());
    }

    @Override
    public boolean stillValid(Player player) {
        return player.getMainHandItem().is(itemStack.getItem())
                || player.getOffhandItem().is(itemStack.getItem());
    }

    /**
     * Implementation taken from {@link net.minecraft.world.inventory.ChestMenu#quickMoveStack(Player, int)}
     */
    @Override
    public @NotNull ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index < ROWS * 9) {
                if (!this.moveItemStackTo(itemstack1, ROWS * 9, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, ROWS * 9, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    protected void addPlayerInventory(Inventory playerInventory) {
        addPlayerInventory(playerInventory, 83);
    }

    protected void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 141));
        }
    }

    protected void addPlayerInventory(Inventory playerInventory, int y) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, y + i * 18));
            }
        }
    }

}
