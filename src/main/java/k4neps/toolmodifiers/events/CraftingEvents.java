package k4neps.toolmodifiers.events;

import k4neps.toolmodifiers.Lang;
import k4neps.toolmodifiers.PermCheck;
import k4neps.toolmodifiers.ToolModifiers;
import k4neps.toolmodifiers.crafting.cleaver.CleaverRecipe;
import k4neps.toolmodifiers.crafting.excavator.ExcavatorRecipe;
import k4neps.toolmodifiers.crafting.hammer.Hammer35Recipe;
import k4neps.toolmodifiers.crafting.hammer.HammerRecipe;
import k4neps.toolmodifiers.crafting.lumberaxe.LumberaxeRecipe;
import k4neps.toolmodifiers.utils.ToolUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class CraftingEvents implements Listener
{
	public CraftingEvents(ToolModifiers tm) {}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onCraft(CraftItemEvent event)
	{
		if (
				event.getInventory().getType() != InventoryType.CRAFTING
				|| event.getSlotType() != InventoryType.SlotType.RESULT
				|| event.getCurrentItem() == null
				|| event.getCurrentItem().getType() == Material.AIR
				|| event.isCancelled()
		) return;

		ItemStack clickedItem = event.getCurrentItem();
		ItemMeta meta = clickedItem.getItemMeta();

		if (!meta.hasDisplayName()) // modified tools are renamed
			return;

		final List<String> lore = meta.getLore();
		if (lore == null) return;

		boolean modified = false;
		for (String line : lore)
			if (
					HammerRecipe.HAMMER_MODIFIER_STRING.equals(line)
					|| Hammer35Recipe.HAMMER_MODIFIER_STRING.equals(line)
					|| ExcavatorRecipe.EXCAVATOR_MODIFIER_STRING.equals(line)
					|| LumberaxeRecipe.LUMBERAXE_MODIFIER_STRING.equals(line)
					|| CleaverRecipe.CLEAVER_MODIFIER_STRING.equals(line)
			)
			{
				modified = true;
				break;
			}
		if (!modified) return;

		final HumanEntity p = event.getWhoClicked();

		final boolean hammer = ToolUtils.isPickaxe(clickedItem) && !PermCheck.canCraftHammer(p);
		final boolean excavator = ToolUtils.isShovel(clickedItem) && !PermCheck.canCraftExcavator(p);
		final boolean lumberaxe = ToolUtils.isAxe(clickedItem) && !PermCheck.canCraftLumberaxe(p);
		final boolean cleaver = ToolUtils.isSword(clickedItem) && !PermCheck.canCraftCleaver(p);

		if (hammer || excavator || lumberaxe || cleaver) event.setCancelled(true);
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onNetheriteUpgrade(InventoryClickEvent e)
	{
		if (e.getInventory().getType() != InventoryType.SMITHING
			|| e.getSlotType() != InventoryType.SlotType.RESULT
			|| e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR
			|| e.isCancelled())
			return;

		final ItemStack clickedItem = e.getCurrentItem();
		final ItemMeta meta = clickedItem.getItemMeta();

		if (!meta.hasDisplayName()) // modified tools are renamed
			return;

		final List<String> lore = meta.getLore();
		if (lore == null) return;

		boolean modify = false;
		for (String line : lore)
			if (
					HammerRecipe.HAMMER_MODIFIER_STRING.equals(line)
					|| Hammer35Recipe.HAMMER_MODIFIER_STRING.equals(line)
					|| ExcavatorRecipe.EXCAVATOR_MODIFIER_STRING.equals(line)
					|| LumberaxeRecipe.LUMBERAXE_MODIFIER_STRING.equals(line)
					|| CleaverRecipe.CLEAVER_MODIFIER_STRING.equals(line)
			)
			{
				modify = true;
				break;
			}
		if (!modify) return;

		final HumanEntity p = e.getWhoClicked();

		String displayName = meta.getDisplayName();

		switch (clickedItem.getType())
		{
			case NETHERITE_AXE:
				if (!PermCheck.canCraftLumberaxe(p))
				{
					e.setCancelled(true);
					return;
				}
				clickedItem.setType(Material.NETHERITE_AXE);
				if (ChatColor.stripColor(displayName).trim()
							 .equals(ChatColor.stripColor(Lang.DIAMOND.LUMBERAXE).trim()))
					displayName = Lang.NETHERITE.LUMBERAXE;
				break;
			case NETHERITE_PICKAXE:
				if (!PermCheck.canCraftHammer(p))
				{
					e.setCancelled(true);
					return;
				}
				clickedItem.setType(Material.NETHERITE_PICKAXE);
				if (meta.hasDisplayName())
				{
					if (
							meta.getLore().contains(HammerRecipe.HAMMER_MODIFIER_STRING)
							&& ChatColor.stripColor(displayName).trim()
										.equals(ChatColor.stripColor(Lang.DIAMOND.HAMMER).trim()))
						displayName = Lang.NETHERITE.HAMMER;
					else if (
							meta.getLore().contains(Hammer35Recipe.HAMMER_MODIFIER_STRING)
							&& ChatColor.stripColor(displayName).trim()
										.equals(ChatColor.stripColor(Lang.DIAMOND.HAMMER_3X5).trim()))
						displayName = Lang.NETHERITE.HAMMER_3X5;
				}
				break;
			case NETHERITE_SHOVEL:
				if (!PermCheck.canCraftExcavator(p))
				{
					e.setCancelled(true);
					return;
				}
				clickedItem.setType(Material.NETHERITE_SHOVEL);
				if (ChatColor.stripColor(displayName).trim()
							 .equals(ChatColor.stripColor(Lang.DIAMOND.EXCAVATOR).trim()))
					displayName = Lang.NETHERITE.EXCAVATOR;
				break;
			case NETHERITE_SWORD:
				if (!PermCheck.canCraftCleaver(p))
				{
					e.setCancelled(true);
					return;
				}
				clickedItem.setType(Material.NETHERITE_SWORD);
				if (ChatColor.stripColor(displayName).trim()
							 .equals(ChatColor.stripColor(Lang.DIAMOND.CLEAVER).trim()))
					displayName = Lang.NETHERITE.CLEAVER;
				break;
			default:
				return;
		}

		meta.setDisplayName(displayName);

		clickedItem.setItemMeta(meta);
	}
}
