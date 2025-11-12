package com.portingdeadmods.minimal_exchange;

import com.portingdeadmods.minimal_exchange.client.screens.AlchemicalBagScreen;
import com.portingdeadmods.minimal_exchange.content.items.DestructionCatalystItem;
import com.portingdeadmods.minimal_exchange.registries.MEMenuTypes;
import com.portingdeadmods.minimal_exchange.registries.MEItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.FastColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = MinimalExchange.MODID, dist = Dist.CLIENT)
public final class MinimalExchangeClient {
    public MinimalExchangeClient(IEventBus modEventBus, ModContainer container) {
        modEventBus.addListener(this::registerMenuScreens);
        modEventBus.addListener(this::registerColorHandlers);
        modEventBus.addListener(this::clientSetup);

        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    private void registerMenuScreens(RegisterMenuScreensEvent event) {
        event.register(MEMenuTypes.ALCHEMICAL_BAG.get(), AlchemicalBagScreen::new);
    }

    private void registerColorHandlers(RegisterColorHandlersEvent.Item event) {
        event.register((itemStack, i) -> i == 0 ? FastColor.ARGB32.color(255, itemStack.get(DataComponents.DYED_COLOR).rgb()) : -1, MEItems.ALCHEMICAL_BAG.get());
    }

    private void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemProperties.register(MEItems.DESTRUCTION_CATALYST.get(), MinimalExchange.rl("matter"),
                    (stack, level, living, id) -> DestructionCatalystItem.matterAmount(stack));
        });
    }

}
