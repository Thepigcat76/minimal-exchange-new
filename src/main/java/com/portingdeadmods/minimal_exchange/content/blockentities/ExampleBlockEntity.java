package com.portingdeadmods.minimal_exchange.content.blockentities;

import com.portingdeadmods.minimal_exchange.content.menus.ExampleMenu;
import com.portingdeadmods.minimal_exchange.registries.EMBlockEntityTypes;
import com.portingdeadmods.minimal_exchange.registries.EMTranslations;
import com.portingdeadmods.portingdeadlibs.api.blockentities.ContainerBlockEntity;
import com.portingdeadmods.portingdeadlibs.utils.capabilities.HandlerUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class ExampleBlockEntity extends ContainerBlockEntity implements MenuProvider {
    public ExampleBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(null/*EMBlockEntityTypes.EXAMPLE.get()*/, blockPos, blockState);
        addItemHandler(HandlerUtils::newItemStackHandler, builder -> builder
                .slots(9)
                .onChange(slot -> this.updateData()));
    }

    @Override
    public Component getDisplayName() {
        return EMTranslations.EXAMPLE_SCREEN_TITLE.component();
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new ExampleMenu(i, inventory, this);
    }
}
