package jp.epepe.siege;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import jp.epepe.siege.Listener.EventListener;

public class main extends JavaPlugin implements Listener{
	public EventListener el = null;

	public void onEnable(){
		el = new EventListener(this);
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

					if(cmd.equalsIgnoreCase("test")){
						p.sendMessage("ちんこ");
					}
				}
			}

		}
		return ret;

	}

}
