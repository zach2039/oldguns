package zach2039.oldguns.common.item.ammo;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import zach2039.oldguns.common.entity.EntityProjectile;

public class ItemLargeStoneBirdshot extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemLargeStoneBirdshot()
	{
		super("large_stone_birdshot", 4);
		setAmmoDamage(2.0f);
	}

	@Override
	public List<EntityProjectile> createProjectiles(World worldIn, ItemStack stack, EntityLivingBase shooter)
	{
		/* Create list to hold all projectile entities that this bullet makes when fired. */
		List<EntityProjectile> projectileEntityList = new ArrayList<EntityProjectile>();
		
		/* Add multi-projectile for musket ball. */
		for (int i = 0; i < 10; i++) 
		{
			EntityProjectile entityBullet = new EntityProjectile(worldIn, shooter);
			entityBullet.setDamage(getAmmoDamage());			
			projectileEntityList.add(entityBullet);
		}
		
		return projectileEntityList;
	}
}
