package k4neps.toolmodifiers;

import k4neps.toolmodifiers.commands.BaseCommand;
import k4neps.toolmodifiers.crafting.excavator.DiamondExcavator;
import k4neps.toolmodifiers.crafting.excavator.GoldExcavator;
import k4neps.toolmodifiers.crafting.excavator.IronExcavator;
import k4neps.toolmodifiers.crafting.hammer.*;
import k4neps.toolmodifiers.crafting.lumberaxe.*;
import k4neps.toolmodifiers.events.Events;
import k4neps.toolmodifiers.integration.FactionsHook;
import k4neps.toolmodifiers.integration.TownyHook;
import k4neps.toolmodifiers.utils.Area;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

/**
 * Created by Kristians on 7/28/2017.
 *
 * @author Kristians
 */
public class ToolModifiers extends JavaPlugin
{
	public static ToolModifiers instance;

	public static final String NAME = "ToolModifiers";
	public static final String PREFIX = "[" + NAME + "] ";

	public static Logger log;

	@Override
	public void onEnable()
	{
		instance = this;

		Server server = getServer();
		PluginManager pm = server.getPluginManager();
		log = server.getLogger();

		loadConfig();

		pm.registerEvents(new Events(this), this);

		Objects.requireNonNull(this.getCommand("tm")).setExecutor(new BaseCommand());

		new DiamondHammer().add();
		new GoldHammer().add();
		new IronHammer().add();
		new DiamondHammer35().add();
		new GoldHammer35().add();
		new IronHammer35().add();

		new DiamondExcavator().add();
		new GoldExcavator().add();
		new IronExcavator().add();

		new DiamondLumberaxe().add();
		new DiamondLumberaxeRight().add();
		new GoldLumberaxe().add();
		new GoldLumberaxeRight().add();
		new IronLumberaxe().add();
		new IronLumberaxeRight().add();

		final Plugin[] plugins = pm.getPlugins();
		boolean[] terr = {false, false};
		for(Plugin plugin : plugins)
		{
			String pluginName = plugin.getName();
			if(pluginName.equalsIgnoreCase("factions"))
			{
				terr[0] = true;
				FactionsHook.hook();
				Area.setTerritoryPlugin(Area.TerritoryPlugin.FACTIONS);
			}
			if(pluginName.equalsIgnoreCase("towny"))
			{
				terr[1] = true;
				TownyHook.hook();
				Area.setTerritoryPlugin(Area.TerritoryPlugin.TOWNY);
			}
			if(terr[0] && terr[1])
			{
				Area.setTerritoryPlugin(Area.TerritoryPlugin.BOTH);
				break;
			}
		}
	}

	@Override
	public void onDisable()
	{

	}

	private void loadConfig()
	{
		getConfig().options().copyDefaults(true);
		saveConfig();

		Area.loadConfigValues(getConfig());
		Lang.loadConfigValues(getConfig());
	}

	public static void log(Object obj)
	{
		log.info(PREFIX + obj.toString());
	}
}
