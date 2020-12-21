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
		setProjectileSize(0.5f);
		setProjectileCount(1);
	}
}
