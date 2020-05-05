package k4neps.toolmodifiers.crafting.lumberaxe;

import k4neps.toolmodifiers.Lang;
import k4neps.toolmodifiers.crafting.Ingredient;
import k4neps.toolmodifiers.crafting.RecipeShape;
import k4neps.toolmodifiers.crafting.ToolRecipe;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

/**
 * Created by Kristians on 7/28/2017.
 *
 * @author Kristians
 */
public class LumberaxeRecipe extends ToolRecipe
{
	public static final RecipeShape LUMBERAXE_SHAPE_L = new RecipeShape("BB", "BS", " S");
	public static final RecipeShape LUMBERAXE_SHAPE_R = new RecipeShape("BB", "SB", "S ");
	public static final String LUMBERAXE_MODIFIER_STRING = ChatColor.AQUA + "Lumberaxe";

	public static final ItemStack result_diamond = new ItemStack(Material.DIAMOND_AXE);
	public static final ItemStack result_iron = new ItemStack(Material.IRON_AXE);
	public static final ItemStack result_gold = new ItemStack(Material.GOLDEN_AXE);

	static
	{
		ItemMeta meta;

		meta = result_diamond.getItemMeta();
		meta.setDisplayName(Lang.DIAMOND.LUMBERAXE);
		meta.setLore(new ArrayList<String>(){{add(LUMBERAXE_MODIFIER_STRING);}});
		result_diamond.setItemMeta(meta);

		meta = result_iron.getItemMeta();
		meta.setDisplayName(Lang.IRON.LUMBERAXE);
		meta.setLore(new ArrayList<String>(){{add(LUMBERAXE_MODIFIER_STRING);}});
		result_iron.setItemMeta(meta);

		meta = result_gold.getItemMeta();
		meta.setDisplayName(Lang.GOLD.LUMBERAXE);
		meta.setLore(new ArrayList<String>(){{add(LUMBERAXE_MODIFIER_STRING);}});
		result_gold.setItemMeta(meta);
	}

	public LumberaxeRecipe(String key, ItemStack result, RecipeShape shape, Ingredient... ingredients)
	{
		super(key, result, shape, ingredients);
	}
}
