package Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.cookiecool.TopMiners.GeneralMessages;

import APIHandlers.LeaderBoardHandler;

public class topmtop {

	public void CommandFired(CommandSender sender) {
		if(sender instanceof Player) {
			Player Player = (Player) sender;
			
			for(Integer i=1;i<11;i++) {
				
				String currentSpotString = LeaderBoardHandler.getTopString(i);
				if(currentSpotString != null) {//Make sure it is a player spot
					
					Player.sendMessage(GeneralMessages.leaderBoardNumberColor+i+". "+GeneralMessages.leaderBaordPlayerColor+currentSpotString);
					
				} else {//Reach the top spot where players end, break to avoid pointless looping
					break;
				}
			}
			
		} else { //isn't player aka console
			sender.sendMessage(GeneralMessages.consolePrefix+GeneralMessages.notPlayer);
		}
	}

	
}
