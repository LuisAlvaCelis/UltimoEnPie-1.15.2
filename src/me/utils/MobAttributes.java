package me.utils;

import java.lang.reflect.Field;

import org.bukkit.craftbukkit.v1_15_R1.entity.CraftCreeper;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftLivingEntity;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;

import net.minecraft.server.v1_15_R1.AttributeInstance;
import net.minecraft.server.v1_15_R1.EntityCreeper;
import net.minecraft.server.v1_15_R1.EntityInsentient;
import net.minecraft.server.v1_15_R1.GenericAttributes;


public class MobAttributes {

	private static MobAttributes instance=new MobAttributes();
	
	public static MobAttributes getInstance() {
		return instance;
	}
	
	public void setExplosionRadius(Creeper e,int radius) {
		try {
			EntityCreeper ec=((CraftCreeper)e).getHandle();
			Field radiusF=EntityCreeper.class.getDeclaredField("explosionRadius");
			radiusF.setAccessible(true);
			radiusF.setInt(ec, radius);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	public void setMaxHealth(Entity e,int maxHealth) {
		((CraftLivingEntity)e).setMaxHealth(maxHealth);
		((CraftLivingEntity)e).setHealth(((CraftLivingEntity)e).getMaxHealth());
		
	}
	
	public void setMobSpeed(Entity e, float speed){
		AttributeInstance attributes = ((EntityInsentient)((CraftLivingEntity)e).getHandle()).getAttributeInstance(GenericAttributes.MOVEMENT_SPEED);
		attributes.setValue(speed);
	}
	
	public void setAttackDamage(Entity e, double damage) {
		AttributeInstance attributes = ((EntityInsentient)((CraftLivingEntity)e).getHandle()).getAttributeInstance(GenericAttributes.ATTACK_DAMAGE);
		attributes.setValue(damage);
	}
}
