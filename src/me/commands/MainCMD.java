package me.commands;

import java.text.DecimalFormat;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.main.MainClass;
import me.utils.SourceCode;
import me.utils.SourceManager;

public class MainCMD implements CommandExecutor{

	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!cmd.getName().equalsIgnoreCase("main")) {
			return false;
		}else {
			if(sender instanceof Player) {
				Player player=(Player)sender;
				FileConfiguration config=MainClass.getInstance().getConfig();
				if(player.hasPermission("main.*")) {
					if(args.length<1) {
						player.sendMessage(SourceCode.translateColor("&cError: Usage /main <setspawn,spawn,wl,start,pvp,reload>"));
					}else {
						if(args[0].equalsIgnoreCase("pvp")) {
							if(Bukkit.getServer().getWorld("world").getPVP()==true && Bukkit.getServer().getWorld("world_nether").getPVP()==true && Bukkit.getServer().getWorld("world_the_end").getPVP()==true) {
								Bukkit.getServer().getWorld("world").setPVP(false);
								Bukkit.getServer().getWorld("world_nether").setPVP(false);
								Bukkit.getServer().getWorld("world_the_end").setPVP(false);
								player.sendMessage(SourceCode.translateColor("&ePVP has been &cdisabled &ein all words"));
							}else {
								Bukkit.getServer().getWorld("world").setPVP(true);
								Bukkit.getServer().getWorld("world_nether").setPVP(true);
								Bukkit.getServer().getWorld("world_the_end").setPVP(true);
								player.sendMessage(SourceCode.translateColor("&ePVP has been &aenabled &ein all words"));
							}
						}else if(args[0].equalsIgnoreCase("reload")) {
							MainClass.getInstance().reloadConfig();
							player.sendMessage(SourceCode.translateColor("&aUltimoEnPie has been reloaded."));
						}else if(args[0].equalsIgnoreCase("start")) {
							if(SourceManager.getInstance().isStart()==false) {
								SourceManager.getInstance().setStart(true);
								SourceCode.sendMessageToAll("&e&lBIENVENIDOS SEAN TODOS A ULTIMO EN PIE BETA!");
								new BukkitRunnable() {
									int i=5;
									@Override
									public void run() {
										i--;
										if(i>0) {
											SourceCode.sendMessageToAll("&aEl juego comenzará en "+i+"segundos..");
											SourceCode.sendSoundToAll();											
										}
										if(i==0) {
											SourceCode.sendMessageToAll("&e&lMUCHA SUERTE A TODOS Y QUE GANE EL ULTIMO EN PIE!");
											cancel();
										}
									}
								}.runTaskTimer(MainClass.getInstance(), 20L,20L);
							}else {
								SourceManager.getInstance().setStart(false);
								SourceCode.sendMessageToAll("&cError: Se ha detenido el juego.");
							}
						}else if(args[0].equalsIgnoreCase("setspawn")) {
							Location location=player.getLocation();
							config.set("Config.spawnsettings.status", true);
							config.set("Config.spawnsettings.world", location.getWorld().getName());
							config.set("Config.spawnsettings.x", location.getX());
							config.set("Config.spawnsettings.y", location.getY());
							config.set("Config.spawnsettings.z", location.getZ());
							config.set("Config.spawnsettings.yaw", location.getYaw());
							config.set("Config.spawnsettings.pitch", location.getPitch());
							MainClass.getInstance().saveConfig();
							Collection<? extends Player> allPlayers;
							int j=(allPlayers=Bukkit.getServer().getOnlinePlayers()).size();
							for(int i=0;i<j;i++) {
								Player playerOnline=(Player)allPlayers.toArray()[i];
								playerOnline.getInventory().clear();
								playerOnline.getInventory().setArmorContents(null);
								playerOnline.setHealth(20.0D);
								playerOnline.setFoodLevel(20);
								playerOnline.setLevel(0);
								playerOnline.teleport(location);
							}
							DecimalFormat df=new DecimalFormat("##0.00");
							player.sendMessage(SourceCode.translateColor("&aSpawn has been sttled in world: "+player.getWorld().getName()+" in the location:"));
							player.sendMessage(SourceCode.translateColor("&eX: "+df.format(location.getX())+" Y: "+df.format(location.getY())+" Z: "+df.format(location.getZ())));
						}else if(args[0].equalsIgnoreCase("spawn")) {
							player.teleport(SourceCode.getLocationSpawnYML());
							player.sendMessage(SourceCode.translateColor("&aYou has been teleported to spawn."));
						}else if(args[0].equalsIgnoreCase("wl")) {
							
							if(args[1].equalsIgnoreCase("list")) {
								player.sendMessage(SourceCode.translateColor("&aWhitelist list:"));
								for(int i=0;i<SourceManager.getInstance().getListPlayers().size();i++) {
									player.sendMessage(SourceCode.translateColor("&a - "+SourceManager.getInstance().getListPlayers().get(i)));
								}
							}else if(args[1].equalsIgnoreCase("add")) {
								OfflinePlayer target=Bukkit.getServer().getOfflinePlayer(args[2]);
								if(!SourceManager.getInstance().getListPlayers().contains(target.getName())) {
									SourceManager.getInstance().addPlayerToList(target.getName());
									player.sendMessage(SourceCode.translateColor("&a"+target.getName()+" has been add to whitelist."));
								}else {
									player.sendMessage(SourceCode.translateColor("&cError: "+target.getName()+" is already whitelisted."));
								}
							}else if(args[1].equalsIgnoreCase("remove")) {
								OfflinePlayer target=Bukkit.getServer().getOfflinePlayer(args[2]);
								if(SourceManager.getInstance().getListPlayers().contains(target.getName())) {
									SourceManager.getInstance().removePlayerFromList(target.getName());
									player.sendMessage(SourceCode.translateColor("&a"+target.getName()+" has been remove from whitelist."));
								}else {
									player.sendMessage(SourceCode.translateColor("&cError: "+target.getName()+" is not whitelisted."));
								}
							}else if(args[1].equalsIgnoreCase("on")) {
								if(SourceManager.getInstance().isWhitelistEnable()==false) {
									MainClass.getInstance().getConfig().set("wl.status", true);
									MainClass.getInstance().saveConfig();
									player.sendMessage(SourceCode.translateColor("&aWhitelist: &6ON"));
								}else {
									player.sendMessage(SourceCode.translateColor("&7Whitelis is already ON."));
								}
							}else if(args[1].equalsIgnoreCase("off")) {
								if(SourceManager.getInstance().isWhitelistEnable()==true) {
									MainClass.getInstance().getConfig().set("wl.status", false);
									MainClass.getInstance().saveConfig();
									player.sendMessage(SourceCode.translateColor("&aWhitelist: &cOFF"));
								}else {
									player.sendMessage(SourceCode.translateColor("&7Whitelis is already OFF."));
								}
							}else {
								player.sendMessage(SourceCode.translateColor("&cError: Usage /main <wl> <add,remove,on,off>"));								
							}
						}else {
							player.sendMessage(SourceCode.translateColor("&cError: Usage /main <setspawn,spawn,wl,start,pvp,reload>"));
						}
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
