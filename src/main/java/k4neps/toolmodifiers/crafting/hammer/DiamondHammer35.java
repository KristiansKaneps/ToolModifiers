package k4neps.toolmodifiers.crafting.hammer;

import org.bukkit.Material;

public class DiamondHammer35 extends Hammer35Recipe
{
	public DiamondHammer35()
	{
		super("diamond_hammer_3x5", Hammer35Recipe.result_diamond, HAMMER35_SHAPE, BIINGREDIENTS.apply(Material.DIAMOND_BLOCK, Material.DIAMOND));
	}
}
