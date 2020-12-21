package zach2039.oldguns.common.item.ammo;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import zach2039.oldguns.common.entity.EntityProjectile;

public class ItemMediumStoneMusketBall extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemMediumStoneMusketBall()
	{
		super("medium_stone_musket_ball", 6);
		setAmmoDamage(13.0f);
		setProjectileSize(0.4f);
		setProjectileCount(1);
	}
}
