package k4neps.toolmodifiers.events;

import k4neps.toolmodifiers.Lang;
import k4neps.toolmodifiers.PermCheck;
import k4neps.toolmodifiers.ToolModifiers;
import k4neps.toolmodifiers.crafting.excavator.ExcavatorRecipe;
import k4neps.toolmodifiers.crafting.hammer.Hammer35Recipe;
import k4neps.toolmodifiers.crafting.hammer.HammerRecipe;
import k4neps.toolmodifiers.crafting.lumberaxe.LumberaxeRecipe;
import k4neps.toolmodifiers.utils.Area;
import k4neps.toolmodifiers.utils.BlockUtils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;
import java.util.function.Function;

import static org.bukkit.block.BlockFace.*;

/**
 * Created by Kristians on 7/28/2017.
 *
 * @author Kristians
 */
public class Events implements Listener
{
	private final ToolModifiers tm;

	private final int maxSearch = 72;
	private final Function<Block, Boolean> checkIfLeavesF = b -> POSSIBLE_LEAF_TYPES.contains(b.getType());

	private final int lumberAxeRange;
	private final float hardnessThreshold;
	private final boolean lumberAxeDropsAtSrc;

	public Events(ToolModifiers tm)
	{
		this.tm = tm;

		hardnessThreshold = (float) tm.getConfig().getDouble("hardness_threshold");
		lumberAxeRange = tm.getConfig().getInt("lumberaxe.tree_branch_radius");
		lumberAxeDropsAtSrc = tm.getConfig().getBoolean("lumberaxe.drop_tree_logs_at_source");
	}

	private static final List<Material> validItems = Arrays.asList(
			Material.NETHERITE_AXE,
			Material.NETHERITE_PICKAXE,
			Material.NETHERITE_SHOVEL
	);

