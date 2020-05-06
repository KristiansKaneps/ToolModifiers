package k4neps.toolmodifiers.integration;

import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.TownyUniverse;
import k4neps.toolmodifiers.utils.Area;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public final class TownyHook
{
	private static boolean isHooked = false;

	private TownyHook() {}

	public static void hook()
	{
		isHooked = true;
	}

	public static boolean intersectsTerritory(Block block, Player player, Area.TerritoryPlugin plugin, boolean towny_protect_wild)
	{
		if(!isHooked) return false;

		Resident resident = null;
		boolean inATown = false;

		try
		{
			resident = TownyUniverse.getDataSource().getResident(player.getName());
			inATown = true;
		}
		catch (NotRegisteredException e)
		{
			inATown = false;
		}

		if (!towny_protect_wild && TownyUniverse.isWilderness(block))
			return false;
		if (towny_protect_wild && TownyUniverse.isWilderness(block))
			return true;

		boolean sameTown = false;

		try
		{
			sameTown = inATown && resident.getTown().getUuid().compareTo(TownyUniverse.getTownBlock(block.getLocation()).getTown().getUuid()) == 0;
		}
		catch (NotRegisteredException e)
		{
			sameTown = false;
		}

		return !sameTown;
	}
}
