package com.zach2039.oldguns.world.entity.monster;

import javax.annotation.Nullable;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.init.ModEntities;
import com.zach2039.oldguns.init.ModItems;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.animal.horse.SkeletonHorse;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public class HarquebusierSkeleton extends AbstractFirearmSkeleton {

	public HarquebusierSkeleton(final EntityType<? extends AbstractFirearmSkeleton> entityType, Level level) {
		super(ModEntities.HARQUEBUSIER_SKELETON.get(), level);
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Skeleton.createAttributes();
	}
	
	@Override
	protected void populateDefaultEquipmentSlots(DifficultyInstance difficulty) {
		float diff = (difficulty.getEffectiveDifficulty() / 4.0F);
		if (this.random.nextFloat() < 0.15F + diff) {
			int i = this.random.nextInt(2);

			if (this.random.nextFloat() < 0.095F) {
				++i;
			}

			if (this.random.nextFloat() < 0.095F) {
				++i;
			}

			if (this.random.nextFloat() < 0.095F) {
				++i;
			}

			this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(HarquebusierSkeleton.getEquipmentForSlot(EquipmentSlot.MAINHAND, i)));
		} else {
			this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.WHEELLOCK_PISTOL.get()));
		}

		this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(ModItems.HORSEMANS_POT_HELM.get()));
		this.setItemSlot(EquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
	}

	@Nullable
	public static Item getEquipmentForSlot(EquipmentSlot slot, int difficultyFactor) {
		switch(slot) {
		case MAINHAND:
			if (difficultyFactor == 0) {
				return ModItems.WHEELLOCK_PISTOL.get();
			} else if (difficultyFactor == 1) {
				return ModItems.WHEELLOCK_DOUBLEBARREL_PISTOL.get();
			} else if (difficultyFactor == 2) {
				return ModItems.WHEELLOCK_ARQUEBUS.get();
			} else if (difficultyFactor == 3) {
				return ModItems.WHEELLOCK_CALIVER.get();
			} else if (difficultyFactor == 4) {
				return ModItems.WHEELLOCK_MUSKETOON.get();
			}
			return ModItems.WHEELLOCK_PISTOL.get();
		default:
			return null;
		}
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData groupData, @Nullable CompoundTag compound) {
		groupData = super.finalizeSpawn(level, difficulty, spawnType, groupData, compound);
		
		if (!this.level.isClientSide()) {
			SkeletonHorse skeletonHorse = EntityType.SKELETON_HORSE.create(this.level);
			skeletonHorse.moveTo(this.getX(), this.getY(), this.getZ());
			skeletonHorse.setTamed(true);
			skeletonHorse.finalizeSpawn(level, difficulty, spawnType, (SpawnGroupData)null, (CompoundTag)null);
			this.level.addFreshEntity(skeletonHorse);
			this.startRiding(skeletonHorse);
		}
		
		return groupData;
	}
}
