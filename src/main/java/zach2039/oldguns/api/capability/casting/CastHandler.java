package zach2039.oldguns.api.capability.casting;

import javax.annotation.Nullable;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class CastHandler implements ICapabilitySerializable<NBTTagCompound>, ICast
{
	public CastHandler(@Nullable NBTTagCompound nbt)
	{
	}
	
	public CastHandler()
	{
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		return capability == CapabilityCast.CAST_CAPABILITY;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		  return hasCapability(capability, facing) ? (T) this : null;
	}

	@Override
	public NBTTagCompound serializeNBT()
	{
		return null;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt)
	{
	}
}
