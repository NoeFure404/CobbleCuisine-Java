package com.fyre.cobblecuisine.item.food;

import com.fyre.cobblecuisine.CobbleCuisine;
import com.fyre.cobblecuisine.config.CobbleCuisineConfig;
import com.fyre.cobblecuisine.effect.CobbleCuisineEffects;
import com.fyre.cobblecuisine.item.CobbleCuisineItems;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public enum SandwichType {
	SOUR_PICKLE("sour_pickle_sandwich", effect(CobbleCuisineEffects.TERA_PSYCHIC.entry)),
	SOUR_CHEESE("sour_cheese_sandwich", effect(CobbleCuisineEffects.TERA_NORMAL.entry)),
	SOUR_ZESTY("sour_zesty_sandwich", effect(CobbleCuisineEffects.TERA_ELECTRIC.entry)),
	SWEET_JAM("sweet_jam_sandwich", effect(CobbleCuisineEffects.TERA_BUG.entry)),
	SPICY_NOODLE("spicy_noodle_sandwich", effect(CobbleCuisineEffects.TERA_FLYING.entry)),
	SALTY_VEGETABLE("salty_vegetable_sandwich", effect(CobbleCuisineEffects.TERA_GRASS.entry)),
	SPICY_HAM("spicy_ham_sandwich", effect(CobbleCuisineEffects.TERA_DARK.entry)),
	DRY_TOWER("dry_tower_sandwich", effect(CobbleCuisineEffects.TERA_STEEL.entry)),
	SPICY_FIVE_ALARM("spicy_five_alarm_sandwich", effect(CobbleCuisineEffects.TERA_FIRE.entry)),
	SPICY_CLAW("spicy_claw_sandwich", effect(CobbleCuisineEffects.TERA_ROCK.entry)),
	DRY_HEFTY("dry_hefty_sandwich", effect(CobbleCuisineEffects.TERA_DRAGON.entry)),
	SPICY_FILLET("spicy_fillet_sandwich", effect(CobbleCuisineEffects.TERA_POISON.entry)),
	SWEET_FRUIT("sweet_fruit_sandwich", effect(CobbleCuisineEffects.TERA_FAIRY.entry)),
	SALTY_TOFU("salty_tofu_sandwich", effect(CobbleCuisineEffects.TERA_WATER.entry)),
	SALTY_EGG("salty_egg_sandwich", effect(CobbleCuisineEffects.TERA_FIGHTING.entry)),
	SWEET_DESSERT("sweet_dessert_sandwich", effect(CobbleCuisineEffects.TERA_ICE.entry)),
	DRY_SMOKY("dry_smoky_sandwich", effect(CobbleCuisineEffects.TERA_GHOST.entry)),
	BITTER_POTATO("bitter_potato_sandwich", effect(CobbleCuisineEffects.TERA_GROUND.entry)),
	BITTER_JAMBON_BEURRE("bitter_jambon_beurre", effect(CobbleCuisineEffects.TERA_STELLAR.entry));

	public final String id;
	public final Item item;

	SandwichType(String id, CobbleCuisineItems.FoodEffect... foodEffects) {
		this.id = id;
		this.item = new SandwichItem(id, CobbleCuisineItems.buildFoodComponent(10, 1f, false, foodEffects));
	}

	private static CobbleCuisineItems.FoodEffect effect(RegistryEntry<StatusEffect> effect) {
		return new CobbleCuisineItems.FoodEffect(effect, CobbleCuisineConfig.data.effectDuration.teraBoostEffectDuration);
	}

	public void register() {
		Registry.register(Registries.ITEM, Identifier.of(CobbleCuisine.MOD_ID, id), item);
	}

	public static void registerAll() {
		for (SandwichType type : values()) {
			type.register();
		}
	}
}
