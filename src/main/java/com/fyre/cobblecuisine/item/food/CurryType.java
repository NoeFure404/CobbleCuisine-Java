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

public enum CurryType {
	SWEET_APPLE("sweet_apple_curry", effect(CobbleCuisineEffects.DRAGON.entry)),
	SWEET_WHIPPED_CREAM("sweet_whipped_cream_curry", effect(CobbleCuisineEffects.FAIRY.entry)),
	BITTER_HERB_MEDLEY("bitter_herb_medley_curry", effect(CobbleCuisineEffects.POISON.entry)),
	BITTER_LEEK("bitter_leek_curry", effect(CobbleCuisineEffects.WATER.entry)),
	DRY("dry_curry", effect(CobbleCuisineEffects.FIGHTING.entry)),
	DRY_FROZEN("dry_frozen_curry", effect(CobbleCuisineEffects.ICE.entry)),
	DRY_SMOKED_TAIL("dry_smoked_tail_curry", effect(CobbleCuisineEffects.PSYCHIC.entry)),
	SPICY_POTATO("spicy_potato_curry", effect(CobbleCuisineEffects.STEEL.entry)),
	DRY_BONE("dry_bone_curry", effect(CobbleCuisineEffects.GROUND.entry)),
	DROUGHT_KATSU("drought_katsu_curry", effect(CobbleCuisineEffects.FIRE.entry)),
	DREAM_EATER_BUTTER("dream_eater_butter_curry", effect(CobbleCuisineEffects.GHOST.entry)),
	NINJA("ninja_curry", effect(CobbleCuisineEffects.DARK.entry)),
	MILD_HONEY("mild_honey_curry", effect(CobbleCuisineEffects.BUG.entry)),
	BEAN_MEDLEY("bean_medley_curry", effect(CobbleCuisineEffects.FLYING.entry)),
	SPICY_MUSHROOM_MEDLEY("spicy_mushroom_medley_curry", effect(CobbleCuisineEffects.GRASS.entry)),
	SALTY_BOILED_EGG("salty_boiled_egg_curry", effect(CobbleCuisineEffects.NORMAL.entry)),
	ZING_ZAP("zing_zap_curry", effect(CobbleCuisineEffects.ELECTRIC.entry)),
	BEANBURGER("beanburger_curry", effect(CobbleCuisineEffects.ROCK.entry));

	public final String id;
	public final Item item;

	CurryType(String id, CobbleCuisineItems.FoodEffect... foodEffects) {
		this.id = id;
		this.item = new CurryItem(id, CobbleCuisineItems.buildFoodComponent(10, 1, false, foodEffects));
	}

	private static CobbleCuisineItems.FoodEffect effect(RegistryEntry<StatusEffect> effect) {
		return new CobbleCuisineItems.FoodEffect(effect, CobbleCuisineConfig.data.effectDuration.typeBoostEffectDuration);
	}

	public void register() {
		Registry.register(Registries.ITEM, Identifier.of(CobbleCuisine.MOD_ID, id), item);
	}

	public static void registerAll() {
		CurryType[] values = values();
		//noinspection ForLoopReplaceableByForEach
		for (int i = 0; i < values.length; i++) {
			CurryType type = values[i];
			type.register();
		}
	}
}
