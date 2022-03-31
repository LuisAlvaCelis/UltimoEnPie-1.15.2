package me.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.utils.SourceCode;

public class GetTimePlayerCMD implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender,Command cmd, String label,String[] args) {
		if(!cmd.getName().equalsIgnoreCase("gettime")) {
			return false;
		}else {
			if(sender instanceof Player) {
				Player player=(Player)sender;
				if(player.hasPermission("main.*")) {
					if(args.length<1) {
						player.sendMessage(SourceCode.translateColor("&cError: Coming soon"));
					}else {
						player.sendMessage(SourceCode.translateColor("&cError: Coming soon"));
					}
				}else {
					player.sendMessage(SourceCode.translateColor("&cError: You don't have permission to use the command /main"));
				}
			}else {
				sender.sendMessage(SourceCode.translateColor("&cError: You don't have permission to execute the command '/main' with the console."));
			}
		}
		return false;
	}


}
