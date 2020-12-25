package zach2039.oldguns.common.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import zach2039.oldguns.api.artillery.ArtilleryEffect;
import zach2039.oldguns.api.artillery.ArtilleryType;
import zach2039.oldguns.api.artillery.impl.IArtillery;
import zach2039.oldguns.api.artillery.impl.IArtilleryPowderable;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.init.ModConfigs.ConfigCategoryArtillery;
import zach2039.oldguns.common.network.MessageArtilleryEffect;

public class TileEntityStationaryCannon extends TileEntityStationaryArtillery implements IArtillery, IArtilleryPowderable {
	
	public TileEntityStationaryCannon()
	{
		super();
		initArtilleryConfiguration();
	}
	
	@Override
	public void initArtilleryConfiguration() {
		setArtilleryType(ArtilleryType.STATIONARY_CANNON);
		setProjectileSpeed(ConfigCategoryArtillery.configNavalCannon.projectileSpeed);
		setEffectiveRange(ConfigCategoryArtillery.configNavalCannon.baseEffectiveRange);
		setDeviationModifier(ConfigCategoryArtillery.configMediumCannon.baseShotDeviationModifier);
		setDamageModifier(ConfigCategoryArtillery.configMediumCannon.baseShotDamageModifier);
	}
	
	@Override
	public int getMaxPowderCharge() {
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
		return -360f;
	}

	@Override
	public float getMaxBarrelYaw() {
		return 360f;
	}
	
	@Override
	public float getBarrelHeight() {
		return 1f;
	}

	@Override
	public void doFiringEffect(World worldIn, EntityPlayer player, double posX, double posY, double posZ) {
		NetworkRegistry.TargetPoint point = new NetworkRegistry.TargetPoint(
				player.dimension, posX, posY, posZ, 64d);
		
		OldGuns.network.sendToAllAround(
				new MessageArtilleryEffect(player, ArtilleryEffect.CANNON_SHOT, posX, posY + getBarrelHeight(), posZ,
						getBarrelPitch(), getBarrelYaw(), 0),
				point
				);
	}

	
}
