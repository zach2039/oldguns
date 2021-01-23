package zach2039.oldguns.common.network.util;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import zach2039.oldguns.api.artillery.ArtilleryEffect;
import zach2039.oldguns.api.firearm.FirearmType.FirearmEffect;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.init.ModSoundEvents;

public class FirearmEffectHelper
{
	public static void doFirearmShootEffect(World world, Entity shootingEntity, FirearmEffect effect, double posX, double posY, double posZ, double rotationPitch, double rotationYaw, int parameter)
	{
		/* Get player entity from entity reference. */
		EntityLivingBase player;
		boolean rightSide;	
		if (shootingEntity instanceof EntityLivingBase)
		{
			player = (EntityLivingBase)shootingEntity;
			rightSide = (player.getPrimaryHand() == EnumHandSide.RIGHT) && (EnumHand.values()[parameter] == EnumHand.MAIN_HAND); 
		}
		else
		{
			player = null;
			rightSide = true;
		}
			
		/* Create random number gen. */
		Random rand = new Random();
		
		/* Calculate offset from player hand size, passed in parameter. */
		float offset = (rightSide) ? 23f : -23f;
		
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
			float range = 1.0f * (1.2f * (i + 1));
			
			// Get the position of the player's hand using trig.
			float handX = -MathHelper.sin((float) (((rotationYaw + offset / 1.5F) / 180F) * 3.141593F)) * MathHelper.cos((float) ((rotationPitch / 180F) * 3.141593F)) * range;
			float handY = -MathHelper.sin((float) ((rotationPitch / 180F) * 3.141593F)) * range - 0.1F;
			float handZ = MathHelper.cos((float) (((rotationYaw + offset / 1.5F) / 180F) * 3.141593F)) * MathHelper.cos((float) ((rotationPitch / 180F) * 3.141593F)) * range;
			
			// Calculate the actual position of the particles.
			double particleX = posX + handX;
			double particleY = posY + handY;
			double particleZ = posZ + handZ;
			
			world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL,
					particleX + (float)(rand.nextFloat() / 16f),
					particleY + (float)(rand.nextFloat() / 16f),
					particleZ + (float)(rand.nextFloat() / 16f),
					0d, 0d, 0d, 0);
			world.spawnParticle(EnumParticleTypes.SMOKE_LARGE,
					particleX + (float)(rand.nextFloat() / 16f),
					particleY + (float)(rand.nextFloat() / 16f),
					particleZ + (float)(rand.nextFloat() / 16f),
					0d, 0d, 0d, 0);
		}
		
		/* Play sound based on effect. */
		switch (effect)
		{
			case SMALL_FIREARM_SHOOT:
				world.playSound(posX, posY, posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS,
						1.0f, 2.5f / (new Random().nextFloat() * 0.4F + 1.2F), true);
				break;
			case MEDIUM_FIREARM_SHOOT:
				world.playSound(posX, posY, posZ, ModSoundEvents.BULLET_SHOOT, SoundCategory.PLAYERS,
						25.0F, 3.0f / (new Random().nextFloat() * 0.6F + 1.2F), true);
				world.playSound(posX, posY, posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS,
						1.0f, 1.5f / (new Random().nextFloat() * 0.4F + 1.2F), true);
				break;
			case LARGE_FIREARM_SHOOT:
				world.playSound(posX, posY, posZ, ModSoundEvents.BULLET_SHOOT, SoundCategory.PLAYERS,
						50.0F, 2.2f / (new Random().nextFloat() * 0.6F + 1.2F), true);
				world.playSound(posX, posY, posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS,
						1.0f, 1.0f / (new Random().nextFloat() * 0.4F + 1.2F), true);
				break;
			default:
				world.playSound(posX, posY, posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS,
						1.0f, 2.0f / (new Random().nextFloat() * 0.4F + 1.2F), true);
				break; 
		}
		
	}
	
	public static void doFirearmMisfireEffect(World world, Entity shootingEntity, FirearmEffect effect, double posX, double posY, double posZ, double rotationPitch, double rotationYaw, int parameter)
	{
		/* Get player entity from entity reference. */
		EntityLivingBase player;
		boolean rightSide;	
		if (shootingEntity instanceof EntityLivingBase)
		{
			player = (EntityLivingBase)shootingEntity;
			rightSide = (player.getPrimaryHand() == EnumHandSide.RIGHT) && (EnumHand.values()[parameter] == EnumHand.MAIN_HAND); 
		}
		else
		{
			player = null;
			rightSide = true;
		}
		 
		/* Create random number gen. */
		Random rand = new Random();
		
		/* Calculate offset from player hand size, passed in parameter. */
		float offset = (rightSide) ? 23f : -23f;
		
		/* Change number of particles based on effect. */
		int numParticles = 2 + rand.nextInt(3);
		
		/* Generate particles. */
		for (int i = 0; i < numParticles; i++)
		{
			// Calculate the next distance for the particles to propagate to.
			float range = 1.0f * (1.2f * (i + 1));
			
			// Get the position of the player's hand using trig.
			float handX = -MathHelper.sin((float) (((rotationYaw + offset / 1.5F) / 180F) * 3.141593F)) * MathHelper.cos((float) ((rotationPitch / 180F) * 3.141593F)) * range;
			float handY = -MathHelper.sin((float) ((rotationPitch / 180F) * 3.141593F)) * range - 0.1F;
			float handZ = MathHelper.cos((float) (((rotationYaw + offset / 1.5F) / 180F) * 3.141593F)) * MathHelper.cos((float) ((rotationPitch / 180F) * 3.141593F)) * range;
			
			// Calculate the actual position of the particles.
			double particleX = posX + handX;
			double particleY = posY + handY;
			double particleZ = posZ + handZ;
			
			world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL,
					particleX + (float)(rand.nextFloat() / 16f),
					particleY + (float)(rand.nextFloat() / 16f),
					particleZ + (float)(rand.nextFloat() / 16f),
					0d, 0d, 0d, 0);
		}
		
		/* Play sound based on effect. */
		switch (effect)
		{
			case MISFIRE:
				player.world.playSound(posX, posY, posZ, SoundEvents.BLOCK_NOTE_HAT, SoundCategory.PLAYERS,
						0.5f, 1.0f / (new Random().nextFloat() * 0.4F + 1.2F), true);
				break;
			case MISFIRE_WET:
				world.playSound(posX, posY, posZ, SoundEvents.BLOCK_NOTE_HAT, SoundCategory.PLAYERS,
						0.5f, 1.0f / (new Random().nextFloat() * 0.4F + 1.2F), true);
				world.playSound(posX, posY, posZ, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS,
						0.5f, 1.0f / (new Random().nextFloat() * 0.4F + 1.2F), true);
			case BREAK:
				world.playSound(posX, posY, posZ, SoundEvents.ITEM_SHIELD_BREAK, SoundCategory.PLAYERS,
						0.5f, 0.25f / (new Random().nextFloat() * 0.4F + 1.2F), true);
				break;
			default:
				break;
		}
		
	}
	
	public static void doArtilleryShootEffect(World world, Entity shootingEntity, ArtilleryEffect effect, double posX, double posY, double posZ, double rotationPitch, double rotationYaw, int parameter)
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
			float handX = -MathHelper.sin((float) (((rotationYaw) / 180F) * 3.141593F)) * MathHelper.cos((float) ((rotationPitch / 180F) * 3.141593F)) * range;
			float handY = -MathHelper.sin((float) ((rotationPitch / 180F) * 3.141593F)) * range - 0.1F;
			float handZ = MathHelper.cos((float) (((rotationYaw) / 180F) * 3.141593F)) * MathHelper.cos((float) ((rotationPitch / 180F) * 3.141593F)) * range;
			
			// Calculate the actual position of the particles.
			double particleX = posX + handX;
			double particleY = posY + handY;
			double particleZ = posZ + handZ;
			
			world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL,
					particleX + (float)(rand.nextFloat() / 16f),
					particleY + (float)(rand.nextFloat() / 16f),
					particleZ + (float)(rand.nextFloat() / 16f),
					0d, 0d, 0d, 0);
			world.spawnParticle(EnumParticleTypes.SMOKE_LARGE,
					particleX + (float)(rand.nextFloat() / 16f),
					particleY + (float)(rand.nextFloat() / 16f),
					particleZ + (float)(rand.nextFloat() / 16f),
					0d, 0d, 0d, 0);
		}
		
		/* Play sound based on effect. */
		switch (effect)
		{
			case CANNON_SHOT:
				world.playSound(posX, posY, posZ, SoundEvents.ENTITY_LIGHTNING_THUNDER, SoundCategory.PLAYERS,
						10000.0f, 1.5f / (new Random().nextFloat() * 0.4F + 1.2F), true);
				world.playSound(posX, posY, posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS,
						1.0f, 1.5f / (new Random().nextFloat() * 0.4F + 1.2F), true);
				break;
			default:
				world.playSound(posX, posY, posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS,
						1.0f, 2.0f / (new Random().nextFloat() * 0.4F + 1.2F), true);
				break;
		}
		
	}
}
