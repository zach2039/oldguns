package zach2039.oldguns.common.item.ammo;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import zach2039.oldguns.common.entity.EntityProjectile;

public class ItemSmallIronMusketBall extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemSmallIronMusketBall()
	{
		super("small_iron_musket_ball", 8);
		setAmmoDamage(20.0f);
		setProjectileSize(0.3f);
		setProjectileCount(1);
	}
}
