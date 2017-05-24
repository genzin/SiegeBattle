package jp.epepe.siege;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import jp.epepe.siege.Library.lib;
import jp.epepe.siege.Listener.EventListener;

public class main extends JavaPlugin implements Listener{


	public EventListener el = null;

	BossBar bb = Bukkit.createBossBar("ああん", BarColor.PURPLE, BarStyle.SOLID, BarFlag.DARKEN_SKY);
	boolean start = false;


	public void onEnable(){
		el = new EventListener(this);
		Timer();
		FirstSettings();
	}

	public void onDisable(){
		bb.removeAll();
	}

	@Override
	public boolean onCommand(CommandSender s, Command c, String cl, String[] args) {
		boolean ret = false;
		Player p = null;



		//float pitch = rnd.nextInt(4) * 0.2F;

		if (s instanceof Player) p = (Player)s ;

		if (c.getName().equalsIgnoreCase("sb")) {

			if(!p.isOp()){
				return ret;
			}
			if(args.length > 0){
				String cmd = args[0];
				if(cmd != null){

					if(cmd.equalsIgnoreCase("start")){
						start = true;
					}

					if(cmd.equalsIgnoreCase("stop")){
						start = false;
					}


					if(cmd.equalsIgnoreCase("ad")){
						bb.addPlayer(p);
						double current = bb.getProgress();
						lib.sendPlayer(p, "" + current);
					}
					if(cmd.equalsIgnoreCase("a")){
						bb.setTitle("ちんこ");
					}
					if(cmd.equalsIgnoreCase("b")){
						bb.setTitle("ぱいぱん");
					}
					if(cmd.equalsIgnoreCase("c")){
						bb.setTitle("おまんまん");
					}
				}
			}

		}
		return ret;

	}
	double time = 120D;

	public void FirstSettings(){
		bb.setProgress(1);
		time = 120D;
	}

	public void Timer(){


		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){

			@Override
			public void run() {
				if(start == false){

					bb.removeAll();

				} else {

					time--;

					for(Player a : Bukkit.getOnlinePlayers()){
						lib.sendPlayer(a, "" + time + " " + time/120D);
						bb.setProgress(time/120D);
						bb.removePlayer(a);

						bb.addPlayer(a);
					}
					if(time == 0){
						start = false;
					}
				}
			}

		}, 0L, 20L);
	}



	public Location configLocation(String xyz) {
		int x1 = getConfig().getInt(xyz + ".x");
		int y1 = getConfig().getInt(xyz + ".y");
		int z1 = getConfig().getInt(xyz + ".z");
		String st = getConfig().getString(xyz + ".world");
		World w1 = Bukkit.getWorld(st);
		Location loc = new Location(w1 , x1 +0.5D , y1 +0.5D , z1 +0.5D);
		return loc;
	}

	public void configLocationTeleport(String xyz , Player p, Boolean Spawn) {
		int x1 = getConfig().getInt(xyz + ".x");
		int y1 = getConfig().getInt(xyz + ".y");
		int z1 = getConfig().getInt(xyz + ".z");
		String st = getConfig().getString(xyz + ".world");
		World w1 = Bukkit.getWorld(st);

		Location loc = new Location(w1 , x1 +0.5D , y1 +0.5D , z1 +0.5D);

		loc.setYaw(p.getLocation().getYaw());
		loc.setPitch(p.getLocation().getPitch());
		p.teleport(loc);

		if(Spawn == true){
			p.setBedSpawnLocation(loc, true);
		}

		lib.SoundPlayer(p, Sound.ENTITY_ENDERMEN_TELEPORT, 0.2F);
	}


}
