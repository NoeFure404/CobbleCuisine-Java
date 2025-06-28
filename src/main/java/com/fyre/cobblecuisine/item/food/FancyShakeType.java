package com.fyre.cobblecuisine.item.food;

import com.fyre.cobblecuisine.CobbleCuisine;
import com.fyre.cobblecuisine.item.CobbleCuisineItems;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public enum FancyShakeType {
	REGULAR_JEWEL("regular_jewel_shake"),
	REGULAR_EARTHY("regular_earthy_shake"),
	REGULAR_VIOLET("regular_violet_shake"),
	REGULAR_VERDANT("regular_verdant_shake"),
	REGULAR_CORAL("regular_coral_shake"),
	REGULAR_BB("regular_bb_shake"),

	DELUXE_JEWEL("deluxe_jewel_shake"),
	DELUXE_EARTHY("deluxe_earthy_shake"),
	DELUXE_VIOLET("deluxe_violet_shake"),
	DELUXE_VERDANT("deluxe_verdant_shake"),
	DELUXE_CORAL("deluxe_coral_shake"),
	DELUXE_BB("deluxe_bb_shake");

	public final String id;
	public final Item item;

	FancyShakeType(String id, CobbleCuisineItems.FoodEffect... foodEffects) {
		this.id = id;
		this.item = new FancyShakeItem(id, CobbleCuisineItems.buildFoodComponent(5, 1f, false, foodEffects));
	}

	private static CobbleCuisineItems.FoodEffect effect(RegistryEntry<StatusEffect> effect) {
		return new CobbleCuisineItems.FoodEffect(effect, 200);
	}

	public void register() {
		Registry.register(Registries.ITEM, Identifier.of(CobbleCuisine.MOD_ID, id), item);
	}

	public static void registerAll() {
		for (var type : values()) {
			type.register();
		}
	}
}
