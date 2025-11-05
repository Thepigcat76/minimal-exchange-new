package com.portingdeadmods.minimal_exchange.registries;

import com.portingdeadmods.minimal_exchange.MinimalExchange;
import com.portingdeadmods.minimal_exchange.content.blockentities.CrucibleBlockEntity;
import com.portingdeadmods.minimal_exchange.content.blockentities.CrucibleExtensionBlockEntity;
import com.portingdeadmods.minimal_exchange.content.blocks.CrucibleExtensionBlock;
import com.portingdeadmods.portingdeadlibs.api.utils.PDLDeferredRegisterBlockEntities;
import io.netty.util.Attribute;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class MEBlockEntityTypes {
    public static final PDLDeferredRegisterBlockEntities BLOCK_ENTITY_TYPES = PDLDeferredRegisterBlockEntities.createBlockEntities(MinimalExchange.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CrucibleBlockEntity>> CRUCIBLE = BLOCK_ENTITY_TYPES.register("crucible", CrucibleBlockEntity::new, MEBlocks.CRUCIBLE);
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CrucibleExtensionBlockEntity>> CRUCIBLE_EXTENSION = BLOCK_ENTITY_TYPES.register("crucible_extension", CrucibleExtensionBlockEntity::new, MEBlocks.CRUCIBLE_EXTENSION);

}
