package k4neps.toolmodifiers.commands;

import k4neps.toolmodifiers.PermCheck;
import k4neps.toolmodifiers.crafting.cleaver.CleaverRecipe;
import k4neps.toolmodifiers.crafting.excavator.ExcavatorRecipe;
import k4neps.toolmodifiers.crafting.hammer.Hammer35Recipe;
import k4neps.toolmodifiers.crafting.hammer.HammerRecipe;
import k4neps.toolmodifiers.crafting.lumberaxe.LumberaxeRecipe;
import k4neps.toolmodifiers.utils.Area;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

/**
 * Created by Kristians on 8/2/2017.
 *
 * @author Kristians
 */
public class BaseCommand implements CommandExecutor
{
	@SuppressWarnings("deprecated")
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args)
	{
		if(!(commandSender instanceof Player))
		{
			commandSender.sendMessage("You must be a player to use this command.");
			return false;
		}

		Player p = (Player) commandSender;

		if(!PermCheck.canUseBaseCommand(p))
		{
			error(p, "You don't have enough permissions to use this command.");
			return false;
		}

		if(args.length == 1)
		{
			if(args[0].equalsIgnoreCase("itemlist"))
			{
				listItems(p);
				return true;
			}

			if(args[0].equalsIgnoreCase("help"))
			{
				usage(p);
				return true;
			}

			if(args[0].equalsIgnoreCase("territoryhook"))
			{
				p.sendMessage(ChatColor.AQUA + "Using " + Area.getTerritoryPlugin());
				return true;
			}
		}

		if(args.length == 2 || args.length == 3)
		{
			if(!args[0].equalsIgnoreCase("give"))
			{
				usage(p);
				return false;
			}

			Player toGive = p;

			if(args.length == 3)
			{
				try
				{
					UUID uuid = UUID.fromString(args[2]);
					toGive = Bukkit.getPlayer(uuid);
				}
				catch(IllegalArgumentException e)
				{
					toGive = Bukkit.getPlayer(args[2]);
				}

				if (toGive == null || !toGive.isOnline()) toGive = p;
			}

			final String item = args[1].toLowerCase();
			final Inventory inv = toGive.getInventory();

			if(item.contains("hammer"))
			{
				if(!PermCheck.canGiveHammerToTheirselves(p))
				{
					error(p, "You don't have enough permissions to give yourself a hammer.");
					return false;
				}

				if(item.contains("iron"))
				{
					inv.addItem(item.contains("3x5") ? Hammer35Recipe.result_iron : HammerRecipe.result_iron);
					success(p, "You gave yourself an iron hammer.");
					return true;
				}
				if(item.contains("gold"))
				{
					inv.addItem(item.contains("3x5") ? Hammer35Recipe.result_gold : HammerRecipe.result_gold);
					success(p, "You gave yourself a gold hammer.");
					return true;
				}
				if(item.contains("netherite"))
				{
					inv.addItem(item.contains("3x5") ? Hammer35Recipe.result_netherite : HammerRecipe.result_netherite);
					success(p, "You gave yourself a netherite hammer.");
					return true;
				}

				inv.addItem(item.contains("3x5") ? Hammer35Recipe.result_diamond : HammerRecipe.result_diamond);
				success(p, "You gave yourself a diamond hammer.");
				return true;
			}
			if(item.contains("excavator"))
			{
				if(!PermCheck.canGiveExcavatorToTheirselves(p))
				{
					error(p, "You don't have enough permissions to give yourself an excavator.");
					return false;
				}

				if(item.contains("iron"))
				{
					inv.addItem(ExcavatorRecipe.result_iron);
					success(p, "You gave yourself an iron excavator.");
					return true;
				}
				if(item.contains("gold"))
				{
					inv.addItem(ExcavatorRecipe.result_gold);
					success(p, "You gave yourself a gold excavator.");
					return true;
				}
				if(item.contains("netherite"))
				{
					inv.addItem(ExcavatorRecipe.result_netherite);
					success(p, "You gave yourself a netherite excavator.");
					return true;
				}

				inv.addItem(ExcavatorRecipe.result_diamond);
				success(p, "You gave yourself a diamond excavator.");
				return true;
			}
			if(item.contains("lumberaxe"))
			{
				if(!PermCheck.canGiveLumberaxeToTheirselves(p))
				{
					error(p, "You don't have enough permissions to give yourself a lumberaxe.");
					return false;
				}

				if(item.contains("iron"))
				{
					inv.addItem(LumberaxeRecipe.result_iron);
					success(p, "You gave yourself an iron lumberaxe.");
					return true;
				}
				if(item.contains("gold"))
				{
					inv.addItem(LumberaxeRecipe.result_gold);
					success(p, "You gave yourself a gold lumberaxe.");
					return true;
				}
				if(item.contains("netherite"))
				{
					inv.addItem(LumberaxeRecipe.result_netherite);
					success(p, "You gave yourself a netherite lumberaxe.");
					return true;
				}

				inv.addItem(LumberaxeRecipe.result_diamond);
				success(p, "You gave yourself a diamond lumberaxe.");
				return true;
			}
			if(item.contains("cleaver"))
			{
				if(!PermCheck.canGiveCleaverToTheirselves(p))
				{
					error(p, "You don't have enough permissions to give yourself a cleaver.");
					return false;
				}

				if(item.contains("iron"))
				{
					inv.addItem(CleaverRecipe.result_iron);
					success(p, "You gave yourself an iron cleaver.");
					return true;
				}
				if(item.contains("gold"))
				{
					inv.addItem(CleaverRecipe.result_gold);
					success(p, "You gave yourself a gold cleaver.");
					return true;
				}
				if(item.contains("netherite"))
				{
					inv.addItem(CleaverRecipe.result_netherite);
					success(p, "You gave yourself a netherite cleaver.");
					return true;
				}

				inv.addItem(CleaverRecipe.result_diamond);
				success(p, "You gave yourself a diamond cleaver.");
				return true;
			}

			error(p, "Unknown item " + ChatColor.DARK_AQUA + item);
			return false;
		}

		usage(p);
		return false;
	}

	private static void usage(Player p)
	{
		p.sendMessage(ChatColor.AQUA + "Usage: ");
		p.sendMessage(ChatColor.DARK_AQUA + " /tm give <item> [player]");
		p.sendMessage(ChatColor.DARK_AQUA + " /tm itemlist");
	}

	private static void listItems(Player p)
	{
		p.sendMessage(ChatColor.AQUA + "Item List: ");
		p.sendMessage(ChatColor.DARK_AQUA + " diamondhammer[3x5], ironhammer[3x5], goldhammer[3x5], netheritehammer[3x5]");
		p.sendMessage(ChatColor.DARK_AQUA + " diamondexcavator, ironexcavator, goldexcavator, netheriteexcavator");
		p.sendMessage(ChatColor.DARK_AQUA + " diamondlumberaxe, ironlumberaxe, goldlumberaxe, netheritelumberaxe");
		p.sendMessage(ChatColor.DARK_AQUA + " diamondcleaver, ironcleaver, goldcleaver, netheritecleaver");
	}

	private static void error(Player p, String msg)
	{
		p.sendMessage(ChatColor.RED + msg);
	}

	private static void success(Player p, String msg)
	{
		p.sendMessage(ChatColor.DARK_GREEN + msg);
	}
}
