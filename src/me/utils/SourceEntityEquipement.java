package me.utils;

import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;

public class SourceEntityEquipement {
	
	private LivingEntity le;
	private EntityEquipment ee;

	public SourceEntityEquipement(LivingEntity le) {
		this.le=le;
		this.ee=le.getEquipment();
	}
	
	public LivingEntity getLivingEntity() {
		return this.le;
	}
	
	public EntityEquipment getEntityEquipment() {
		return this.ee;
	}
	
	public SourceEntityEquipement getEquipment() {
		return this;
	}
	
	
	
}
