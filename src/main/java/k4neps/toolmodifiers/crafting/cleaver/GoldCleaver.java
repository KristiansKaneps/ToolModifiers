package k4neps.toolmodifiers.crafting.cleaver;

import org.bukkit.Material;

/**
 * Created by Kristians on 12/05/2022.
 *
 * @author Kristians
 */
public class GoldCleaver extends CleaverRecipe
{
	public GoldCleaver()
	{
		super("gold_excavator", CleaverRecipe.result_gold, CLEAVER_SHAPE, INGREDIENTS.apply(Material.GOLD_BLOCK));
	}
}
