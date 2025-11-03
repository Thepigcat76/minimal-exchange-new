package com.portingdeadmods.minimal_exchange.networking;

import com.portingdeadmods.minimal_exchange.MinimalExchange;
import com.portingdeadmods.minimal_exchange.networking.bidirectional.ExampleBidirectionalPayload;
import com.portingdeadmods.minimal_exchange.networking.clientbound.ExampleClientboundPayload;
import com.portingdeadmods.minimal_exchange.networking.serverbound.ExampleServerboundPayload;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = MinimalExchange.MODID)
public class NetworkEvents {
    @SubscribeEvent
    public static void registerPayloads(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(MinimalExchange.MODID);

        registrar.playToServer(
                ExampleServerboundPayload.TYPE,
                ExampleServerboundPayload.STREAM_CODEC,
                ExampleServerboundPayload::exampleServerboundAction
        );

        registrar.playToClient(
                ExampleClientboundPayload.TYPE,
                ExampleClientboundPayload.STREAM_CODEC,
                ExampleClientboundPayload::exampleClientboundAction
        );

        registrar.playBidirectional(
                ExampleBidirectionalPayload.TYPE,
                ExampleBidirectionalPayload.STREAM_CODEC,
                ExampleBidirectionalPayload::exampleBidirectionalAction
        );
    }
}