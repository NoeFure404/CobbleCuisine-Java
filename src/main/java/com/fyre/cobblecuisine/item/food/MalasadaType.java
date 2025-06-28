package com.fyre.cobblecuisine.item.food;

import com.cobblemon.mod.common.api.berry.Flavor;
import com.fyre.cobblecuisine.CobbleCuisine;
import com.fyre.cobblecuisine.item.CobbleCuisineItems;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public enum MalasadaType {
	SWEET("sweet_malasada", Flavor.SWEET, effect(StatusEffects.REGENERATION)),
	SPICY("spicy_malasada", Flavor.SPICY, effect(StatusEffects.REGENERATION)),
	SOUR("sour_malasada", Flavor.SOUR, effect(StatusEffects.REGENERATION)),
	BITTER("bitter_malasada", Flavor.BITTER, effect(StatusEffects.REGENERATION)),
	DRY("dry_malasada", Flavor.DRY, effect(StatusEffects.REGENERATION));

	public final String id;
	public final Item item;

	MalasadaType(String id, Flavor flavor, CobbleCuisineItems.FoodEffect... foodEffects) {
		this.id = id;
		this.item = new MalasadaItem(id, flavor, CobbleCuisineItems.buildFoodComponent(8, 1f, true, foodEffects));
	}

	private static CobbleCuisineItems.FoodEffect effect(RegistryEntry<StatusEffect> effect) {
		return new CobbleCuisineItems.FoodEffect(effect, 600);
	}

	public void register() {
		Registry.register(Registries.ITEM, Identifier.of(CobbleCuisine.MOD_ID, id), item);
	}

	public static void registerAll() {
		for (MalasadaType type : values()) {
			type.register();
		}
	}
}
