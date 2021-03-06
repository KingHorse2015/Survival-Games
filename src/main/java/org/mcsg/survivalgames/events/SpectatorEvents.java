package org.mcsg.survivalgames.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.mcsg.survivalgames.Game;
import org.mcsg.survivalgames.GameManager;

public class SpectatorEvents implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockPlace(BlockPlaceEvent event) {
		if (GameManager.getInstance().isSpectator(event.getPlayer())) {
			event.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockDamage(BlockDamageEvent event) {
		if (GameManager.getInstance().isSpectator(event.getPlayer())) {
			event.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockBreak(BlockBreakEvent event) {
		if (GameManager.getInstance().isSpectator(event.getPlayer())) {
			event.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerClickEvent(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		try {
			if (GameManager.getInstance().isSpectator(player) && player.isSneaking() && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_AIR) || GameManager.getInstance().isSpectator(player) && player.isSneaking() && (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_AIR)) {
				Player[] players = GameManager.getInstance().getGame(GameManager.getInstance().getPlayerSpectateId(player)).getPlayers()[0];
				Game g = GameManager.getInstance().getGame(GameManager.getInstance().getPlayerSpectateId(player));

				int i = g.getNextSpec().get(player);
				if ((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_AIR)) {
					i++;
				} else if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_AIR) {
					i--;
				}
				if (i > players.length - 1) {
					i = 0;
				}
				if (i < 0) {
					i = players.length - 1;
				}
				g.getNextSpec().put(player, i);
				Player tpto = players[i];
				player.teleport(tpto.getLocation());
				player.sendMessage(ChatColor.AQUA + "You are now spectating " + tpto.getName());
			} else if (GameManager.getInstance().isSpectator(player)) {
				event.setCancelled(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onItemPickup(PlayerPickupItemEvent event) {
		if (GameManager.getInstance().isSpectator(event.getPlayer())) {
			event.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityDamage(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player) {
			Player player = (Player) event.getDamager();
			if (GameManager.getInstance().isSpectator(player)) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityDamage(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			if (GameManager.getInstance().isSpectator(player)) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityTarget(EntityTargetEvent event) {
		if (event.getTarget() instanceof Player) {
			Player player = (Player) event.getTarget();
			if (GameManager.getInstance().isSpectator(player)) {
				event.setCancelled(true);
			}
		}
	}
}
