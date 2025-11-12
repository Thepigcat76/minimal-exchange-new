package com.portingdeadmods.minimal_exchange.registries;

import com.portingdeadmods.minimal_exchange.MinimalExchange;
import com.portingdeadmods.minimal_exchange.api.items.MatterItem;
import com.portingdeadmods.minimal_exchange.data.MEDataComponents;
import net.minecraft.core.Vec3i;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FastColor;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public final class MECreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB, MinimalExchange.MODID);

    public static final Supplier<CreativeModeTab> MAIN = TABS.register("main", () -> CreativeModeTab.builder()
            .icon(MEItems.DESTRUCTION_CATALYST::toStack)
            .title(Component.translatable("creative_tab.minimal_exchange.main"))
            .displayItems((params, out) -> {
                for (Supplier<? extends Item> supplier : MEItems.ITEMS.getCreativeTabItems()) {
                    Item item = supplier.get();
                    ItemStack stack = new ItemStack(item);
                    if (item instanceof MatterItem matterItem) {
                        stack.set(MEDataComponents.MATTER.get(), matterItem.getMatterCapacity(stack));
                    }
                    out.accept(stack);
                }

                for (DyeColor color : DyeColor.values()) {
                    ItemStack itemStack = new ItemStack(MEItems.ALCHEMICAL_BAG.get());
                    int textureDiffuseColor = color.getTextureDiffuseColor();
                    Vec3i rgb = new Vec3i((int) Math.min(FastColor.ARGB32.red(textureDiffuseColor) * 1.4, 255),
                            (int) Math.min(FastColor.ARGB32.blue(textureDiffuseColor) * 1.4, 255),
                            (int) Math.min(FastColor.ARGB32.green(textureDiffuseColor) * 1.4, 255));
                    itemStack.set(DataComponents.DYED_COLOR, new DyedItemColor(FastColor.ARGB32.color(rgb.getX(), rgb.getY(), rgb.getZ()), false));
                    out.accept(itemStack);
                }
            })
            .build());
}
