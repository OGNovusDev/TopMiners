package com.cookiecool.TopMiners;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import APIHandlers.ConfigHandler;
import APIHandlers.LeaderBoardHandler;
import PlaceHolderAPI.PlaceHolders;

public class Main extends JavaPlugin {

	public static Main plugin;
	
	public String prefix = GeneralMessages.consolePrefix;
	ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
	
	@Override
	public void onEnable() {
		plugin = this;
		ConfigHandler.startUp();
		LeaderBoardHandler.startUp();
		getCommand("topm").setExecutor((CommandExecutor) new topm());
		getServer().getPluginManager().registerEvents(new BlockBreak(), this);
		if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PlaceHolders().register();
		}
		console.sendMessage(prefix+"is now loaded");
	}
	
	@Override
	public void onDisable() {
		console.sendMessage(prefix+"stopping");
	}
	
}
