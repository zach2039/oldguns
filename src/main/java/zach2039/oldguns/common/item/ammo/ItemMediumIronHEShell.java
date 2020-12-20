package zach2039.oldguns.common.item.ammo;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import zach2039.oldguns.common.entity.EntityArtillery;
import zach2039.oldguns.common.entity.EntityArtilleryProjectile;
import zach2039.oldguns.common.entity.EntityProjectile;
import zach2039.oldguns.common.entity.EntityArtilleryProjectile.ProjectileType;

public class ItemMediumIronHEShell extends ItemArtilleryAmmo implements IArtilleryAmmo
{
	public ItemMediumIronHEShell()
	{
		super("medium_iron_he_shell", 1);
		setAmmoDamage(25.0f);
	}

	@Override
	public List<EntityProjectile> createProjectiles(World worldIn, ItemStack stack, EntityArtillery artillery, EntityLivingBase shooter)
	{
		/* Create list to hold all projectile entities that this bullet makes when fired. */
		List<EntityProjectile> projectileEntityList = new ArrayList<EntityProjectile>();
		
		/* Add single projectile for cannonball. */
		// Calculate the next distance for the particles to propagate to.
		float range = 1.5f;
					
		// Get the position of the cannon's barrel using trig.
		float barrelX = -MathHelper.sin((float) (((artillery.rotationYaw) / 180F) * 3.141593F)) * MathHelper.cos((float) ((artillery.rotationPitch / 180F) * 3.141593F)) * range;
		float barrelY = -MathHelper.sin((float) ((artillery.rotationPitch / 180F) * 3.141593F)) * range - 0.1F;
		float barrelZ = MathHelper.cos((float) (((artillery.rotationYaw) / 180F) * 3.141593F)) * MathHelper.cos((float) ((artillery.rotationPitch / 180F) * 3.141593F)) * range;
		EntityArtilleryProjectile entityBullet = new EntityArtilleryProjectile(worldIn, artillery.posX + barrelX, artillery.posY + artillery.getBarrelHeight() + barrelY, artillery.posZ + barrelZ);
		entityBullet.setProjectileSize(2f);
		entityBullet.setProjectileType(ProjectileType.EXPLOSIVE);
		entityBullet.setEffectStrength(3.0f);
		//entityBullet.setShootingEntity(shooter);
		entityBullet.setDamage(getAmmoDamage());
		//entityBullet.setPotionEffect(stack);
		projectileEntityList.add(entityBullet);
		
		return projectileEntityList;
	}
}
