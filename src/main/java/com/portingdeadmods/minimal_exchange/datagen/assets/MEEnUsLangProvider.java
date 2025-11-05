package com.portingdeadmods.minimal_exchange.datagen.assets;

import com.portingdeadmods.minimal_exchange.MEConfig;
import com.portingdeadmods.minimal_exchange.MinimalExchange;
import com.portingdeadmods.minimal_exchange.registries.MEBlocks;
import com.portingdeadmods.minimal_exchange.registries.MEItems;
import com.portingdeadmods.minimal_exchange.registries.METranslations;
import com.portingdeadmods.portingdeadlibs.api.config.PDLConfigHelper;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class MEEnUsLangProvider extends LanguageProvider {
    public MEEnUsLangProvider(PackOutput output) {
        super(output, MinimalExchange.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        METranslations.TRANSLATIONS.getDefaultTranslations().forEach(this::add);
        PDLConfigHelper.generateConfigNames(MEConfig.class, MinimalExchange.MODID, this::add);

        addItem(MEItems.MINIUM_SHARD, "Minium Shard");
        addItem(MEItems.IRON_BAND, "Iron Band");
        addItem(MEItems.ASHEN_INGOT, "Ashen Ingot");
        addItem(MEItems.ALCHEMICAL_ASHES, "Alchemical Ashes");

        addItem(MEItems.MATTER_SWORD, "Matter Sword");
        addItem(MEItems.MATTER_PICKAXE, "Matter Pickaxe");
        addItem(MEItems.MATTER_AXE, "Matter Axe");
        addItem(MEItems.MATTER_HOE, "Matter Hoe");
        addItem(MEItems.MATTER_SHOVEL, "Matter Shovel");

        addItem(MEItems.DESTRUCTION_CATALYST, "Destruction Catalyst");
        addItem(MEItems.TRANSMUTATION_STONE, "Transmutation Stone");

        addBlock(MEBlocks.ALCHEMICAL_STONE, "Alchemical Stone");
        addBlock(MEBlocks.ALCHEMICAL_FIRE, "Alchemical Fire");
    }

}
