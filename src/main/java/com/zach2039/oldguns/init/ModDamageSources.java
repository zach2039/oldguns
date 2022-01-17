package com.zach2039.oldguns.init;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.world.entity.BulletProjectile;

import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.IndirectEntityDamageSource;

public class ModDamageSources {
	public static enum DamageType {
		FIREARM		("firearm"),
		ARTILLERY	("artillery");
		
		private DamageType(String typeName)
		{
			this.typeName = typeName;
		}
		
		public String getTypeName()
		{
			return OldGuns.MODID + "." + this.typeName;
		}
		
		private String typeName;
	}
	
	public static class OldGunsDamageSource extends DamageSource
	{
		public OldGunsDamageSource(String tag)
		{
			super(tag);
		}
	}
	
	public static class OldGunsDamageSourceDirect extends EntityDamageSource
	{
		public OldGunsDamageSourceDirect(String tag, Entity attacker) {
			super(tag, attacker);
		}
	}
	
	public static class IndirectOldGunsDamageSource extends IndirectEntityDamageSource
	{
		public IndirectOldGunsDamageSource(String tag, Entity shot, Entity shooter)
		{
			super(tag, shot, shooter);
		}
	}
	
	public static class BulletDamageSource extends OldGunsDamageSource
	{
		public BulletDamageSource(String damageTypeIn)
		{
			super(damageTypeIn);
		}
	}
	
	public static DamageSource causeBulletDamage(DamageType type, BulletProjectile projectileEntity, Entity shooter)
	{
		if(shooter==null) 
			return new BulletDamageSource(type.getTypeName());
		
		return new IndirectOldGunsDamageSource(type.getTypeName() + ".player", projectileEntity, shooter);
	}

}
