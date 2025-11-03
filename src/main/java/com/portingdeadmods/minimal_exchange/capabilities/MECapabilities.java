package com.portingdeadmods.minimal_exchange.capabilities;

import com.portingdeadmods.minimal_exchange.MinimalExchange;
import com.portingdeadmods.minimal_exchange.capabilities.matter.MatterStorage;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.capabilities.ItemCapability;
import org.jetbrains.annotations.Nullable;

public final class MECapabilities {
    private static final ResourceLocation MATTER_STORAGE = ResourceLocation.fromNamespaceAndPath(MinimalExchange.MODID, "matter_storage");

    public static final BlockCapability<MatterStorage, @Nullable Direction> MATTER_BLOCK = BlockCapability.createSided(MATTER_STORAGE, MatterStorage.class);
    public static final ItemCapability<MatterStorage, Void> MATTER_ITEM = ItemCapability.createVoid(MATTER_STORAGE, MatterStorage.class);
}