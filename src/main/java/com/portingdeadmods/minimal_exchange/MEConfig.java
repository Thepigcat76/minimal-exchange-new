package com.portingdeadmods.minimal_exchange;

import com.portingdeadmods.portingdeadlibs.api.config.ConfigValue;

public final class MEConfig {
    @ConfigValue(category = "matter", name = "Transmutation Stone Matter Capacity", comment = "Matter Capacity of the Transmutation Stone")
    public static int transmutationStoneCapacity = 3000;

    @ConfigValue(category = "matter", name = "Destruction Catalyst Matter Capacity", comment = "Matter Capacity of the Destruction Catalyst")
    public static int destructionCatalystCapacity = 3000;

    @ConfigValue(category = "matter", name = "Matter Sword Matter Capacity", comment = "Matter Capacity of the Matter Sword")
    public static int matterSwordCapacity = 4000;

    @ConfigValue(category = "matter", name = "Matter Pickaxe Matter Capacity", comment = "Matter Capacity of the Matter Pickaxe")
    public static int matterPickaxeCapacity = 4000;

    @ConfigValue(category = "matter", name = "Matter Axe Matter Capacity", comment = "Matter Capacity of the Matter Axe")
    public static int matterAxeCapacity = 4000;

    @ConfigValue(category = "matter", name = "Matter Shovel Matter Capacity", comment = "Matter Capacity of the Matter Shovel")
    public static int matterShovelCapacity = 4000;

    @ConfigValue(category = "matter", name = "Matter Sword Hoe Capacity", comment = "Matter Capacity of the Matter Hoe")
    public static int matterHoeCapacity = 4000;

    @ConfigValue(category = "misc", name = "Destruction Catalyst Matter Capacity", comment = "Drop Chance of the Minium Shard from hostile monsters", range = {0, 100})
    public static int miniumShardDropChance = 5;
}
