package com.portingdeadmods.minimal_exchange.registries;

import com.portingdeadmods.minimal_exchange.MinimalExchange;
import com.portingdeadmods.minimal_exchange.content.items.DestructionCatalystItem;
import com.portingdeadmods.minimal_exchange.content.items.TransmutationStoneItem;
import com.portingdeadmods.minimal_exchange.content.items.tools.*;
import com.portingdeadmods.minimal_exchange.data.MEDataComponents;
import com.portingdeadmods.portingdeadlibs.api.utils.PDLDeferredRegisterItems;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredItem;

public final class MEItems {
    public static final PDLDeferredRegisterItems ITEMS = PDLDeferredRegisterItems.createItemsRegister(MinimalExchange.MODID);

    public static final DeferredItem<Item> MINIUM_SHARD = ITEMS.registerSimpleItem("minium_shard");
    public static final DeferredItem<Item> IRON_BAND = ITEMS.registerSimpleItem("iron_band");
    public static final DeferredItem<Item> ALCHEMICAL_ASHES = ITEMS.registerSimpleItem("alchemical_ash");
    public static final DeferredItem<Item> MATTER = ITEMS.registerSimpleItem("matter");

    public static final DeferredItem<MatterSwordItem> MATTER_SWORD = ITEMS.register("matter_sword", () -> new MatterSwordItem(METoolTiers.MATTER, new Item.Properties()
            .stacksTo(1)));
    public static final DeferredItem<MatterPickaxeItem> MATTER_PICKAXE = ITEMS.register("matter_pickaxe", () -> new MatterPickaxeItem(METoolTiers.MATTER, new Item.Properties()
            .stacksTo(1)));
    public static final DeferredItem<MatterAxeItem> MATTER_AXE = ITEMS.register("matter_axe", () -> new MatterAxeItem(METoolTiers.MATTER, new Item.Properties()
            .stacksTo(1)));
    public static final DeferredItem<MatterShovelItem> MATTER_SHOVEL = ITEMS.register("matter_shovel", () -> new MatterShovelItem(METoolTiers.MATTER, new Item.Properties()
            .stacksTo(1)));
    public static final DeferredItem<MatterHoeItem> MATTER_HOE = ITEMS.register("matter_hoe", () -> new MatterHoeItem(METoolTiers.MATTER, new Item.Properties()
            .stacksTo(1)));

    public static final DeferredItem<DestructionCatalystItem> DESTRUCTION_CATALYST = ITEMS.register("destruction_catalyst",
            () -> new DestructionCatalystItem(new Item.Properties().component(MEDataComponents.MATTER.get(), 0).stacksTo(1)));
    public static final DeferredItem<TransmutationStoneItem> TRANSMUTATION_STONE = ITEMS.register("transmutation_stone",
            () -> new TransmutationStoneItem(new Item.Properties().component(MEDataComponents.MATTER.get(), 0).stacksTo(1)));

}
