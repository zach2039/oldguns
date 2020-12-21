package zach2039.oldguns.api.ammo;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import zach2039.oldguns.api.artillery.impl.IArtillery;
import zach2039.oldguns.common.entity.EntityProjectile;

public interface IArtilleryAmmo
{
	List<EntityProjectile> createProjectiles(World worldIn, double posX, double posY, double posZ, ItemStack stack, IArtillery artillery, EntityLivingBase shooter);
	
	float getProjectileSize();
	
	void setProjectileSize(float size);
	
	float getProjectileCount();
	
	void setProjectileCount(int count);
	
	void setEffectStrength(float strength);
	
	float getEffectStrength();
}
