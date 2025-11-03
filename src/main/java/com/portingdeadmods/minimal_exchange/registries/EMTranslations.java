package com.portingdeadmods.minimal_exchange.registries;

import com.portingdeadmods.minimal_exchange.MinimalExchange;
import com.portingdeadmods.portingdeadlibs.api.translations.*;

public final class EMTranslations {
    public static final DeferredTranslationRegister TRANSLATIONS = DeferredTranslationRegister.createTranslations(MinimalExchange.MODID);

    // -- Messages --
    private static final DefaultTranslationCategory MESSAGES_CATEGORY = TRANSLATIONS.createCategory("messages");

    public static final DeferredTranslation<TranslatableConstant> NICE_MESSAGE = MESSAGES_CATEGORY.registerWithDefault("nice_message", "Hello, silly :3");

    // -- Creative Tabs --
    private static final DefaultTranslationCategory CREATIVE_TAB_CATEGORY = TRANSLATIONS.createCategory("creative_tabs");

    public static final DeferredTranslation<TranslatableConstant> MAIN_TAB = CREATIVE_TAB_CATEGORY.registerWithDefault("main", MinimalExchange.MODNAME);

    // -- Screens --
    private static final DefaultTranslationCategory SCREEN_CATEGORY = TRANSLATIONS.createCategory("screens");

    public static final DeferredTranslation<TranslatableConstant> EXAMPLE_SCREEN_TITLE = CREATIVE_TAB_CATEGORY.registerWithDefault("title.example", "Example");

    // -- Tooltips --
    private static final DefaultTranslationCategory TOOLTIP_CATEGORY = TRANSLATIONS.createCategory("tooltip");

    public static final DeferredTranslation<TranslatableConstant> TOOLTIP_MATTER_STORED = CREATIVE_TAB_CATEGORY.registerWithDefault("matter_stored", "Matter Stored: %d/%d");
}
