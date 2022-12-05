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
		switch (item.getType())
		{
			case STONE_SHOVEL:
			case DIAMOND_SHOVEL:
			case NETHERITE_SHOVEL:
			case GOLDEN_SHOVEL:
			case IRON_SHOVEL:
			case WOODEN_SHOVEL:
				return true;
			default:
				return false;
		}
	}

	public static boolean isPickaxe(ItemStack item)
	{
		switch (item.getType())
		{
			case DIAMOND_PICKAXE:
			case NETHERITE_PICKAXE:
			case GOLDEN_PICKAXE:
			case IRON_PICKAXE:
			case STONE_PICKAXE:
			case WOODEN_PICKAXE:
				return true;
			default:
				return false;
		}
	}

	public static boolean isAxe(ItemStack item)
	{
		switch (item.getType())
		{
			case DIAMOND_AXE:
			case NETHERITE_AXE:
			case GOLDEN_AXE:
			case IRON_AXE:
			case STONE_AXE:
			case WOODEN_AXE:
				return true;
			default:
				return false;
		}
	}

	public static boolean isSword(ItemStack item)
	{
		switch (item.getType())
		{
			case DIAMOND_SWORD:
			case NETHERITE_SWORD:
			case GOLDEN_SWORD:
			case IRON_SWORD:
			case STONE_SWORD:
			case WOODEN_SWORD:
				return true;
			default:
				return false;
		}
	}

	public static boolean isHoe(ItemStack item)
	{
		switch (item.getType())
		{
			case DIAMOND_HOE:
			case NETHERITE_HOE:
			case GOLDEN_HOE:
			case IRON_HOE:
			case STONE_HOE:
			case WOODEN_HOE:
				return true;
			default:
				return false;
		}
	}
}
