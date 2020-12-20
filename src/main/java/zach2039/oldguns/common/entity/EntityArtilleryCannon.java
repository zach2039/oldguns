package zach2039.oldguns.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.init.ModItems;
import zach2039.oldguns.common.item.util.ArtilleryType.ArtilleryEffect;
import zach2039.oldguns.common.network.MessageArtilleryEffect;

public class EntityArtilleryCannon extends EntityArtillery
{
	public EntityArtilleryCannon(World worldIn)
	{
		super(worldIn);
	}
	
	public EntityArtilleryCannon(World worldIn, double x, double y, double z)
	{
		super(worldIn, x, y, z);
		this.setArtilleryType(EntityArtillery.Type.CANNON);
	}

	@Override
	public void doFiringEffect(World worldIn, Entity shooter)
	{
		NetworkRegistry.TargetPoint point = new NetworkRegistry.TargetPoint(
				shooter.dimension, shooter.posX, shooter.posY, shooter.posZ, 64d);
		
		OldGuns.network.sendToAllAround(
				new MessageArtilleryEffect(shooter, ArtilleryEffect.CANNON_SHOT, shooter.posX, shooter.posY + this.getBarrelHeight(), shooter.posZ,
						shooter.rotationPitch, shooter.rotationYaw, 0),
				point
				);
	}

	
	@Override
	public Item getItemArtillery()
	{
		return ModItems.ARTILLERY_CANNON;
	}

	@Override
	protected float getProjectileBaseSpeed()
	{
		return 2.5f;
	}

	@Override
	protected float getEffectiveRange()
	{
		return 500f;
	}

	@Override
	public float getBarrelHeight()
	{
		return 1.0f;
	}
	
	@Override
	public int getMaxPowderCharge()
	{
		return 3;
	}

	@Override
	public float getMinBarrelPitch() {
		return -15f;
	}

	@Override
	public float getMaxBarrelPitch() {
		return 15;
	}

}
