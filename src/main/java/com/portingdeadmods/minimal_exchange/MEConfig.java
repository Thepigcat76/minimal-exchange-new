package com.portingdeadmods.minimal_exchange;

import com.portingdeadmods.portingdeadlibs.api.config.ConfigValue;

public final class MEConfig {
    @ConfigValue(category = "matter.capacity", name = "Transmutation Stone Matter Capacity", comment = "Matter Capacity of the Transmutation Stone")
    public static int transmutationStoneCapacity = 3000;

    @ConfigValue(category = "matter.capacity", name = "Destruction Catalyst Matter Capacity", comment = "Matter Capacity of the Destruction Catalyst")
    public static int destructionCatalystCapacity = 3000;

    @ConfigValue(category = "matter.capacity", name = "Matter Sword Matter Capacity", comment = "Matter Capacity of the Matter Sword")
    public static int matterSwordCapacity = 4000;

    @ConfigValue(category = "matter.capacity", name = "Matter Pickaxe Matter Capacity", comment = "Matter Capacity of the Matter Pickaxe")
    public static int matterPickaxeCapacity = 4000;

    @ConfigValue(category = "matter.capacity", name = "Matter Axe Matter Capacity", comment = "Matter Capacity of the Matter Axe")
    public static int matterAxeCapacity = 4000;

    @ConfigValue(category = "matter.capacity", name = "Matter Shovel Matter Capacity", comment = "Matter Capacity of the Matter Shovel")
    public static int matterShovelCapacity = 4000;

    @ConfigValue(category = "matter.capacity", name = "Matter Sword Hoe Capacity", comment = "Matter Capacity of the Matter Hoe")
    public static int matterHoeCapacity = 4000;

    @ConfigValue(category = "matter.usage", name = "Matter Sword Matter Usage", comment = "Matter Usage of the Matter Sword")
    public static int matterSwordUsage = 20;

    @ConfigValue(category = "matter.usage", name = "Matter Pickaxe Matter Usage", comment = "Matter Usage of the Matter Pickaxe")
    public static int matterPickaxeUsage = 20;

    @ConfigValue(category = "matter.usage", name = "Matter Axe Matter Usage", comment = "Matter Usage of the Matter Axe")
    public static int matterAxeUsage = 20;

    @ConfigValue(category = "matter.usage", name = "Matter Shovel Matter Usage", comment = "Matter Usage of the Matter Shovel")
    public static int matterShovelUsage = 20;

    @ConfigValue(category = "matter.usage", name = "Matter Hoe Usage", comment = "Matter Usage of the Matter Hoe")
    public static int matterHoeUsage = 20;

    @ConfigValue(category = "misc", name = "Minium Shard Drop Chance", comment = "Drop Chance of the Minium Shard from hostile monsters", range = {0, 100})
    public static int miniumShardDropChance = 5;
}
