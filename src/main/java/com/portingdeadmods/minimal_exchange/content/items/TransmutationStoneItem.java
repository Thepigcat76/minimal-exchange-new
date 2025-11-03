package com.portingdeadmods.minimal_exchange.content.items;

import com.portingdeadmods.minimal_exchange.MEConfig;
import com.portingdeadmods.minimal_exchange.api.items.SimpleMatterItem;
import com.portingdeadmods.minimal_exchange.capabilities.MECapabilities;
import com.portingdeadmods.minimal_exchange.capabilities.matter.MatterStorage;
import com.portingdeadmods.minimal_exchange.data.MEDataMaps;
import com.portingdeadmods.minimal_exchange.data.maps.BlockTransmutationValue;
import com.portingdeadmods.minimal_exchange.data.maps.EntityTransmutationValue;
import com.portingdeadmods.minimal_exchange.registries.MESoundEvents;
import com.portingdeadmods.portingdeadlibs.utils.RegistryUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class TransmutationStoneItem extends SimpleMatterItem {
    public TransmutationStoneItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos clickedPos = context.getClickedPos();
        BlockState blockState = level.getBlockState(clickedPos);
        ItemStack stack = context.getItemInHand();

        Holder<Block> blockHolder = blockState.getBlockHolder();
        BlockTransmutationValue transmutation = blockHolder.getData(MEDataMaps.BLOCK_TRANSMUTATIONS);
        if (transmutation != null) {
            int matterCost = transmutation.matterCost();
            MatterStorage matterStorage = stack.getCapability(MECapabilities.MATTER_ITEM);
            if (matterStorage.getMatter() >= matterCost) {
                level.setBlockAndUpdate(context.getClickedPos(), transmutation.result().defaultBlockState());
                level.playSound(null, clickedPos, MESoundEvents.TRANSMUTE.get(), SoundSource.PLAYERS, 0.8f, 1);
                if (!context.getPlayer().hasInfiniteMaterials()) {
                    matterStorage.extractMatter(matterCost, false);
                }
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.FAIL;
    }

    @Override
    public @NotNull InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        Level level = player.level();
        EntityTransmutationValue transmutation = RegistryUtils.holder(BuiltInRegistries.ENTITY_TYPE, target.getType()).getData(MEDataMaps.ENTITY_TRANSMUTATIONS);
        if (transmutation != null) {
            int matterCost = transmutation.matterCost();
            MatterStorage matterStorage = stack.getCapability(MECapabilities.MATTER_ITEM);
            if (target instanceof Mob targetMob && matterStorage.getMatter() >= matterCost) {
                EntityType<?> transmutatedEntityType = transmutation.result();
                Entity transmutatedEntity = transmutatedEntityType.create(level);
                if (transmutatedEntity instanceof Mob) {
                    if (!level.isClientSide()) {
                        targetMob.convertTo((EntityType<? extends Mob>) transmutatedEntityType, true);
                        target.level().playSound(null, target.blockPosition(), MESoundEvents.TRANSMUTE.get(), SoundSource.PLAYERS, 0.8f, 1);
                        if (!player.hasInfiniteMaterials()) {
                            matterStorage.extractMatter(matterCost, false);
                        }
                    }
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return InteractionResult.FAIL;
    }

    @Override
    public int getMatterCapacity(ItemStack itemStack) {
        return MEConfig.transmutationStoneCapacity;
    }
}