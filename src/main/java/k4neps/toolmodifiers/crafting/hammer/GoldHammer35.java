package k4neps.toolmodifiers.crafting.hammer;

import org.bukkit.Material;

public class GoldHammer35 extends Hammer35Recipe
{
	public GoldHammer35()
	{
		super("golden_hammer_3x5", Hammer35Recipe.result_gold, HAMMER35_SHAPE, BIINGREDIENTS.apply(Material.GOLD_BLOCK, Material.GOLD_INGOT));
	}
}
