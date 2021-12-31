package Commands;

import org.bukkit.command.CommandSender;

import com.cookiecool.TopMiners.GeneralMessages;

import APIHandlers.ConfigHandler;
import APIHandlers.LeaderBoardHandler;

public class topmreload {

	public void CommandFired(CommandSender sender) {
		ConfigHandler.reloadConfig();
		LeaderBoardHandler.reloadCurrentConfig();
		sender.sendMessage(GeneralMessages.getChatPrefix()+GeneralMessages.reloadedConfig);
	}

}
