package k4neps.toolmodifiers.crafting.cleaver;

import org.bukkit.Material;

/**
 * Created by Kristians on 12/05/2022.
 *
 * @author Kristians
 */
public class IronCleaver extends CleaverRecipe
{
	public IronCleaver()
	{
		super("iron_cleaver", CleaverRecipe.result_iron, CLEAVER_SHAPE, INGREDIENTS.apply(Material.IRON_BLOCK));
	}
}
