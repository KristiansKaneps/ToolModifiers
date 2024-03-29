package k4neps.toolmodifiers.crafting.excavator;

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
public class ExcavatorRecipe extends ToolRecipe
{
	public static final RecipeShape EXCAVATOR_SHAPE = new RecipeShape("B", "S", "S");
	public static final String EXCAVATOR_MODIFIER_STRING = ChatColor.AQUA + "Excavator";

	public static final ItemStack result_diamond = new ItemStack(Material.DIAMOND_SHOVEL);
	public static final ItemStack result_iron = new ItemStack(Material.IRON_SHOVEL);
	public static final ItemStack result_gold = new ItemStack(Material.GOLDEN_SHOVEL);
	public static final ItemStack result_netherite = new ItemStack(Material.NETHERITE_SHOVEL);

	static
	{
		ItemMeta meta;

		meta = result_diamond.getItemMeta();
		meta.setDisplayName(Lang.DIAMOND.EXCAVATOR);
		meta.setLore(new ArrayList<String>(){{add(EXCAVATOR_MODIFIER_STRING);}});
		result_diamond.setItemMeta(meta);

		meta = result_iron.getItemMeta();
		meta.setDisplayName(Lang.IRON.EXCAVATOR);
		meta.setLore(new ArrayList<String>(){{add(EXCAVATOR_MODIFIER_STRING);}});
		result_iron.setItemMeta(meta);

		meta = result_gold.getItemMeta();
		meta.setDisplayName(Lang.GOLD.EXCAVATOR);
		meta.setLore(new ArrayList<String>(){{add(EXCAVATOR_MODIFIER_STRING);}});
		result_gold.setItemMeta(meta);

		meta = result_netherite.getItemMeta();
		meta.setDisplayName(Lang.NETHERITE.EXCAVATOR);
		meta.setLore(new ArrayList<String>(){{add(EXCAVATOR_MODIFIER_STRING);}});
		result_netherite.setItemMeta(meta);
	}

	public ExcavatorRecipe(String key, ItemStack result, RecipeShape shape, Ingredient... ingredients)
	{
		super(key, result, shape, ingredients);
	}
}
