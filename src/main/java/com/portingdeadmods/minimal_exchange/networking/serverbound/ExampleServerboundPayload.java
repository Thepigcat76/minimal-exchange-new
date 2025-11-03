package com.portingdeadmods.minimal_exchange.networking.serverbound;

import com.portingdeadmods.minimal_exchange.MinimalExchange;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record ExampleServerboundPayload(int payload) implements CustomPacketPayload {
    public static final Type<ExampleServerboundPayload> TYPE = new Type<>(MinimalExchange.rl("example_serverbound_payload"));
    public static final StreamCodec<? super RegistryFriendlyByteBuf, ExampleServerboundPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            ExampleServerboundPayload::payload,
            ExampleServerboundPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public void exampleServerboundAction(IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();
            ServerLevel level = (ServerLevel) player.level();
        }).exceptionally(err -> {
            MinimalExchange.LOGGER.error("Failed to handle " + TYPE.id(), err);
            return null;
        });
    }

}
