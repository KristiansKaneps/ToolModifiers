package k4neps.toolmodifiers.crafting.excavator;

import org.bukkit.Material;

/**
 * Created by Kristians on 7/28/2017.
 *
 * @author Kristians
 */
public class IronExcavator extends ExcavatorRecipe
{
	public IronExcavator()
	{
		super("iron_excavator", ExcavatorRecipe.result_iron, EXCAVATOR_SHAPE, INGREDIENTS.apply(Material.IRON_BLOCK));
	}
}
