package com.fyre.cobblecuisine.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import com.fyre.cobblecuisine.CobbleCuisine;
import com.fyre.cobblecuisine.block.crop.BeanCropBlock;

import static com.fyre.cobblecuisine.CobbleCuisine.LOGGER;

public class CobbleCuisineBlocks {
	public static final Block BEAN_CROP_BLOCK = registerBlock(
			new BeanCropBlock(AbstractBlock.Settings.create()
							.noCollision()
							.ticksRandomly()
							.breakInstantly()
							.sounds(BlockSoundGroup.CROP)
							.pistonBehavior(PistonBehavior.DESTROY)
							.mapColor(MapColor.DARK_GREEN)
			)
	);

	private static Block registerBlock(Block block) {
		LOGGER.info("CobbleCuisine >> Registering Blocks...");
		return Registry.register(Registries.BLOCK, Identifier.of(CobbleCuisine.MOD_ID, "bean_crop"), block);
	}
}
