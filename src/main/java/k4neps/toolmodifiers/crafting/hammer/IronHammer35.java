package k4neps.toolmodifiers.crafting.hammer;

import org.bukkit.Material;

public class IronHammer35 extends Hammer35Recipe
{
	public IronHammer35()
	{
		super("iron_hammer_3x5", Hammer35Recipe.result_iron, HAMMER35_SHAPE, BIINGREDIENTS.apply(Material.IRON_BLOCK, Material.IRON_INGOT));
	}
}
