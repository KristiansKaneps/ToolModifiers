package k4neps.toolmodifiers.utils;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_19_R1.util.CraftMagicNumbers;
import org.bukkit.enchantments.Enchantment;
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
		catch (SecurityException | ClassNotFoundException | IllegalArgumentException | IllegalAccessException |
			   NoSuchFieldException e)
		{
			e.printStackTrace();
		}

		return 0.0f;
	}

	public static final Set<Material> pickaxeBlocks = new HashSet<>(Arrays.asList(
			Material.GRANITE_STAIRS,
			Material.GRANITE_WALL,
			Material.STONE_BRICK_WALL,
			Material.STONE_BRICKS,
			Material.STONE_STAIRS,
			Material.CHISELED_STONE_BRICKS,
			Material.CRACKED_STONE_BRICKS,
			Material.END_STONE_BRICK_SLAB,
			Material.END_STONE_BRICK_STAIRS,
			Material.END_STONE_BRICK_WALL,
			Material.STONECUTTER,
			Material.SMOOTH_STONE_SLAB,
			Material.MOSSY_STONE_BRICK_SLAB,
			Material.MOSSY_COBBLESTONE_SLAB,
			Material.MOSSY_COBBLESTONE_STAIRS,
			Material.MOSSY_STONE_BRICK_STAIRS,
			Material.MOSSY_STONE_BRICK_WALL,
			Material.MOSSY_STONE_BRICKS,
			Material.CHISELED_RED_SANDSTONE,
			Material.SANDSTONE_SLAB,
			Material.SANDSTONE_WALL,
			Material.CHISELED_SANDSTONE,
			Material.CUT_RED_SANDSTONE,
			Material.CUT_SANDSTONE,
			Material.CUT_RED_SANDSTONE_SLAB,
			Material.CUT_SANDSTONE_SLAB,
			Material.RED_SANDSTONE_SLAB,
			Material.RED_SANDSTONE_WALL,
			Material.SMOOTH_SANDSTONE_STAIRS,
			Material.SMOOTH_SANDSTONE_SLAB,
			Material.SMOOTH_RED_SANDSTONE_STAIRS,
			Material.SMOOTH_RED_SANDSTONE_SLAB,
			Material.REDSTONE_LAMP,
			Material.COBBLESTONE_SLAB,
			Material.GRINDSTONE,
			Material.STONE,
			Material.STONE_BUTTON,
			Material.STONE_PRESSURE_PLATE,
			Material.STONE_SLAB,
			Material.STONE_BRICK_SLAB,
			Material.COBBLESTONE,
			Material.COBBLESTONE_WALL,
			Material.MOSSY_COBBLESTONE_WALL,
			Material.COBBLESTONE_STAIRS,
			Material.MOSSY_COBBLESTONE,
			Material.END_STONE,
			Material.END_STONE_BRICKS,
			Material.SANDSTONE,
			Material.GLOWSTONE,
			Material.RED_SANDSTONE,
			Material.SANDSTONE_STAIRS,
			Material.COAL_ORE,
			Material.DIAMOND_ORE,
			Material.EMERALD_ORE,
			Material.GOLD_ORE,
			Material.IRON_ORE,
			Material.LAPIS_ORE,
			Material.NETHER_QUARTZ_ORE,
			Material.REDSTONE_ORE,
			Material.RED_SANDSTONE_STAIRS,
			Material.REDSTONE_BLOCK,
			Material.COAL_BLOCK,
			Material.DIAMOND_BLOCK,
			Material.EMERALD_BLOCK,
			Material.GOLD_BLOCK,
			Material.IRON_BLOCK,
			Material.LAPIS_BLOCK,
			Material.PURPUR_BLOCK,
			Material.QUARTZ_BLOCK,
			Material.ANVIL,
			Material.ACTIVATOR_RAIL,
			Material.BEACON,
			Material.BONE_BLOCK,
			Material.BREWING_STAND,
			Material.BRICK,
			Material.BRICK_STAIRS,
			Material.SHULKER_BOX,
			Material.LIGHT_GRAY_SHULKER_BOX,
			Material.BLACK_SHULKER_BOX,
			Material.BLUE_SHULKER_BOX,
			Material.BROWN_SHULKER_BOX,
			Material.CYAN_SHULKER_BOX,
			Material.GRAY_SHULKER_BOX,
			Material.GREEN_SHULKER_BOX,
			Material.LIGHT_BLUE_SHULKER_BOX,
			Material.LIME_SHULKER_BOX,
			Material.MAGENTA_SHULKER_BOX,
			Material.ORANGE_SHULKER_BOX,
			Material.PINK_SHULKER_BOX,
			Material.PURPLE_SHULKER_BOX,
			Material.RED_SHULKER_BOX,
			Material.WHITE_SHULKER_BOX,
			Material.YELLOW_SHULKER_BOX,
			Material.DAYLIGHT_DETECTOR,
			Material.CAULDRON,
			Material.DETECTOR_RAIL,
			Material.DISPENSER,
			Material.ENCHANTING_TABLE,
			Material.END_CRYSTAL,
			Material.END_GATEWAY,
			Material.ENDER_CHEST,
			Material.FURNACE,
			Material.ICE,
			Material.FROSTED_ICE,
			Material.PACKED_ICE,
			Material.LEVER,
			Material.DROPPER,
			Material.FLOWER_POT,
			Material.HOPPER,
			Material.OBSIDIAN,
			Material.NETHER_BRICK,
			Material.NETHER_BRICK_STAIRS,
			Material.NETHER_BRICK_FENCE,
			Material.NETHERRACK,
			Material.RED_NETHER_BRICKS,
			Material.SPAWNER,
			Material.OBSERVER,
			Material.PISTON,
			Material.STICKY_PISTON,
			Material.POWERED_RAIL,
			Material.PRISMARINE,
			Material.PURPUR_PILLAR,
			Material.PURPUR_SLAB,
			Material.PURPUR_STAIRS,
			Material.QUARTZ_STAIRS,
			Material.SEA_LANTERN,
			Material.SMOOTH_QUARTZ,
			Material.SMOOTH_RED_SANDSTONE,
			Material.SMOOTH_SANDSTONE,
			Material.SMOOTH_STONE,
			Material.STONE_BRICK_STAIRS,
			Material.DARK_PRISMARINE_STAIRS,
			Material.PRISMARINE_BRICK_STAIRS,
			Material.PRISMARINE_STAIRS,
			Material.GLASS,
			Material.BLACK_STAINED_GLASS,
			Material.BLUE_STAINED_GLASS,
			Material.BROWN_STAINED_GLASS,
			Material.CYAN_STAINED_GLASS,
			Material.GRAY_STAINED_GLASS,
			Material.GREEN_STAINED_GLASS,
			Material.LIGHT_BLUE_STAINED_GLASS,
			Material.LIGHT_GRAY_STAINED_GLASS,
			Material.LIME_STAINED_GLASS,
			Material.MAGENTA_STAINED_GLASS,
			Material.ORANGE_STAINED_GLASS,
			Material.PINK_STAINED_GLASS,
			Material.PURPLE_STAINED_GLASS,
			Material.RED_STAINED_GLASS,
			Material.WHITE_STAINED_GLASS,
			Material.YELLOW_STAINED_GLASS,
			Material.GLASS_PANE,
			Material.BLACK_STAINED_GLASS_PANE,
			Material.BLUE_STAINED_GLASS_PANE,
			Material.BROWN_STAINED_GLASS_PANE,
			Material.CYAN_STAINED_GLASS_PANE,
			Material.GRAY_STAINED_GLASS_PANE,
			Material.GREEN_STAINED_GLASS_PANE,
			Material.LIGHT_BLUE_STAINED_GLASS_PANE,
			Material.LIGHT_GRAY_STAINED_GLASS_PANE,
			Material.LIME_STAINED_GLASS_PANE,
			Material.MAGENTA_STAINED_GLASS_PANE,
			Material.ORANGE_STAINED_GLASS_PANE,
			Material.PINK_STAINED_GLASS_PANE,
			Material.PURPLE_STAINED_GLASS_PANE,
			Material.RED_STAINED_GLASS_PANE,
			Material.WHITE_STAINED_GLASS_PANE,
			Material.YELLOW_STAINED_GLASS_PANE,
			Material.IRON_TRAPDOOR,
			Material.MAGMA_BLOCK,
			Material.BLACK_CONCRETE,
			Material.BLUE_CONCRETE,
			Material.BROWN_CONCRETE,
			Material.CYAN_CONCRETE,
			Material.GRAY_CONCRETE,
			Material.GREEN_CONCRETE,
			Material.LIGHT_BLUE_CONCRETE,
			Material.LIGHT_GRAY_CONCRETE,
			Material.LIME_CONCRETE,
			Material.MAGENTA_CONCRETE,
			Material.ORANGE_CONCRETE,
			Material.PINK_CONCRETE,
			Material.PURPLE_CONCRETE,
			Material.RED_CONCRETE,
			Material.WHITE_CONCRETE,
			Material.YELLOW_CONCRETE,
			Material.ANDESITE,
			Material.POLISHED_ANDESITE,
			Material.DIORITE,
			Material.POLISHED_DIORITE,
			Material.GRANITE,
			Material.POLISHED_GRANITE,
			Material.BRICK_SLAB,
			Material.BRICKS,
			Material.BRICK_WALL,
			Material.ANDESITE_SLAB,
			Material.DARK_PRISMARINE_SLAB,
			Material.GRANITE_SLAB,
			Material.NETHER_BRICK_SLAB,
			Material.PETRIFIED_OAK_SLAB,
			Material.POLISHED_ANDESITE_SLAB,
			Material.POLISHED_DIORITE_SLAB,
			Material.POLISHED_GRANITE_SLAB,
			Material.PRISMARINE_BRICK_SLAB,
			Material.PRISMARINE_SLAB,
			Material.QUARTZ_SLAB,
			Material.RED_NETHER_BRICK_SLAB,
			Material.SMOOTH_QUARTZ_SLAB,
			Material.QUARTZ_PILLAR,
			Material.CHISELED_QUARTZ_BLOCK,
			Material.SMOOTH_QUARTZ_STAIRS,
			Material.PRISMARINE_BRICKS,
			Material.PRISMARINE_CRYSTALS,
			Material.PRISMARINE_WALL,
			Material.DARK_PRISMARINE,
			Material.HEAVY_WEIGHTED_PRESSURE_PLATE,
			Material.LIGHT_WEIGHTED_PRESSURE_PLATE,
			Material.BLAST_FURNACE,
			Material.SMOKER,
			Material.BLACKSTONE,
			Material.BLACKSTONE_SLAB,
			Material.BLACKSTONE_STAIRS,
			Material.BLACKSTONE_WALL,
			Material.CHISELED_POLISHED_BLACKSTONE,
			Material.GILDED_BLACKSTONE,
			Material.POLISHED_BLACKSTONE,
			Material.CRACKED_POLISHED_BLACKSTONE_BRICKS,
			Material.POLISHED_BLACKSTONE_BRICK_SLAB,
			Material.POLISHED_BLACKSTONE_BRICK_STAIRS,
			Material.POLISHED_BLACKSTONE_BRICK_WALL,
			Material.POLISHED_BLACKSTONE_BRICKS,
			Material.POLISHED_BLACKSTONE_BUTTON,
			Material.POLISHED_BLACKSTONE_PRESSURE_PLATE,
			Material.POLISHED_BLACKSTONE_SLAB,
			Material.POLISHED_BLACKSTONE_STAIRS,
			Material.POLISHED_BLACKSTONE_WALL,
			Material.POLISHED_ANDESITE_STAIRS,
			Material.POLISHED_BASALT,
			Material.POLISHED_DIORITE_STAIRS,
			Material.POLISHED_GRANITE_STAIRS,
			Material.BASALT,
			Material.ANCIENT_DEBRIS,
			Material.RESPAWN_ANCHOR,
			Material.TERRACOTTA,
			Material.GREEN_TERRACOTTA,
			Material.BLACK_GLAZED_TERRACOTTA,
			Material.BLACK_TERRACOTTA,
			Material.BLUE_GLAZED_TERRACOTTA,
			Material.BLUE_TERRACOTTA,
			Material.BROWN_GLAZED_TERRACOTTA,
			Material.BROWN_TERRACOTTA,
			Material.CYAN_GLAZED_TERRACOTTA,
			Material.CYAN_TERRACOTTA,
			Material.GRAY_GLAZED_TERRACOTTA,
			Material.GRAY_TERRACOTTA,
			Material.GREEN_GLAZED_TERRACOTTA,
			Material.LIGHT_BLUE_GLAZED_TERRACOTTA,
			Material.LIGHT_BLUE_TERRACOTTA,
			Material.LIGHT_GRAY_GLAZED_TERRACOTTA,
			Material.LIGHT_GRAY_TERRACOTTA,
			Material.LIME_GLAZED_TERRACOTTA,
			Material.LIME_TERRACOTTA,
			Material.MAGENTA_GLAZED_TERRACOTTA,
			Material.MAGENTA_TERRACOTTA,
			Material.ORANGE_GLAZED_TERRACOTTA,
			Material.ORANGE_TERRACOTTA,
			Material.PINK_GLAZED_TERRACOTTA,
			Material.PINK_TERRACOTTA,
			Material.PURPLE_GLAZED_TERRACOTTA,
			Material.PURPLE_TERRACOTTA,
			Material.RED_GLAZED_TERRACOTTA,
			Material.RED_TERRACOTTA,
			Material.WHITE_GLAZED_TERRACOTTA,
			Material.WHITE_TERRACOTTA,
			Material.YELLOW_GLAZED_TERRACOTTA,
			Material.YELLOW_TERRACOTTA,
			Material.NETHER_BRICK_WALL,
			Material.NETHER_BRICKS,
			Material.NETHER_GOLD_ORE,
			Material.NETHER_PORTAL,
			Material.NETHERITE_BLOCK,
			Material.RED_NETHER_BRICK_WALL,
			Material.CHISELED_NETHER_BRICKS,
			Material.RED_NETHER_BRICK_STAIRS,
			Material.CRACKED_NETHER_BRICKS,
			Material.CRYING_OBSIDIAN,
			Material.CRIMSON_NYLIUM,
			Material.WARPED_NYLIUM,
			Material.ANDESITE_STAIRS,
			Material.DIORITE_STAIRS,
			Material.LODESTONE
	));
	public static final Set<Material> shovelBlocks = new HashSet<>(Arrays.asList(
			Material.DIRT,
			Material.GRASS_BLOCK,
			Material.COARSE_DIRT,
			Material.FARMLAND,
			Material.GRAVEL,
			Material.SAND,
			Material.MYCELIUM,
			Material.SOUL_SAND,
			Material.SNOW,
			Material.SNOW_BLOCK,
			Material.BLACK_CONCRETE_POWDER,
			Material.BLUE_CONCRETE_POWDER,
			Material.BROWN_CONCRETE_POWDER,
			Material.CYAN_CONCRETE_POWDER,
			Material.GRAY_CONCRETE_POWDER,
			Material.GREEN_CONCRETE_POWDER,
			Material.LIGHT_BLUE_CONCRETE_POWDER,
			Material.LIGHT_GRAY_CONCRETE_POWDER,
			Material.LIME_CONCRETE_POWDER,
			Material.MAGENTA_CONCRETE_POWDER,
			Material.ORANGE_CONCRETE_POWDER,
			Material.PINK_CONCRETE_POWDER,
			Material.PURPLE_CONCRETE_POWDER,
			Material.RED_CONCRETE_POWDER,
			Material.WHITE_CONCRETE_POWDER,
			Material.YELLOW_CONCRETE_POWDER,
			Material.CLAY,
			Material.RED_SAND,
			Material.SOUL_SOIL,
			Material.PODZOL,
			Material.DIRT_PATH,
			Material.ROOTED_DIRT
	));
	public static final Set<Material> axeBlocks = new HashSet<>(Arrays.asList(
			Material.DARK_OAK_WOOD,
			Material.STRIPPED_ACACIA_WOOD,
			Material.STRIPPED_BIRCH_WOOD,
			Material.STRIPPED_DARK_OAK_WOOD,
			Material.STRIPPED_JUNGLE_WOOD,
			Material.STRIPPED_OAK_WOOD,
			Material.STRIPPED_SPRUCE_WOOD,
			Material.ACACIA_WOOD,
			Material.BIRCH_WOOD,
			Material.JUNGLE_WOOD,
			Material.OAK_WOOD,
			Material.SPRUCE_WOOD,
			Material.BIRCH_STAIRS,
			Material.JUNGLE_STAIRS,
			Material.OAK_STAIRS,
			Material.SPRUCE_STAIRS,
			Material.BIRCH_FENCE,
			Material.BIRCH_FENCE_GATE,
			Material.JUNGLE_FENCE,
			Material.JUNGLE_FENCE_GATE,
			Material.ACACIA_FENCE,
			Material.ACACIA_FENCE_GATE,
			Material.ACACIA_STAIRS,
			Material.DARK_OAK_FENCE,
			Material.DARK_OAK_FENCE_GATE,
			Material.DARK_OAK_STAIRS,
			Material.SPRUCE_FENCE,
			Material.SPRUCE_FENCE_GATE,
			Material.DARK_OAK_DOOR,
			Material.ACACIA_DOOR,
			Material.BIRCH_DOOR,
			Material.JUNGLE_DOOR,
			Material.SPRUCE_DOOR,
			Material.OAK_TRAPDOOR,
			Material.ACACIA_TRAPDOOR,
			Material.BIRCH_TRAPDOOR,
			Material.DARK_OAK_TRAPDOOR,
			Material.JUNGLE_TRAPDOOR,
			Material.SPRUCE_TRAPDOOR,
			Material.OAK_DOOR,
			Material.MUSHROOM_STEM,
			Material.BROWN_MUSHROOM_BLOCK,
			Material.RED_MUSHROOM_BLOCK,
			Material.BOOKSHELF,
			Material.OAK_FENCE,
			Material.OAK_FENCE_GATE,
			Material.ACACIA_LOG,
			Material.BIRCH_LOG,
			Material.JUNGLE_LOG,
			Material.OAK_LOG,
			Material.SPRUCE_LOG,
			Material.CHEST,
			Material.TRAPPED_CHEST,
			Material.PUMPKIN,
			Material.MELON,
			Material.ACACIA_SLAB,
			Material.BIRCH_SLAB,
			Material.DARK_OAK_SLAB,
			Material.DIORITE_SLAB,
			Material.JUNGLE_SLAB,
			Material.OAK_SLAB,
			Material.SPRUCE_SLAB,
			Material.DARK_OAK_BUTTON,
			Material.ACACIA_BUTTON,
			Material.BIRCH_BUTTON,
			Material.JUNGLE_BUTTON,
			Material.OAK_BUTTON,
			Material.SPRUCE_BUTTON,
			Material.DARK_OAK_LOG,
			Material.DARK_OAK_PLANKS,
			Material.DARK_OAK_PRESSURE_PLATE,
			Material.DARK_OAK_SIGN,
			Material.DARK_OAK_WALL_SIGN,
			Material.ACACIA_PRESSURE_PLATE,
			Material.BIRCH_PRESSURE_PLATE,
			Material.JUNGLE_PRESSURE_PLATE,
			Material.OAK_PRESSURE_PLATE,
			Material.SPRUCE_PRESSURE_PLATE,
			Material.STRIPPED_DARK_OAK_LOG,
			Material.STRIPPED_ACACIA_LOG,
			Material.STRIPPED_BIRCH_LOG,
			Material.STRIPPED_JUNGLE_LOG,
			Material.STRIPPED_OAK_LOG,
			Material.STRIPPED_SPRUCE_LOG,
			Material.CRIMSON_FENCE,
			Material.WARPED_FENCE,
			Material.CRIMSON_FENCE_GATE,
			Material.WARPED_FENCE_GATE,
			Material.CRIMSON_BUTTON,
			Material.CRIMSON_DOOR,
			Material.CRIMSON_PLANKS,
			Material.CRIMSON_PRESSURE_PLATE,
			Material.CRIMSON_SIGN,
			Material.CRIMSON_SLAB,
			Material.CRIMSON_STAIRS,
			Material.CRIMSON_TRAPDOOR,
			Material.CRIMSON_WALL_SIGN,
			Material.WARPED_BUTTON,
			Material.WARPED_DOOR,
			Material.WARPED_PLANKS,
			Material.WARPED_PRESSURE_PLATE,
			Material.WARPED_SIGN,
			Material.WARPED_SLAB,
			Material.WARPED_STAIRS,
			Material.WARPED_TRAPDOOR,
			Material.WARPED_WALL_SIGN,
			Material.CRIMSON_STEM,
			Material.WARPED_STEM,
			Material.STRIPPED_CRIMSON_STEM,
			Material.STRIPPED_WARPED_STEM,
			Material.CRIMSON_HYPHAE,
			Material.WARPED_HYPHAE,
			Material.STRIPPED_CRIMSON_HYPHAE,
			Material.STRIPPED_WARPED_HYPHAE
	));

	public static final Set<Material> leaves = new HashSet<>(Arrays.asList(
			Material.ACACIA_LEAVES,
			Material.BIRCH_LEAVES,
			Material.DARK_OAK_LEAVES,
			Material.JUNGLE_LEAVES,
			Material.OAK_LEAVES,
			Material.SPRUCE_LEAVES,
			Material.AZALEA_LEAVES,
			Material.FLOWERING_AZALEA_LEAVES,
			Material.MANGROVE_LEAVES
	));

	public static final Set<Material> woodLogs = new HashSet<>(Arrays.asList(
			Material.ACACIA_LOG,
			Material.BIRCH_LOG,
			Material.DARK_OAK_LOG,
			Material.JUNGLE_LOG,
			Material.OAK_LOG,
			Material.SPRUCE_LOG,
			Material.STRIPPED_ACACIA_LOG,
			Material.STRIPPED_BIRCH_LOG,
			Material.STRIPPED_DARK_OAK_LOG,
			Material.STRIPPED_JUNGLE_LOG,
			Material.STRIPPED_OAK_LOG,
			Material.STRIPPED_SPRUCE_LOG,
			Material.CRIMSON_STEM,
			Material.WARPED_STEM,
			Material.STRIPPED_CRIMSON_STEM,
			Material.STRIPPED_WARPED_STEM,
			Material.CRIMSON_HYPHAE,
			Material.WARPED_HYPHAE,
			Material.STRIPPED_CRIMSON_HYPHAE,
			Material.STRIPPED_WARPED_HYPHAE,
			Material.MANGROVE_LOG,
			Material.STRIPPED_MANGROVE_LOG
	));

	public static final Set<Material> fortuneOres = new HashSet<>(Arrays.asList(
			Material.COAL_ORE,
			Material.DIAMOND_ORE,
			Material.EMERALD_ORE,
			Material.LAPIS_ORE,
			Material.NETHER_GOLD_ORE,
			Material.NETHER_QUARTZ_ORE,
			Material.REDSTONE_ORE,
			Material.DEEPSLATE_COAL_ORE,
			Material.DEEPSLATE_DIAMOND_ORE,
			Material.DEEPSLATE_EMERALD_ORE,
			Material.DEEPSLATE_LAPIS_ORE,
			Material.DEEPSLATE_REDSTONE_ORE
	));

	private static net.minecraft.world.level.block.Block getNmsBlock(Material material)
	{
		return CraftMagicNumbers.getBlock(material);
	}

	private static net.minecraft.world.level.block.state.IBlockData getNmsBlockData(Material material)
	{
		return getNmsBlock(material).m();
	}

	private static net.minecraft.world.level.material.Material getNmsBlockMaterial(Material material)
	{
		return getNmsBlockData(material).d();
	}

	private static final Set<net.minecraft.world.level.material.Material> pickaxeMaterials =
			Collections.unmodifiableSet(
			new HashSet<>(Arrays.asList(
					net.minecraft.world.level.material.Material.r, // BUILDABLE_GLASS
					net.minecraft.world.level.material.Material.v, // ICE_SOLID
					net.minecraft.world.level.material.Material.G, // GLASS
					net.minecraft.world.level.material.Material.H, // ICE
					net.minecraft.world.level.material.Material.J, // STONE
					net.minecraft.world.level.material.Material.K, // METAL
					net.minecraft.world.level.material.Material.M, // HEAVY_METAL
					net.minecraft.world.level.material.Material.P, // MOSS
					net.minecraft.world.level.material.Material.T // AMETHYST
			)));

	private static final Set<net.minecraft.world.level.material.Material> shovelMaterials =
			Collections.unmodifiableSet(
			new HashSet<>(Arrays.asList(
					net.minecraft.world.level.material.Material.m, // TOP_SNOW
					net.minecraft.world.level.material.Material.s, // CLAY
					net.minecraft.world.level.material.Material.t, // DIRT
					net.minecraft.world.level.material.Material.u, // GRASS
					net.minecraft.world.level.material.Material.w, // SAND
					net.minecraft.world.level.material.Material.L, // SNOW
					net.minecraft.world.level.material.Material.U // POWDER_SNOW
			)));

	private static final Set<net.minecraft.world.level.material.Material> axeMaterials =
			Collections.unmodifiableSet(new HashSet<>(
			Arrays.asList(
					net.minecraft.world.level.material.Material.z, // WOOD
					net.minecraft.world.level.material.Material.A, // NETHER_WOOD
					net.minecraft.world.level.material.Material.C // BAMBOO
			)));

	public static boolean isPickaxeBlock(Block block)
	{
		final Material material = block.getType();
		net.minecraft.world.level.material.Material nmsMaterial = getNmsBlockMaterial(material);
		return pickaxeMaterials.contains(nmsMaterial) || pickaxeBlocks.contains(material);
	}

	public static boolean isShovelBlock(Block block)
	{
		final Material material = block.getType();
		net.minecraft.world.level.material.Material nmsMaterial = getNmsBlockMaterial(material);
		return shovelMaterials.contains(nmsMaterial) || shovelBlocks.contains(material);
	}

	public static boolean isAxeBlock(Block block)
	{
		final Material material = block.getType();
		net.minecraft.world.level.material.Material nmsMaterial = getNmsBlockMaterial(material);
		return axeMaterials.contains(nmsMaterial) || axeBlocks.contains(material);
	}

	public static boolean isLeaves(Block block)
	{
		return leaves.contains(block.getType());
	}

	public static boolean isWoodLog(Block block)
	{
		return woodLogs.contains(block.getType());
	}

	public static boolean isFortuneOre(Block block)
	{
		return fortuneOres.contains(block.getType());
	}

	public static Collection<ItemStack> getDrops(Block block, ItemStack tool)
	{
		Map<Enchantment, Integer> enchantments = tool.getEnchantments();

		boolean silkTouchFound = enchantments.containsKey(Enchantment.SILK_TOUCH);
		boolean fortuneFound = enchantments.containsKey(Enchantment.LOOT_BONUS_BLOCKS);

		Collection<ItemStack> toReturn;

		if (silkTouchFound)
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
		else if (fortuneFound && isFortuneOre(block))
		{
			int baseDrop = 1;
			int bonus = Math.round(ThreadLocalRandom.current()
													.nextFloat() * (enchantments.get(Enchantment.LOOT_BONUS_BLOCKS) + 2) - 0.1f) - 1;
			if (bonus < 0) bonus = 0;

			ItemStack toDrop = block.getDrops().toArray(new ItemStack[0])[0];
			int quantity;

			switch (block.getType())
			{
				case REDSTONE_ORE:
					baseDrop = 4 + ThreadLocalRandom.current().nextInt(2);
					quantity = baseDrop + bonus;
					break;
				case LAPIS_ORE:
					baseDrop = 6 + ThreadLocalRandom.current().nextInt(3);
					quantity = baseDrop + bonus;
					break;
				case NETHER_GOLD_ORE:
					baseDrop = 4 + ThreadLocalRandom.current().nextInt(3);
					quantity = baseDrop + bonus;
					break;
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
		expRandMap.put(Material.NETHER_GOLD_ORE, new Bounds(2, 5));
		expRandMap.put(Material.DEEPSLATE_COAL_ORE, new Bounds(0, 2));
		expRandMap.put(Material.DEEPSLATE_DIAMOND_ORE, new Bounds(3, 7));
		expRandMap.put(Material.DEEPSLATE_EMERALD_ORE, new Bounds(3, 7));
		expRandMap.put(Material.DEEPSLATE_LAPIS_ORE, new Bounds(2, 5));
		expRandMap.put(Material.DEEPSLATE_REDSTONE_ORE, new Bounds(1, 5));
	}

	public static int getExpDrop(Block block)
	{
		Material type = block.getType();
		if (!expRandMap.containsKey(type)) return 0;
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
