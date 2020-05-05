package k4neps.toolmodifiers.crafting.hammer;

import org.bukkit.Material;

/**
 * Created by Kristians on 7/28/2017.
 *
 * @author Kristians
 */
public class DiamondHammer extends HammerRecipe
{
	public DiamondHammer()
	{
		super("diamond_hammer", HammerRecipe.result_diamond, HAMMER_SHAPE, INGREDIENTS.apply(Material.DIAMOND_BLOCK));
	}
}
