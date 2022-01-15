package com.zach2039.oldguns.world.item.firearm;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.firearm.FirearmType.FirearmEffect;
import com.zach2039.oldguns.api.firearm.FirearmType.FirearmReloadType;
import com.zach2039.oldguns.api.firearm.FirearmType.FirearmSize;
import com.zach2039.oldguns.api.firearm.FirearmType.FirearmWaterResiliency;
import com.zach2039.oldguns.api.firearm.IFirearm;
import com.zach2039.oldguns.api.firearm.util.FirearmNBTHelper;
import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.config.OldGunsConfig.MuzzleloadingFirearmAttributes;
import com.zach2039.oldguns.network.FirearmEffectMessage;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.PacketDistributor.TargetPoint;

public class FlintlockMusketoonItem extends FirearmItem implements IFirearm {
	
	private static final MuzzleloadingFirearmAttributes firearmAttributes = OldGunsConfig.COMMON.firearmSettings.flintlockSettings.flintlock_musketoon;
	
	public FlintlockMusketoonItem()
	{		
		super((FirearmProperties) new FirearmProperties()				
				.ammoCapacity(1)
				.firearmSize(FirearmSize.MEDIUM)
				.firearmWaterResiliency(FirearmWaterResiliency.POOR)
				.reloadType(FirearmReloadType.MUZZLELOADER)
				.effectiveRangeModifier(firearmAttributes.effectiveRangeModifier.get().floatValue())
				.damageModifier(firearmAttributes.shotDamageModifier.get().floatValue())
				.deviationModifier(firearmAttributes.shotDeviationModifier.get().floatValue())
				.projectileSpeed(firearmAttributes.projectileSpeed.get().floatValue())
				.defaultDurability(firearmAttributes.durability.get())		
				.setNoRepair()
				.tab(OldGuns.CREATIVE_MODE_TAB)
				);
	}

	@Override
	public void initNBTTags(ItemStack stackIn)
	{
		FirearmNBTHelper.refreshFirearmCondition(stackIn);
		
		FirearmNBTHelper.peekNBTTagAmmo(stackIn);
	}

	@Override
	public boolean canReload(ItemStack stackIn)
	{
		return (FirearmNBTHelper.peekNBTTagAmmoCount(stackIn) < this.getAmmoCapacity());
	}

	@Override
	public void doFiringEffect(Level worldIn, Entity shooter, ItemStack stackIn)
	{
		TargetPoint point = new PacketDistributor.TargetPoint(
				shooter.xo, shooter.yo, shooter.zo, 1600d, shooter.level.dimension());
		
		
		OldGuns.network.send(PacketDistributor.NEAR.with(() -> point), 
				new FirearmEffectMessage((LivingEntity)shooter, FirearmEffect.MEDIUM_FIREARM_SHOOT, shooter.xo, shooter.yo + shooter.getEyeHeight(), shooter.zo,
						shooter.xRotO, shooter.yRotO, ((Player)shooter).getUsedItemHand().ordinal())
				);
	}
	
}
