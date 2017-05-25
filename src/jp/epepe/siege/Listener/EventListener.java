package jp.epepe.siege.Listener;

import java.util.Random;

import org.bukkit.event.Listener;

import jp.epepe.siege.main;

public class EventListener implements Listener {
	Random rnd = new Random();

	private main plugin;

	public EventListener(main plugin) {
		this.plugin = plugin;

		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);

		return;
	}


























































}
