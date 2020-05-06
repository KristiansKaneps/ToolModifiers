package k4neps.toolmodifiers.crafting.hammer;

import org.bukkit.Material;

/**
 * Created by Kristians on 7/28/2017.
 *
 * @author Kristians
 */
public class IronHammer extends HammerRecipe
{
	public IronHammer()
	{
		super("iron_hammer", HammerRecipe.result_iron, HAMMER_SHAPE, INGREDIENTS.apply(Material.IRON_BLOCK));
	}
}
