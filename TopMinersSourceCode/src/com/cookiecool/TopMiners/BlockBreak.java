package com.cookiecool.TopMiners;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;

import APIHandlers.DataHandler;
import APIHandlers.LeaderBoardHandler;

public class BlockBreak implements Listener {
	
	static Plugin plugin = Main.plugin;
	
	ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
	
	@EventHandler
	public void blockBreak(BlockBreakEvent event) {
		Player Player = event.getPlayer();
		Integer newBlocksBroken = DataHandler.addBlockBreak(Player);
		LeaderBoardHandler.blockBroke(Player, newBlocksBroken);
	}
	
}
