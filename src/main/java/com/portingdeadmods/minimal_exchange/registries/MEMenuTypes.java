package com.portingdeadmods.minimal_exchange.registries;

import com.portingdeadmods.minimal_exchange.MinimalExchange;
import com.portingdeadmods.minimal_exchange.content.menus.AlchemicalBagMenu;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class MEMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(BuiltInRegistries.MENU, MinimalExchange.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<AlchemicalBagMenu>> ALCHEMICAL_BAG = MENU_TYPES.register("alchemical_bag", () -> IMenuTypeExtension.create(AlchemicalBagMenu::new));
}
