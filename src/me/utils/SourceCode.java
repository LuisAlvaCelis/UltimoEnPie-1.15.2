package me.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import me.main.MainClass;
public class SourceCode {
	
	@SuppressWarnings("deprecation")
	public static void registerItems() {
		/*ItemStack is=new ItemStack(Material.LEATHER_BOOTS,1);
		is.addEnchantment(Enchantment.DURABILITY, 1);
		im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		ShapedRecipe sr=new ShapedRecipe(is);
		sr.shape(new String[] {"ABC","CCC","CCC"});
		sr.setIngredient('A', Material.SUGAR_CANE).setIngredient('B', Material.LEATHER_BOOTS);
		List<String> lore=new ArrayList<>();
		lore.add(SourceCode.translateColor("&7Habilidad:"));
		lore.add(SourceCode.translateColor("&7- Velocidad II"));
		ItemMeta im=is.getItemMeta();
		im.setDisplayName(translateColor("&aBotas del brayan"));
		im.setLore(lore);
		im.setLocalizedName("bootsBrayan");
		im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		is.setItemMeta(im);
		Bukkit.getServer().addRecipe(sr);*/
		
		ItemStack is1=new ItemStack(Material.BOOK,1);
		ItemMeta im1=is1.getItemMeta();
		List<String> lore=new ArrayList<>();
		lore.add(SourceCode.translateColor("&7Encantamiento:"));
		lore.add(SourceCode.translateColor("&7- Resistencia X"));
		lore.add(SourceCode.translateColor("&7- Irrompibilidad V"));
		lore.add(SourceCode.translateColor("&7- Reparación"));
		lore.add(SourceCode.translateColor("&7- Respiración Acuática"));
		im1.setLocalizedName("bX1");
		im1.setDisplayName(translateColor("&aLibro X Casco"));
		im1.setLore(lore);
		is1.setItemMeta(im1);
		ShapedRecipe sr=new ShapedRecipe(is1);
		sr.shape(new String[] {"EBE","D1D","EDE"});
		sr.setIngredient('E', Material.EMERALD_BLOCK);
		sr.setIngredient('B', Material.BOOK);
		sr.setIngredient('D', Material.DIAMOND_BLOCK);
		sr.setIngredient('1', Material.DIAMOND_HELMET);
		Bukkit.getServer().addRecipe(sr);
		
		ItemStack is2=new ItemStack(Material.BOOK,1);
		ItemMeta im2=is2.getItemMeta();
		lore.clear();
		lore.add(SourceCode.translateColor("&7Encantamiento:"));
		lore.add(SourceCode.translateColor("&7- Resistencia X"));
		lore.add(SourceCode.translateColor("&7- Irrompibilidad V"));
		lore.add(SourceCode.translateColor("&7- Reparación"));
		lore.add(SourceCode.translateColor("&7- Espinas IV"));
		im2.setLocalizedName("bX2");
		im2.setDisplayName(translateColor("&aLibro X Pechera"));
		im2.setLore(lore);
		is2.setItemMeta(im2);
		sr=new ShapedRecipe(is2);
		sr.shape(new String[] {"EBE","D2D","EDE"});
		sr.setIngredient('E', Material.EMERALD_BLOCK);
		sr.setIngredient('B', Material.BOOK);
		sr.setIngredient('D', Material.DIAMOND_BLOCK);
		sr.setIngredient('2', Material.DIAMOND_CHESTPLATE);
		Bukkit.getServer().addRecipe(sr);
		
		ItemStack is3=new ItemStack(Material.BOOK,1);
		ItemMeta im3=is3.getItemMeta();
		lore.clear();
		lore.add(SourceCode.translateColor("&7Encantamiento:"));
		lore.add(SourceCode.translateColor("&7- Resistencia X"));
		lore.add(SourceCode.translateColor("&7- Irrompibilidad V"));
		lore.add(SourceCode.translateColor("&7- Reparación"));
		im3.setLocalizedName("bX3");
		im3.setDisplayName(translateColor("&aLibro X Pantalón"));
		im3.setLore(lore);
		is3.setItemMeta(im3);
		sr=new ShapedRecipe(is3);
		sr.shape(new String[] {"EBE","D3D","EDE"});
		sr.setIngredient('E', Material.EMERALD_BLOCK);
		sr.setIngredient('B', Material.BOOK);
		sr.setIngredient('D', Material.DIAMOND_BLOCK);
		sr.setIngredient('3', Material.DIAMOND_LEGGINGS);
		Bukkit.getServer().addRecipe(sr);
		
		ItemStack is4=new ItemStack(Material.BOOK,1);
		ItemMeta im4=is4.getItemMeta();
		lore.clear();
		lore.add(SourceCode.translateColor("&7Encantamiento:"));
		lore.add(SourceCode.translateColor("&7- Resistencia X"));
		lore.add(SourceCode.translateColor("&7- Irrompibilidad V"));
		lore.add(SourceCode.translateColor("&7- Reparación"));
		lore.add(SourceCode.translateColor("&7- Caída de pluma IV"));
		im4.setLocalizedName("bX4");
		im4.setDisplayName(translateColor("&aLibro X Botas"));
		im4.setLore(lore);
		is4.setItemMeta(im4);
		sr=new ShapedRecipe(is4);
		sr.shape(new String[] {"EBE","D4D","EDE"});
		sr.setIngredient('E', Material.EMERALD_BLOCK);
		sr.setIngredient('B', Material.BOOK);
		sr.setIngredient('D', Material.DIAMOND_BLOCK);
		sr.setIngredient('4', Material.DIAMOND_BOOTS);
		Bukkit.getServer().addRecipe(sr);
		
		ItemStack is5=new ItemStack(Material.BOOK,1);
		ItemMeta im5=is5.getItemMeta();
		lore.clear();
		lore.add(SourceCode.translateColor("&7Encantamiento:"));
		lore.add(SourceCode.translateColor("&7- Filo XV"));
		lore.add(SourceCode.translateColor("&7- Golpeo XV"));
		lore.add(SourceCode.translateColor("&7- Irrompibilidad IV"));
		lore.add(SourceCode.translateColor("&7- Reparación"));
		lore.add(SourceCode.translateColor("&7- Suerte"));
		im5.setLocalizedName("bX5");
		im5.setDisplayName(translateColor("&aLibro X Espada"));
		im5.setLore(lore);
		is5.setItemMeta(im5);
		sr=new ShapedRecipe(is5);
		sr.shape(new String[] {"EBE","D5D","EDE"});
		sr.setIngredient('E', Material.EMERALD_BLOCK);
		sr.setIngredient('B', Material.BOOK);
		sr.setIngredient('D', Material.DIAMOND_BLOCK);
		sr.setIngredient('5', Material.DIAMOND_SWORD);
		Bukkit.getServer().addRecipe(sr);
		
		ItemStack is=new ItemStack(Material.DIAMOND_HELMET,1);
		ItemMeta im=is.getItemMeta();
		lore.clear();
		lore.add(translateColor("&7Casco de los dioses"));
		im.setLore(lore);
		im.setLocalizedName("1D");
		im.setDisplayName(translateColor("&aCasco-sama"));
		im.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);
		im.addEnchant(Enchantment.DURABILITY, 5, true);
		im.addEnchant(Enchantment.OXYGEN, 1, true);
		im.addEnchant(Enchantment.MENDING, 1, true);
		is.setItemMeta(im);
		sr=new ShapedRecipe(is);
		sr.shape(new String[] {"1A ","   ","   "});
		sr.setIngredient('1', new RecipeChoice.ExactChoice(is1));
		sr.setIngredient('A', Material.DIAMOND_HELMET);
		Bukkit.getServer().addRecipe(sr);
		
		
		
