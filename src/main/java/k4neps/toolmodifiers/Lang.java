package k4neps.toolmodifiers;

import org.bukkit.configuration.file.FileConfiguration;

import java.lang.ref.WeakReference;

public final class Lang
{
	private static WeakReference<FileConfiguration> weakC;

	public static final class DIAMOND
	{
		private DIAMOND() {}
		public static String HAMMER, HAMMER_3X5, EXCAVATOR, LUMBERAXE;
	}

	public static final class IRON
	{
		private IRON() {}
		public static String HAMMER, HAMMER_3X5, EXCAVATOR, LUMBERAXE;
	}

	public static final class GOLD
	{
		private GOLD() {}
		public static String HAMMER, HAMMER_3X5, EXCAVATOR, LUMBERAXE;
	}

	public static final class NETHERITE
	{
		private NETHERITE() {}
		public static String HAMMER, HAMMER_3X5, EXCAVATOR, LUMBERAXE;
	}

	private Lang() {}

	public static void loadConfigValues(FileConfiguration c)
	{
		weakC = new WeakReference<>(c);

		DIAMOND.HAMMER = s("diamond.hammer");
		DIAMOND.EXCAVATOR = s("diamond.excavator");
		DIAMOND.LUMBERAXE = s("diamond.lumberaxe");
		IRON.HAMMER = s("iron.hammer");
		IRON.EXCAVATOR = s("iron.excavator");
		IRON.LUMBERAXE = s("iron.lumberaxe");
		GOLD.HAMMER = s("gold.hammer");
		GOLD.EXCAVATOR = s("gold.excavator");
		GOLD.LUMBERAXE = s("gold.lumberaxe");
		NETHERITE.HAMMER = s("netherite.hammer");
		NETHERITE.EXCAVATOR = s("netherite.excavator");
		NETHERITE.LUMBERAXE = s("netherite.lumberaxe");

		DIAMOND.HAMMER_3X5 = DIAMOND.HAMMER;
		IRON.HAMMER_3X5 = IRON.HAMMER;
		GOLD.HAMMER_3X5 = GOLD.HAMMER;
		NETHERITE.HAMMER_3X5 = NETHERITE.HAMMER;
	}

	private static String s(final String path)
	{
		try
		{
			final FileConfiguration cfg = weakC.get();
			if(cfg != null)
				return cfg.getString("display.names." + path);
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
		}

		return "unlocalized";
	}
}
