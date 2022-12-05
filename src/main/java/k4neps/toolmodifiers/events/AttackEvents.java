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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;
import java.util.function.Predicate;

public class AttackEvents implements Listener
{
//	private static final long AREA_DAMAGE_INTERVAL = 4L; // In ticks
//	private final Map<Integer, LastDamage> lastDamagedEntities = new HashMap<>();
//
//	private static class LastDamage
//	{
//		public final int entityId;
//		public UUID worldId;
//		public long ticks;
//
//		public LastDamage(int entityId, UUID worldId, long ticks)
//		{
//			this.entityId = entityId;
//			this.worldId = worldId;
//			this.ticks = ticks;
//		}
//
//		public LastDamage(int entityId, UUID worldId)
//		{
//			this(entityId, worldId, 0L);
//		}
//	}

	private static final double DAMAGE_MULTIPLIER = 1.25;
	private static final double AREA_DAMAGE_MULTIPLIER = 0.67;

	private static final double AREA_DAMAGE_HORIZONTAL_PLANE_RADIUS = 2.25;
	private static final double AREA_DAMAGE_VERTICAL_RADIUS = 1.75;

	private final boolean mobsCanUseCleavers;

	private final ToolModifiers tm;

	public AttackEvents(ToolModifiers tm)
	{
		this.tm = tm;
		mobsCanUseCleavers = tm.getConfig().getBoolean("cleaver.mobs_can_use_cleavers");
	}

	private Predicate<Entity> filterOtherEntities(Entity attacker, Entity victim)
	{
		return entity -> entity.getEntityId() != attacker.getEntityId() && entity.getEntityId() != victim.getEntityId() && entity instanceof LivingEntity;
	}

	/**
	 * Cleans up the lastDamagedEntities map.
	 */
//	public void cleanLastDamagedEntities()
//	{
//		lastDamagedEntities.entrySet().removeIf(entry -> {
//			final World world = tm.getServer().getWorld(entry.getValue().worldId);
//			if (world == null) return true;
//			final long fullTime = world.getFullTime();
//			return entry.getValue().ticks < fullTime - AREA_DAMAGE_INTERVAL;
//		});
//	}

	/**
	 * Cleans up the lastDamagedEntities map.
	 *
	 * @param event Entity death event.
	 */
//	@EventHandler(priority = EventPriority.HIGHEST)
//	public void onEntityDeath(EntityDeathEvent event)
//	{
//		lastDamagedEntities.remove(event.getEntity().getEntityId());
//		cleanLastDamagedEntities();
//	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event)
	{
		final EntityDamageEvent.DamageCause cause = event.getCause();
		final Entity victim = event.getEntity();
		if (!(victim instanceof LivingEntity)) return;
		if (cause == EntityDamageEvent.DamageCause.ENTITY_ATTACK || cause == EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK)
		{
			swordAttack(event);
		}
		else if (cause == EntityDamageEvent.DamageCause.PROJECTILE)
		{
			projectileAttack(event);
		}
	}

	private void swordAttack(EntityDamageByEntityEvent event)
	{
		final Entity damager = event.getDamager();
		if (!(damager instanceof InventoryHolder)) return;
		final Entity victim = event.getEntity();
		final Location loc = victim.getLocation();

		final InventoryHolder ih = (InventoryHolder) damager;
		final Inventory inv = ih.getInventory();

		final ItemStack weapon;
		final ItemMeta meta;
		final List<String> lore;

		if (damager.getType() != EntityType.PLAYER || !(inv instanceof PlayerInventory))
		{
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
		if (lore == null || lore.size() < 1 || !PermCheck.canUseCleaver(p) || !lore.contains(CleaverRecipe.CLEAVER_MODIFIER_STRING))
			return;
		cleaverAttackByPlayer(event, weapon, p, victim, loc);
	}

	private void cleaverAttackByMob(EntityDamageByEntityEvent event, ItemStack weapon, Entity attacker, Entity victim,
									Location loc)
	{
		final World world = loc.getWorld();
		final Collection<Entity> nearbyEntities = world.getNearbyEntities(attacker.getLocation(),
																		  AREA_DAMAGE_HORIZONTAL_PLANE_RADIUS,
																		  AREA_DAMAGE_HORIZONTAL_PLANE_RADIUS,
																		  AREA_DAMAGE_VERTICAL_RADIUS,
																		  filterOtherEntities(attacker, victim));

		boolean hasSlowEffect = true;
		double damageDivisor = 1;

		if (attacker instanceof LivingEntity)
		{
			final LivingEntity le = (LivingEntity) attacker;
			final PotionEffect slowEffect = le.getPotionEffect(PotionEffectType.SLOW_DIGGING);
			hasSlowEffect = slowEffect != null;
			damageDivisor = hasSlowEffect
									? Math.max(DAMAGE_MULTIPLIER, (slowEffect.getAmplifier() / 2.75))
									: 1;
			le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 27, 4));
		}