		is=new ItemStack(Material.DIAMOND_CHESTPLATE,1);
		im=is.getItemMeta();
		lore.clear();
		lore.add(translateColor("&7Pechera de los dioses"));
		im.setLore(lore);
		im.setLocalizedName("2D");
		im.setDisplayName(translateColor("&aPechera-sama"));
		im.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);
		im.addEnchant(Enchantment.DURABILITY, 5, true);
		im.addEnchant(Enchantment.MENDING, 1, true);
		im.addEnchant(Enchantment.THORNS, 4, true);
		is.setItemMeta(im);
		sr=new ShapedRecipe(is);
		sr.shape(new String[] {"2A ","   ","   "});
		sr.setIngredient('2', new RecipeChoice.ExactChoice(is2));
		sr.setIngredient('A', Material.DIAMOND_CHESTPLATE);
		Bukkit.getServer().addRecipe(sr);
		
		
		
		is=new ItemStack(Material.DIAMOND_LEGGINGS,1);
		im=is.getItemMeta();
		lore.clear();
		lore.add(translateColor("&7Pantalón de los dioses"));
		im.setLore(lore);
		im.setLocalizedName("3D");
		im.setDisplayName(translateColor("&aPantalón-sama"));
		im.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);
		im.addEnchant(Enchantment.DURABILITY, 5, true);
		im.addEnchant(Enchantment.MENDING, 1, true);
		is.setItemMeta(im);
		sr=new ShapedRecipe(is);
		sr.shape(new String[] {"3A ","   ","   "});
		sr.setIngredient('3', new RecipeChoice.ExactChoice(is3));
		sr.setIngredient('A', Material.DIAMOND_LEGGINGS);
		Bukkit.getServer().addRecipe(sr);
		
		
		
		is=new ItemStack(Material.DIAMOND_BOOTS,1);
		im=is.getItemMeta();
		lore.clear();
		lore.add(translateColor("&7Botas de los dioses"));
		im.setLore(lore);
		im.setLocalizedName("4D");
		im.setDisplayName(translateColor("&aBotas-sama"));
		im.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);
		im.addEnchant(Enchantment.DURABILITY, 5, true);
		im.addEnchant(Enchantment.MENDING, 1, true);
		im.addEnchant(Enchantment.PROTECTION_FALL, 4, true);
		is.setItemMeta(im);
		sr=new ShapedRecipe(is);
		sr.shape(new String[] {"4A ","   ","   "});
		sr.setIngredient('4', new RecipeChoice.ExactChoice(is4));
		sr.setIngredient('A', Material.DIAMOND_BOOTS);
		Bukkit.getServer().addRecipe(sr);
		
		is=new ItemStack(Material.DIAMOND_SWORD,1);
		im=is.getItemMeta();
		lore.clear();
		lore.add(translateColor("&7Espada de los dioses"));
		im.setLore(lore);
		im.setLocalizedName("5D");
		im.setDisplayName(translateColor("&aEspada-sama"));
		im.addEnchant(Enchantment.DAMAGE_ALL, 15, true);
		im.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 15, true);
		im.addEnchant(Enchantment.DAMAGE_UNDEAD, 15, true);
		im.addEnchant(Enchantment.MENDING, 1, true);
		im.addEnchant(Enchantment.LOOT_BONUS_MOBS, 4, true);
		is.setItemMeta(im);
		sr=new ShapedRecipe(is);
		sr.shape(new String[] {"5A ","   ","   "});
		sr.setIngredient('5', new RecipeChoice.ExactChoice(is5));
		sr.setIngredient('A', Material.DIAMOND_SWORD);
		Bukkit.getServer().addRecipe(sr);
	}
	
	public static void sendSoundToAll() {
		Collection<? extends Player> allPlayers;
		int j=(allPlayers=Bukkit.getServer().getOnlinePlayers()).size();
		for(int i=0;i<j;i++) {
			Player playerOnline=(Player)allPlayers.toArray()[i];
			playerOnline.playSound(playerOnline.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10.0F, 0.0F);
		}
	}
	
	public static void sendMessageToAll(String msg) {
		Collection<? extends Player> allPlayers;
		int j=(allPlayers=Bukkit.getServer().getOnlinePlayers()).size();
		for(int i=0;i<j;i++) {
			Player playerOnline=(Player)allPlayers.toArray()[i];
			playerOnline.sendMessage(translateColor(msg));
		}
	}
	
	
	public static String translateColor(String input) {
		return ChatColor.translateAlternateColorCodes('&', input);
	}
	
	public static List<String> translateColorArray(List<String> text){
		List<String> msg=new ArrayList<>();
		for(String st:text) {
			msg.add(translateColor(st));
		}
		return msg;
	}
	public static Location getLocationSpawnYML() {
		FileConfiguration config=MainClass.getInstance().getConfig();
		Location location=new Location(Bukkit.getServer().getWorld(config.getString("Config.spawnsettings.world")), 
				config.getDouble("Config.spawnsettings.x"), 
				config.getDouble("Config.spawnsettings.y"), 
				config.getDouble("Config.spawnsettings.z"), 
				Float.valueOf(config.getString("Config.spawnsettings.yaw")), 
				Float.valueOf(config.getString("Config.spawnsettings.pitch")));
		return location;
	}
}
