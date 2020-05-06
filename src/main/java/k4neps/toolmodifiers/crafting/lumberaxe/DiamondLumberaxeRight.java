package k4neps.toolmodifiers.crafting.lumberaxe;

import org.bukkit.Material;

/**
 * Created by Kristians on 7/28/2017.
 *
 * @author Kristians
 */
public class DiamondLumberaxeRight extends LumberaxeRecipe
{
	public DiamondLumberaxeRight()
	{
		super("diamond_lumberaxe_r", LumberaxeRecipe.result_diamond, LUMBERAXE_SHAPE_R, INGREDIENTS.apply(Material.DIAMOND_BLOCK));
	}
}
