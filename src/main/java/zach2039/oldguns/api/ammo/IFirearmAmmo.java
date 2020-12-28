package zach2039.oldguns.api.ammo;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import zach2039.oldguns.common.entity.EntityProjectile;

public interface IFirearmAmmo
{
	List<EntityProjectile> createProjectiles(World worldIn, ItemStack stack, EntityLivingBase shooter);
	
	float getProjectileSize();
	
	void setProjectileSize(float size);
	
	float getProjectileCount();
	
	void setProjectileCount(int count);

	double getAmmoDamage();
	
	void setAmmoDamage(double ammoDamage);

	public float getProjectileDeviationModifier();

	public void setProjectileDeviationModifier(float projectileDeviation);
	
	public float getProjectileEffectiveRange();

	public void setProjectileEffectiveRange(float projectileEffectiveRange);
}
