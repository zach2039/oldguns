package zach2039.oldguns.common.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityArtilleryProjectile extends EntityProjectile
{
	private float explosionForce = 0.0f;

	public EntityArtilleryProjectile(World worldIn)
	{
		super(worldIn);
	}	
	
	public EntityArtilleryProjectile(World worldIn, double x, double y, double z)
	{
		super(worldIn, x, y, z);
	}

	public void setExplosionForce(float explosionForce)
	{
		this.explosionForce = explosionForce;
	}
	
	public float getExplosionForce()
	{
		return this.explosionForce;
	}
	
	@Override
	protected void projectileHit(EntityLivingBase entityLiving)
	{
		if (!this.world.isRemote)
		{
			if (this.explosionForce > 0.0f)
			{
				this.world.createExplosion(this, this.posX, this.posY, this.posZ, this.explosionForce, true);
			}
		}
	}
	
	@Override
	protected void projectileBlockHit(BlockPos blockPos)
	{
		if (!this.world.isRemote)
		{
			if (this.explosionForce > 0.0f)
			{
				this.world.createExplosion(this, this.posX, this.posY, this.posZ, this.explosionForce, true);
				this.setDead();
			}
		}
	}

	@Override
	protected double getGravity()
	{		
		return 0.05000000074505806D;
	}
}
