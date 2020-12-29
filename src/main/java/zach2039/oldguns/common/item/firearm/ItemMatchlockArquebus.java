package zach2039.oldguns.common.item.firearm;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import zach2039.oldguns.api.firearm.FirearmType.FirearmEffect;
import zach2039.oldguns.api.firearm.FirearmType.FirearmReloadType;
import zach2039.oldguns.api.firearm.FirearmType.FirearmSize;
import zach2039.oldguns.api.firearm.FirearmType.FirearmWaterResiliency;
import zach2039.oldguns.api.firearm.impl.IFirearm;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.init.ModConfigs.ConfigCategoryFirearms;
import zach2039.oldguns.common.item.util.FirearmNBTHelper;
import zach2039.oldguns.common.network.MessageFirearmEffect;

public class ItemMatchlockArquebus extends ItemFirearm implements IFirearm
{
	public ItemMatchlockArquebus()
	{
		super("matchlock_arquebus");
		setAmmoCapacity(1);
		setReloadType(FirearmReloadType.MUZZLELOADER);
		setFirearmSize(FirearmSize.MEDIUM);
		setFirearmWaterResiliency(FirearmWaterResiliency.POOR);
		setMaxDamage(ConfigCategoryFirearms.configMatchlockArquebus.durability);
		setEffectiveRangeModifier(ConfigCategoryFirearms.configMatchlockArquebus.baseEffectiveRange);
		setProjectileSpeed(ConfigCategoryFirearms.configMatchlockArquebus.projectileSpeed);
		setDamageModifier(ConfigCategoryFirearms.configMatchlockArquebus.baseShotDamageModifier);
		setFirearmDeviation(ConfigCategoryFirearms.configMatchlockArquebus.baseShotDeviationModifier);
	}

	@Override
	public void initNBTTags(ItemStack stackIn)
	{
		/* Set ammo NBT by peeking top of magazine stack. */
		FirearmNBTHelper.peekNBTTagAmmo(stackIn);
	}

	@Override
	public boolean canReload(ItemStack stackIn)
	{
		return (FirearmNBTHelper.peekNBTTagAmmoCount(stackIn) < getAmmoCapacity());
	}

	@Override
	public void doFiringEffect(World worldIn, Entity shooter, ItemStack stackIn)
	{
		NetworkRegistry.TargetPoint point = new NetworkRegistry.TargetPoint(
				shooter.dimension, shooter.posX, shooter.posY, shooter.posZ, 1600d);
		
		OldGuns.network.sendToAllAround(
				new MessageFirearmEffect((EntityLivingBase)shooter, FirearmEffect.MEDIUM_FIREARM_SHOOT, shooter.posX, shooter.posY + shooter.getEyeHeight(), shooter.posZ,
						shooter.rotationPitch, shooter.rotationYaw, ((EntityPlayer)shooter).getActiveHand().ordinal()),
				point
				);
	}
}
