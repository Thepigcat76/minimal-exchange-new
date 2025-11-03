package com.portingdeadmods.minimal_exchange.registries;

import com.portingdeadmods.minimal_exchange.MinimalExchange;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public final class MECreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB, MinimalExchange.MODID);

    public static final Supplier<CreativeModeTab> MAIN = TABS.register("main", () -> CreativeModeTab.builder()
            .icon(MEItems.DESTRUCTION_CATALYST::toStack)
            .title(Component.translatable("creative_tab.minimal_exchange.main"))
            .displayItems((params, out) -> {
                MEItems.ITEMS.getCreativeTabItems().stream().map(Supplier::get).forEach(out::accept);
            })
            .build());
}
