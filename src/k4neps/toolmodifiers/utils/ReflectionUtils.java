package k4neps.toolmodifiers.utils;

import io.netty.channel.Channel;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class ReflectionUtils
{
	public static String MINECRAFT_NMS_BLOCK = null;

	private ReflectionUtils() {}

	public static String getVersion()
	{
		return Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + ".";
	}

	public static Class<?> getNMSClass(String classString) throws ClassNotFoundException
	{
		String name = MINECRAFT_NMS_BLOCK != null ? MINECRAFT_NMS_BLOCK : "net.minecraft.server." + getVersion() + classString;
		Class<?> nmsClass = Class.forName(name);
		return nmsClass;
	}

	public static Class<?> getOBCClass(String classString)
	{
		String name = "org.bukkit.craftbukkit." + getVersion() + classString;
		@SuppressWarnings("rawtypes")
		Class obcClass = null;
		try
		{
			obcClass = Class.forName(name);
		}
		catch (ClassNotFoundException error)
		{
			error.printStackTrace();
			return null;
		}
		return obcClass;
	}

	public static Channel getChannel(Player player) throws SecurityException, NoSuchMethodException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		Object connection = getConnection(player);
		Field connectionField = connection.getClass().getField("networkManager");
		Object networkManager = connectionField.get(connection);
		Field channelField = networkManager.getClass().getField("channel");
		return (Channel) channelField.get(networkManager);
	}

	public static Object getConnection(Player player) throws SecurityException, NoSuchMethodException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		Object nmsPlayer = getHandle(player);
		Field connectionField = nmsPlayer.getClass().getField("playerConnection");
		return connectionField.get(nmsPlayer);
	}

	public static Object getHandle(Object obj) throws SecurityException, NoSuchMethodException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		if (obj != null)
		{
			Method getHandle = obj.getClass().getMethod("getHandle");
			getHandle.setAccessible(true);
			Object nmsPlayer = getHandle.invoke(obj);
			return nmsPlayer;
		}
		return null;
	}

	public static void sendPacket(Player player, Object object) throws NoSuchMethodException
	{
		try
		{
			Method method = getConnection(player).getClass().getMethod("sendPacket", getNMSClass("Packet"));
			method.invoke(getConnection(player), object);
		}
		catch (SecurityException | NoSuchFieldException | IllegalArgumentException | IllegalAccessException | InvocationTargetException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public static Object getNMSBlock(Block block)
	{
		try
		{
			Method method = ReflectionUtils.getOBCClass("util.CraftMagicNumbers").getDeclaredMethod("getBlock", Material.class);
			method.setAccessible(true);
			return method.invoke(ReflectionUtils.getOBCClass("util.CraftMagicNumbers"), block.getType());
		}
		catch (SecurityException | IllegalArgumentException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
