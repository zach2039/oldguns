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
		// ZTH 200802: Not having this makes the game forget the cast when saving/loading NBT of tile ent.
		return new NBTTagCompound();
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt)
	{
	}
}
