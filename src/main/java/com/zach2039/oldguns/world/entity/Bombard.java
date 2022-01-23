package com.zach2039.oldguns.world.entity;

//public class Bombard extends MoveableArtillery {
//	private static final ArtilleryAttributes artilleryAttributes = OldGunsConfig.SERVER.artillerySettings.bombard;
//	
//	public Bombard(EntityType<? extends MoveableArtillery> entity, Level level) {
//		super(ModEntities.BOMBARD.get(), level);
//	}
//
//	public Bombard(Level level, double x, double y, double z) {
//		super(ModEntities.BOMBARD.get(), level, x, y, z);
//	}
//
//	@Override
//	public void initArtilleryConfiguration() {
//		setArtilleryType(ArtilleryType.BOMBARD);
//		setBaseProjectileSpeed(artilleryAttributes.projectileSpeed.get());
//		setEffectiveRangeModifier(artilleryAttributes.effectiveRangeModifier.get());
//		setBaseProjectileDeviation(artilleryAttributes.deviationModifier.get());
//		setDamageModifier(artilleryAttributes.damageModifier.get());
//	}
//	
//	@Override
//	public void doFiringEffect(Level level, Player player, double posX, double posY, double posZ)
//	{
//		TargetPoint point = new PacketDistributor.TargetPoint(
//				posX, posY, posZ, 1600d, level.dimension());
//		
//		
//		OldGuns.network.send(PacketDistributor.NEAR.with(() -> point), 
//				new ArtilleryEffectMessage((LivingEntity)player, ArtilleryEffect.CANNON_SHOT, posX, posY + getShotHeight(), posZ,
//						getShotPitch(), getShotYaw(), 0)
//				);
//	}
//}
