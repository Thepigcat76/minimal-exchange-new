package com.portingdeadmods.minimal_exchange.registries;

import com.portingdeadmods.minimal_exchange.MinimalExchange;
import com.portingdeadmods.minimal_exchange.content.blockentities.ExampleBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public final class EMBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, MinimalExchange.MODID);

//    public static final Supplier<BlockEntityType<ExampleBlockEntity>> EXAMPLE = BLOCK_ENTITY_TYPES.register("example", () -> BlockEntityType.Builder.of(ExampleBlockEntity::new, MEBlocks.EXAMPLE_BLOCK.get())
//            .build(null));
}
