package com.portingdeadmods.minimal_exchange;

import com.portingdeadmods.minimal_exchange.client.screens.ExampleScreen;
import com.portingdeadmods.minimal_exchange.registries.EMMenuTypes;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = MinimalExchange.MODID, dist = Dist.CLIENT)
public final class MinimalExchangeClient {
    public MinimalExchangeClient(IEventBus modEventBus, ModContainer container) {
        modEventBus.addListener(this::registerMenuScreens);

        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    private void registerMenuScreens(RegisterMenuScreensEvent event) {
        event.register(EMMenuTypes.EXAMPLE.get(), ExampleScreen::new);
    }
}