	@EventHandler(priority = EventPriority.NORMAL)
	public void onNetheriteUpgrade(InventoryClickEvent e)
	{
		if (e.getInventory().getType() != InventoryType.SMITHING
			|| e.getSlotType() != InventoryType.SlotType.RESULT
			|| e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR
			|| e.isCancelled())
			return;

		ItemStack clickedItem = e.getCurrentItem();

		ItemMeta meta = clickedItem.getItemMeta();

		if (!meta.hasDisplayName()) // modified tools are renamed
			return;

		boolean modify = false;
		for (String line : meta.getLore())
			if (
					HammerRecipe.HAMMER_MODIFIER_STRING.equals(line)
					|| Hammer35Recipe.HAMMER_MODIFIER_STRING.equals(line)
					|| ExcavatorRecipe.EXCAVATOR_MODIFIER_STRING.equals(line)
					|| LumberaxeRecipe.LUMBERAXE_MODIFIER_STRING.equals(line)
			)
			{
				modify = true;
				break;
			}

		if (!modify) return;

		String displayName = meta.getDisplayName();

		switch (clickedItem.getType())
		{
			case NETHERITE_AXE:
				clickedItem.setType(Material.NETHERITE_AXE);
				if (ChatColor.stripColor(displayName).trim()
							  .equals(ChatColor.stripColor(Lang.DIAMOND.LUMBERAXE).trim()))
					displayName = Lang.NETHERITE.LUMBERAXE;
				break;
			case NETHERITE_PICKAXE:
				clickedItem.setType(Material.NETHERITE_PICKAXE);
				if (meta.hasDisplayName())
				{
					if (
							meta.getLore().contains(HammerRecipe.HAMMER_MODIFIER_STRING)
							&& ChatColor.stripColor(displayName).trim()
										.equals(ChatColor.stripColor(Lang.DIAMOND.HAMMER).trim()))
						displayName = Lang.NETHERITE.HAMMER;
					else if (
							meta.getLore().contains(Hammer35Recipe.HAMMER_MODIFIER_STRING)
							&& ChatColor.stripColor(displayName).trim()
										.equals(ChatColor.stripColor(Lang.DIAMOND.HAMMER_3X5).trim()))
						displayName = Lang.NETHERITE.HAMMER_3X5;
				}
				break;
			case NETHERITE_SHOVEL:
				clickedItem.setType(Material.NETHERITE_SHOVEL);
				if (ChatColor.stripColor(displayName).trim()
							  .equals(ChatColor.stripColor(Lang.DIAMOND.EXCAVATOR).trim()))
					displayName = Lang.NETHERITE.EXCAVATOR;
				break;
			default:
				return;
		}

		meta.setDisplayName(displayName);

		clickedItem.setItemMeta(meta);
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onBlockBreak(BlockBreakEvent event)
	{
		if (event.isCancelled()) return;

		Player p = event.getPlayer();

		if (p.isSneaking()) return;

		ItemStack tool = p.getInventory().getItemInMainHand();

		if (tool.getType() == Material.AIR) return;
		if (!BlockUtils.isPickaxe(tool) && !BlockUtils.isShovel(tool) && !BlockUtils.isAxe(tool)) return;
		if (BlockUtils.isPickaxe(tool) && !PermCheck.canUseHammer(p)) return;
		if (BlockUtils.isShovel(tool) && !PermCheck.canUseExcavator(p)) return;
		if (BlockUtils.isAxe(tool) && !PermCheck.canUseLumberaxe(p)) return;

		ItemMeta meta = tool.getItemMeta();

		if (meta == null) return;

		List<String> lore = meta.getLore();

		if (lore == null || lore.size() < 1) return;

		Block block = event.getBlock();
		Location loc = block.getLocation();
		World world = loc.getWorld();

		if (world == null) return;

		if (block.getType() == Material.AIR || !BlockUtils.canMineBlock(block, tool)) return;

		if (PermCheck.canUseLumberaxeToCutTrees(p) && lore.contains(LumberaxeRecipe.LUMBERAXE_MODIFIER_STRING) && isTree(
				block))
		{
			List<Block> logs = getAllLogs(block, false);
			if (logs.size() >= 3)
			{
				for (Block log : logs)
				{
					if (Area.intersectsTerritory(log, p)) continue;
					if (p.getGameMode() == GameMode.SURVIVAL)
					{
						log.getDrops(tool).forEach(eLogIs -> {
							if (lumberAxeDropsAtSrc)
								world.dropItemNaturally(loc, eLogIs);
							else log.breakNaturally(tool);
						});
						log.setType(Material.AIR);
					}
					else log.setType(Material.AIR);
				}
				return;
			}
		}

		int modifiedTool = -1;

		if (lore.contains(Hammer35Recipe.HAMMER_MODIFIER_STRING))
			modifiedTool = 2;
		else if (lore.contains(HammerRecipe.HAMMER_MODIFIER_STRING) || lore.contains(ExcavatorRecipe.EXCAVATOR_MODIFIER_STRING) || lore
				.contains(LumberaxeRecipe.LUMBERAXE_MODIFIER_STRING))
			modifiedTool = 1;

		if (modifiedTool > 0)
		{
			Area area = new Area(block, p, modifiedTool == 2 ? Area.Size._3X5 : Area.Size._3X3);

			final float srcHardness = block.getType().getHardness();

			for (Block b : area.getBlocksAround())
			{
				if (b == null || b.getType() == Material.AIR || Area.intersectsTerritory(b, p) || b.getType()
																								   .getHardness() > srcHardness + hardnessThreshold || !BlockUtils
						.canMineBlock(b, tool))
					continue;

				if (p.getGameMode() == GameMode.SURVIVAL)
				{
					Collection<ItemStack> drops = BlockUtils.getDrops(b, tool);
					int exp = BlockUtils.getExpDrop(b);

					b.setType(Material.AIR);

					boolean isSilkTouch = tool.getEnchantments().containsKey(Enchantment.SILK_TOUCH);

					if (exp > 0 && !isSilkTouch)
						world.spawn(loc, ExperienceOrb.class).setExperience(exp);

					if (drops.size() > 0)
						for (ItemStack drop : drops)
							world.dropItemNaturally(loc, drop);

					continue;
				}

				b.setType(Material.AIR);
			}
		}
	}

	private boolean isTree(Block srcBlock)
	{
		if (!BlockUtils.isWoodLog(srcBlock)) return false;
		Location loc = srcBlock.getLocation();
		while (BlockUtils.isWoodLog(loc.getBlock())) loc.add(0, 1, 0);
		return BlockUtils.isLeaves(loc.getBlock());
	}

	/**
	 * Lumberaxe method.
	 */
	private List<Block> getAllLogs(Block srcBlock, boolean includeSrcBlock)
	{
		return getAllLogsRecursively(srcBlock, srcBlock, null, 0, includeSrcBlock);
	}

	private static final Set<Material> POSSIBLE_LEAF_TYPES = new HashSet<Material>()
	{{
		add(Material.ACACIA_LEAVES);
		add(Material.BIRCH_LEAVES);
		add(Material.DARK_OAK_LEAVES);
		add(Material.JUNGLE_LEAVES);
		add(Material.OAK_LEAVES);
		add(Material.SPRUCE_LEAVES);
		// Nether biomes tree leaves:
		add(Material.SHROOMLIGHT);
		add(Material.NETHER_WART_BLOCK);
	}};

	private static final Set<Material> BIG_TREE_LOG_TYPES = new HashSet<Material>()
	{{
		add(Material.DARK_OAK_LOG);
		add(Material.JUNGLE_LOG);
		add(Material.STRIPPED_DARK_OAK_LOG);
		add(Material.STRIPPED_JUNGLE_LOG);
	}};

	/**
	 * Lumberaxe helper method.
	 */
	private List<Block> getAllLogsRecursively(Block srcBlock, Block relativeBlock, List<Block> existingList,
											  int currentHeight, boolean includeSrcBlock)
	{
		if (existingList == null) existingList = includeSrcBlock ? new LinkedList<Block>()
		{{add(srcBlock);}} : new LinkedList<>();

		final int lumberAxeRange = BIG_TREE_LOG_TYPES.contains(srcBlock.getType())
								   ? this.lumberAxeRange + 2
								   : this.lumberAxeRange;
		final Material type = srcBlock.getType();
		final Material leafType;

		// Check if valid tree
		Block __l = srcBlock;
		while (__l.getType() == type)
			__l = __l.getRelative(UP);
		leafType = __l.getType();

		if (!POSSIBLE_LEAF_TYPES.contains(leafType))
			return existingList;


		// Adjacent blocks (all logs in a vertical line starting from the source block)

		Block relBlock;
		if ((relBlock = relativeBlock.getRelative(UP)).getType() == type)
		{
			currentHeight++;
			existingList.add(relBlock);
			existingList = getAllLogsRecursively(srcBlock, relBlock, existingList, currentHeight, includeSrcBlock);
		}

		Block side;
		if (
				((side = relBlock.getRelative(BlockFace.EAST)).getType() == type && checkLine(side, UP,
																							  checkIfLeavesF))
				|| ((side = relBlock.getRelative(BlockFace.NORTH)).getType() == type && checkLine(side,
																								  UP,
																								  checkIfLeavesF))
				|| ((side = relBlock.getRelative(BlockFace.SOUTH)).getType() == type && checkLine(side,
																								  UP,
																								  checkIfLeavesF))
				|| ((side = relBlock.getRelative(BlockFace.WEST)).getType() == type && checkLine(side,
																								 UP,
																								 checkIfLeavesF))
		)
			existingList.add(side);

		// Adjacent corner blocks

		if (currentHeight > 2)
		{
			int range = lumberAxeRange * 2 + 1;
			for (int i = 0; i <= range; i++)
			{
				Location loc = new Location(relativeBlock.getWorld(),
											relativeBlock.getX() - lumberAxeRange + i,
											relativeBlock.getY(),
											relativeBlock.getZ() - lumberAxeRange);
				for (int j = 0; j <= range; j++)
				{
					loc = new Location(loc.getWorld(),
									   loc.getX(),
									   loc.getY(),
									   relativeBlock.getZ() - lumberAxeRange + j);
					if (relativeBlock.getX() == loc.getBlockX() && relativeBlock.getZ() == loc.getBlockZ()) continue;
					Block b = loc.getBlock();
					if (b.getType() == type && checkLine(b, UP, _b -> _b.getType() == leafType))
					{
						Location testSrcLoc = new Location(srcBlock.getLocation().getWorld(),
														   loc.getX(),
														   srcBlock.getLocation().getY(),
														   loc.getZ());
						Block testSrc = testSrcLoc.getBlock();

						if (
								testSrc != srcBlock
								&& !checkLine(b, DOWN, _b -> {
									Block potSrc = _b.getRelative(UP);
									Block potSrcSide;
									return
											_b.getType() == Material.DIRT
											&& !(
													potSrc == srcBlock
													|| ((potSrcSide = potSrc.getRelative(BlockFace.EAST)).getType() == type && checkLine(
															potSrcSide,
															UP,
															checkIfLeavesF))
													|| ((potSrcSide = potSrc.getRelative(BlockFace.SOUTH)).getType() == type && checkLine(
															potSrcSide,
															UP,
															checkIfLeavesF))
													|| ((potSrcSide = potSrc.getRelative(BlockFace.NORTH)).getType() == type && checkLine(
															potSrcSide,
															UP,
															checkIfLeavesF))
													|| ((potSrcSide = potSrc.getRelative(BlockFace.WEST)).getType() == type && checkLine(
															potSrcSide,
															UP,
															checkIfLeavesF))
											)
											;
								})
						) existingList.add(b);
					}
				}
			}
		}

		return existingList;
	}

	private boolean checkLine(final Block block, BlockFace relFace, Function<Block, Boolean> destBlockF)
	{
		Block b = block.getRelative(relFace);
		int searchIterations = 0;
		while (b.getType() == block.getType() && searchIterations <= maxSearch)
		{
			searchIterations++;
			b = b.getRelative(relFace);
		}

		return destBlockF.apply(b);
	}
}
