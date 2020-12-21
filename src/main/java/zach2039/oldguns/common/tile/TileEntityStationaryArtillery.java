package zach2039.oldguns.common.tile;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import zach2039.oldguns.api.artillery.ArtilleryType;
import zach2039.oldguns.api.artillery.FiringState;
import zach2039.oldguns.api.artillery.impl.IArtillery;
import zach2039.oldguns.api.artillery.impl.IArtilleryPowderable;

public class TileEntityStationaryArtillery extends TileEntity implements IArtillery, IArtilleryPowderable {

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		return super.writeToNBT(compound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
	}

	@Override
	public void setLoadedProjectile(ItemStack stackIn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ItemStack getLoadedProjectile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setArtilleryType(ArtilleryType artilleryType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArtilleryType getArtilleryType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFiringState(FiringState firingState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FiringState getFiringState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMaxPowderCharge() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPowderCharge() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPowderCharge(int charge) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getProjectileBaseSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getEffectiveRange() {
		// TODO Auto-generated method stub
		return 0;
	}
}
