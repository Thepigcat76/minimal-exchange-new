package com.portingdeadmods.minimal_exchange.api.items;

import com.portingdeadmods.minimal_exchange.capabilities.MECapabilities;
import com.portingdeadmods.minimal_exchange.capabilities.matter.MatterStorage;
import net.minecraft.world.item.ItemStack;

public interface MatterItem {
    int getMatterCapacity(ItemStack itemStack);

    static MatterStorage getMatterCap(ItemStack stack) {
        return stack.getCapability(MECapabilities.MATTER_ITEM);
    }

}
