package k4neps.toolmodifiers.crafting.lumberaxe;

import org.bukkit.Material;

/**
 * Created by Kristians on 7/28/2017.
 *
 * @author Kristians
 */
public class GoldLumberaxeRight extends LumberaxeRecipe
{
	public GoldLumberaxeRight()
	{
		super("golden_lumberaxe_r", LumberaxeRecipe.result_gold, LUMBERAXE_SHAPE_R, INGREDIENTS.apply(Material.GOLD_BLOCK));
	}
}
