package k4neps.toolmodifiers.utils;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Kristians on 7/28/2017.
 *
 * @author Kristians
 */
public final class BlockUtils
{
	private BlockUtils() {}

	public static float getHardness(Block block)
	{
		try
		{
			Object nmsBlock = ReflectionUtils.getNMSBlock(block);
			Field field = ReflectionUtils.getNMSClass("Block").getDeclaredField("strength");
			field.setAccessible(true);
			return field.getFloat(nmsBlock);
		}
		catch (SecurityException | ClassNotFoundException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException e)
		{
			e.printStackTrace();
		}

		return 0.0f;
	}

	public static boolean canMineBlock(Block what, ItemStack with)
	{
		Material type = what.getType();
		if(!type.isBlock()) return false;

		switch(type)
		{
			case DIRT:
			case GRASS_BLOCK:
			case COARSE_DIRT:
			case FARMLAND:
			case GRASS_PATH:
			case GRAVEL:
			case SAND:
			case MYCELIUM:
			case SOUL_SAND:
			case SNOW:
			case SNOW_BLOCK:
			case BLACK_CONCRETE_POWDER:
			case BLUE_CONCRETE_POWDER:
			case BROWN_CONCRETE_POWDER:
			case CYAN_CONCRETE_POWDER:
			case GRAY_CONCRETE_POWDER:
			case GREEN_CONCRETE_POWDER:
			case LIGHT_BLUE_CONCRETE_POWDER:
			case LIGHT_GRAY_CONCRETE_POWDER:
			case LIME_CONCRETE_POWDER:
			case MAGENTA_CONCRETE_POWDER:
			case ORANGE_CONCRETE_POWDER:
			case PINK_CONCRETE_POWDER:
			case PURPLE_CONCRETE_POWDER:
			case RED_CONCRETE_POWDER:
			case WHITE_CONCRETE_POWDER:
			case YELLOW_CONCRETE_POWDER:
			case CLAY:
			case RED_SAND:
				return isShovel(with);
			case STONE_BRICK_WALL:
			case STONE_BRICKS:
			case STONE_STAIRS:
			case CHISELED_STONE_BRICKS:
			case CRACKED_STONE_BRICKS:
			case END_STONE_BRICK_SLAB:
			case END_STONE_BRICK_STAIRS:
			case END_STONE_BRICK_WALL:
			case STONECUTTER:
			case SMOOTH_STONE_SLAB:
			case MOSSY_STONE_BRICK_SLAB:
			case MOSSY_COBBLESTONE_SLAB:
			case MOSSY_COBBLESTONE_STAIRS:
			case MOSSY_STONE_BRICK_STAIRS:
			case MOSSY_STONE_BRICK_WALL:
			case MOSSY_STONE_BRICKS:
			case CHISELED_RED_SANDSTONE:
			case SANDSTONE_SLAB:
			case SANDSTONE_WALL:
			case CHISELED_SANDSTONE:
			case CUT_RED_SANDSTONE:
			case CUT_SANDSTONE:
			case CUT_RED_SANDSTONE_SLAB:
			case CUT_SANDSTONE_SLAB:
			case RED_SANDSTONE_SLAB:
			case RED_SANDSTONE_WALL:
			case SMOOTH_SANDSTONE_STAIRS:
			case SMOOTH_SANDSTONE_SLAB:
			case SMOOTH_RED_SANDSTONE_STAIRS:
			case SMOOTH_RED_SANDSTONE_SLAB:
			case REDSTONE_LAMP:
			case COBBLESTONE_SLAB:
			case GRINDSTONE:
			case STONE:
			case STONE_BUTTON:
			case STONE_PRESSURE_PLATE:
			case STONE_SLAB:
			case STONE_BRICK_SLAB:
			case COBBLESTONE:
			case COBBLESTONE_WALL:
			case MOSSY_COBBLESTONE_WALL:
			case COBBLESTONE_STAIRS:
			case MOSSY_COBBLESTONE:
			case END_STONE:
			case END_STONE_BRICKS:
			case SANDSTONE:
			case GLOWSTONE:
			case RED_SANDSTONE:
			case SANDSTONE_STAIRS:
			case COAL_ORE:
			case DIAMOND_ORE:
			case EMERALD_ORE:
			case GOLD_ORE:
			case IRON_ORE:
			case LAPIS_ORE:
			case NETHER_QUARTZ_ORE:
			case REDSTONE_ORE:
			case RED_SANDSTONE_STAIRS:
			case REDSTONE_BLOCK:
			case COAL_BLOCK:
			case DIAMOND_BLOCK:
			case EMERALD_BLOCK:
			case GOLD_BLOCK:
			case IRON_BLOCK:
			case LAPIS_BLOCK:
			case PURPUR_BLOCK:
			case QUARTZ_BLOCK:
			case ANVIL:
			case ACTIVATOR_RAIL:
			case BEACON:
			case BONE_BLOCK:
			case BREWING_STAND:
			case BRICK:
			case BRICK_STAIRS:
			case SHULKER_BOX:
			case LIGHT_GRAY_SHULKER_BOX:
			case BLACK_SHULKER_BOX:
			case BLUE_SHULKER_BOX:
			case BROWN_SHULKER_BOX:
			case CYAN_SHULKER_BOX:
			case GRAY_SHULKER_BOX:
			case GREEN_SHULKER_BOX:
			case LIGHT_BLUE_SHULKER_BOX:
			case LIME_SHULKER_BOX:
			case MAGENTA_SHULKER_BOX:
			case ORANGE_SHULKER_BOX:
			case PINK_SHULKER_BOX:
			case PURPLE_SHULKER_BOX:
			case RED_SHULKER_BOX:
			case WHITE_SHULKER_BOX:
			case YELLOW_SHULKER_BOX:
			case DAYLIGHT_DETECTOR:
			case CAULDRON:
			case DETECTOR_RAIL:
			case DISPENSER:
			case ENCHANTING_TABLE:
			case END_CRYSTAL:
			case END_GATEWAY:
			case ENDER_CHEST:
			case FURNACE:
			case ICE:
			case FROSTED_ICE:
			case PACKED_ICE:
			case LEVER:
			case DROPPER:
			case FLOWER_POT:
			case HOPPER:
			case OBSIDIAN:
			case NETHER_BRICK:
			case NETHER_BRICK_STAIRS:
			case NETHER_BRICK_FENCE:
			case NETHERRACK:
			case RED_NETHER_BRICKS:
			case SPAWNER:
			case OBSERVER:
			case PISTON:
			case STICKY_PISTON:
			case POWERED_RAIL:
			case PRISMARINE:
			case PURPUR_PILLAR:
			case PURPUR_SLAB:
			case PURPUR_STAIRS:
			case QUARTZ_STAIRS:
			case SEA_LANTERN:
			case SMOOTH_QUARTZ:
			case SMOOTH_RED_SANDSTONE:
			case SMOOTH_SANDSTONE:
			case SMOOTH_STONE:
			case STONE_BRICK_STAIRS:
			case DARK_PRISMARINE_STAIRS:
			case PRISMARINE_BRICK_STAIRS:
			case PRISMARINE_STAIRS:
			case GLASS:
			case BLACK_STAINED_GLASS:
			case BLUE_STAINED_GLASS:
			case BROWN_STAINED_GLASS:
			case CYAN_STAINED_GLASS:
			case GRAY_STAINED_GLASS:
			case GREEN_STAINED_GLASS:
			case LIGHT_BLUE_STAINED_GLASS:
			case LIGHT_GRAY_STAINED_GLASS:
			case LIME_STAINED_GLASS:
			case MAGENTA_STAINED_GLASS:
			case ORANGE_STAINED_GLASS:
			case PINK_STAINED_GLASS:
			case PURPLE_STAINED_GLASS:
			case RED_STAINED_GLASS:
			case WHITE_STAINED_GLASS:
			case YELLOW_STAINED_GLASS:
			case GLASS_PANE:
			case BLACK_STAINED_GLASS_PANE:
			case BLUE_STAINED_GLASS_PANE:
			case BROWN_STAINED_GLASS_PANE:
			case CYAN_STAINED_GLASS_PANE:
			case GRAY_STAINED_GLASS_PANE:
			case GREEN_STAINED_GLASS_PANE:
			case LIGHT_BLUE_STAINED_GLASS_PANE:
			case LIGHT_GRAY_STAINED_GLASS_PANE:
			case LIME_STAINED_GLASS_PANE:
			case MAGENTA_STAINED_GLASS_PANE:
			case ORANGE_STAINED_GLASS_PANE:
			case PINK_STAINED_GLASS_PANE:
			case PURPLE_STAINED_GLASS_PANE:
			case RED_STAINED_GLASS_PANE:
			case WHITE_STAINED_GLASS_PANE:
			case YELLOW_STAINED_GLASS_PANE:
			case IRON_TRAPDOOR:
			case MAGMA_BLOCK:
			case BLACK_CONCRETE:
			case BLUE_CONCRETE:
			case BROWN_CONCRETE:
			case CYAN_CONCRETE:
			case GRAY_CONCRETE:
			case GREEN_CONCRETE:
			case LIGHT_BLUE_CONCRETE:
			case LIGHT_GRAY_CONCRETE:
			case LIME_CONCRETE:
			case MAGENTA_CONCRETE:
			case ORANGE_CONCRETE:
			case PINK_CONCRETE:
			case PURPLE_CONCRETE:
			case RED_CONCRETE:
			case WHITE_CONCRETE:
			case YELLOW_CONCRETE:
			case ANDESITE:
			case POLISHED_ANDESITE:
			case DIORITE:
			case POLISHED_DIORITE:
			case GRANITE:
			case POLISHED_GRANITE:
			case BRICK_SLAB:
			case BRICKS:
			case BRICK_WALL:
			case ANDESITE_SLAB:
			case DARK_PRISMARINE_SLAB:
			case GRANITE_SLAB:
			case NETHER_BRICK_SLAB:
			case PETRIFIED_OAK_SLAB:
			case POLISHED_ANDESITE_SLAB:
			case POLISHED_DIORITE_SLAB:
			case POLISHED_GRANITE_SLAB:
			case PRISMARINE_BRICK_SLAB:
			case PRISMARINE_SLAB:
			case QUARTZ_SLAB:
			case RED_NETHER_BRICK_SLAB:
			case SMOOTH_QUARTZ_SLAB:
			case QUARTZ_PILLAR:
			case CHISELED_QUARTZ_BLOCK:
			case SMOOTH_QUARTZ_STAIRS:
			case PRISMARINE_BRICKS:
			case PRISMARINE_CRYSTALS:
			case PRISMARINE_WALL:
			case DARK_PRISMARINE:
			case HEAVY_WEIGHTED_PRESSURE_PLATE:
			case LIGHT_WEIGHTED_PRESSURE_PLATE:
			case BLAST_FURNACE:
			case SMOKER:
				return isPickaxe(with);
			case DARK_OAK_WOOD:
			case STRIPPED_ACACIA_WOOD:
			case STRIPPED_BIRCH_WOOD:
			case STRIPPED_DARK_OAK_WOOD:
			case STRIPPED_JUNGLE_WOOD:
			case STRIPPED_OAK_WOOD:
			case STRIPPED_SPRUCE_WOOD:
			case ACACIA_WOOD:
			case BIRCH_WOOD:
			case JUNGLE_WOOD:
			case OAK_WOOD:
			case SPRUCE_WOOD:
			case BIRCH_STAIRS:
			case JUNGLE_STAIRS:
			case OAK_STAIRS:
			case SPRUCE_STAIRS:
			case BIRCH_FENCE:
			case BIRCH_FENCE_GATE:
			case JUNGLE_FENCE:
			case JUNGLE_FENCE_GATE:
			case ACACIA_FENCE:
			case ACACIA_FENCE_GATE:
			case ACACIA_STAIRS:
			case DARK_OAK_FENCE:
			case DARK_OAK_FENCE_GATE:
			case DARK_OAK_STAIRS:
			case SPRUCE_FENCE:
			case SPRUCE_FENCE_GATE:
			case DARK_OAK_DOOR:
			case ACACIA_DOOR:
			case BIRCH_DOOR:
			case JUNGLE_DOOR:
			case SPRUCE_DOOR:
			case OAK_TRAPDOOR:
			case ACACIA_TRAPDOOR:
			case BIRCH_TRAPDOOR:
			case DARK_OAK_TRAPDOOR:
			case JUNGLE_TRAPDOOR:
			case SPRUCE_TRAPDOOR:
			case OAK_DOOR:
			case MUSHROOM_STEM:
			case BROWN_MUSHROOM_BLOCK:
			case RED_MUSHROOM_BLOCK:
			case BOOKSHELF:
			case OAK_FENCE:
			case OAK_FENCE_GATE:
			case ACACIA_LOG:
			case BIRCH_LOG:
			case JUNGLE_LOG:
			case OAK_LOG:
			case SPRUCE_LOG:
			case CHEST:
			case TRAPPED_CHEST:
			case PUMPKIN:
			case MELON:
			case ACACIA_SLAB:
			case BIRCH_SLAB:
			case DARK_OAK_SLAB:
			case DIORITE_SLAB:
			case JUNGLE_SLAB:
			case OAK_SLAB:
			case SPRUCE_SLAB:
			case DARK_OAK_BUTTON:
			case ACACIA_BUTTON:
			case BIRCH_BUTTON:
			case JUNGLE_BUTTON:
			case OAK_BUTTON:
			case SPRUCE_BUTTON:
			case DARK_OAK_LOG:
			case DARK_OAK_PLANKS:
			case DARK_OAK_PRESSURE_PLATE:
			case DARK_OAK_SIGN:
			case DARK_OAK_WALL_SIGN:
			case ACACIA_PRESSURE_PLATE:
			case BIRCH_PRESSURE_PLATE:
			case JUNGLE_PRESSURE_PLATE:
			case OAK_PRESSURE_PLATE:
			case SPRUCE_PRESSURE_PLATE:
			case STRIPPED_DARK_OAK_LOG:
			case STRIPPED_ACACIA_LOG:
			case STRIPPED_BIRCH_LOG:
			case STRIPPED_JUNGLE_LOG:
			case STRIPPED_OAK_LOG:
			case STRIPPED_SPRUCE_LOG:
				return isAxe(with);
			default: return false;
		}
	}

