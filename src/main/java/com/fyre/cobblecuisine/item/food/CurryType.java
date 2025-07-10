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
	DRAGON("curry_dragon", effect(CobbleCuisineEffects.DRAGON.entry)),
	FAIRY("curry_fairy", effect(CobbleCuisineEffects.FAIRY.entry)),
	POISON("curry_poison", effect(CobbleCuisineEffects.POISON.entry)),
	WATER("curry_water", effect(CobbleCuisineEffects.WATER.entry)),
	FIGHTING("curry_fighting", effect(CobbleCuisineEffects.FIGHTING.entry)),
	ICE("curry_ice", effect(CobbleCuisineEffects.ICE.entry)),
	PSYCHIC("curry_psychic", effect(CobbleCuisineEffects.PSYCHIC.entry)),
	STEEL("curry_steel", effect(CobbleCuisineEffects.STEEL.entry)),
	GROUND("curry_ground", effect(CobbleCuisineEffects.GROUND.entry)),
	FIRE("curry_fire", effect(CobbleCuisineEffects.FIRE.entry)),
	GHOST("curry_ghost", effect(CobbleCuisineEffects.GHOST.entry)),
	DARK("curry_dark", effect(CobbleCuisineEffects.DARK.entry)),
	BUG("curry_bug", effect(CobbleCuisineEffects.BUG.entry)),
	FLYING("curry_flying", effect(CobbleCuisineEffects.FLYING.entry)),
	GRASS("curry_grass", effect(CobbleCuisineEffects.GRASS.entry)),
	NORMAL("curry_normal", effect(CobbleCuisineEffects.NORMAL.entry)),
	ELECTRIC("curry_electric", effect(CobbleCuisineEffects.ELECTRIC.entry)),
	ROCK("curry_rock", effect(CobbleCuisineEffects.ROCK.entry));

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
