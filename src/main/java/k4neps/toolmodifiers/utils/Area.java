package k4neps.toolmodifiers.utils;

import k4neps.toolmodifiers.integration.FactionsHook;
import k4neps.toolmodifiers.integration.TownyHook;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.function.BiConsumer;

/**
 * Created by Kristians on 7/28/2017.
 *
 * @author Kristians
 */
public class Area
{
	private final double playerYaw;
	private final Block block;
	private final BlockFace face;
	private final Size size;
	private Block[] blocks;

	private static TerritoryPlugin plugin = TerritoryPlugin.NONE;
	private static String faction_wilderness;
	private static boolean towny_protect_wild;

	public enum TerritoryPlugin
	{
		FACTIONS, TOWNY, BOTH, NONE
	}

	public static void setTerritoryPlugin(TerritoryPlugin plugin)
	{
		Area.plugin = plugin;
	}

	public static void loadConfigValues(FileConfiguration config)
	{
		faction_wilderness = config.getString("hook.factions.default_faction_name", "Wilderness");
		towny_protect_wild = config.getBoolean("hook.towny.protect_wild", true);

	}

	public enum Size
	{
		_3X3(-1, 1),
		_5X5(-2, 2),
		_7X7(-3, 3),
		_3X5(-1, 1, -2, 2);

		private final int minX, maxX, minY, maxY;
		private final int xLen, yLen;
		private final int totalBlockCount;

		Size(int min, int max)
		{
			this(min, max, min, max);
		}

		Size(int minX, int maxX, int minY, int maxY)
		{
			this.minX = minX;
			this.maxX = maxX;
			this.minY = minY;
			this.maxY = maxY;
			xLen = Math.abs(minX) + Math.abs(maxX) + 1;
			yLen = Math.abs(minY) + Math.abs(maxY) + 1;
			totalBlockCount = xLen * yLen;
		}

		public int getTotalBlockCount()
		{
			return totalBlockCount;
		}

		public void forEachBlock(double playerYaw, Location baseLoc, BlockFace face, BiConsumer<Integer, Block> consumer)
		{
			final Utils.Int idx = new Utils.Int(0);

			final Location loc = new Location(baseLoc.getWorld(), baseLoc.getX(), baseLoc.getY(), baseLoc.getZ());
			int dx = 1;
			switch(face)
			{
				case DOWN:
				case UP:
					boolean rotate = (minX != minY || maxX != maxY) && Utils.check(Utils.fixValue((playerYaw - 90) % 360, r -> r < 0, r -> r + 360), rot -> (rot >= 45 && rot < 135) || (rot >= 225 && rot < 315));
					consumer.accept(idx.incrementAfter(), loc.add(rotate ? minX : minY, 0, rotate ? minY : minX).getBlock());
					for(int y = 0; y < (rotate ? xLen : yLen); y++)
					{
						for (int x = 0; x < (rotate ? yLen : xLen) - 1; x++) consumer.accept(idx.incrementAfter(), loc.add(0, 0, dx).getBlock());
						if(y + 1 < (rotate ? xLen : yLen)) consumer.accept(idx.incrementAfter(), loc.add(1, 0, 0).getBlock());
						dx = -dx;
					}
					//int i_ = minX, _i = maxX, j_ = minY, _j = maxY;
					//if((minX != minY || maxX != maxY) && Utils.check(Utils.fixValue((playerYaw - 90) % 360, r -> r < 0, r -> r + 360), rot -> (rot >= 45 && rot < 135) || (rot >= 225 && rot < 315)))
					//{
					//	i_ = minY; _i = maxY; j_ = minX; _j = maxX;
					//}
					//Utils.intRange2D(i_, _i, j_, _j, (i, j) -> consumer.accept(idx.incrementAfter(), new Location(baseLoc.getWorld(), baseLoc.getX() + j, baseLoc.getY(), baseLoc.getZ() + i).getBlock()));
					break;
				case EAST:
				case WEST:
					consumer.accept(idx.incrementAfter(), loc.add(0, minY, minX).getBlock());
					for(int y = 0; y < yLen; y++)
					{
						for (int x = 0; x < xLen - 1; x++) consumer.accept(idx.incrementAfter(), loc.add(0, 0, dx).getBlock());
						if(y + 1 < yLen) consumer.accept(idx.incrementAfter(), loc.add(0, 1, 0).getBlock());
						dx = -dx;
					}
					//Utils.intRange2D(minX, maxX, minY, maxY, (i, j) -> consumer.accept(idx.incrementAfter(), new Location(baseLoc.getWorld(), baseLoc.getX(), baseLoc.getY() + j, baseLoc.getZ() + i).getBlock()));
					break;
				case NORTH:
				case SOUTH:
					consumer.accept(idx.incrementAfter(), loc.add(minX, minY, 0).getBlock());
					for(int y = 0; y < yLen; y++)
					{
						for (int x = 0; x < xLen - 1; x++) consumer.accept(idx.incrementAfter(), loc.add(dx, 0, 0).getBlock());
						if(y + 1 < yLen) consumer.accept(idx.incrementAfter(), loc.add(0, 1, 0).getBlock());
						dx = -dx;
					}
					//Utils.intRange2D(minX, maxX, minY, maxY, (i, j) -> consumer.accept(idx.incrementAfter(), new Location(baseLoc.getWorld(), baseLoc.getX() + i, baseLoc.getY() + j, baseLoc.getZ()).getBlock()));
			}
		}
	}

	/**
	 * Calculates at which block face player is looking.
	 */
	public Area(Block block, Player player, Size size)
	{
		List<Block> lastBlocks = player.getLastTwoTargetBlocks(null, 10);

		this.playerYaw = player.getLocation().getYaw();
		this.block = block;
		this.face = lastBlocks.size() < 2 ? BlockFace.DOWN : lastBlocks.get(1).getFace(lastBlocks.get(0));
		this.size = size;

		init();
	}

	public Area(Block block, double playerYaw, BlockFace face, Size size)
	{
		this.playerYaw = playerYaw;
		this.block = block;
		this.face = face;
		this.size = size;

		init();
	}

	private void init()
	{
		Location loc = block.getLocation();

		blocks = new Block[size.getTotalBlockCount()];

		size.forEachBlock(playerYaw, loc, face, (i, b) -> blocks[i] = b);
	}

	public Block getMiddleBlock()
	{
		return block;
	}

	public BlockFace getBlockFace()
	{
		return face;
	}

	public Block[] getBlocksAround()
	{
		return blocks;
	}

	public static String getTerritoryPlugin()
	{
		return plugin.name();
	}

	public static boolean intersectsTerritory(Block block, Player player)
	{
		if(plugin == TerritoryPlugin.BOTH)
			return intersectsTerritory(block, player, TerritoryPlugin.FACTIONS) || intersectsTerritory(block, player, TerritoryPlugin.TOWNY);
		return intersectsTerritory(block, player, plugin);
	}

	private static boolean intersectsTerritory(Block block, Player player, TerritoryPlugin plugin)
	{
		switch(plugin)
		{
			case FACTIONS:
				return FactionsHook.intersectsTerritory(block, player, plugin, faction_wilderness);
			case TOWNY:
				return TownyHook.intersectsTerritory(block, player, plugin, towny_protect_wild);
			default: return false;
		}
	}
}
