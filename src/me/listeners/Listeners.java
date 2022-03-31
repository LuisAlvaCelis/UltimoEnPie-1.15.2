package me.listeners;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftCreeper;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftEntity;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.SmallFireball;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.entity.Wither;
import org.bukkit.entity.minecart.ExplosiveMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import me.main.MainClass;
import me.utils.SourceCode;
import me.utils.SourceManager;
import net.minecraft.server.v1_15_R1.EntityLiving;
import net.minecraft.server.v1_15_R1.NBTTagCompound;

public class Listeners implements Listener {
	

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerLogin(PlayerLoginEvent e) {
		if(SourceManager.getInstance().isWhitelistEnable()==true && !SourceManager.getInstance().getListPlayers().contains(e.getPlayer().getName()) 
				&& !e.getPlayer().hasPermission("main.*")) {
			e.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, SourceCode.translateColor("&cError: You can not enter the game because the whitelist is &a&lON"));
		}else {
			e.allow();
		}
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player player=e.getPlayer();
		e.setJoinMessage(SourceCode.translateColor("&7[&a+&7] &c"+player.getName()+"&f&o ha entrado al servidor."));
		player.sendMessage(" ");
		player.sendMessage(SourceCode.translateColor("&b --- [Bienvenido al servidor] ---"));
		player.sendMessage(SourceCode.translateColor("&7&n------------------------------------------------"));
		player.sendMessage(SourceCode.translateColor("&aNormas: Ser activo, pasarla bien."));
		player.sendMessage(" ");
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		Player player=e.getPlayer();
		e.setQuitMessage(SourceCode.translateColor("&7[&c-&7] &c"+player.getName()+"&7&o ha salido del servidor."));
	}
	
	@EventHandler
	public void onAsyncPlayerChat(AsyncPlayerChatEvent e) {
		e.setCancelled(true);
		Bukkit.getServer().broadcastMessage(SourceCode.translateColor("&a"+e.getPlayer().getName()+"&7: &e"+e.getMessage()));
	}
	
	/*@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player player=(Player)e.getWhoClicked();
		if(e.getSlotType()==SlotType.ARMOR) {
			if(player.getInventory().getHelmet()==null) {
				if((e.getCurrentItem()!=null) && e.getCurrentItem().getItemMeta().getLocalizedName().equalsIgnoreCase("1D")) {
					
					player.getInventory().setHelmet(e.getCurrentItem());
				}
			}
		}
	}*/
	
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent e) {
		Player player=(Player)e.getPlayer();
		if(player.getInventory().getBoots()!=null) {
			if(player.getInventory().getBoots().getItemMeta().getLocalizedName().equalsIgnoreCase("bootsBrayan")) {
				if(!SourceManager.getInstance().getListBB().contains(player.getName())) {
					SourceManager.getInstance().addPlayerToListBB(player.getName());
					player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,Integer.MAX_VALUE,1));
					player.sendMessage(SourceCode.translateColor("&aGenial, ahora eres el brayan, consigueme un celular plox."));
				}
			}else {
				if(SourceManager.getInstance().getListBB().contains(player.getName())) {
					SourceManager.getInstance().getListBB().remove(player.getName());
					player.removePotionEffect(PotionEffectType.SPEED);
					player.sendMessage(SourceCode.translateColor("&cDejaste de ser el brayan, ahora ponte a trabajar plox."));
				}				
			}
		}else {
			if(SourceManager.getInstance().getListBB().contains(player.getName())) {
				SourceManager.getInstance().getListBB().remove(player.getName());
				player.removePotionEffect(PotionEffectType.SPEED);
				player.sendMessage(SourceCode.translateColor("&cDejaste de ser el brayan, ahora ponte a trabajar plox."));
			}
		}
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		if (e.getDamager().getType() == EntityType.ARROW) {
			ProjectileSource source = ((Arrow)e.getDamager()).getShooter();
			if ((source instanceof Skeleton) && (MainClass.getInstance().getConfig().getInt("Game.custom-mobs.custom-skeleton.damage") != -1)) {
				e.setDamage(MainClass.getInstance().getConfig().getDouble("Game.custom-mobs.custom-skeleton.damage"));
			}
		}else if (e.getDamager().getType() == EntityType.FIREBALL){
	      	ProjectileSource source = ((Fireball)e.getDamager()).getShooter();
	      	if ((source instanceof Ghast) && (MainClass.getInstance().getConfig().getInt("Game.custom-mobs.custom-ghast.damage") != -1)) {
	      		e.setDamage(MainClass.getInstance().getConfig().getDouble("Game.custom-mobs.custom-ghast.damage"));
		      }
	    }else if (e.getDamager().getType() == EntityType.SMALL_FIREBALL){
	    	ProjectileSource source = ((SmallFireball)e.getDamager()).getShooter();
	    	if ((source instanceof Blaze) && (MainClass.getInstance().getConfig().getInt("Game.custom-mobs.custom-blaze.damage") != -1)) {
	    		e.setDamage(MainClass.getInstance().getConfig().getDouble("Game.custom-mobs.custom-blaze.damage"));
	    	}
	    }
	}
	
	@EventHandler
	public void onCraftItem(CraftItemEvent e) {
		ItemStack is=e.getCurrentItem();
		if((e.getWhoClicked() instanceof Player)) {
			Player player=(Player)e.getWhoClicked();
			if(is.getItemMeta().getLocalizedName()!=null) {
				
				if(is.getItemMeta().getLocalizedName().equalsIgnoreCase("bX1")) {
					if(player.getLevel()<20) {
						player.sendMessage(SourceCode.translateColor("&cError: Tienes que tener 20 o más niveles para poder craftear el item."));
						e.setCancelled(true);
					}else {
						player.setLevel((player.getLevel()-20));
					}
				}else if(is.getItemMeta().getLocalizedName().equalsIgnoreCase("bX2")) {
					if(player.getLevel()<20) {
						player.sendMessage(SourceCode.translateColor("&cError: Tienes que tener 20 o más niveles para poder craftear el item."));
						e.setCancelled(true);
					}else {
						player.setLevel((player.getLevel()-20));
					}
				}else if(is.getItemMeta().getLocalizedName().equalsIgnoreCase("bX3")) {
					if(player.getLevel()<20) {
						player.sendMessage(SourceCode.translateColor("&cError: Tienes que tener 20 o más niveles para poder craftear el item."));
						e.setCancelled(true);
					}else {
						player.setLevel((player.getLevel()-20));
					}
				}else if(is.getItemMeta().getLocalizedName().equalsIgnoreCase("bX4")) {
					if(player.getLevel()<20) {
						player.sendMessage(SourceCode.translateColor("&cError: Tienes que tener 20 o más niveles para poder craftear el item."));
						e.setCancelled(true);
					}else {
						player.setLevel((player.getLevel()-20));
					}
				}else if(is.getItemMeta().getLocalizedName().equalsIgnoreCase("bX5")) {
					if(player.getLevel()<20) {
						player.sendMessage(SourceCode.translateColor("&cError: Tienes que tener 20 o más niveles para poder craftear el item."));
						e.setCancelled(true);
					}else {
						player.setLevel((player.getLevel()-20));
					}
				}else if(is.getItemMeta().getLocalizedName().equalsIgnoreCase("1D")) {
					if(player.getLevel()<25) {
						player.sendMessage(SourceCode.translateColor("&cError: Tienes que tener 25 o más niveles para poder craftear el item."));
						e.setCancelled(true);
					}else {
						player.setLevel((player.getLevel()-25));
					}
				}else if(is.getItemMeta().getLocalizedName().equalsIgnoreCase("2D")) {
					if(player.getLevel()<25) {
						player.sendMessage(SourceCode.translateColor("&cError: Tienes que tener 25 o más niveles para poder craftear el item."));
						e.setCancelled(true);
					}else {
						player.setLevel((player.getLevel()-25));
					}
				}else if(is.getItemMeta().getLocalizedName().equalsIgnoreCase("3D")) {
					if(player.getLevel()<25) {
						player.sendMessage(SourceCode.translateColor("&cError: Tienes que tener 25 o más niveles para poder craftear el item."));
						e.setCancelled(true);
					}else {
						player.setLevel((player.getLevel()-25));
					}
				}else if(is.getItemMeta().getLocalizedName().equalsIgnoreCase("4D")) {
					if(player.getLevel()<25) {
						player.sendMessage(SourceCode.translateColor("&cError: Tienes que tener 25 o más niveles para poder craftear el item."));
						e.setCancelled(true);
					}else {
						player.setLevel((player.getLevel()-25));
					}
				}if(is.getItemMeta().getLocalizedName().equalsIgnoreCase("5D")) {
					if(player.getLevel()<25) {
						player.sendMessage(SourceCode.translateColor("&cError: Tienes que tener 25 o más niveles para poder craftear el item."));
						e.setCancelled(true);
					}else {
						player.setLevel((player.getLevel()-25));
					}
				}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent e) {
		if(SourceManager.getInstance().isStart()==true) {
			Entity entity=e.getEntity();
			if(entity instanceof LivingEntity) {
				LivingEntity le=(LivingEntity)entity;
				if(le.getType() == EntityType.ZOMBIE) {
					SourceManager.getInstance().setEntityDamageSpeedHealth(entity, "zombie");
				}else if(le.getType()==EntityType.SKELETON) {
					SourceManager.getInstance().setEntityDamageSpeedHealth(entity, "skeleton");
				}else if(le.getType()==EntityType.SPIDER) {
					SourceManager.getInstance().setEntityDamageSpeedHealth(entity, "spider");
				}else if(le.getType() == EntityType.CREEPER) {
					Creeper c=(Creeper)entity;
		    		if(c.isPowered()==true) {
		    			if(MainClass.getInstance().getConfig().getInt("Game.custom-mobs.custom-changed-creeper.speed-explosion")!=-1) {
		    				CraftCreeper cc = (CraftCreeper)entity;
							net.minecraft.server.v1_15_R1.Entity nms = ((CraftEntity) cc).getHandle();
					        NBTTagCompound nbttag = new NBTTagCompound();
					        nms.c(nbttag);
					        nbttag.setInt("Fuse", MainClass.getInstance().getConfig().getInt("Game.custom-mobs.custom-changed-creeper.speed-explosion"));
					        EntityLiving livingcreeper = (EntityLiving) nms;
					        livingcreeper.a(nbttag);
						}
		    		}else {
		    			if(MainClass.getInstance().getConfig().getInt("Game.custom-mobs.custom-creeper.speed-explosion")!=-1) {
		    				CraftCreeper cc = (CraftCreeper)entity;
							net.minecraft.server.v1_15_R1.Entity nms = ((CraftEntity) cc).getHandle();
					        NBTTagCompound nbttag = new NBTTagCompound();
					        nms.c(nbttag);
					        nbttag.setInt("Fuse", MainClass.getInstance().getConfig().getInt("Game.custom-mobs.custom-creeper.speed-explosion"));
					        EntityLiving livingcreeper = (EntityLiving) nms;
					        livingcreeper.a(nbttag);
						}
		    		}
			        
					SourceManager.getInstance().setEntityDamageSpeedHealth(entity, "changed-creeper");
					SourceManager.getInstance().setEntityDamageSpeedHealth(entity, "creeper");
				}else if(le.getType()==EntityType.ENDERMAN) {
						SourceManager.getInstance().setEntityDamageSpeedHealth(entity, "enderman");
				}else if(le.getType()==EntityType.WOLF) {
					SourceManager.getInstance().setEntityDamageSpeedHealth(entity, "wolf");
				}else if(le.getType()==EntityType.CAVE_SPIDER) {
					SourceManager.getInstance().setEntityDamageSpeedHealth(entity, "cave-spider");
				}else if(le.getType()==EntityType.SLIME) {
					SourceManager.getInstance().setEntityDamageSpeedHealth(entity, "slime");
				}else if(le.getType()==EntityType.SILVERFISH) {
					SourceManager.getInstance().setEntityDamageSpeedHealth(entity, "silverfish");
				}else if(le.getType()==EntityType.PIG_ZOMBIE) {
					SourceManager.getInstance().setEntityDamageSpeedHealth(entity, "zombie-pigman");
				}else if(le.getType()==EntityType.GHAST) {
					if(MainClass.getInstance().getConfig().getInt("Game.custom-mobs.custom-ghast.health")!=-1) {
						((Ghast)entity).setMaxHealth(MainClass.getInstance().getConfig().getInt("Game.custom-mobs.custom-ghast.health"));
						((Ghast)entity).setHealth(((Ghast)entity).getMaxHealth());
					}
				}else if(le.getType()==EntityType.MAGMA_CUBE) {
						SourceManager.getInstance().setEntityDamageSpeedHealth(entity, "magma-cube");
				}else if(le.getType()==EntityType.BLAZE) {
					((Blaze)entity).setSwimming(false);
					if(MainClass.getInstance().getConfig().getInt("Game.custom-mobs.custom-blaze.fire-rate")!=-1) {
						
					}
					if(MainClass.getInstance().getConfig().getInt("Game.custom-mobs.custom-blaze.health")!=-1) {
						((Blaze)entity).setMaxHealth(MainClass.getInstance().getConfig().getInt("Game.custom-mobs.custom-blaze.health"));
						((Blaze)entity).setHealth(((Ghast)entity).getMaxHealth());
					}
					SourceManager.getInstance().setEntityDamageSpeedHealth(entity, "blaze");
				}else if(le.getType()==EntityType.IRON_GOLEM) {
					SourceManager.getInstance().setEntityDamageSpeedHealth(entity, "iron-golem");
				}else if(le.getType()==EntityType.ENDER_DRAGON) {
					SourceManager.getInstance().setEntityDamageSpeedHealth(entity, "ender-dragon");
				}else if(le.getType()==EntityType.DROWNED) {
					SourceManager.getInstance().setEntityDamageSpeedHealth(entity, "drowned");
				}else if(le.getType()==EntityType.EVOKER) {
					SourceManager.getInstance().setEntityDamageSpeedHealth(entity, "evoker");
				}else if(le.getType()==EntityType.VEX) {
					SourceManager.getInstance().setEntityDamageSpeedHealth(entity, "vex");
				}else if(le.getType()==EntityType.PHANTOM) {
					if(MainClass.getInstance().getConfig().getInt("Game.custom-mobs.custom-phantom.health")!=-1) {
						((Phantom)entity).setMaxHealth(MainClass.getInstance().getConfig().getDouble("Game.custom-mobs.custom-phantom.health"));
						((Phantom)entity).setHealth(((Phantom)entity).getMaxHealth());
					}
					SourceManager.getInstance().setEntityDamageSpeedHealth(entity, "phantom");
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		e.setDeathMessage(null);
		Player victim=e.getEntity();
		EntityDamageEvent ede = victim.getLastDamageCause();
		EntityDamageEvent.DamageCause dc = ede.getCause();
		if((e.getEntity().getKiller() instanceof Player)) {
			if(SourceManager.getInstance().isStart()==true) {
				Player killer=e.getEntity().getKiller();
				SourceManager.getInstance().removePlayerFromList(victim.getName());
				victim.setHealth(20.0D);
				victim.setFoodLevel(20);
				victim.setLevel(0);
				victim.setTotalExperience(0);
				victim.setGameMode(GameMode.SPECTATOR);
				SourceCode.sendMessageToAll(SourceCode.translateColor("&a"+victim.getName()+" &eha muerto por &a"+killer.getName()));			
				new BukkitRunnable() {
					
					@Override
					public void run() {
						SourceCode.sendMessageToAll(SourceCode.translateColor("&e"+victim.getName()+" ha sido expulsado del servidor, F en el chat chavales."));
						victim.setGameMode(GameMode.SURVIVAL);
						victim.kickPlayer(SourceCode.translateColor("&7Has sido asesinado por "+killer.getName()+"\nSuerte para la próxima crack :)"));
						cancel();
					}
				}.runTaskLater(MainClass.getInstance(), (20*10));
				if(SourceManager.getInstance().getListPlayers().size()==1) {
					
				}
				SourceManager.getInstance().sendTweet(victim.getName());
			}
		}else {
			if(SourceManager.getInstance().isStart()==true) {
				SourceManager.getInstance().removePlayerFromList(victim.getName());
				victim.setHealth(20.0D);
				victim.setFoodLevel(20);
				victim.setLevel(0);
				victim.setTotalExperience(0);
				victim.setGameMode(GameMode.SPECTATOR);
				SourceCode.sendMessageToAll(SourceCode.translateColor("&a"+victim.getName()+" &eha muerto a causa de &a"+MainClass.getInstance().getConfig().getStringList("Death.message."+SourceManager.getInstance().dcString(dc))).replace("[", "").replace("]", ""));
				new BukkitRunnable() {
					@Override
					public void run() {
						SourceCode.sendMessageToAll(SourceCode.translateColor("&e"+victim.getName()+" ha sido expulsado del servidor, F en el chat chavales."));
						victim.setGameMode(GameMode.SURVIVAL);
						victim.kickPlayer(SourceCode.translateColor("&7Has muerto a causa de\n"+MainClass.getInstance().getConfig().getStringList("Death.message."+SourceManager.getInstance().dcString(dc)).toString().replace("[", "").replace("]", "")+"\n&7Suerte para la próxima crack :)"));
						cancel();
					}
				}.runTaskLater(MainClass.getInstance(), (20*10));
				SourceManager.getInstance().sendTweet(victim.getName());
			}
		}
	}
	
	@EventHandler
	public void onEntityDeathPlayer(EntityDeathEvent e) {
		if((e.getEntity().getType()==EntityType.EVOKER )) {
			if(SourceManager.getInstance().isEvokerDropsEnabled()==false) {
				e.getDrops().clear();
			}
		}
	}
	
	@EventHandler
	public void onExplosionPrime(ExplosionPrimeEvent e) {
		if(SourceManager.getInstance().isStart()==true) {
			Entity entity = e.getEntity();
		    EntityType et = entity != null ? entity.getType() : EntityType.UNKNOWN;
		    if (((entity instanceof Creeper)) 
		    		|| ((entity instanceof TNTPrimed)) 
		    		|| ((entity instanceof Fireball)) 
		    		|| ((entity instanceof Wither)) 
		    		|| ((entity instanceof EnderDragon)) 
		    		|| ((entity instanceof ExplosiveMinecart)) 
		    		|| ((entity instanceof EnderCrystal)) 
		    		|| (entity == null)) {
		    	if(et==EntityType.CREEPER) {
		    		Creeper creeper=(Creeper)entity;
		    		if(creeper.isPowered()==true) {
		    			if(MainClass.getInstance().getConfig().getDouble("Game.custom-mobs.custom-changed-creeper.radius")!=-1) {
		    				e.setRadius(Float.valueOf(MainClass.getInstance().getConfig().getString("Game.custom-mobs.custom-changed-creeper.radius")));
					    	e.setFire(true);
		    			}
		    		}else {
		    			if(MainClass.getInstance().getConfig().getDouble("Game.custom-mobs.custom-creeper.radius")!=-1) {
		    				e.setRadius(Float.valueOf(MainClass.getInstance().getConfig().getString("Game.custom-mobs.custom-creeper.radius")));
					    	e.setFire(true);
		    			}
		    		}
		    	}
		    }
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onEntityExplode(EntityExplodeEvent e) {
		Entity ent = e.getEntity();
		if (ent.getType()==EntityType.CREEPER) {
			if(SourceManager.getInstance().isStart()==true) {
				Location loc = e.getEntity().getLocation();
				Random rand=new Random();
				for(Block entry:e.blockList()) {
					if (80 > rand.nextInt(100)) {
						Location entryLoc = entry.getLocation();
						Vector blockVel = entryLoc.toVector().subtract(loc.toVector()).normalize();
						blockVel.add(new Vector(0.0D, 0.7D, 0.0D));
						FallingBlock fb = entry.getWorld().spawnFallingBlock(entryLoc.add(0.0D, 0.6D, 0.0D), entry.getType(), entry.getData());
						fb.setVelocity(blockVel);
						fb.setDropItem(false);				
					}
				}
				
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
