package com.fyre.cobblecuisine.item.food;

import com.fyre.cobblecuisine.util.CobbleCuisineUtils;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.UseAction;

import java.util.List;

public class FancyShakeItem extends Item {
	private final List<Text> tooltips;

	public FancyShakeItem(String name, FoodComponent foodComponent) {
		super(new Settings().food(foodComponent));
		this.tooltips = CobbleCuisineUtils.getItemTooltip(name, foodComponent, null, 0, null, null, null);
	}

	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.DRINK;
	}

	@Override
	public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
		tooltip.addAll(tooltips);
		super.appendTooltip(stack, context, tooltip, type);
	}
}
