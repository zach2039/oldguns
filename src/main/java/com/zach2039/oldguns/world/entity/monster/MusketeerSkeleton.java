package com.zach2039.oldguns.world.entity.monster;

import javax.annotation.Nullable;

import com.zach2039.oldguns.init.ModEntities;
import com.zach2039.oldguns.init.ModItems;

import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class MusketeerSkeleton extends AbstractFirearmSkeleton {

	public MusketeerSkeleton(final EntityType<? extends AbstractFirearmSkeleton> entityType, Level level) {
		super(ModEntities.MUSKETEER_SKELETON.get(), level);
	}

	@Override
	protected void populateDefaultEquipmentSlots(DifficultyInstance difficulty) {
		super.populateDefaultEquipmentSlots(difficulty);
		this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.MATCHLOCK_CALIVER.get()));
		this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(ModItems.MUSKETEER_HAT.get()));
	}

	@Nullable
	public static Item getEquipmentForSlot(EquipmentSlot slot, int difficultyFactor) {
		switch(slot) {
		case MAINHAND:
			if (difficultyFactor == 0) {
				return ModItems.MATCHLOCK_DERRINGER.get();
			} else if (difficultyFactor == 1) {
				return ModItems.MATCHLOCK_ARQUEBUS.get();
			} else if (difficultyFactor == 2) {
				return ModItems.MATCHLOCK_CALIVER.get();
			} else if (difficultyFactor == 3) {
				return ModItems.MATCHLOCK_MUSKET.get();
			} else if (difficultyFactor == 4) {
				return ModItems.MATCHLOCK_BLUNDERBUSS.get();
			}
		default:
			return null;
		}
	}
}
