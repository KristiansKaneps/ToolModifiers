package k4neps.toolmodifiers.crafting.lumberaxe;

import org.bukkit.Material;

/**
 * Created by Kristians on 7/28/2017.
 *
 * @author Kristians
 */
public class IronLumberaxe extends LumberaxeRecipe
{
	public IronLumberaxe()
	{
		super("iron_lumberaxe", LumberaxeRecipe.result_iron, LUMBERAXE_SHAPE_L, INGREDIENTS.apply(Material.IRON_BLOCK));
	}
}
