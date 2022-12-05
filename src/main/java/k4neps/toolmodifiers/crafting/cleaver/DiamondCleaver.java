package k4neps.toolmodifiers.crafting.cleaver;

import org.bukkit.Material;

/**
 * Created by Kristians on 12/05/2022.
 *
 * @author Kristians
 */
public class DiamondCleaver extends CleaverRecipe
{
	public DiamondCleaver()
	{
		super("diamond_cleaver", CleaverRecipe.result_diamond, CLEAVER_SHAPE, INGREDIENTS.apply(Material.DIAMOND_BLOCK));
	}
}
