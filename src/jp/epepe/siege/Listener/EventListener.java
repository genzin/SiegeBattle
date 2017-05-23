package jp.epepe.siege.Listener;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import jp.epepe.siege.main;

public class EventListener implements Listener {
	Random rnd = new Random();

	private main plugin;

	public EventListener(main plugin) {
		this.plugin = plugin;

		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);

		return;
	}

	@EventHandler
	public void playerFirstJoinEvent(PlayerJoinEvent b){
		Player p = b.getPlayer();
		Location loc = new Location(p.getWorld(), 0.5, 64.5, 0.5);

		p.teleport(loc);
	}

}
