package k4neps.toolmodifiers.events;

import k4neps.toolmodifiers.PermCheck;
import k4neps.toolmodifiers.ToolModifiers;
import k4neps.toolmodifiers.crafting.cleaver.CleaverRecipe;
import k4neps.toolmodifiers.crafting.excavator.ExcavatorRecipe;
import k4neps.toolmodifiers.crafting.hammer.Hammer35Recipe;
import k4neps.toolmodifiers.crafting.hammer.HammerRecipe;
import k4neps.toolmodifiers.crafting.lumberaxe.LumberaxeRecipe;
import k4neps.toolmodifiers.utils.Area;
import k4neps.toolmodifiers.utils.BlockUtils;
import k4neps.toolmodifiers.utils.ToolUtils;
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
public class BlockBreakEvents implements Listener
{
	private final int maxSearch = 72;
	private final Function<Block, Boolean> checkIfLeavesF = b -> POSSIBLE_LEAF_TYPES.contains(b.getType());

	private final int lumberAxeRange;
	private final float hardnessThreshold;
	private final boolean lumberAxeDropsAtSrc;

	public BlockBreakEvents(ToolModifiers tm)
	{
		hardnessThreshold = (float) tm.getConfig().getDouble("hardness_threshold");
		lumberAxeRange = tm.getConfig().getInt("lumberaxe.tree_branch_radius");
		lumberAxeDropsAtSrc = tm.getConfig().getBoolean("lumberaxe.drop_tree_logs_at_source");
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onBlockBreak(BlockBreakEvent event)
	{
		if (event.isCancelled()) return;

		Player p = event.getPlayer();

		if (p.isSneaking()) return;

		ItemStack tool = p.getInventory().getItemInMainHand();

		if (tool.getType() == Material.AIR) return;
		if (
				(!ToolUtils.isPickaxe(tool) && !ToolUtils.isShovel(tool) && !ToolUtils.isAxe(tool)) ||
				(ToolUtils.isPickaxe(tool) && !PermCheck.canUseHammer(p)) ||
				(ToolUtils.isShovel(tool) && !PermCheck.canUseExcavator(p)) ||
				(ToolUtils.isAxe(tool) && !PermCheck.canUseLumberaxe(p)) ||
				(ToolUtils.isSword(tool) && !PermCheck.canUseCleaver(p))
		) return;

		ItemMeta meta = tool.getItemMeta();

		if (meta == null) return;

		List<String> lore = meta.getLore();

		if (lore == null || lore.size() < 1) return;

		Block block = event.getBlock();
		Location loc = block.getLocation();
		World world = loc.getWorld();

		if ((world == null) || (block.getType() == Material.AIR || !ToolUtils.canMineBlock(block, tool))) return;

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
				.contains(LumberaxeRecipe.LUMBERAXE_MODIFIER_STRING) || lore.contains(CleaverRecipe.CLEAVER_MODIFIER_STRING))
			modifiedTool = 1;

		if (modifiedTool > 0)
		{
			Area area = new Area(block, p, modifiedTool == 2 ? Area.Size._3X5 : Area.Size._3X3);

			final float srcHardness = block.getType().getHardness();

			for (Block b : area.getBlocksAround())
			{
				if (b == null || b.getType() == Material.AIR || Area.intersectsTerritory(b, p) || b.getType()
																								   .getHardness() > srcHardness + hardnessThreshold || !ToolUtils
						.canMineBlock(b, tool))
					continue;

				if (p.getGameMode() == GameMode.SURVIVAL)
				{
					Collection<ItemStack> drops = BlockUtils.getDrops(p, b, tool);
					int exp = BlockUtils.getExpDrop(b, tool);

					b.setType(Material.AIR);

					boolean isSilkTouch = tool.getEnchantments().containsKey(Enchantment.SILK_TOUCH);

					if (exp > 0 && !isSilkTouch)
						world.spawn(loc, ExperienceOrb.class).setExperience(exp);

					if (drops.size() > 0)
						for (ItemStack drop : drops)
							world.dropItemNaturally(loc, drop);
				}
				else if (p.getGameMode() == GameMode.CREATIVE)
				{
					b.setType(Material.AIR);
				}
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
