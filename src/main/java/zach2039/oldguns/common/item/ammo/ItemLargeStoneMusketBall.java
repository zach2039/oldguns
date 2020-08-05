package zach2039.oldguns.common.item.ammo;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import zach2039.oldguns.common.entity.EntityProjectile;

public class ItemLargeStoneMusketBall extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemLargeStoneMusketBall()
	{
		super("large_stone_musket_ball", 4);
		setAmmoDamage(16.0f);
	}

	@Override
	public List<EntityProjectile> createProjectiles(World worldIn, ItemStack stack, EntityLivingBase shooter)
	{
		/* Create list to hold all projectile entities that this bullet makes when fired. */
		List<EntityProjectile> projectileEntityList = new ArrayList<EntityProjectile>();
		
		/* Add single projectile for musket ball. */
		EntityProjectile entityBullet = new EntityProjectile(worldIn, shooter);
		entityBullet.setDamage(getAmmoDamage());
		//entityBullet.setPotionEffect(stack);
		projectileEntityList.add(entityBullet);
		
		return projectileEntityList;
	}
}
