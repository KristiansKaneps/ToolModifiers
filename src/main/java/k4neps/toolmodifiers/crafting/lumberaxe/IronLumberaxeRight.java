package k4neps.toolmodifiers.crafting.lumberaxe;

import org.bukkit.Material;

/**
 * Created by Kristians on 7/28/2017.
 *
 * @author Kristians
 */
public class IronLumberaxeRight extends LumberaxeRecipe
{
	public IronLumberaxeRight()
	{
		super("iron_lumberaxe_r", LumberaxeRecipe.result_iron, LUMBERAXE_SHAPE_R, INGREDIENTS.apply(Material.IRON_BLOCK));
	}
}
