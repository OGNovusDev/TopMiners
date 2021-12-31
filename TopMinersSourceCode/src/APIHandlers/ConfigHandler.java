package APIHandlers;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import com.cookiecool.TopMiners.GeneralMessages;
import com.cookiecool.TopMiners.Main;

public class ConfigHandler {
	
	static Plugin plugin = Main.plugin;
	static ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
	
	public static FileConfiguration config = plugin.getConfig();
	
	public static void startUp() {
		ensureConfigExistence();
	}
	
	public static void ensureConfigExistence() {//Ensure config.yml exist, if not create it. Used on reload
		File configFile = new File(plugin.getDataFolder(), "config.yml");
		if(!configFile.exists()) {//Config doesn't exist create it
			config.options().copyDefaults(true);
			plugin.saveDefaultConfig();
		}
	}
	
	public static void reloadConfig() {
		console.sendMessage(GeneralMessages.consolePrefix+"Config.YML reloading");
		ensureConfigExistence();
		plugin.reloadConfig();
		config = plugin.getConfig();
		console.sendMessage(GeneralMessages.consolePrefix+"Config.YML reloaded");
	}
	
	public static Integer getInteger(String Index) {
		return config.getInt(Index);
	}
	
	public static Boolean getBoolean(String Index) {
		return config.getBoolean(Index);
	}
	
	public static String getString(String Index) {
		return config.getString(Index);
	}
	
	
}
