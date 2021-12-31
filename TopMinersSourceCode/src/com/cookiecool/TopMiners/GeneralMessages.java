package com.cookiecool.TopMiners;

import org.bukkit.ChatColor;

import APIHandlers.ConfigHandler;

public class GeneralMessages {

	public static String consolePrefix = ChatColor.DARK_GRAY+"["+ChatColor.AQUA+"TopMiners"+ChatColor.DARK_GRAY+"]"+ChatColor.WHITE+" - ";
	
	public static String getChatPrefix() {
		return ChatColor.translateAlternateColorCodes('&', ConfigHandler.getString("Prefix"))+" ";
	}
	
	public static String notPlayer = (ChatColor.RED+"Only players can use this command!");
	public static String noPerm = (ChatColor.RED+"You lack the permissions to use this command!");
	public static String invalidArgs = (ChatColor.RED+"Invalid arguments! \"/topm help\" for help");
	
	public static String statReplyColor = ChatColor.AQUA.toString();
	public static String statReplyBlocksColor = ChatColor.WHITE+ChatColor.BOLD.toString();
	public static String statReply = statReplyColor+"You have broken a total of ";
	
	public static String reloadedConfig = ChatColor.GREEN+"Config file reloaded!";
	
	public static String leaderBoardNumberColor = ChatColor.AQUA.toString()+ChatColor.BOLD.toString();
	public static String leaderBaordPlayerColor = ChatColor.GRAY.toString();
}
