package k4neps.toolmodifiers.crafting.lumberaxe;

import org.bukkit.Material;

/**
 * Created by Kristians on 7/28/2017.
 *
 * @author Kristians
 */
public class GoldLumberaxe extends LumberaxeRecipe
{
	public GoldLumberaxe()
	{
		super("golden_lumberaxe", LumberaxeRecipe.result_gold, LUMBERAXE_SHAPE_L, INGREDIENTS.apply(Material.GOLD_BLOCK));
	}
}
