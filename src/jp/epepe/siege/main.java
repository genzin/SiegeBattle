package jp.epepe.siege;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import jp.epepe.siege.Library.codes;
import jp.epepe.siege.Listener.EventListener;

public class main extends JavaPlugin implements Listener{
	public EventListener el = null;

	BossBar bb = Bukkit.createBossBar("ちんちん", BarColor.PURPLE, BarStyle.SEGMENTED_10, BarFlag.DARKEN_SKY);

	public void onEnable(){
		el = new EventListener(this);

		bb.setProgress(1);
	}

	public void onDisable(){

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

					if(cmd.equalsIgnoreCase("ad")){
						bb.addPlayer(p);
						double current = bb.getProgress();
						codes.sendPlayer(p, "" + current);
					}
					if(cmd.equalsIgnoreCase("a")){
						double current = bb.getProgress();
						bb.setProgress(current -0.1);
						codes.sendPlayer(p, "" + current);
					}
					if(cmd.equalsIgnoreCase("b")){
						double current = bb.getProgress();
						bb.setProgress(current +0.1);
						codes.sendPlayer(p, "" + current);
					}
					if(cmd.equalsIgnoreCase("c")){
						bb.removePlayer(p);
						p.sendMessage("a");
					}
				}
			}

		}
		return ret;

	}

}
