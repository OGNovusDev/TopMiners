package APIHandlers;

import java.io.File;
import java.io.IOException;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import com.cookiecool.TopMiners.Main;

public class DataHandler {
	
	static Plugin plugin = Main.plugin;
	
	public static Integer getStatByPlayer(Player player) {//Get stat of blocks broken of player
		File dataFolder = ensureDataFolderExistence();
		File playerFile = ensurePlayerDataExistence(dataFolder, player.getUniqueId().toString(), player.getName());
		
		YamlConfiguration Config = YamlConfiguration.loadConfiguration(playerFile);
		Integer blocksBroken = Config.getInt("BlocksBroken");
		return blocksBroken;
	}
	
	public static Integer getStatByOfflinePlayer(OfflinePlayer player) {//Get stat of blocks broken of player
		File dataFolder = ensureDataFolderExistence();
		File playerFile = ensurePlayerDataExistence(dataFolder, player.getUniqueId().toString(), player.getName());
		
		YamlConfiguration Config = YamlConfiguration.loadConfiguration(playerFile);
		Integer blocksBroken = Config.getInt("BlocksBroken");
		return blocksBroken;
	}
	
	public static Integer addBlockBreak(Player player) {//Increase block break value in player dataFile
		File dataFolder = ensureDataFolderExistence();
		File playerFile = ensurePlayerDataExistence(dataFolder, player.getUniqueId().toString(), player.getName());
		
		YamlConfiguration Config = YamlConfiguration.loadConfiguration(playerFile);
		Integer newBlockAmount = Config.getInt("BlocksBroken") + 1;
		Config.set("Username", player.getName());;//Ensure username is updated
		Config.set("BlocksBroken", newBlockAmount);
		
		try {
			Config.save(playerFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return newBlockAmount;
	}	
	
	private static File ensureDataFolderExistence() { //Ensures the datafolder does exist
		File playerDataFolder = new File(plugin.getDataFolder(), "PlayerData");
		playerDataFolder.mkdirs();
		return playerDataFolder;
	}
	
	private static File ensurePlayerDataExistence(File playerDataFolder, String playerUUID, String playerName) {//Ensures player has a data.yml file and returns it
		File playerFile = new File(playerDataFolder, playerUUID + ".yml");
		if (!playerFile.exists()) {//Doesnt exist so create it
			try {
				playerFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			setDefaultData(playerFile, playerUUID, playerName);//Sets default data in new playerDataFile
		}
		return playerFile;
		
	}
	
	private static void setDefaultData(File playerFile, String playerUUID, String playerName) {
		YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(playerFile);
		defaultConfig.set("Username", playerName);
		defaultConfig.set("UUID", playerUUID);
		defaultConfig.set("BlocksBroken", 0);
		
		try {
			defaultConfig.save(playerFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
