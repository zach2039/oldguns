package zach2039.oldguns.common.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import zach2039.oldguns.api.artillery.ArtilleryEffect;
import zach2039.oldguns.api.artillery.ArtilleryType;
import zach2039.oldguns.api.artillery.impl.IArtillery;
import zach2039.oldguns.api.artillery.impl.IArtilleryPowderable;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.init.ModConfigs.ConfigCategoryArtillery;
import zach2039.oldguns.common.init.ModItems;
import zach2039.oldguns.common.network.MessageArtilleryEffect;

public class EntityArtilleryCannon extends EntityArtillery implements IArtillery, IArtilleryPowderable
{
	public EntityArtilleryCannon(World worldIn)
	{
		super(worldIn);
		initArtilleryConfiguration();
	}
	
	public EntityArtilleryCannon(World worldIn, double x, double y, double z)
	{
		super(worldIn, x, y, z);
		initArtilleryConfiguration();
	}
	
	@Override
	public void initArtilleryConfiguration() {
		setArtilleryType(ArtilleryType.CANNON);
		setProjectileSpeed(ConfigCategoryArtillery.configMediumCannon.projectileSpeed);
		setEffectiveRange(ConfigCategoryArtillery.configMediumCannon.baseEffectiveRange);
		setDeviationModifier(ConfigCategoryArtillery.configMediumCannon.baseShotDeviationModifier);
		setDamageModifier(ConfigCategoryArtillery.configMediumCannon.baseShotDamageModifier);
	}

	@Override
	public void doFiringEffect(World worldIn, EntityPlayer player, double posX, double posY, double posZ)
	{
		NetworkRegistry.TargetPoint point = new NetworkRegistry.TargetPoint(
				player.dimension, posX, posY, posZ, 1600d);
		
		OldGuns.network.sendToAllAround(
				new MessageArtilleryEffect(player, ArtilleryEffect.CANNON_SHOT, posX, posY + getBarrelHeight(), posZ,
						getBarrelPitch(), getBarrelYaw(), 0),
				point
				);
	}
	
	@Override
	public Item getItemArtillery()
	{
		return ModItems.ARTILLERY_CANNON;
	}

	@Override
	public float getBarrelHeight()
	{
		return 1.0f;
	}
	
	@Override
	public int getMaxPowderCharge()
	{
		return 3;
	}

	@Override
	public float getMinBarrelPitch() {
		return -15f;
	}

	@Override
	public float getMaxBarrelPitch() {
		return 15f;
	}
	
	@Override
	public float getMinBarrelYaw() {
		return 0f;
	}

	@Override
	public float getMaxBarrelYaw() {
		return 360f;
	}

}
