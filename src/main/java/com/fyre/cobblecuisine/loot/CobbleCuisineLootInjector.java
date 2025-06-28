package com.fyre.cobblecuisine.loot;

import com.cobblemon.mod.common.CobblemonItems;

import com.fyre.cobblecuisine.config.CobbleCuisineConfig;
import com.fyre.cobblecuisine.item.food.BeanType;
import com.fyre.cobblecuisine.item.CobbleCuisineItems;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableSource;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Map;

import static com.fyre.cobblecuisine.CobbleCuisine.LOGGER;

public class CobbleCuisineLootInjector {
	private static final Identifier GRASS_BLOCK_ID = Identifier.of("minecraft", "blocks/short_grass");

	private static final List<RegistryKey<LootTable>> VILLAGE_CHESTS = List.of(
			LootTables.VILLAGE_DESERT_HOUSE_CHEST,
			LootTables.VILLAGE_PLAINS_CHEST,
			LootTables.VILLAGE_SAVANNA_HOUSE_CHEST,
			LootTables.VILLAGE_TAIGA_HOUSE_CHEST,
			LootTables.VILLAGE_SNOWY_HOUSE_CHEST
	);

	private static final Map<RegistryKey<LootTable>, Item> BEAN_MAP = Map.of(
			LootTables.VILLAGE_DESERT_HOUSE_CHEST, BeanType.ORANGE.item,
			LootTables.VILLAGE_PLAINS_CHEST, BeanType.GREEN.item,
			LootTables.VILLAGE_SAVANNA_HOUSE_CHEST, BeanType.YELLOW.item,
			LootTables.VILLAGE_TAIGA_HOUSE_CHEST, BeanType.RED.item,
			LootTables.VILLAGE_SNOWY_HOUSE_CHEST, BeanType.BLUE.item
	);

	private CobbleCuisineLootInjector() {}

	public static void register() {
		LOGGER.info("CobbleCuisine >> Injecting Loot Tables...");
		LootTableEvents.MODIFY.register(CobbleCuisineLootInjector::onLootTableModify);
	}

	private static void onLootTableModify(RegistryKey<LootTable> key, LootTable.Builder table, LootTableSource source, WrapperLookup registries) {
		if (key.getValue().equals(GRASS_BLOCK_ID)) {
			addPool(table, CobbleCuisineConfig.data.dropRates.beanSeedsDropRate, CobbleCuisineItems.BEAN_SEEDS, 1, 1);
		}

		if (VILLAGE_CHESTS.contains(key)) {
			addPool(table, CobbleCuisineConfig.data.dropRates.galaricaNutDropRate, CobblemonItems.GALARICA_NUTS, 1, 3);
			addPool(table, CobbleCuisineConfig.data.dropRates.torchflowerDropRate, Items.TORCHFLOWER, 1, 3);
			addPool(table, CobbleCuisineConfig.data.dropRates.torchflowerSeedsDropRate, Items.TORCHFLOWER_SEEDS, 1, 1);

			Item bean = BEAN_MAP.get(key);
			if (bean != null) {
				addPool(table, CobbleCuisineConfig.data.dropRates.beanDropRate, bean, 2, 5);
			}
		}
	}

	private static void addPool(LootTable.Builder table, float chance, Item item, float min, float max) {
		table.pool(LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1))
						.conditionally(RandomChanceLootCondition.builder(chance))
						.with(ItemEntry.builder(item))
						.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(min, max)).build())
						.build()
		);
	}
}
