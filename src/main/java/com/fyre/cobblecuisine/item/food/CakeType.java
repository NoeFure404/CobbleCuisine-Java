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

public enum CakeType {
	TWIN_MUSHROOM("twin_mushroom_cake", effect(CobbleCuisineEffects.AMORPHOUS_EG.entry)),
	RED_MUSHROOM("red_mushroom_cake", effect(CobbleCuisineEffects.MONSTER_EG.entry)),
	BROWN_MUSHROOM("brown_mushroom_cake", effect(CobbleCuisineEffects.DRAGON_EG.entry)),
	FRUIT_HONEY("fruit_honey_cake", effect(CobbleCuisineEffects.BUG_EG.entry)),
	VEGETABLE_HONEY("vegetable_honey_cake", effect(CobbleCuisineEffects.FAIRY_EG.entry)),
	BERRY_GRAIN("berry_grain_cake", effect(CobbleCuisineEffects.FIELD_EG.entry)),
	SEED_GRAIN("seed_grain_cake", effect(CobbleCuisineEffects.HUMANLIKE_EG.entry)),
	RED_BEAN("red_bean_cake", effect(CobbleCuisineEffects.WATER1_EG.entry)),
	YELLOW_BEAN("yellow_bean_cake", effect(CobbleCuisineEffects.FLYING_EG.entry)),
	GREEN_BEAN("green_bean_cake", effect(CobbleCuisineEffects.GRASS_EG.entry)),
	ORANGE_BEAN("orange_bean_cake", effect(CobbleCuisineEffects.UNDISCOVERED_EG.entry)),
	BLUE_BEAN("blue_bean_cake", effect(CobbleCuisineEffects.WATER23_EG.entry)),
	SALT("salt_cake", effect(CobbleCuisineEffects.MINERAL_EG.entry));

	public final String id;
	public final Item item;

	CakeType(String id, CobbleCuisineItems.FoodEffect... foodEffects) {
		this.id = id;
		this.item = new CakeItem(id, CobbleCuisineItems.buildFoodComponent(6, 0.4f, false, foodEffects));
	}

	private static CobbleCuisineItems.FoodEffect effect(RegistryEntry<StatusEffect> effect) {
		return new CobbleCuisineItems.FoodEffect(effect, CobbleCuisineConfig.data.effectDuration.eggBoostEffectDuration);
	}

	public void register() {
		Registry.register(Registries.ITEM, Identifier.of(CobbleCuisine.MOD_ID, id), item);
	}

	public static void registerAll() {
		for (CakeType type : values()) {
			type.register();
		}
	}
}
