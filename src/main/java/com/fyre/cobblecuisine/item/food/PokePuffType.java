package com.fyre.cobblecuisine.item.food;

import com.fyre.cobblecuisine.CobbleCuisine;
import com.fyre.cobblecuisine.item.CobbleCuisineItems;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public enum PokePuffType {
	SWEET("sweet_pokepuff", effect(StatusEffects.REGENERATION)),
	MINT("mint_pokepuff", effect(StatusEffects.REGENERATION)),
	CITRUS("citrus_pokepuff", effect(StatusEffects.REGENERATION)),
	MOCHA("mocha_pokepuff", effect(StatusEffects.REGENERATION)),
	SPICE("spice_pokepuff", effect(StatusEffects.REGENERATION));

	public final String id;
	public final Item item;

	PokePuffType(String id, CobbleCuisineItems.FoodEffect...foodEffects) {
		this.id = id;
		this.item = new PokePuffItem(id, CobbleCuisineItems.buildFoodComponent(8, 1f, true, foodEffects));
	}

	private static CobbleCuisineItems.FoodEffect effect(RegistryEntry<StatusEffect> effect) {
		return new CobbleCuisineItems.FoodEffect(effect, 300);
	}

	public void register() {
		Registry.register(Registries.ITEM, Identifier.of(CobbleCuisine.MOD_ID, id), item);
	}

	public static void registerAll() {
		for (PokePuffType type : values()) {
			type.register();
		}
	}
}
