package com.zach2039.oldguns.world.level.block.entity;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.artillery.ArtilleryEffect;
import com.zach2039.oldguns.api.artillery.ArtilleryType;
import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.config.OldGunsConfig.CannonArtilleryAttributes;
import com.zach2039.oldguns.init.ModBlockEntities;
import com.zach2039.oldguns.network.ArtilleryEffectMessage;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.PacketDistributor.TargetPoint;

public class MediumNavalCannonBlockEntity extends StationaryCannonBlockEntity {

	private static final CannonArtilleryAttributes artilleryAttributes = OldGunsConfig.SERVER.artillerySettings.medium_naval_cannon;
	
	public MediumNavalCannonBlockEntity(BlockPos blockpos, BlockState state) {
		super(ModBlockEntities.MEDIUM_NAVAL_CANNON.get(), blockpos, state, new ArtilleryProperties()
				.artilleryType(ArtilleryType.NAVAL_CANNON)
				.ammoSlots(1)
				.baseProjectileDeviation(artilleryAttributes.projectileDeviation.get().floatValue())
				.baseProjectileSpeed(artilleryAttributes.projectileSpeed.get().floatValue())
				.effectiveRangeModifier(artilleryAttributes.effectiveRangeModifier.get().floatValue())
				.projectileDamageModifier(artilleryAttributes.damageModifier.get().floatValue())
				);
	}
	
	@Override
	public void doFiringEffect(Level level, Player player, double posX, double posY, double posZ) {
		
		OldGuns.NETWORK.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player), 
				new ArtilleryEffectMessage((LivingEntity)player, ArtilleryEffect.CANNON_SHOT, 
						posX, posY + getShotHeight(), posZ,
						getShotPitch(), getShotYaw(), 0)
				);
	}

}
