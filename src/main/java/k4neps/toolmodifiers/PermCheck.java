package k4neps.toolmodifiers;

import org.bukkit.entity.Player;

/**
 * Created by Kristians on 7/28/2017.
 *
 * @author Kristians
 */
public final class PermCheck
{
	private PermCheck() {}

	public static boolean hasPerm(Player p, String perm)
	{
		return p.hasPermission(perm);
	}

	public static boolean canUseHammer(Player p)
	{
		return hasPerm(p, "toolmodifiers.hammer.use");
	}

	public static boolean canUseExcavator(Player p)
	{
		return hasPerm(p, "toolmodifiers.excavator.use");
	}

	public static boolean canUseLumberaxe(Player p)
	{
		return hasPerm(p, "toolmodifiers.lumberaxe.use");
	}

	public static boolean canUseLumberaxeToCutTrees(Player p)
	{
		return hasPerm(p, "toolmodifiers.lumberaxe.tree");
	}

	public static boolean canCraftHammer(Player p)
	{
		return hasPerm(p, "toolmodifiers.hammer.craft");
	}

	public static boolean canCraftExcavator(Player p)
	{
		return hasPerm(p, "toolmodifiers.excavator.craft");
	}

	public static boolean canCraftLumberaxe(Player p)
	{
		return hasPerm(p, "toolmodifiers.lumberaxe.craft");
	}

	public static boolean canGiveHammerToTheirselves(Player p)
	{
		return hasPerm(p, "toolmodifiers.command.give.hammer");
	}

	public static boolean canGiveExcavatorToTheirselves(Player p)
	{
		return hasPerm(p, "toolmodifiers.command.give.excavator");
	}

	public static boolean canGiveLumberaxeToTheirselves(Player p)
	{
		return hasPerm(p, "toolmodifiers.command.give.lumberaxe");
	}

	public static boolean canUseBaseCommand(Player p)
	{
		return hasPerm(p, "toolmodifiers.command");
	}
}
