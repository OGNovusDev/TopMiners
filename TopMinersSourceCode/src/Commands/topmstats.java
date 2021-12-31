package Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.cookiecool.TopMiners.GeneralMessages;

import APIHandlers.DataHandler;

public class topmstats {

	public void CommandFired(CommandSender sender) {
		if(sender instanceof Player) {
			Player Player = (Player) sender;
			Integer blocksBroken = DataHandler.getStatByPlayer(Player);
			
			Player.sendMessage(GeneralMessages.getChatPrefix()+GeneralMessages.statReply+GeneralMessages.statReplyBlocksColor+blocksBroken+GeneralMessages.statReplyColor+" Blocks!");
			
		} else { //isn't player aka console
			sender.sendMessage(GeneralMessages.consolePrefix+GeneralMessages.notPlayer);
		}
	}

}
