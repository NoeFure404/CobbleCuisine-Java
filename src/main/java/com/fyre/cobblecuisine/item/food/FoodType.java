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

public enum FoodType {
	ECLAIR("eclair", 6, 0.5f, effect(CobbleCuisineEffects.GIANT.entry, CobbleCuisineConfig.data.effectDuration.scaleBoostEffectDuration)),
	FRUITY_FLAN("fruity_flan", 6, 0.5f, effect(CobbleCuisineEffects.TINY.entry, CobbleCuisineConfig.data.effectDuration.scaleBoostEffectDuration)),
	KANTONIAN_CREPE("kantonian_crepe", 6, 0.5f, effect(CobbleCuisineEffects.CATCH_BOOST.entry, CobbleCuisineConfig.data.effectDuration.catchRateEffectDuration)),
	ALOLAN_BLUE_SHAVED_ICE("alolan_blue_shaved_ice", 6, 0.5f, effect(CobbleCuisineEffects.SHINY.entry, CobbleCuisineConfig.data.effectDuration.shinyBoostEffectDuration)),
	PICKLED_TOEDSCOOL_AND_CUCUMBER("pickled_toedscool_and_cucumber", 6, 0.5f, effect(CobbleCuisineEffects.EXP_BOOST.entry, CobbleCuisineConfig.data.effectDuration.expBoostEffectDuration)),
	HOENNIAN_MELON_STIR_FRY("hoennian_melon_stir_fry", 6, 0.5f, effect(CobbleCuisineEffects.IV_MODIFY.entry, CobbleCuisineConfig.data.effectDuration.statBoostEffectDuration)),
	DUBIOUS_FOOD("dubious_food", 2, 0.15f, effect(CobbleCuisineEffects.DUBIOUS.entry, CobbleCuisineConfig.data.effectDuration.dubiousFoodEffectDuration));

	public final String id;
	public final Item item;

	FoodType(String id, int nutrition, float saturation, CobbleCuisineItems.FoodEffect ...effect) {
		this.id = id;
		this.item = new FoodItem(id, CobbleCuisineItems.buildFoodComponent(nutrition, saturation, false, effect));
	}

	private static CobbleCuisineItems.FoodEffect effect(RegistryEntry<StatusEffect> effect, int duration) {
		return new CobbleCuisineItems.FoodEffect(effect, duration);
	}

	public void register() {
		Registry.register(Registries.ITEM, Identifier.of(CobbleCuisine.MOD_ID, id), item);
	}

	public static void registerAll() {
		for (FoodType type : values()) {
			type.register();
		}
	}
}
