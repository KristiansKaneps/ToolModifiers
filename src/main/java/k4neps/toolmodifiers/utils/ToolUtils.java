package k4neps.toolmodifiers.utils;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public final class ToolUtils
{
	private ToolUtils() {}

	public static boolean canMineBlock(Block block, ItemStack tool)
	{
		return (
				block.getType().isBlock() && (
						(isPickaxe(tool) && BlockUtils.isPickaxeBlock(block)) ||
						(isShovel(tool) && BlockUtils.isShovelBlock(block)) ||
						(isAxe(tool) && BlockUtils.isAxeBlock(block)) ||
						(isSword(tool) && BlockUtils.isSwordBlock(block))
				)
		);
	}

	public static boolean isShovel(ItemStack item)
	{
		if (item == null) return false;
		return switch (item.getType())
				{
					case STONE_SHOVEL, DIAMOND_SHOVEL, NETHERITE_SHOVEL, GOLDEN_SHOVEL, IRON_SHOVEL, WOODEN_SHOVEL ->
							true;
					default -> false;
				};
	}

	public static boolean isPickaxe(ItemStack item)
	{
		if (item == null) return false;
		return switch (item.getType())
				{
					case DIAMOND_PICKAXE, NETHERITE_PICKAXE, GOLDEN_PICKAXE, IRON_PICKAXE, STONE_PICKAXE,
							WOODEN_PICKAXE -> true;
					default -> false;
				};
	}

	public static boolean isAxe(ItemStack item)
	{
		if (item == null) return false;
		return switch (item.getType())
				{
					case DIAMOND_AXE, NETHERITE_AXE, GOLDEN_AXE, IRON_AXE, STONE_AXE, WOODEN_AXE -> true;
					default -> false;
				};
	}

	public static boolean isSword(ItemStack item)
	{
		if (item == null) return false;
		return switch (item.getType())
				{
					case DIAMOND_SWORD, NETHERITE_SWORD, GOLDEN_SWORD, IRON_SWORD, STONE_SWORD, WOODEN_SWORD -> true;
					default -> false;
				};
	}

	public static boolean isHoe(ItemStack item)
	{
		if (item == null) return false;
		return switch (item.getType())
				{
					case DIAMOND_HOE, NETHERITE_HOE, GOLDEN_HOE, IRON_HOE, STONE_HOE, WOODEN_HOE -> true;
					default -> false;
				};
	}
}
