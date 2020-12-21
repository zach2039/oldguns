package zach2039.oldguns.api.artillery.impl;

import net.minecraft.item.ItemStack;
import zach2039.oldguns.api.artillery.ArtilleryType;
import zach2039.oldguns.api.artillery.FiringState;

public interface IArtillery {
	
	public void setArtilleryType(ArtilleryType artilleryType);
    
    public ArtilleryType getArtilleryType();
    
    public void setLoadedProjectile(ItemStack stackIn);
    
    public ItemStack getLoadedProjectile();
    
    public void setFiringState(FiringState firingState);
    
    public FiringState getFiringState();
    
    public float getProjectileBaseSpeed();

    public float getEffectiveRange();
    
}
