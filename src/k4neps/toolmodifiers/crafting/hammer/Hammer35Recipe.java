package k4neps.toolmodifiers.crafting.hammer;

import k4neps.toolmodifiers.Lang;
import k4neps.toolmodifiers.crafting.Ingredient;
import k4neps.toolmodifiers.crafting.RecipeShape;
import k4neps.toolmodifiers.crafting.ToolRecipe;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Hammer35Recipe extends ToolRecipe
{
	public static final RecipeShape HAMMER35_SHAPE = new RecipeShape("BBB", "DSD", " S ");
	public static final String HAMMER_MODIFIER_STRING = ChatColor.AQUA + "Hammer 3x5";

	public static final ItemStack result_diamond = new ItemStack(Material.DIAMOND_PICKAXE);
	public static final ItemStack result_iron = new ItemStack(Material.IRON_PICKAXE);
	public static final ItemStack result_gold = new ItemStack(Material.GOLDEN_PICKAXE);

	static
	{
		ItemMeta meta;

		meta = result_diamond.getItemMeta();
		meta.setDisplayName(Lang.DIAMOND.HAMMER_3X5);
		meta.setLore(new ArrayList<String>(){{add(HAMMER_MODIFIER_STRING);}});
		result_diamond.setItemMeta(meta);

		meta = result_iron.getItemMeta();
		meta.setDisplayName(Lang.IRON.HAMMER_3X5);
		meta.setLore(new ArrayList<String>(){{add(HAMMER_MODIFIER_STRING);}});
		result_iron.setItemMeta(meta);

		meta = result_gold.getItemMeta();
		meta.setDisplayName(Lang.GOLD.HAMMER_3X5);
		meta.setLore(new ArrayList<String>(){{add(HAMMER_MODIFIER_STRING);}});
		result_gold.setItemMeta(meta);
	}

	public Hammer35Recipe(String key, ItemStack result, RecipeShape shape, Ingredient... ingredients)
	{
		super(key, result, shape, ingredients);
	}
}
