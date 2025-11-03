package com.portingdeadmods.minimal_exchange;

import com.portingdeadmods.minimal_exchange.api.items.MatterItem;
import com.portingdeadmods.minimal_exchange.capabilities.MECapabilities;
import com.portingdeadmods.minimal_exchange.capabilities.matter.MatterComponentWrapper;
import com.portingdeadmods.minimal_exchange.data.MEDataComponents;
import com.portingdeadmods.minimal_exchange.data.MEDataMaps;
import com.portingdeadmods.minimal_exchange.registries.*;
import com.portingdeadmods.portingdeadlibs.api.config.PDLConfigHelper;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;

@Mod(MinimalExchange.MODID)
public final class MinimalExchange {
    public static final String MODID = "minimal_exchange";
    public static final String MODNAME = "Minimal Exchange";
    public static final Logger LOGGER = LogUtils.getLogger();

    public MinimalExchange(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::registerPayloads);
        modEventBus.addListener(this::registerCapabilities);
        modEventBus.addListener(this::registerDataMaps);

        MECreativeTabs.TABS.register(modEventBus);
        MEDataComponents.DATA_COMPONENTS.register(modEventBus);
        MEItems.ITEMS.register(modEventBus);
        MESoundEvents.SOUND_EVENTS.register(modEventBus);
        MEBlocks.BLOCKS.register(modEventBus);
        EMTranslations.TRANSLATIONS.register(modEventBus);
        EMBlockEntityTypes.BLOCK_ENTITY_TYPES.register(modEventBus);
        EMMenuTypes.MENU_TYPES.register(modEventBus);

        PDLConfigHelper.registerConfig(MEConfig.class, ModConfig.Type.COMMON).register(modContainer);
    }

    private void registerPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(MODID);
    }

    private void registerDataMaps(RegisterDataMapTypesEvent event) {
        event.register(MEDataMaps.BLOCK_TRANSMUTATIONS);
        event.register(MEDataMaps.ITEM_TRANSMUTATIONS);
        event.register(MEDataMaps.ENTITY_TRANSMUTATIONS);
    }

    private void registerCapabilities(RegisterCapabilitiesEvent event) {
        //event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, EMBlockEntityTypes.EXAMPLE.get(), ContainerBlockEntity::getItemHandlerOnSide);
        event.registerItem(
                MECapabilities.MATTER_ITEM,
                (item, ctx) -> new MatterComponentWrapper(item, ((MatterItem) item.getItem()).getMatterCapacity(item)),
                MEItems.DESTRUCTION_CATALYST.get(),
                MEItems.TRANSMUTATION_STONE.get(),
                MEItems.MATTER_SWORD.get(),
                MEItems.MATTER_PICKAXE.get(),
                MEItems.MATTER_AXE.get(),
                MEItems.MATTER_SHOVEL.get(),
                MEItems.MATTER_HOE.get()
        );
    }

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}
