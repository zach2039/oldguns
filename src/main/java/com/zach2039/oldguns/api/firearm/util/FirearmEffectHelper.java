package com.zach2039.oldguns.api.firearm.util;

import java.util.Random;

import com.zach2039.oldguns.api.artillery.ArtilleryEffect;
import com.zach2039.oldguns.api.firearm.FirearmEffect;
import com.zach2039.oldguns.init.ModSoundEvents;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class FirearmEffectHelper {
	public static void doFirearmShootEffect(Level world, Entity shootingEntity, FirearmEffect effect, double posX, double posY, double posZ, double rotationPitch, double rotationYaw, int parameter)
	{
		/* Get player entity from entity reference. */
		Player player;
		boolean rightSide;	
		if (shootingEntity instanceof Player)
		{
			player = (Player)shootingEntity;
			rightSide = (player.getMainArm() == HumanoidArm.RIGHT) && (HumanoidArm.values()[parameter] == HumanoidArm.RIGHT); 
		}
		else
		{
			player = null;
			rightSide = true;
		}
			
		/* Create random number gen. */
		Random rand = new Random();
		
		/* Calculate offset from player hand size, passed in parameter. */
		float offset = (rightSide) ? -8f : 8f;
		
		/* Change number of particles based on effect. */
		int numParticles = 0;
		
		switch (effect)
		{
			case SMALL_FIREARM_SHOOT:
				numParticles = 1 + rand.nextInt(2);
				break;
			case MEDIUM_FIREARM_SHOOT:
				numParticles = 2 + rand.nextInt(4);
				break;
			case LARGE_FIREARM_SHOOT:
				numParticles = 3 + rand.nextInt(6);
				break;
			default:
				numParticles = 1 + rand.nextInt(5);
				break;
		}
		
		/* Generate particles. */
		for (int i = 0; i < numParticles; i++)
		{
			// Calculate the next distance for the particles to propagate to.
			float range = 1.2f * (0.8f * (i + 1));
			
			// Get the position of the player's hand using trig.
			float handX = -Mth.sin((float) (((rotationYaw + offset / 1.5F) / 180F) * 3.141593F)) * Mth.cos((float) ((rotationPitch / 180F) * 3.141593F)) * range;
			float handY = -Mth.sin((float) ((rotationPitch / 180F) * 3.141593F)) * range - 0.1F;
			float handZ = Mth.cos((float) (((rotationYaw + offset / 1.5F) / 180F) * 3.141593F)) * Mth.cos((float) ((rotationPitch / 180F) * 3.141593F)) * range;
			
			// Calculate the actual position of the particles.
			double particleX = posX + handX;
			double particleY = posY + handY;
			double particleZ = posZ + handZ;
			
			if (i < 1) {
				world.addParticle(ParticleTypes.FLAME,
						particleX,
						particleY,
						particleZ,
						0d, 0d, 0d);
			}
			
			world.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE,
					particleX + (float)(0.5f - rand.nextFloat()),
					particleY + (float)(0.5f - rand.nextFloat()),
					particleZ + (float)(0.5f - rand.nextFloat()),
					0d, 0d, 0d);
			world.addParticle(ParticleTypes.POOF,
					particleX + (float)(0.5f - rand.nextFloat()),
					particleY + (float)(0.5f - rand.nextFloat()),
					particleZ + (float)(0.5f - rand.nextFloat()),
					0d, 0d, 0d);
		}
		
		/* Play sound based on effect. */
		switch (effect)
		{
			case SMALL_FIREARM_SHOOT:
				world.playLocalSound(posX, posY, posZ, SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS,
						1.0f, 2.5f / (new Random().nextFloat() * 0.4F + 1.2F), true);
				break;
			case MEDIUM_FIREARM_SHOOT:
				world.playLocalSound(posX, posY, posZ, ModSoundEvents.BULLET_SHOOT.get(), SoundSource.PLAYERS,
						25.0F, 3.0f / (new Random().nextFloat() * 0.6F + 1.2F), true);
				world.playLocalSound(posX, posY, posZ, SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS,
						1.0f, 1.5f / (new Random().nextFloat() * 0.4F + 1.2F), true);
				break;
			case LARGE_FIREARM_SHOOT:
				world.playLocalSound(posX, posY, posZ, ModSoundEvents.BULLET_SHOOT.get(), SoundSource.PLAYERS,
						50.0F, 2.2f / (new Random().nextFloat() * 0.6F + 1.2F), true);
				world.playLocalSound(posX, posY, posZ, SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS,
						1.0f, 1.0f / (new Random().nextFloat() * 0.4F + 1.2F), true);
				break;
			default:
				world.playLocalSound(posX, posY, posZ, SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS,
						1.0f, 2.0f / (new Random().nextFloat() * 0.4F + 1.2F), true);
				break; 
		}
		
	}
	
	public static void doFirearmMisfireEffect(Level world, Entity shootingEntity, FirearmEffect effect, double posX, double posY, double posZ, double rotationPitch, double rotationYaw, int parameter)
	{
		/* Get player entity from entity reference. */
		Player player;
		boolean rightSide;	
		if (shootingEntity instanceof Player)
		{
			player = (Player)shootingEntity;
			rightSide = (player.getMainArm() == HumanoidArm.RIGHT) && (HumanoidArm.values()[parameter] == HumanoidArm.RIGHT); 
		}
		else
		{
			player = null;
			rightSide = true;
		}
		 
		/* Create random number gen. */
		Random rand = new Random();
		
		/* Calculate offset from player hand size, passed in parameter. */
		float offset = (rightSide) ? -8f : 8f;
		
		/* Change number of particles based on effect. */
		int numParticles = 2 + rand.nextInt(3);
		
		/* Generate particles. */
		for (int i = 0; i < numParticles; i++)
		{
			// Calculate the next distance for the particles to propagate to.
			float range = 1.0f * (1.2f * (i + 1));
			
			// Get the position of the player's hand using trig.
			float handX = -Mth.sin((float) (((rotationYaw + offset / 1.5F) / 180F) * 3.141593F)) * Mth.cos((float) ((rotationPitch / 180F) * 3.141593F)) * range;
			float handY = -Mth.sin((float) ((rotationPitch / 180F) * 3.141593F)) * range - 0.1F;
			float handZ = Mth.cos((float) (((rotationYaw + offset / 1.5F) / 180F) * 3.141593F)) * Mth.cos((float) ((rotationPitch / 180F) * 3.141593F)) * range;
			
			// Calculate the actual position of the particles.
			double particleX = posX + handX;
			double particleY = posY + handY;
			double particleZ = posZ + handZ;
			
			world.addParticle(ParticleTypes.SMOKE,
					particleX + (float)(rand.nextFloat() / 16f),
					particleY + (float)(rand.nextFloat() / 16f),
					particleZ + (float)(rand.nextFloat() / 16f),
					0d, 0d, 0d);
		}
		
		/* Play sound based on effect. */
		switch (effect)
		{
			case MISFIRE:
				player.level.playLocalSound(posX, posY, posZ, SoundEvents.NOTE_BLOCK_HAT.get(), SoundSource.PLAYERS,
						0.5f, 1.0f / (new Random().nextFloat() * 0.4F + 1.2F), true);
				break;
			case MISFIRE_WET:
				world.playLocalSound(posX, posY, posZ, SoundEvents.NOTE_BLOCK_HAT.get(), SoundSource.PLAYERS,
						0.5f, 1.0f / (new Random().nextFloat() * 0.4F + 1.2F), true);
				world.playLocalSound(posX, posY, posZ, SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS,
						0.5f, 1.0f / (new Random().nextFloat() * 0.4F + 1.2F), true);
			case BREAK:
				world.playLocalSound(posX, posY, posZ, SoundEvents.SHIELD_BREAK, SoundSource.PLAYERS,
						0.5f, 0.25f / (new Random().nextFloat() * 0.4F + 1.2F), true);
				break;
			default:
				break;
		}
		
	}
	
	public static void doArtilleryShootEffect(Level world, Entity shootingEntity, ArtilleryEffect effect, double posX, double posY, double posZ, double rotationPitch, double rotationYaw, int parameter)
	{		 
		/* Create random number gen. */
		Random rand = new Random();
		
		/* Change number of particles based on effect. */
		int numParticles = 0;
		
		switch (effect)
		{
			case CANNON_SHOT:
				numParticles = 3 + rand.nextInt(6);
				break;
			default:
				numParticles = 1 + rand.nextInt(5);
				break;
		}
		
		/* Generate particles. */
		for (int i = 0; i < numParticles; i++)
		{
			// Calculate the next distance for the particles to propagate to.
			float range = 1.0f * (1.2f * (i + 1));
			
			// Get the position of the player's hand using trig.
			float handX = -Mth.sin((float) (((rotationYaw) / 180F) * 3.141593F)) * Mth.cos((float) ((rotationPitch / 180F) * 3.141593F)) * range;
			float handY = -Mth.sin((float) ((rotationPitch / 180F) * 3.141593F)) * range - 0.1F;
			float handZ = Mth.cos((float) (((rotationYaw) / 180F) * 3.141593F)) * Mth.cos((float) ((rotationPitch / 180F) * 3.141593F)) * range;
			
			// Calculate the actual position of the particles.
			double particleX = posX + handX;
			double particleY = posY + handY;
			double particleZ = posZ + handZ;
			
			world.addParticle(ParticleTypes.EXPLOSION,
					particleX + (float)(rand.nextFloat() / 16f),
					particleY + (float)(rand.nextFloat() / 16f),
					particleZ + (float)(rand.nextFloat() / 16f),
					0d, 0d, 0d);
			world.addParticle(ParticleTypes.LARGE_SMOKE,
					particleX + (float)(rand.nextFloat() / 16f),
					particleY + (float)(rand.nextFloat() / 16f),
					particleZ + (float)(rand.nextFloat() / 16f),
					0d, 0d, 0d);
		}
		
		/* Play sound based on effect. */
		switch (effect)
		{
			case CANNON_SHOT:
				world.playLocalSound(posX, posY, posZ, SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.PLAYERS,
						10000.0f, 1.5f / (new Random().nextFloat() * 0.4F + 1.2F), true);
				world.playLocalSound(posX, posY, posZ, SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS,
						1.0f, 1.5f / (new Random().nextFloat() * 0.4F + 1.2F), true);
				break;
			default:
				world.playLocalSound(posX, posY, posZ, SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS,
						1.0f, 2.0f / (new Random().nextFloat() * 0.4F + 1.2F), true);
				break;
		}
		
	}

}
