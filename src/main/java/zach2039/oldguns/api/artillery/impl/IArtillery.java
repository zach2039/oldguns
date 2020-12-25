package zach2039.oldguns.api.artillery.impl;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import zach2039.oldguns.api.artillery.ArtilleryType;
import zach2039.oldguns.api.artillery.FiringState;

public interface IArtillery {
	
	public void initArtilleryConfiguration();
	
	public void setArtilleryType(ArtilleryType artilleryType);
    
    public ArtilleryType getArtilleryType();
    
    public void setLoadedProjectile(ItemStack stackIn);
    
    public ItemStack getLoadedProjectile();
    
    public void setFiringState(FiringState firingState);
    
    public FiringState getFiringState();
    
    public void setFiringCooldown(int cooldown);
    
    public int getFiringCooldown();
    
    public void setProjectileSpeed(float projectileSpeed);
    
    public float getProjectileSpeed();
    
    public void setDeviationModifier(float deviationModifier);
    
    public float getDeviationModifier();
    
    public void setDamageModifier(float damageModifier);
    
    public float getDamageModifier();

    public void setEffectiveRange(float effectiveRange);
    
    public float getEffectiveRange();
    
	public float getBarrelHeight();

	public float getMinBarrelPitch();

	public float getMaxBarrelPitch();
	
	public float getMinBarrelYaw();

	public float getMaxBarrelYaw();
	
	public void setBarrelPitch(float pitch);
	
	public float getBarrelPitch();
	
	public void setBarrelYaw(float yaw);
	
	public float getBarrelYaw();
	
	public void doFiringEffect(World worldIn, EntityPlayer player, double posX, double posY, double posZ);
}
