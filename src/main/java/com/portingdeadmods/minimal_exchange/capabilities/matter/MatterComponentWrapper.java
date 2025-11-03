package com.portingdeadmods.minimal_exchange.capabilities.matter;

import com.portingdeadmods.minimal_exchange.data.MEDataComponents;
import net.minecraft.world.item.ItemStack;

public record MatterComponentWrapper(ItemStack itemStack, int capacity) implements MatterStorage {

    @Override
    public int getMatter() {
        return itemStack.getOrDefault(MEDataComponents.MATTER, 0);
    }

    @Override
    public int getMatterCapacity() {
        return capacity;
    }

    @Override
    public void setMatter(int matter) {
        itemStack.set(MEDataComponents.MATTER, matter);
    }

    @Override
    public void setMatterCapacity(int matterCapacity) {
    }
}