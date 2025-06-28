package com.fyre.cobblecuisine.influence;

import com.cobblemon.mod.common.api.pokemon.egg.EggGroup;
import com.cobblemon.mod.common.api.spawning.SpawnBucket;
import com.cobblemon.mod.common.api.spawning.context.SpawningContext;
import com.cobblemon.mod.common.api.spawning.context.calculators.SpawningContextCalculator;
import com.cobblemon.mod.common.api.spawning.detail.PokemonSpawnDetail;
import com.cobblemon.mod.common.api.spawning.detail.SpawnAction;
import com.cobblemon.mod.common.api.spawning.detail.SpawnDetail;
import com.cobblemon.mod.common.api.spawning.influence.SpawningInfluence;
import com.cobblemon.mod.common.pokemon.Species;

import com.fyre.cobblecuisine.config.CobbleCuisineConfig;
import com.fyre.cobblecuisine.effect.CobbleCuisineEffects;
import com.fyre.cobblecuisine.util.CobbleCuisineUtils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import org.jetbrains.annotations.NotNull;

import static com.fyre.cobblecuisine.CobbleCuisine.LOGGER;
import static com.fyre.cobblecuisine.CobbleCuisine.DEBUG;

public class EggGroupInfluence implements SpawningInfluence {

	private static final EggGroup[] GROUP_ORDER = new EggGroup[]{
			EggGroup.AMORPHOUS,
			EggGroup.FAIRY,
			EggGroup.BUG,
			EggGroup.DRAGON,
			EggGroup.FIELD,
			EggGroup.FLYING,
			EggGroup.GRASS,
			EggGroup.HUMAN_LIKE,
			EggGroup.MINERAL,
			EggGroup.MONSTER,
			EggGroup.UNDISCOVERED,
			EggGroup.WATER_1,
			EggGroup.WATER_2,
			EggGroup.WATER_3
	};

	@SuppressWarnings("unchecked")
	private static final RegistryEntry<StatusEffect>[] STATUS_EFFECTS = new RegistryEntry[]{
			CobbleCuisineEffects.AMORPHOUS_EG.entry,
			CobbleCuisineEffects.FAIRY_EG.entry,
			CobbleCuisineEffects.BUG_EG.entry,
			CobbleCuisineEffects.DRAGON_EG.entry,
			CobbleCuisineEffects.FIELD_EG.entry,
			CobbleCuisineEffects.FLYING_EG.entry,
			CobbleCuisineEffects.GRASS_EG.entry,
			CobbleCuisineEffects.HUMANLIKE_EG.entry,
			CobbleCuisineEffects.MINERAL_EG.entry,
			CobbleCuisineEffects.MONSTER_EG.entry,
			CobbleCuisineEffects.UNDISCOVERED_EG.entry,
			CobbleCuisineEffects.WATER1_EG.entry,
			CobbleCuisineEffects.WATER23_EG.entry,
			CobbleCuisineEffects.WATER23_EG.entry
	};

	private static final float[] MATCH_MULTIPLIERS = {
			CobbleCuisineConfig.data.eggGroupMultipliers.amorphous.weightMultiplier,
			CobbleCuisineConfig.data.eggGroupMultipliers.fairy.weightMultiplier,
			CobbleCuisineConfig.data.eggGroupMultipliers.bug.weightMultiplier,
			CobbleCuisineConfig.data.eggGroupMultipliers.dragon.weightMultiplier,
			CobbleCuisineConfig.data.eggGroupMultipliers.field.weightMultiplier,
			CobbleCuisineConfig.data.eggGroupMultipliers.flying.weightMultiplier,
			CobbleCuisineConfig.data.eggGroupMultipliers.grass.weightMultiplier,
			CobbleCuisineConfig.data.eggGroupMultipliers.humanLike.weightMultiplier,
			CobbleCuisineConfig.data.eggGroupMultipliers.mineral.weightMultiplier,
			CobbleCuisineConfig.data.eggGroupMultipliers.monster.weightMultiplier,
			CobbleCuisineConfig.data.eggGroupMultipliers.undiscovered.weightMultiplier,
			CobbleCuisineConfig.data.eggGroupMultipliers.water1.weightMultiplier,
			CobbleCuisineConfig.data.eggGroupMultipliers.water23.weightMultiplier,
			CobbleCuisineConfig.data.eggGroupMultipliers.water23.weightMultiplier
	};

