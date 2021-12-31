package PlaceHolderAPI;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.cookiecool.TopMiners.Main;

import APIHandlers.DataHandler;
import APIHandlers.LeaderBoardHandler;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class PlaceHolders extends PlaceholderExpansion {
	
	static Plugin plugin = Main.plugin;
	static ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

	@Override
    public boolean canRegister() {
        return (plugin.getServer().getPluginManager().getPlugin("PlaceHolderAPI")!=null);
    }
	
	@Override
    public String getAuthor() {
        return "cookiecool/Novus";
    }
    
    @Override
    public String getIdentifier() {
        return "topminers";
    }
    
    @Override
    public String getPlugin() {
        return null;
    }

    @Override
    public String getVersion() {
        return "1.0.1";
    }

    @Override
    public String onPlaceholderRequest(Player player, String params) {
        if(params.equalsIgnoreCase("blocksbroken")) {//Blocks broken
            return DataHandler.getStatByPlayer(player).toString();
        }
        
        if(params.substring(0,3).equalsIgnoreCase("top")) {//Top spot request
        	Integer spot = Integer.parseInt(params.substring(4));
        	if(spot != null) {
        		String topString = LeaderBoardHandler.getTopString(spot);
        		if(topString != null) {//null check cause returning null lieks to break other plugins.
        			return topString;
        		} else {
        			return "";
        		}
        	}
        }
        
        return null; // Placeholder is unknown by the Expansion
    }

}
