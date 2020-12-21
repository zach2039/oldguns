package zach2039.oldguns.common.item.ammo;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import zach2039.oldguns.common.entity.EntityProjectile;

public class ItemLargeIronBirdshot extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemLargeIronBirdshot()
	{
		super("large_iron_birdshot", 4);
		setAmmoDamage(3.0f);
		setProjectileSize(0.2f);
		setProjectileCount(10);
	}
}
