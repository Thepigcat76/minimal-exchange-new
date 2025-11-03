package com.portingdeadmods.minimal_exchange.content.items.tools;

import com.portingdeadmods.minimal_exchange.MEConfig;
import com.portingdeadmods.minimal_exchange.api.items.MatterItem;
import com.portingdeadmods.minimal_exchange.api.items.MatterToolItem;
import com.portingdeadmods.minimal_exchange.capabilities.MECapabilities;
import com.portingdeadmods.minimal_exchange.capabilities.matter.MatterStorage;
import com.portingdeadmods.minimal_exchange.registries.EMTranslations;
import com.portingdeadmods.minimal_exchange.registries.METoolTiers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MatterAxeItem extends AxeItem implements MatterItem, MatterToolItem {
    public static final ItemAttributeModifiers ATTRIBUTE_MODIFIERS = PickaxeItem.createAttributes(METoolTiers.MATTER, 5, -3);

    public MatterAxeItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public int getMatterCapacity(ItemStack itemStack) {
        return MEConfig.matterAxeCapacity;
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

    @Override
    public boolean mineBlock(ItemStack stack, Level p_41417_, BlockState p_41418_, BlockPos p_41419_, LivingEntity entity) {
        Player player = entity instanceof Player player0 ? player0 : null;
        if (canWork(stack, player)) {
            MatterStorage energyStorage = MatterItem.getMatterCap(stack);
            int energyUsage = getMatterUsage(stack, player);
            energyStorage.extractMatter(energyUsage, false);
        }
        return true;
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity p_40995_, LivingEntity entity) {
        Player player = entity instanceof Player player0 ? player0 : null;
        if (canWork(stack, player)) {
            MatterStorage energyStorage = MatterItem.getMatterCap(stack);
            int energyUsage = (int) (getMatterUsage(stack, player) * 1.5f);
            energyStorage.extractMatter(energyUsage, false);
        }
        return true;
    }

    @Override
    public @NotNull ItemAttributeModifiers getDefaultAttributeModifiers(ItemStack stack) {
        MatterStorage matterStorage = stack.getCapability(MECapabilities.MATTER_ITEM);
        if (matterStorage != null && matterStorage.getMatter() > 0) {
            return ATTRIBUTE_MODIFIERS;
        }
        return ItemAttributeModifiers.EMPTY;
    }

    @Override
    public boolean requireMatterToWork(ItemStack itemStack, @Nullable Entity entity) {
        return true;
    }

    @Override
    public int getMatterUsage(ItemStack itemStack, @Nullable Entity entity) {
        return MEConfig.matterAxeUsage;
    }

}