		event.setDamage(event.getDamage() * DAMAGE_MULTIPLIER / damageDivisor);

		if (hasSlowEffect) return;

		final Location attackerEyeLocation = attacker.getLocation().add(0, 1.5, 0);
		final World attackerWorld = attackerEyeLocation.getWorld();
		if (attackerWorld != null)
		{
			attackerWorld.spawnParticle(Particle.SWEEP_ATTACK, attackerEyeLocation, 1);
			attackerWorld.spawnParticle(Particle.CRIT, attackerEyeLocation, 1);
		}

		for (final Entity nearbyEntity : nearbyEntities)
		{
			final LivingEntity le = (LivingEntity) nearbyEntity;
//			final LastDamage lastDamaged = lastDamagedEntities.getOrDefault(le.getEntityId(),
//																			new LastDamage(le.getEntityId(),
//																						   le.getWorld().getUID()));
//			final long now = nearbyEntity.getWorld().getFullTime();
//			final World prevWorld = tm.getServer().getWorld(lastDamaged.worldId);
//			if (prevWorld != null && prevWorld.getFullTime() - lastDamaged.ticks < AREA_DAMAGE_INTERVAL) continue;
//			lastDamaged.worldId = le.getWorld().getUID();
//			lastDamaged.ticks = now;
//			lastDamagedEntities.put(le.getEntityId(), lastDamaged);
			le.damage(event.getDamage() * AREA_DAMAGE_MULTIPLIER, attacker);
		}
	}

	private void cleaverAttackByPlayer(EntityDamageByEntityEvent event, ItemStack weapon, Player attacker,
									   Entity victim, Location loc)
	{
		final PotionEffect slowEffect = attacker.getPotionEffect(PotionEffectType.SLOW_DIGGING);
		final boolean hasSlowEffect = slowEffect != null;
		final double damageDivisor = hasSlowEffect
											 ? Math.max(DAMAGE_MULTIPLIER, (slowEffect.getAmplifier() / 2.75))
											 : 1;
		attacker.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 27, 4));
		event.setDamage(event.getDamage() * DAMAGE_MULTIPLIER / damageDivisor);

		if (hasSlowEffect || attacker.isSneaking()) return;

		final World world = loc.getWorld();
		final Collection<Entity> nearbyEntities = world.getNearbyEntities(attacker.getLocation(),
																		  AREA_DAMAGE_HORIZONTAL_PLANE_RADIUS,
																		  AREA_DAMAGE_HORIZONTAL_PLANE_RADIUS,
																		  AREA_DAMAGE_VERTICAL_RADIUS,
																		  filterOtherEntities(attacker, victim));

		final Location attackerEyeLocation = attacker.getLocation().add(0, 1.5, 0);
		final World attackerWorld = attackerEyeLocation.getWorld();
		if (attackerWorld != null)
		{
			attackerWorld.spawnParticle(Particle.SWEEP_ATTACK, attackerEyeLocation, 1);
			attackerWorld.spawnParticle(Particle.CRIT, attackerEyeLocation, 1);
		}

		for (final Entity nearbyEntity : nearbyEntities)
		{
			final LivingEntity le = (LivingEntity) nearbyEntity;
//			final LastDamage lastDamaged = lastDamagedEntities.getOrDefault(le.getEntityId(),
//																			new LastDamage(le.getEntityId(),
//																						   le.getWorld().getUID()));
//			final long now = nearbyEntity.getWorld().getFullTime();
//			final World prevWorld = tm.getServer().getWorld(lastDamaged.worldId);
//			if (prevWorld != null && prevWorld.getFullTime() - lastDamaged.ticks < AREA_DAMAGE_INTERVAL) continue;
//			lastDamaged.worldId = le.getWorld().getUID();
//			lastDamaged.ticks = now;
//			lastDamagedEntities.put(le.getEntityId(), lastDamaged);
			le.damage(event.getDamage() * AREA_DAMAGE_MULTIPLIER, attacker);
		}
	}

	private void projectileAttack(EntityDamageByEntityEvent event)
	{
	}
}