	public static boolean isShovel(ItemStack item)
	{
		switch(item.getType())
		{
			case STONE_SHOVEL:
			case DIAMOND_SHOVEL:
			case GOLDEN_SHOVEL:
			case IRON_SHOVEL:
			case WOODEN_SHOVEL:
				return true;
			default: return false;
		}
	}

	public static boolean isPickaxe(ItemStack item)
	{
		switch(item.getType())
		{
			case DIAMOND_PICKAXE:
			case GOLDEN_PICKAXE:
			case IRON_PICKAXE:
			case STONE_PICKAXE:
			case WOODEN_PICKAXE:
				return true;
			default: return false;
		}
	}

	public static boolean isAxe(ItemStack item)
	{
		switch(item.getType())
		{
			case DIAMOND_AXE:
			case GOLDEN_AXE:
			case IRON_AXE:
			case STONE_AXE:
			case WOODEN_AXE:
				return true;
			default: return false;
		}
	}

	public static boolean isHoe(ItemStack item)
	{
		switch(item.getType())
		{
			case DIAMOND_HOE:
			case GOLDEN_HOE:
			case IRON_HOE:
			case STONE_HOE:
			case WOODEN_HOE:
				return true;
			default: return false;
		}
	}

	public static boolean isLeaves(Block block)
	{
		switch(block.getType())
		{
			case ACACIA_LEAVES:
			case BIRCH_LEAVES:
			case DARK_OAK_LEAVES:
			case JUNGLE_LEAVES:
			case OAK_LEAVES:
			case SPRUCE_LEAVES:
				return true;
			default: return false;
		}
	}

