package me.main;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.commands.GetTimePlayerCMD;
import me.commands.MainCMD;
import me.listeners.Listeners;
import me.utils.SourceCode;


public class MainClass extends JavaPlugin{
	
	private static MainClass instance;
	
	public static MainClass getInstance() {
		if(instance==null) {
			instance=new MainClass();
		}
		return instance;
	}
	
	@Override
	public void onEnable() {
		instance=this;
		this.getServer().getConsoleSender().sendMessage(SourceCode.translateColor("&aUltimoEnPie has been enabled."));
		this.registerEvents();
		this.registerCommands();
		this.registerYML();
		SourceCode.registerItems();
	}
	
	@Override
	public void onDisable() {
		instance=null;
		this.getServer().getConsoleSender().sendMessage(SourceCode.translateColor("&cUltimoEnPie has been disabled."));
	}
	
	private void registerEvents() {
		PluginManager pm=Bukkit.getServer().getPluginManager();
		pm.registerEvents(new Listeners(), this);
	}
	
	private void registerCommands() {
		this.getCommand("gettime").setExecutor(new GetTimePlayerCMD());
		this.getCommand("main").setExecutor(new MainCMD());
	}
	
	private void registerYML() {
		try {
			File file1=new File(this.getDataFolder(),"config.yml");
			File file2=new File("plugins/UltimoEnPie-1.15.2/database.yml");
			if(!file1.exists()) {
				this.getConfig().options().copyDefaults(true);
				this.saveConfig();
				if(!file2.exists()) {
					file2.createNewFile();
					this.saveResource("database.yml", true);
				}
			}else if(!file2.exists()) {
				file2.createNewFile();
				this.saveResource("database.yml", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
