package k4neps.toolmodifiers.crafting;

import k4neps.toolmodifiers.ToolModifiers;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.function.BiFunction;
import java.util.function.Function;

public class ToolRecipe
{
	protected static final Function<Material, Ingredient[]> INGREDIENTS = material -> new Ingredient[]{new Ingredient('B', material), new Ingredient('S', Material.STICK)};
	protected static final BiFunction<Material, Material, Ingredient[]> BIINGREDIENTS = (material1, material2) -> new Ingredient[]{new Ingredient('B', material1), new Ingredient('D', material2), new Ingredient('S', Material.STICK)};

	public NamespacedKey key;
	public ShapedRecipe recipe;

	public ToolRecipe(String key, ItemStack result, RecipeShape shape, Ingredient... ingredients)
	{
		this.key = new NamespacedKey(ToolModifiers.instance, key);
		recipe = new ShapedRecipe(this.key, result);
		recipe.shape(shape.shape);
		for(int i = 0; i < ingredients.length; i++)
			if(shape.containsKey(ingredients[i].key))
				recipe.setIngredient(ingredients[i].key, ingredients[i].mat);
	}

	public void add()
	{
		ToolModifiers.instance.getServer().addRecipe(recipe);
	}
}
