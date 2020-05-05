package k4neps.toolmodifiers.crafting;

import org.bukkit.Material;

public class Ingredient
{
	public final char key;
	public final Material mat;

	public Ingredient(char key, Material mat)
	{
		this.key = key;
		this.mat = mat;
	}
}
