package k4neps.toolmodifiers.events;

import k4neps.toolmodifiers.PermCheck;
import k4neps.toolmodifiers.ToolModifiers;
import k4neps.toolmodifiers.crafting.cleaver.CleaverRecipe;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collection;
import java.util.List;

public class AttackEvents implements Listener {
	private final boolean mobsCanUseCleavers;

	public AttackEvents(ToolModifiers tm) {
		mobsCanUseCleavers = tm.getConfig().getBoolean("cleaver.mobs_can_use_cleavers");
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		final EntityDamageEvent.DamageCause cause = event.getCause();
		final Entity victim = event.getEntity();
		if (!(victim instanceof LivingEntity)) return;
		if (cause == EntityDamageEvent.DamageCause.ENTITY_ATTACK || cause == EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK) {
			swordAttack(event);
		} else if (cause == EntityDamageEvent.DamageCause.PROJECTILE) {
			projectileAttack(event);
		}
	}

	private void swordAttack(EntityDamageByEntityEvent event) {
		final Entity damager = event.getDamager();
		if (!(damager instanceof InventoryHolder)) return;
		final Entity victim = event.getEntity();
		final Location loc = victim.getLocation();

		final InventoryHolder ih = (InventoryHolder) damager;
		final Inventory inv = ih.getInventory();

		final ItemStack weapon;
		final ItemMeta meta;
		final List<String> lore;

		if (damager.getType() != EntityType.PLAYER || !(inv instanceof PlayerInventory)) {
			if (!mobsCanUseCleavers) return;
			weapon = inv.getItem(0);
			if (weapon == null) return;
			meta = weapon.getItemMeta();
			if (meta == null) return;
			lore = meta.getLore();
			if (lore == null || lore.size() < 1 || !lore.contains(CleaverRecipe.CLEAVER_MODIFIER_STRING)) return;
			cleaverAttackByMob(event, weapon, damager, victim, loc);
			return;
		}

		final Player p = (Player) damager;
		weapon = p.getInventory().getItemInMainHand();
		if (weapon == null) return;
		meta = weapon.getItemMeta();
		if (meta == null) return;
		lore = meta.getLore();
		if (lore == null || lore.size() < 1 || !PermCheck.canUseCleaver(p) || !lore.contains(CleaverRecipe.CLEAVER_MODIFIER_STRING)) return;
		cleaverAttackByPlayer(event, weapon, p, victim, loc);
	}

	private void cleaverAttackByMob(EntityDamageByEntityEvent event, ItemStack weapon, Entity attacker, Entity victim, Location loc) {
		final World world = loc.getWorld();
		final Collection<Entity> nearbyEntities = world.getNearbyEntities(loc, 2, 2, 1, entity -> entity != attacker && entity != victim && entity instanceof LivingEntity);

		final Location attackerEyeLocation = attacker.getLocation().add(0, 1.5, 0);
		final World attackerWorld = attackerEyeLocation.getWorld();
		if (attackerWorld != null) {
			attackerWorld.spawnParticle(Particle.SWEEP_ATTACK, attackerEyeLocation, 1);
			attackerWorld.spawnParticle(Particle.CRIT, attackerEyeLocation, 1);
		}

		for(final Entity nearbyEntity : nearbyEntities) {
			final LivingEntity le = (LivingEntity) nearbyEntity;
			le.damage(event.getDamage() / 2, attacker);
		}
	}

	private void cleaverAttackByPlayer(EntityDamageByEntityEvent event, ItemStack weapon, Player attacker, Entity victim, Location loc) {
		final World world = loc.getWorld();
		final Collection<Entity> nearbyEntities = world.getNearbyEntities(loc, 2, 2, 1, entity -> entity != attacker && entity != victim && entity instanceof LivingEntity);

		final Location attackerEyeLocation = attacker.getLocation().add(0, 1.5, 0);
		final World attackerWorld = attackerEyeLocation.getWorld();
		if (attackerWorld != null) {
			attackerWorld.spawnParticle(Particle.SWEEP_ATTACK, attackerEyeLocation, 1);
			attackerWorld.spawnParticle(Particle.CRIT, attackerEyeLocation, 1);
		}

		for(final Entity nearbyEntity : nearbyEntities) {
			final LivingEntity le = (LivingEntity) nearbyEntity;
			le.damage(event.getDamage() / 2, attacker);
		}
	}

	private void projectileAttack(EntityDamageByEntityEvent event) {
	}
}
