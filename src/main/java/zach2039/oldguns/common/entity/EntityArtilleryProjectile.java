package zach2039.oldguns.common.entity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import zach2039.oldguns.common.OldGuns;

public class EntityArtilleryProjectile extends EntityProjectile
{
	private ProjectileType projectileType = ProjectileType.SOLID;
	
	private static final DataParameter<Float> EFFECT_STRENGTH = EntityDataManager.<Float>createKey(EntityArtilleryProjectile.class, DataSerializers.FLOAT);

	public EntityArtilleryProjectile(World worldIn)
	{
		super(worldIn);
	}	
	
	public EntityArtilleryProjectile(World worldIn, double x, double y, double z)
	{
		super(worldIn, x, y, z);
	}
	
	public void setProjectileType(ProjectileType type)
	{
		this.projectileType = type;
	}
	
	public ProjectileType getProjectileType()
	{
		return this.projectileType;
	}
	
	public void setEffectStrength(float strength)
	{
		 this.dataManager.set(EFFECT_STRENGTH, Float.valueOf(strength));
	}
	
	public float getEffectStrength()
	{
		return ((Float)this.dataManager.get(EFFECT_STRENGTH)).floatValue();
	}
	
	@Override
	public void entityInit()
	{	
		super.entityInit();
		
		/* Register effect strength storage for projectile effect potency. */
		dataManager.register(EFFECT_STRENGTH, 1f);
	}
	
	@Override
	protected void projectileHit(Entity entity)
	{
		if (!this.world.isRemote)
		{
			/* Choose from table of effects to apply. */
			switch (this.projectileType)
			{
				case EXPLOSIVE:
					if (getEffectStrength() > 0.0f)
					{
						this.world.createExplosion(this, this.posX, this.posY, this.posZ, getEffectStrength(), true);
					}
					break;
				default:
					/* Default behavior is solid projectile. */					
					break;
			}
		}
	}
	
	@Override
	protected boolean allowBlockHitSlowdown() {
		if (this.projectileType == ProjectileType.SOLID && getEffectStrength() > 0.1f)
		{
			return false;
		}
		return true;
	}
	
	@Override
	protected void projectileBlockHit(BlockPos blockPos)
	{
		if (!this.world.isRemote)
		{
			/* Choose from table of effects to apply. */
			switch (this.projectileType)
			{
				case EXPLOSIVE:
					if (getEffectStrength() > 0.0f)
					{
						this.world.createExplosion(this, this.posX, this.posY, this.posZ, getEffectStrength(), true);
						this.setDead();
					}
					break;
				default:
					/* Default behavior is solid projectile. */
					if (getEffectStrength() > 0.0f)
					{
						IBlockState hitBlock = this.world.getBlockState(blockPos);
						float hitBlockHardness = hitBlock.getBlockHardness(this.world, blockPos);
						if (!this.world.isRemote) OldGuns.logger.debug("target hardness : " + hitBlockHardness);
						if (!this.world.isRemote) OldGuns.logger.debug("effect before : " + getEffectStrength());
						if (hitBlockHardness < getEffectStrength())
						{							
							/* Punch through block if effectStrength of solid projectile is greater than block hardness, and keep going with reduced effectiveness. */
							this.world.destroyBlock(blockPos, this.rand.nextBoolean());
							float effectModifier = (!this.isInWater()) ? 0.70f : 0.45f;
							setEffectStrength(Math.max(0.1f, getEffectStrength() * effectModifier));
							if (!this.world.isRemote) OldGuns.logger.debug("broke : " + hitBlock.toString());
						}
						else
						{
							setEffectStrength(0.0f);
							if (!this.world.isRemote) OldGuns.logger.debug("stopped by, inWater : " + hitBlock.toString() + ", " + this.isInWater());
						}
						if (!this.world.isRemote) OldGuns.logger.debug("effect after : " + getEffectStrength());
					}
					//this.setDead();
					break;
			}
			
		}
	}

	@Override
	protected double getGravity()
	{		
		return 0.05000000074505806D;
	}
	
	public static enum ProjectileType {
		SOLID, EXPLOSIVE;
	}
}
