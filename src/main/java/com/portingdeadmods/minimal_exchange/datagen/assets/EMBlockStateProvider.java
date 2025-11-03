package com.portingdeadmods.minimal_exchange.datagen.assets;

import com.portingdeadmods.minimal_exchange.MinimalExchange;
import com.portingdeadmods.minimal_exchange.registries.MEBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class EMBlockStateProvider extends BlockStateProvider {
    public EMBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MinimalExchange.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(MEBlocks.ALCHEMICAL_STONE.get());
    }
}
