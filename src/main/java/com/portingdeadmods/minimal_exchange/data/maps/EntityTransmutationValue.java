package com.portingdeadmods.minimal_exchange.data.maps;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

public record EntityTransmutationValue(EntityType<?> result, int matterCost) {
    public static final Codec<EntityTransmutationValue> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            BuiltInRegistries.ENTITY_TYPE.byNameCodec().fieldOf("result").forGetter(EntityTransmutationValue::result),
            Codec.INT.optionalFieldOf("matterCost", 1).forGetter(EntityTransmutationValue::matterCost)
    ).apply(builder, EntityTransmutationValue::new));
}