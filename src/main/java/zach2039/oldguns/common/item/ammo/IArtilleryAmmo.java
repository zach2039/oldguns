package zach2039.oldguns.common.item.ammo;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import zach2039.oldguns.common.entity.EntityArtillery;
import zach2039.oldguns.common.entity.EntityProjectile;

public interface IArtilleryAmmo
{
	List<EntityProjectile> createProjectiles(World worldIn, ItemStack stack, EntityArtillery artillery, EntityLivingBase shooter);
}
