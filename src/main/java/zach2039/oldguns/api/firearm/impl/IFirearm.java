package zach2039.oldguns.api.firearm.impl;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import zach2039.oldguns.api.firearm.FirearmType.FirearmSize;
import zach2039.oldguns.api.firearm.FirearmType.FirearmWaterResiliency;

public interface IFirearm
{
	void initNBTTags(ItemStack stackIn);
	
	boolean canReload(ItemStack stackIn);
	
	void doFiringEffect(World worldIn, Entity entityShooter, ItemStack stackIn);
	
	void setFirearmSize(FirearmSize size);
	
	FirearmSize getFirearmSize();
	
	void setFirearmWaterResiliency(FirearmWaterResiliency waterResiliency);
	
	FirearmWaterResiliency getFirearmWaterResiliency();
}
