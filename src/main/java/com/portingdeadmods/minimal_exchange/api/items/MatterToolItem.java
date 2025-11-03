package com.portingdeadmods.minimal_exchange.api.items;

import com.portingdeadmods.minimal_exchange.capabilities.MECapabilities;
import com.portingdeadmods.minimal_exchange.capabilities.matter.MatterStorage;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

public interface MatterToolItem {
    default boolean requireMatterToWork(ItemStack itemStack) {
        return requireMatterToWork(itemStack, null);
    }

    boolean requireMatterToWork(ItemStack itemStack, @Nullable Entity entity);

    default int getMatterUsage(ItemStack itemStack) {
        return getMatterUsage(itemStack, null);
    }

    int getMatterUsage(ItemStack itemStack, @Nullable Entity entity);

    default boolean canWork(ItemStack stack) {
        return canWork(stack, null);
    }

    default boolean canWork(ItemStack stack, @Nullable Entity entity) {
        MatterStorage matterStorage = stack.getCapability(MECapabilities.MATTER_ITEM);
        if (matterStorage != null && requireMatterToWork(stack, entity)) {
            return matterStorage.getMatter() >= getMatterUsage(stack, entity);
        }
        return false;
    }

    default void consumeMatter(ItemStack stack) {
        consumeMatter(stack, null);
    }

    default void consumeMatter(ItemStack stack, @Nullable Entity entity) {
        MatterStorage matterStorage = stack.getCapability(MECapabilities.MATTER_ITEM);
        if (requireMatterToWork(stack, entity) && canWork(stack, entity)) {
            matterStorage.extractMatter(getMatterUsage(stack, entity), false);
        }
    }
}
