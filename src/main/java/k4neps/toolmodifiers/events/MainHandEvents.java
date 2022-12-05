package k4neps.toolmodifiers.events;

import k4neps.toolmodifiers.ToolModifiers;
import k4neps.toolmodifiers.crafting.cleaver.CleaverRecipe;
import k4neps.toolmodifiers.utils.ToolUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class MainHandEvents implements Listener {
	public MainHandEvents(ToolModifiers tm) { }

	@EventHandler(priority = EventPriority.NORMAL)
	public void onItemSwitch(PlayerItemHeldEvent event) {
		final Player p = event.getPlayer();
		final PlayerInventory inv = p.getInventory();

		final int currSlot = event.getNewSlot();
		final ItemStack currItem = inv.getItem(currSlot);

		if (currItem != null) handleCurrItem(p, currItem);
	}

	private void handleCurrItem(Player p, ItemStack item) {
		if (!ToolUtils.isSword(item)) return;
		final ItemMeta meta;
		final List<String> lore;
		if (item == null) return;
		meta = item.getItemMeta();
		if (meta == null) return;
		lore = meta.getLore();
		if (lore == null || lore.size() < 1 || !lore.contains(CleaverRecipe.CLEAVER_MODIFIER_STRING)) return;

		p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 40, 4));
	}
}
