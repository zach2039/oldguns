package zach2039.oldguns.common.item.ammo;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import zach2039.oldguns.common.entity.EntityProjectile;

public class ItemMediumIronMusketBall extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemMediumIronMusketBall()
	{
		super("medium_iron_musket_ball", 6);
		setAmmoDamage(23.0f);
		setProjectileSize(0.4f);
		setProjectileCount(1);
	}
}
