package com.portingdeadmods.minimal_exchange.data;

import com.mojang.serialization.Codec;
import com.portingdeadmods.minimal_exchange.MinimalExchange;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public final class MEDataComponents {
    public static final DeferredRegister.DataComponents DATA_COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, MinimalExchange.MODID);

    public static final Supplier<DataComponentType<Integer>> MATTER = DATA_COMPONENTS.registerComponentType("matter",
            builder -> builder
                    .persistent(Codec.INT)
                    .networkSynchronized(ByteBufCodecs.INT));

}