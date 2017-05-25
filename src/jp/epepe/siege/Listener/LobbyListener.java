package jp.epepe.siege.Listener;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import jp.epepe.siege.main;
import jp.epepe.siege.Library.lib;
import jp.epepe.siege.Manager.StringManager;

public class LobbyListener implements Listener {
	Random rnd = new Random();

	private main plugin;

	public LobbyListener(main plugin) {
		this.plugin = plugin;

		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);

		return;
	}


	@EventHandler
	public void playerFirstJoinEvent(PlayerJoinEvent b){
		Player p = b.getPlayer();
		Location loc = new Location(p.getWorld(), 0.5, 64.5, 0.5);
		if(plugin.JoinPlayers.containsKey(p.getName()) == true){
			p.teleport(loc);
		} else {

		}
	}

	@EventHandler
	public void onSignCreate(SignChangeEvent e) {

		String line1 = e.getLine(0);
		//String line2 = e.getLine(1);
		if(e.getPlayer().isOp()){

			if (line1.equals("sietp")) {
				e.setLine(0, ChatColor.RED + "[GenT]");
			}

			if (line1.equals("sj")) {
				e.setLine(0, StringManager.Join01);
				e.setLine(1, StringManager.Join02);
				e.setLine(2, StringManager.Join03);
			}

		}
	}

	@EventHandler
	public void QuitEvent(PlayerQuitEvent b){
		Player p = b.getPlayer();
		if(plugin.JoinPlayers.get(p.getName()).equalsIgnoreCase("join")){
			plugin.JoinPlayers.remove(p.getName());
			lib.sendPlayer(null, plugin.JoinPlayers.get(p.getName()));
		}
	}



	@EventHandler
	public void JoinEvent(PlayerInteractEvent e) {

		Player p = e.getPlayer();

		Action act = e.getAction();
		Block clickedBlock = e.getClickedBlock();

		if (act == Action.RIGHT_CLICK_BLOCK || act == Action.LEFT_CLICK_BLOCK) {
			if(!(clickedBlock.getType()==Material.SIGN || clickedBlock.getType()==Material.SIGN_POST || clickedBlock.getType()==Material.WALL_SIGN))
				return;
			Sign sign = (Sign) clickedBlock.getState();
			String line2 = sign.getLine(1);
			String line3 = sign.getLine(2);

			if (line2.contains(StringManager.Join02)){
				if(line3.contains(StringManager.Join03)) {
					if(plugin.JoinPlayers.containsKey(p.getName()) == true){
						Bukkit.broadcastMessage("ちんこ");
					} else {
						plugin.JoinPlayers.put(p.getName(), "Join");
						Bukkit.broadcastMessage("まんこ");
					}

				}
			}
		}
	}
}


























































