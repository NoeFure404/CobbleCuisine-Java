package com.fyre.cobblecuisine.item;

import com.fyre.cobblecuisine.CobbleCuisine;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public enum LegacyItemType {
	PEPPER_STEAK("pepper_steak"),
	CEVICHE("ceviche"),
	SPICE_MIX("spice_mix"),
	SALAD_MIX("salad_mix"),
	SWEET_POTATO_SANDWICH("sweet_potato_salad_sandwich"),
	BITTER_VARIETY_SANDWICH("bitter_variety_sandwich");

	public final String id;
	public final Item item;

	LegacyItemType(String id) {
		this.id = id;
		this.item = new Item(new Item.Settings());
	}

	public void register() {
		Registry.register(Registries.ITEM, Identifier.of(CobbleCuisine.MOD_ID, id), item);
	}

	public static void registerAll() {
		for (LegacyItemType type : values()) {
			type.register();
		}
	}
}
