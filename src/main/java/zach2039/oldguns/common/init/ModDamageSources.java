package zach2039.oldguns.common.init;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.entity.EntityProjectile;

public class ModDamageSources
{
	public static enum DamageType {
		BULLET 		("bullet"),
		SHOT		("shot"),
		CANNONBALL	("cannonball");
		
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
		public OldGunsDamageSourceDirect(String tag, Entity attacker)
		{
			super(tag, attacker);
		}
	}
	
	public static class OldGunsDamageSourceIndirect extends EntityDamageSourceIndirect
	{
		public OldGunsDamageSourceIndirect(String tag, Entity shot, Entity shooter)
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
	
	public static DamageSource causeBulletDamage(EntityProjectile projectileEntity, Entity shooter)
	{
		if(shooter==null)
			return new BulletDamageSource(DamageType.BULLET.getTypeName());
		
		return new OldGunsDamageSourceIndirect(DamageType.BULLET.getTypeName(), projectileEntity, shooter);
	}
}
