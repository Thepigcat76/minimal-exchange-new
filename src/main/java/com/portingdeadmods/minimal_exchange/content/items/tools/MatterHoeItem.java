package com.portingdeadmods.minimal_exchange.content.items.tools;

import com.portingdeadmods.minimal_exchange.MEConfig;
import com.portingdeadmods.minimal_exchange.api.items.MatterItem;
import com.portingdeadmods.minimal_exchange.capabilities.MECapabilities;
import com.portingdeadmods.minimal_exchange.capabilities.matter.MatterStorage;
import com.portingdeadmods.minimal_exchange.registries.EMTranslations;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FastColor;
import net.minecraft.world.item.*;

import java.util.List;

public class MatterHoeItem extends HoeItem implements MatterItem {
    public MatterHoeItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public int getMatterCapacity(ItemStack itemStack) {
        return MEConfig.matterSwordCapacity;
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return FastColor.ARGB32.color(237, 183, 72);
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return matterForDurabilityBar(stack);
    }

    private static int matterForDurabilityBar(ItemStack itemStack) {
        MatterStorage matterStorage = itemStack.getCapability(MECapabilities.MATTER_ITEM);
        return Math.round(13.0F - ((1 - ((float) matterStorage.getMatter() / matterStorage.getMatterCapacity())) * 13.0F));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        MatterStorage matterComponent = stack.getCapability(MECapabilities.MATTER_ITEM);
        tooltipComponents.add(EMTranslations.TOOLTIP_MATTER_STORED.component(matterComponent.getMatter(), matterComponent.getMatterCapacity())
                .withColor(getBarColor(stack)));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

}
