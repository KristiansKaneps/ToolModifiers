package k4neps.toolmodifiers.crafting.cleaver;

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
 * Created by Kristians on 12/05/2022.
 *
 * @author Kristians
 */
public class CleaverRecipe extends ToolRecipe
{
	public static final RecipeShape CLEAVER_SHAPE = new RecipeShape("B", "B", "S");
	public static final String CLEAVER_MODIFIER_STRING = ChatColor.AQUA + "Cleaver";

	public static final ItemStack result_diamond = new ItemStack(Material.DIAMOND_SWORD);
	public static final ItemStack result_iron = new ItemStack(Material.IRON_SWORD);
	public static final ItemStack result_gold = new ItemStack(Material.GOLDEN_SWORD);
	public static final ItemStack result_netherite = new ItemStack(Material.NETHERITE_SWORD);

	static
	{
		ItemMeta meta;

		meta = result_diamond.getItemMeta();
		meta.setDisplayName(Lang.DIAMOND.CLEAVER);
		meta.setLore(new ArrayList<String>(){{add(CLEAVER_MODIFIER_STRING);}});
		result_diamond.setItemMeta(meta);

		meta = result_iron.getItemMeta();
		meta.setDisplayName(Lang.IRON.CLEAVER);
		meta.setLore(new ArrayList<String>(){{add(CLEAVER_MODIFIER_STRING);}});
		result_iron.setItemMeta(meta);

		meta = result_gold.getItemMeta();
		meta.setDisplayName(Lang.GOLD.CLEAVER);
		meta.setLore(new ArrayList<String>(){{add(CLEAVER_MODIFIER_STRING);}});
		result_gold.setItemMeta(meta);

		meta = result_netherite.getItemMeta();
		meta.setDisplayName(Lang.NETHERITE.CLEAVER);
		meta.setLore(new ArrayList<String>(){{add(CLEAVER_MODIFIER_STRING);}});
		result_netherite.setItemMeta(meta);
	}

	public CleaverRecipe(String key, ItemStack result, RecipeShape shape, Ingredient... ingredients)
	{
		super(key, result, shape, ingredients);
	}
}
