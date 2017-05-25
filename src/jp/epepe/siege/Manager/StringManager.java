package jp.epepe.siege.Manager;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.event.Listener;

import jp.epepe.siege.main;

public class StringManager  implements Listener {
	Random rnd = new Random();

	private main plugin;

	public StringManager(main plugin) {
		this.plugin = plugin;

		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);

		return;
	}


	public String prefixconfig = plugin.getConfig().getString("prefix.string");
	public String prefix = ChatColor.GOLD +  prefixconfig +ChatColor.RESET;

	public String Permission = prefix + "あなたには権限がありません。";
	public String UnknownCommand = prefix + "コマンドが存在しません。 /sb helpでコマンドを確認してください。";







	public String Join01 = ChatColor.DARK_PURPLE + "> Siege Battle <";
	public String Join02 = ChatColor.BLACK + "Join to the";
	public String Join03 = ChatColor.BLACK + "Game";

}
