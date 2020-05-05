package k4neps.toolmodifiers.crafting.excavator;

import org.bukkit.Material;

/**
 * Created by Kristians on 7/28/2017.
 *
 * @author Kristians
 */
public class DiamondExcavator extends ExcavatorRecipe
{
	public DiamondExcavator()
	{
		super("diamond_excavator", ExcavatorRecipe.result_diamond, EXCAVATOR_SHAPE, INGREDIENTS.apply(Material.DIAMOND_BLOCK));
	}
}
