package APIHandlers;

import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import com.cookiecool.TopMiners.Main;

public class LeaderBoardHandler {
	
	static Plugin plugin = Main.plugin;
	
	static YamlConfiguration currentConfig;
	
	public static String getTopString(Integer Spot) {//Get stat of blocks broken of player and put in a string for /topm top
		//File leaderBoardFile = ensureLeaderboardFileExistence();//We use current config rather than line below to avoid constant loading aka memory leak
		//YamlConfiguration Config = YamlConfiguration.loadConfiguration(leaderBoardFile);
		ensureLeaderboardFileExistence();
		
		String playerName = currentConfig.getString(Spot+".Player");
		Integer blocksBroken = currentConfig.getInt(Spot+".BlocksBroken");
		
		String Combine = playerName+" - "+blocksBroken;
		if(blocksBroken > 0) {//Ensure a player is in that spot
			return Combine;
		} else {
			return null;//Player isn't in that spot return null for easier pickup
		}
		
	}
	
	public static void blockBroke(Player player, Integer blocksBroken) {//Block broke check if player new value is on the leaderboard
		File leaderBoardFile = ensureLeaderboardFileExistence();
		//YamlConfiguration Config = YamlConfiguration.loadConfiguration(leaderBoardFile);
		
		ensureLeaderboardFileExistence();
		
		String playerUUID = player.getUniqueId().toString();
		
		for(Integer i=1;i<11;i++) {
			if(currentConfig.getInt(i+".BlocksBroken")<blocksBroken) {
				String currentSpotUUID = currentConfig.getString(i+".UUID");
				if(!currentSpotUUID.equalsIgnoreCase(playerUUID)) {//Make sure your not pumping the same player over and over
					player.sendMessage(currentSpotUUID+" | "+playerUUID+" | "+i);
					newLeaderBoardPlayer(leaderBoardFile, player, i, blocksBroken);
				} else {//Spot is the same player just new blocks, update blocks and name
					currentConfig.set(i+".Player", player.getName());
					currentConfig.set(i+".UUID", playerUUID);
					currentConfig.set(i+".BlocksBroken", blocksBroken);
					
					try {
						currentConfig.save(leaderBoardFile);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
				break;
			}
		}
		
	}	
	
	public static void newLeaderBoardPlayer(File leaderBoardFile, Player player, Integer newSpot, Integer blocksBroken) {//Rearanges leaderboard
		//YamlConfiguration Config = YamlConfiguration.loadConfiguration(leaderBoardFile);
		for(Integer i=9;i>newSpot;i--) {
			Integer changeTo = i+1;
			String changePlayer = currentConfig.getString(i+".Player");
			Integer changeBlocksBroken = currentConfig.getInt(i+".BlocksBroken");
			String changeUUID = currentConfig.getString(i+".UUID");
			currentConfig.set(changeTo+".Player", changePlayer);
			currentConfig.set(changeTo+".UUID", changeUUID);	
			currentConfig.set(changeTo+".BlocksBroken", changeBlocksBroken);	
		}
		
		currentConfig.set(newSpot+".Player", player.getName());
		currentConfig.set(newSpot+".UUID", player.getUniqueId().toString());
		currentConfig.set(newSpot+".BlocksBroken", blocksBroken);	
		
		try {
			currentConfig.save(leaderBoardFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private static File ensureLeaderboardFileExistence() {//Ensures player has a data.yml file and returns it
		File leaderBoardFile = new File(plugin.getDataFolder(), "leaderboard.yml");
		if (!leaderBoardFile.exists()) {//Doesnt exist so create it
			try {
				leaderBoardFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			setDefaultData(leaderBoardFile);//Sets default data in new playerDataFile
		}
		return leaderBoardFile;
		
	}
	
	private static void setDefaultData(File leaderBoardFile) {
		YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(leaderBoardFile);
		
		for(Integer i=1;i<11;i++)  {
			defaultConfig.set(i+".Player", "");
			defaultConfig.set(i+".UUID", "");
			defaultConfig.set(i+".BlocksBroken", 0);
		}
		
		try {
			defaultConfig.save(leaderBoardFile);
			defaultConfig = currentConfig;;//Make it a variable
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void reloadCurrentConfig() {//reloads currentConfig to current leaderboardfile
		File leaderBoardFile = ensureLeaderboardFileExistence();
		currentConfig = YamlConfiguration.loadConfiguration(leaderBoardFile);;
	}
	
	public static void startUp() {//Startup aka load leaderboardfile as currentConfig
		reloadCurrentConfig();
	}
	
	
}
