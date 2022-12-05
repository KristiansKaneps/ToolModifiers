package k4neps.toolmodifiers.crafting;

public class RecipeShape
{
	public String[] shape;

	public RecipeShape()
	{
		this("", "", "");
	}

	public RecipeShape(String... shape)
	{
		this.shape = shape;
	}

	public void set(int row, String rowShape)
	{
		if(row > 2) row = 2;
		else if(row < 0) row = 0;

		shape[row] = rowShape == null ? "" : rowShape;
	}

	public boolean containsKey(char key)
	{
		return shape[0].contains(key + "") || shape[1].contains(key + "") || shape[2].contains(key + "");
	}
}
