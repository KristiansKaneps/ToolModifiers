package k4neps.toolmodifiers;

import org.bukkit.entity.Player;
import org.bukkit.permissions.Permissible;

/**
 * Created by Kristians on 7/28/2017.
 *
 * @author Kristians
 */
public final class PermCheck
{
	private PermCheck() {}

	public static boolean hasPerm(Permissible p, String perm)
	{
		return p.hasPermission(perm);
	}

	public static boolean canUseHammer(Permissible p)
	{
		return hasPerm(p, "toolmodifiers.hammer.use");
	}

	public static boolean canUseExcavator(Permissible p)
	{
		return hasPerm(p, "toolmodifiers.excavator.use");
	}

	public static boolean canUseLumberaxe(Permissible p)
	{
		return hasPerm(p, "toolmodifiers.lumberaxe.use");
	}

	public static boolean canUseLumberaxeToCutTrees(Permissible p)
	{
		return hasPerm(p, "toolmodifiers.lumberaxe.tree");
	}

	public static boolean canUseCleaver(Permissible p)
	{
		return hasPerm(p, "toolmodifiers.cleaver.use");
	}

	public static boolean canCraftHammer(Permissible p)
	{
		return hasPerm(p, "toolmodifiers.hammer.craft");
	}

	public static boolean canCraftExcavator(Permissible p)
	{
		return hasPerm(p, "toolmodifiers.excavator.craft");
	}

	public static boolean canCraftLumberaxe(Permissible p)
	{
		return hasPerm(p, "toolmodifiers.lumberaxe.craft");
	}

	public static boolean canCraftCleaver(Permissible p)
	{
		return hasPerm(p, "toolmodifiers.cleaver.craft");
	}

	public static boolean canGiveHammerToTheirselves(Permissible p)
	{
		return hasPerm(p, "toolmodifiers.command.give.hammer");
	}

	public static boolean canGiveExcavatorToTheirselves(Permissible p)
	{
		return hasPerm(p, "toolmodifiers.command.give.excavator");
	}

	public static boolean canGiveLumberaxeToTheirselves(Permissible p)
	{
		return hasPerm(p, "toolmodifiers.command.give.lumberaxe");
	}

	public static boolean canGiveCleaverToTheirselves(Permissible p)
	{
		return hasPerm(p, "toolmodifiers.command.give.cleaver");
	}

	public static boolean canUseBaseCommand(Permissible p)
	{
		return hasPerm(p, "toolmodifiers.command");
	}
}
