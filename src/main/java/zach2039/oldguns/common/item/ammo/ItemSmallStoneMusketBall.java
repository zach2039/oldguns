package zach2039.oldguns.common.item.ammo;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import zach2039.oldguns.common.entity.EntityProjectile;

public class ItemSmallStoneMusketBall extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemSmallStoneMusketBall()
	{
		super("small_stone_musket_ball", 8);
		setAmmoDamage(10.0f);
		setProjectileSize(0.3f);
		setProjectileCount(1);
	}
}