	private static final float[] NON_MATCH_MULTIPLIERS = {
			CobbleCuisineConfig.data.eggGroupMultipliers.amorphous.nonWeightMultiplier,
			CobbleCuisineConfig.data.eggGroupMultipliers.fairy.nonWeightMultiplier,
			CobbleCuisineConfig.data.eggGroupMultipliers.bug.nonWeightMultiplier,
			CobbleCuisineConfig.data.eggGroupMultipliers.dragon.nonWeightMultiplier,
			CobbleCuisineConfig.data.eggGroupMultipliers.field.nonWeightMultiplier,
			CobbleCuisineConfig.data.eggGroupMultipliers.flying.nonWeightMultiplier,
			CobbleCuisineConfig.data.eggGroupMultipliers.grass.nonWeightMultiplier,
			CobbleCuisineConfig.data.eggGroupMultipliers.humanLike.nonWeightMultiplier,
			CobbleCuisineConfig.data.eggGroupMultipliers.mineral.nonWeightMultiplier,
			CobbleCuisineConfig.data.eggGroupMultipliers.monster.nonWeightMultiplier,
			CobbleCuisineConfig.data.eggGroupMultipliers.undiscovered.nonWeightMultiplier,
			CobbleCuisineConfig.data.eggGroupMultipliers.water1.nonWeightMultiplier,
			CobbleCuisineConfig.data.eggGroupMultipliers.water23.nonWeightMultiplier,
			CobbleCuisineConfig.data.eggGroupMultipliers.water23.nonWeightMultiplier
	};

	private static final double EFFECT_DISTANCE = Math.pow(CobbleCuisineConfig.data.boostSettings.effectDistanceBlocks, 2);

	private final ServerPlayerEntity player;
	public EggGroupInfluence(ServerPlayerEntity player) { this.player = player; }

	@Override
	public float affectWeight(@NotNull SpawnDetail detail, @NotNull SpawningContext ctx, float weight) {
		if (!player.hasStatusEffect(CobbleCuisineEffects.EGG_BUFF_MARKER.entry)) return weight;
		if (!(detail instanceof PokemonSpawnDetail pkm)) return weight;

		Species species = CobbleCuisineUtils.resolveSpecies(pkm);
		if (species == null) return weight;

		if (player.getBlockPos().getSquaredDistance(ctx.getPosition()) > EFFECT_DISTANCE) return weight;

		float result = weight;
		for (int i = 0; i < STATUS_EFFECTS.length; i++) {
			if (player.hasStatusEffect(STATUS_EFFECTS[i])) {
				boolean matches = species.getEggGroups().contains(GROUP_ORDER[i]);
				result *= matches ? MATCH_MULTIPLIERS[i] : NON_MATCH_MULTIPLIERS[i];

				if (DEBUG) LOGGER.info("CobbleCuisine >> EGG GROUP INFLUENCE >> PLAYER: {} PKM: {} OLD WEIGHT: {} NEW WEIGHT: {}", player.getName(), species, weight, result);
			}
		}
		return result;
	}

	@Override public boolean isExpired() { return false; }
	@Override public void affectAction(@NotNull SpawnAction<?> action) { }
	@Override public void affectSpawn(@NotNull Entity entity) { }
	@Override public float affectBucketWeight(@NotNull SpawnBucket bucket, float weight) { return weight; }
	@Override public boolean isAllowedPosition(@NotNull ServerWorld world, @NotNull BlockPos pos, @NotNull SpawningContextCalculator<?, ?> contextCalculator) { return true; }
	@Override public boolean affectSpawnable(@NotNull SpawnDetail detail, @NotNull SpawningContext ctx) { return true; }
}
