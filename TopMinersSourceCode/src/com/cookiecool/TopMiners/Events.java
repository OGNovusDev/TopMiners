package com.cookiecool.TopMiners;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class Events implements Listener {
	static Plugin plugin = Main.plugin;
	public static FileConfiguration config = plugin.getConfig();
	
	
	ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
}
