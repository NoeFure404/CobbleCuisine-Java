package com.fyre.cobblecuisine.client;

import com.fyre.cobblecuisine.block.CobbleCuisineBlocks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;

import net.minecraft.client.render.RenderLayer;

public class CobbleCuisineClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(CobbleCuisineBlocks.BEAN_CROP_BLOCK, RenderLayer.getCutout());
	}
}
