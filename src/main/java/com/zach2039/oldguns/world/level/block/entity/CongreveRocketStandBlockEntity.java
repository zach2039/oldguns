package com.zach2039.oldguns.world.level.block.entity;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.artillery.ArtilleryEffect;
import com.zach2039.oldguns.api.artillery.ArtilleryType;
import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.config.OldGunsConfig.RocketArtilleryAttributes;
import com.zach2039.oldguns.init.ModBlockEntities;
import com.zach2039.oldguns.network.ArtilleryEffectMessage;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.PacketDistributor.TargetPoint;

public class CongreveRocketStandBlockEntity extends StationaryRocketBlockEntity {

	private static final RocketArtilleryAttributes artilleryAttributes = OldGunsConfig.SERVER.artillerySettings.congreve_rocket_stand;
	
	public CongreveRocketStandBlockEntity(BlockPos blockpos, BlockState state) {
		super(ModBlockEntities.CONGREVE_ROCKET_STAND.get(), blockpos, state, new ArtilleryProperties()
				.artilleryType(ArtilleryType.ROCKET)
				.ammoSlots(1)
				.baseProjectileDeviation(artilleryAttributes.rocketDeviation.get().floatValue())
				);
	}
	
	@Override
	public void doFiringEffect(Level level, Player player, double posX, double posY, double posZ) {
		
		TargetPoint point = new PacketDistributor.TargetPoint(
				posX, posY, posZ, 1600d, player.level.dimension());
		
		OldGuns.NETWORK.send(PacketDistributor.NEAR.with(() -> point), 
				new ArtilleryEffectMessage((LivingEntity)player, ArtilleryEffect.ROCKET_LAUNCH, 
						posX, posY + getShotHeight(), posZ,
						getShotPitch(), getShotYaw(), 0)
				);
	}
}
