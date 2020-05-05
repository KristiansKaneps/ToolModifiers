package k4neps.toolmodifiers.crafting.hammer;

import org.bukkit.Material;

/**
 * Created by Kristians on 7/28/2017.
 *
 * @author Kristians
 */
public class GoldHammer extends HammerRecipe
{
	public GoldHammer()
	{
		super("golden_hammer", HammerRecipe.result_gold, HAMMER_SHAPE, INGREDIENTS.apply(Material.GOLD_BLOCK));
	}
}
