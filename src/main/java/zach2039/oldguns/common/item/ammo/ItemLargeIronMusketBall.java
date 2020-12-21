package zach2039.oldguns.common.item.ammo;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import zach2039.oldguns.common.entity.EntityProjectile;

public class ItemLargeIronMusketBall extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemLargeIronMusketBall()
	{
		super("large_iron_musket_ball", 4);
		setAmmoDamage(26.0f);
		setProjectileSize(0.5f);
		setProjectileCount(1);
	}
}