	public static boolean isWoodLog(Block block)
	{
		switch(block.getType())
		{
			case ACACIA_LOG:
			case BIRCH_LOG:
			case DARK_OAK_LOG:
			case JUNGLE_LOG:
			case OAK_LOG:
			case SPRUCE_LOG:
			case STRIPPED_ACACIA_LOG:
			case STRIPPED_BIRCH_LOG:
			case STRIPPED_DARK_OAK_LOG:
			case STRIPPED_JUNGLE_LOG:
			case STRIPPED_OAK_LOG:
			case STRIPPED_SPRUCE_LOG:
				return true;
			default: return false;
		}
	}

	public static boolean isFortuneOre(Block block)
	{
		switch(block.getType())
		{
			case COAL_ORE:
			case DIAMOND_ORE:
			case EMERALD_ORE:
			case LAPIS_ORE:
			case NETHER_QUARTZ_ORE:
			case REDSTONE_ORE:
				return true;
			default: return false;
		}
	}

	public static Collection<ItemStack> getDrops(Block block, ItemStack tool)
	{
		Map<Enchantment, Integer> enchantments = tool.getEnchantments();

		boolean silkTouchFound = enchantments.containsKey(Enchantment.SILK_TOUCH);
		boolean fortuneFound = enchantments.containsKey(Enchantment.LOOT_BONUS_BLOCKS);

		Collection<ItemStack> toReturn;

		if(silkTouchFound)
		{
			toReturn = new HashSet<>();
			/*if(block.getState() instanceof InventoryHolder)
			{
				InventoryHolder invHolder = (InventoryHolder) block.getState();
				ItemStack[] contents = invHolder.getInventory().getContents();
				for(ItemStack stack : contents)
					if(stack != null && stack.getType() != Material.AIR) toReturn.add(stack);
			}*/
			toReturn.add(new ItemStack(block.getType(), 1));
		}
		else if(fortuneFound && isFortuneOre(block))
		{
			int baseDrop = 1;
			int bonus = (int) Math.round(ThreadLocalRandom.current().nextFloat() * (enchantments.get(Enchantment.LOOT_BONUS_BLOCKS) + 2) - 0.1f) - 1;
			if(bonus < 0) bonus = 0;

			ItemStack toDrop = block.getDrops().toArray(new ItemStack[0])[0];
			int quantity;

			switch(block.getType())
			{
				case REDSTONE_ORE:
					baseDrop = 4 + ThreadLocalRandom.current().nextInt(2);
					quantity = baseDrop + bonus;
					break;
				case LAPIS_ORE:
					baseDrop = 6 + ThreadLocalRandom.current().nextInt(3);
				default:
					quantity = baseDrop * (bonus + 1);
			}

			toDrop.setAmount(quantity);

			toReturn = new HashSet<>();
			toReturn.add(toDrop);
		}
		else
		{
			toReturn = block.getDrops(tool);
		}

		return toReturn;
	}

	private static final Map<Material, Bounds> expRandMap = new HashMap<>();
	static
	{
		expRandMap.put(Material.REDSTONE_ORE, new Bounds(1, 5));
		expRandMap.put(Material.COAL_ORE, new Bounds(0, 2));
		expRandMap.put(Material.DIAMOND_ORE, new Bounds(3, 7));
		expRandMap.put(Material.LAPIS_ORE, new Bounds(2, 5));
		expRandMap.put(Material.NETHER_QUARTZ_ORE, new Bounds(2, 5));
		expRandMap.put(Material.EMERALD_ORE, new Bounds(3, 7));
		expRandMap.put(Material.SPAWNER, new Bounds(15, 43));
	}

	public static int getExpDrop(Block block)
	{
		Material type = block.getType();
		if(!expRandMap.containsKey(type)) return 0;
		return expRandMap.get(type).rand();
	}

	private static class Bounds
	{
		public final int max;
		public final int min;

		private Bounds(int min /* including */, int max /* including */)
		{
			this.min = min;
			this.max = max;
		}

		public int rand()
		{
			return ThreadLocalRandom.current().nextInt(min, max + 1);
		}
	}
}
