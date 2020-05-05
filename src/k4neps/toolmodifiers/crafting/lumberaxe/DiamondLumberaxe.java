package k4neps.toolmodifiers.crafting.lumberaxe;

import org.bukkit.Material;

/**
 * Created by Kristians on 7/28/2017.
 *
 * @author Kristians
 */
public class DiamondLumberaxe extends LumberaxeRecipe
{
	public DiamondLumberaxe()
	{
		super("diamond_lumberaxe", LumberaxeRecipe.result_diamond, LUMBERAXE_SHAPE_L, INGREDIENTS.apply(Material.DIAMOND_BLOCK));
	}
}
