package k4neps.toolmodifiers.crafting.excavator;

import org.bukkit.Material;

/**
 * Created by Kristians on 7/28/2017.
 *
 * @author Kristians
 */
public class GoldExcavator extends ExcavatorRecipe
{
	public GoldExcavator()
	{
		super("gold_excavator", ExcavatorRecipe.result_gold, EXCAVATOR_SHAPE, INGREDIENTS.apply(Material.GOLD_BLOCK));
	}
}
