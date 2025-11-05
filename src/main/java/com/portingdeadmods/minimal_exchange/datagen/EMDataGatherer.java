package com.portingdeadmods.minimal_exchange.datagen;

import com.portingdeadmods.minimal_exchange.MinimalExchange;
import com.portingdeadmods.minimal_exchange.datagen.assets.MEBlockStateProvider;
import com.portingdeadmods.minimal_exchange.datagen.assets.MEEnUsLangProvider;
import com.portingdeadmods.minimal_exchange.datagen.assets.MEItemModelProvider;
import com.portingdeadmods.minimal_exchange.datagen.data.MEBlockLootTableProvider;
import com.portingdeadmods.minimal_exchange.datagen.data.MERecipeProvider;
import com.portingdeadmods.minimal_exchange.datagen.data.METagsProvider;
import com.portingdeadmods.minimal_exchange.datagen.data.MEDataMapProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = MinimalExchange.MODID)
public final class EMDataGatherer {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeClient(), new MEBlockStateProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new MEItemModelProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new MEEnUsLangProvider(packOutput));

        METagsProvider.createTagProviders(generator, packOutput, lookupProvider, existingFileHelper, event.includeServer());
        generator.addProvider(event.includeServer(), new MERecipeProvider(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(), List.of(
                new LootTableProvider.SubProviderEntry(MEBlockLootTableProvider::new, LootContextParamSets.BLOCK)
        ), lookupProvider));

        generator.addProvider(event.includeServer(), new MEDataMapProvider(packOutput, lookupProvider));
    }
}
