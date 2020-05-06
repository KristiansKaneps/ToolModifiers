package k4neps.toolmodifiers.integration;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;
import k4neps.toolmodifiers.utils.Area;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public final class FactionsHook
{
	private static boolean isHooked = false;

	private FactionsHook() {}

	public static void hook()
	{
		isHooked = true;
	}

	public static boolean intersectsTerritory(Block block, Player player, Area.TerritoryPlugin plugin, String faction_wilderness)
	{
		if(!isHooked) return false;

		MPlayer mPlayer = MPlayer.get(player);

		if (mPlayer == null) return true;
		if (mPlayer.isOverriding()) return false;

		Faction factionAtBlock = BoardColl.get().getFactionAt(PS.valueOf(block));

		if (factionAtBlock == null) return false;

		Faction playerFaction = mPlayer.getFaction();

		if (playerFaction == null) return true;

		String pfName = ChatColor.stripColor(playerFaction.getName());
		String fbName = ChatColor.stripColor(factionAtBlock.getName());

		return !(fbName.equalsIgnoreCase(pfName) || fbName.equalsIgnoreCase(faction_wilderness));
	}
}
