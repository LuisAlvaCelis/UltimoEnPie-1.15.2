package me.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDamageEvent;

import me.main.MainClass;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class SourceManager {
	
	private static SourceManager instance;
	private File file;
	private YamlConfiguration yml;
	private Location respawnLocation;
	private List<String> lpBB;
	
	
	public SourceManager() {
		this.file=new File(MainClass.getInstance().getDataFolder()+File.separator+"database.yml");
		this.yml=YamlConfiguration.loadConfiguration(file);
		this.lpBB=new ArrayList<>();
	}
	
	public void addPlayerToListBB(String playername) {
		this.lpBB.add(playername);
	}
	
	public List<String> getListBB() {
		return this.lpBB;
	}
	
	public boolean isEvokerDropsEnabled() {
		return MainClass.getInstance().getConfig().getBoolean("Game.evoker-drops");
	}
	
	public void setEntityDamageSpeedHealth(Entity entity,String nameMob) {
        
		if(MainClass.getInstance().getConfig().getDouble("Game.custom-mobs.custom-"+nameMob+".damage")!=-1) {
			MobAttributes.getInstance().setAttackDamage(entity, MainClass.getInstance().getConfig().getDouble("Game.custom-mobs.custom-"+nameMob+".damage"));
		}
		if(MainClass.getInstance().getConfig().getDouble("Game.custom-mobs.custom-"+nameMob+".speed")!=-1) {
			MobAttributes.getInstance().setMobSpeed(entity, (float)MainClass.getInstance().getConfig().getDouble("Game.custom-mobs.custom-"+nameMob+".speed"));
		}
		if(MainClass.getInstance().getConfig().getInt("Game.custom-mobs.custom-"+nameMob+".health")!=-1) {
			MobAttributes.getInstance().setMaxHealth(entity, MainClass.getInstance().getConfig().getInt("Game.custom-mobs.custom-"+nameMob+".health"));
		}
	}
	
	public void setStatusEvokerDrops(boolean status) {
		MainClass.getInstance().getConfig().set("Game.evoker-drops", status);
		MainClass.getInstance().saveConfig();
	}
	
	public boolean isStart() {
		return MainClass.getInstance().getConfig().getBoolean("Game.status");
	}
	
	public void setStart(boolean status) {
		this.setStatusEvokerDrops(!status);
		MainClass.getInstance().getConfig().set("Game.status", status);
		MainClass.getInstance().saveConfig();
		SimpleDateFormat sdf=new SimpleDateFormat("dd");
		int day=Integer.parseInt(sdf.format(Calendar.getInstance().getTime()));
		sdf=new SimpleDateFormat("MM");
		int month=Integer.parseInt(sdf.format(Calendar.getInstance().getTime()));
		sdf=new SimpleDateFormat("yyyy");
		int year=Integer.parseInt(sdf.format(Calendar.getInstance().getTime()));
		sdf=new SimpleDateFormat("hh:mm:ss aa");
		String hour=sdf.format(Calendar.getInstance().getTime());
		this.setDateStart(day, month, year, hour,status);
	}
	public int getDayStart() {
		return this.yml.getInt("System-date.date-start.day");
	}
	
	public void setDateStart(int day,int month,int year,String hour,boolean status) {
		try {
			this.yml.set("System-date.date-count.status",status);
			this.yml.set("System-date.date-start.day",day);
			this.yml.set("System-date.date-start.month",month);
			this.yml.set("System-date.date-start.year",year);
			this.yml.set("System-date.date-start.hour",hour);
			this.yml.save(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static SourceManager getInstance() {
		if(instance==null) {
			instance=new SourceManager();
		}
		return instance;
	}
	
	 @SuppressWarnings("incomplete-switch")
	public String dcString(EntityDamageEvent.DamageCause dc)
	  {
	    switch (dc)
	    {
	    case LAVA: 
	    	return "lava";
	    case BLOCK_EXPLOSION:
	    	return "block_explosion";
	    case WITHER: 
	    	return "wither";
	    case FIRE_TICK:
	    	return "fire_tick";
	    case CONTACT:
	    	return "contact";
	    case LIGHTNING: 
	    	return "lightning";
	    case ENTITY_ATTACK:
	    	return "entity";
	    case THORNS: 
	    	return "thorns";
	    case ENTITY_EXPLOSION:
	    	return "entity_explosion";
	    case FALL: 
	    	return "fall";
	    case FIRE:
	    	return "fire";
	    case MELTING: 
	    	return "melting";
	    case SUFFOCATION:
	    	return "suffocation";
	    case FALLING_BLOCK:
	    	return "falling_block";
	    case STARVATION: 
	    	return "starvation";
	    case CUSTOM:
	    	return "others";
	    case PROJECTILE: 
	    	return "projectile";
	    case DROWNING: 
	    	return "drowning";
	    case POISON: 
	    	return "poison";
	    case VOID:
	    	return "void";
	    case MAGIC: 
	    	return "magic";
	    case SUICIDE:
	    	return "suicide";
	    }
	    return "others";
	  }
	
	public void setRespawnLocation(Location location) {
		this.respawnLocation=location;
	}
	
	public Location getRespawnLocation() {
		return this.respawnLocation;
	}
	
	public boolean isWhitelistEnable() {
		return MainClass.getInstance().getConfig().getBoolean("wl.status");
	}
	
	public List<String> getListPlayers(){
		List<String> list=new ArrayList<>();
		list=yml.getStringList("Whitelist.list");
		return list;
	}
	
	public void addPlayerToList(String player) {
		try {
			List<String> list=new ArrayList<>();
			list=yml.getStringList("Whitelist.list");
			list.add(player);
			this.yml.set("Whitelist.list", list);
			this.yml.save(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removePlayerFromList(String player) {
		try {
			List<String> list=new ArrayList<>();
			list=yml.getStringList("Whitelist.list");
			list.remove(player);
			this.yml.set("Whitelist.list", list);
			this.yml.save(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public YamlConfiguration getDatabaseFile() {
		return this.yml;
	}
	
	public void saveDatabaseFile() {
		try {
			this.yml.save(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	public void sendTweet(String victim) {
		ConfigurationBuilder cb=new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("f9cPCrsUU5a7pjzh0eioKm39i")
		  .setOAuthConsumerSecret("meWIhPBpTuBtH4VaeN486NaAPTVC1aNxobCu0FvJjVd69v8Qos")
		  .setOAuthAccessToken("999070844562354176-4DYS6DnKJ88JbLQovcHONfwvmbNsMia")
		  .setOAuthAccessTokenSecret("dNXjqJCF0JH2WZ3u3IKkg5sIi5UXR5E79AsGQxL0Glsxx");
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter=tf.getInstance();
		
		Status status=null;
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");
		TimeZone tz=TimeZone.getTimeZone("GMT");
		Calendar.getInstance().setTimeZone(tz);
		String date=sdf.format(Calendar.getInstance().getTime());
		String tweet="UltimoEnPie [1.15.2] [Actualización "+date+"]\n \n» "+victim+" ha muerto.\n» Quedan "+getListPlayers().size()+" jugadores\n\nGracias por participar.";
		try {
			status=twitter.updateStatus(tweet);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assert (status==null);
	}
	
}
