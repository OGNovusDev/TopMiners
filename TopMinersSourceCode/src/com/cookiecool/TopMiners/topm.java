package com.cookiecool.TopMiners;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import APIHandlers.ConfigHandler;
import Commands.topmreload;
import Commands.topmstats;
import Commands.topmtop;

public class topm implements CommandExecutor {
	
	
	public HashMap<String, Class<?>> getCommandClassesTable() {
		HashMap<String, Class<?>> commandClassesTable = new HashMap<>();
		
		commandClassesTable.put("top", topmtop.class);
		commandClassesTable.put("stats", topmstats.class);
		commandClassesTable.put("reload", topmreload.class);
		
		return commandClassesTable;
	}
	
	public boolean HasPermission(CommandSender sender, String command) {
		if(sender.hasPermission("topminers."+command) || ConfigHandler.getBoolean(command+"RequiresPermission") == false) {
			return true;
		} else {
			return false;
		}
	}
	
	public void seperateCommand(CommandSender sender, String command) {
		if(HasPermission(sender, command)) {
			
			Class<?> commandClass = getCommandClassesTable().get(command);
			try {
				Method commandFired = commandClass.getDeclaredMethod("CommandFired", CommandSender.class);
				commandFired.invoke(commandClass.getDeclaredConstructor().newInstance(), sender);
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException e) {
				e.printStackTrace();
			}
			
		} else {//Lacks permission
			sender.sendMessage(GeneralMessages.getChatPrefix()+GeneralMessages.noPerm);
		}
		
	}
	
	public void sendIfHasPermission(CommandSender sender, String command, String message) {//send a help line if player has that permissions use for "/topm" no args
	
		if(HasPermission(sender, command)) {
			sender.sendMessage(message);
		}
		
	}
		
	public void noArgCommand(CommandSender sender) {// "/topm" command aka help command with no arguments
		if(sender instanceof Player) {
			
			if(HasPermission(sender, "help")) {
				
				sendIfHasPermission(sender, "help", ChatColor.AQUA+"/topm help"+ChatColor.GRAY+" - Help command");
				sendIfHasPermission(sender, "top", ChatColor.AQUA+"/topm top"+ChatColor.GRAY+" - View leaderboard of blocks broken");
				sendIfHasPermission(sender, "stats", ChatColor.AQUA+"/topm stats"+ChatColor.GRAY+" - View your stats of blocks broken");
				sendIfHasPermission(sender, "reload", ChatColor.AQUA+"/topm top"+ChatColor.GRAY+" - Reload config.yml");
				
			} else {//Lacks permissions
				sender.sendMessage(GeneralMessages.getChatPrefix()+GeneralMessages.noPerm);
			}
			
		} else {//isn't player aka console
			sender.sendMessage(GeneralMessages.consolePrefix+GeneralMessages.notPlayer);
		}
		
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length > 0 && !(args[0].equalsIgnoreCase("help"))) {//Isn't "topm" aka help command or "/topm help" aka the same
			String command = args[0].toLowerCase();
			if (getCommandClassesTable().containsKey(command)) {
				seperateCommand(sender, command);
			} else {//Invalid arguments
				sender.sendMessage(GeneralMessages.invalidArgs);
			}
			
		} else { //Is "topm" aka help command
			
			noArgCommand(sender);
			
		}
		return true;
	}

}
