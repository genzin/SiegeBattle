package jp.epepe.siege.Library;

import java.lang.reflect.Field;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.boss.BossBar;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import net.minecraft.server.v1_11_R1.ChatMessage;
import net.minecraft.server.v1_11_R1.IChatBaseComponent;
import net.minecraft.server.v1_11_R1.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_11_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_11_R1.PacketPlayOutTitle.EnumTitleAction;

public class lib {

	static Random rnd = new Random();

	static public void sendPlayer(Player p, String msg){
		if (p != null){
			p.sendMessage(msg);
		}else{
			Bukkit.getLogger().info(msg);
			return;
		}
	}



	static public void Soundworld(Location loc, Player p, Sound s ,Float f ,Float volume){
		float pitch = rnd.nextInt(4)*f;
		p.getWorld().playSound(p.getLocation(), s, volume, pitch + 0.4F);
	}

	static public void SoundPlayer(Player p, Sound s , Float f){
		float pitch = rnd.nextInt(4) *f;
		p.playSound(p.getLocation(), s, 10, pitch + 0.4F);
	}

	static public void SoundAllPlayer(Sound s, Float f){
		for(Player p : Bukkit.getOnlinePlayers()){
			float pitch = rnd.nextInt(4)*f;
			p.playSound(p.getLocation(), s, 10, pitch + 0.4F);
		}
	}

	@SuppressWarnings("deprecation")
	static public void SoundTeamPlayer(Team t ,Sound s, Float f){
		for(Player p : Bukkit.getOnlinePlayers()){
			if(t.hasPlayer(p)){
				float pitch = rnd.nextInt(4)*f;
				p.playSound(p.getLocation(), s, 10, pitch + 0.4F);
			}
		}
	}

	public static void sendTitle(String main, String sub) {
		for(Player players : Bukkit.getOnlinePlayers()) {
			IChatBaseComponent text = new ChatMessage(main);
			PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, text);

			IChatBaseComponent subtext = new ChatMessage(sub);
			PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, subtext);

			CraftPlayer cp = (CraftPlayer) players;
			cp.getHandle().playerConnection.sendPacket(title);
			cp.getHandle().playerConnection.sendPacket(subtitle);
		}
	}

	public static void sendTitleTarget(String main, String sub, Player target) {
			IChatBaseComponent text = new ChatMessage(main);
			PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, text);

			IChatBaseComponent subtext = new ChatMessage(sub);
			PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, subtext);

			CraftPlayer cp = (CraftPlayer) target;
			cp.getHandle().playerConnection.sendPacket(title);
			cp.getHandle().playerConnection.sendPacket(subtitle);
	}

	public static void sendTab(Player player, String head, String foot){
		IChatBaseComponent header = new ChatMessage(head);
		IChatBaseComponent footer = new ChatMessage(foot);
		PacketPlayOutPlayerListHeaderFooter tablist = new PacketPlayOutPlayerListHeaderFooter();
		try {
			Field headerField = tablist.getClass().getDeclaredField("a");
			headerField.setAccessible(true);
			headerField.set(tablist, header);
			headerField.setAccessible(!headerField.isAccessible());
			Field footerField = tablist.getClass().getDeclaredField("b");
			footerField.setAccessible(true);
			footerField.set(tablist, footer);
			footerField.setAccessible(!footerField.isAccessible());
		} catch (Exception e) {
			e.printStackTrace();
		}

		CraftPlayer cp = (CraftPlayer) player;
		cp.getHandle().playerConnection.sendPacket(tablist);
	}

	public static void sendBossBar(BossBar bb){

	}

	public static void clearBossBar(BossBar bb){
		for(Player p : Bukkit.getOnlinePlayers()){
			bb.removePlayer(p);
			sendPlayer(null, "BossBarをリセットしました。");
		}
	}

}
