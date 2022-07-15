package com.zach2039.oldguns.event;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.init.ModItems;

import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OldGuns.MODID)
public class EntityEventHandler {
	//private static final Method HURT = ObfuscationReflectionHelper.findMethod(LivingEntity.class, /* hurt */ "m_6469_", DamageSource.class, float.class);
	
	@SubscribeEvent
	public static void onEntityHurt(final LivingHurtEvent event) {
		
	}	
	
	@SubscribeEvent
	public static void onEntityEvent(final EntityJoinLevelEvent event) {
		if (event != null) {
			if (!event.getLevel().isClientSide()) {
				Entity entity = event.getEntity();
				RandomSource rand = event.getLevel().getRandom();
				Difficulty diff = event.getLevel().getDifficulty();
				
				float firearmEquipChance = OldGunsConfig.SERVER.mobSettings.mobFirearmEquipChance.get().floatValue();
				float armorEquipChance = OldGunsConfig.SERVER.mobSettings.mobArmorEquipChance.get().floatValue();
				
				if (entity instanceof Skeleton) {
					if (firearmEquipChance > 0.0f && rand.nextFloat() <= firearmEquipChance) {
						equipFirearm(entity, diff, rand);
						
						if (armorEquipChance > 0.0f && rand.nextFloat() <= armorEquipChance) {
							equipArmor(entity, diff, rand);
						}
					}
				}
			}
		}
	}
	
	private static void equipFirearm(Entity entity, Difficulty difficulty, RandomSource rand) {
		float diff = (difficulty.ordinal() / 4.0F);
		if (rand.nextFloat() < 0.15F + diff) {
			int i = rand.nextInt(2);

			if (rand.nextFloat() < 0.095F) {
				++i;
			}

			if (rand.nextFloat() < 0.095F) {
				++i;
			}

			if (rand.nextFloat() < 0.095F) {
				++i;
			}

			entity.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(getEquipmentForSlot(EquipmentSlot.MAINHAND, i)));
		} else {
			entity.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.MATCHLOCK_DERRINGER.get()));
		}
	}
	
	private static void equipArmor(Entity entity, Difficulty difficulty, RandomSource rand) {
		float diff = (difficulty.ordinal() / 4.0F);
		if (rand.nextFloat() < 0.15F + diff) {
			int i = rand.nextInt(2);

			if (rand.nextFloat() < 0.095F) {
				++i;
			}

			if (rand.nextFloat() < 0.095F) {
				++i;
			}

			if (rand.nextFloat() < 0.095F) {
				++i;
			}

			entity.setItemSlot(EquipmentSlot.HEAD, new ItemStack(getEquipmentForSlot(EquipmentSlot.HEAD, i)));
		} else {
			entity.setItemSlot(EquipmentSlot.HEAD, new ItemStack(ModItems.MUSKETEER_HAT.get()));
		}
	}
	
	private static Item getEquipmentForSlot(EquipmentSlot slot, int difficultyFactor) {
		switch(slot) {
			case MAINHAND:
				if (difficultyFactor == 0) {
					return ModItems.MATCHLOCK_PISTOL.get();
				} else if (difficultyFactor == 1) {
					return ModItems.MATCHLOCK_ARQUEBUS.get();
				} else if (difficultyFactor == 2) {
					return ModItems.MATCHLOCK_BLUNDERBUSS_PISTOL.get();
				} else if (difficultyFactor == 3) {
					return ModItems.MATCHLOCK_ARQUEBUS.get();
				} else if (difficultyFactor == 4) {
					return ModItems.MATCHLOCK_BLUNDERBUSS.get();
				} else if (difficultyFactor == 5) {
					return ModItems.MATCHLOCK_LONG_MUSKET.get();
				}
				return ModItems.MATCHLOCK_LONG_MUSKET.get();
			case HEAD:
				if (difficultyFactor == 0) {
					return ModItems.MUSKETEER_HAT.get();
				} else if (difficultyFactor == 1) {
					return ModItems.MUSKETEER_HAT.get();
				} else if (difficultyFactor == 2) {
					return ModItems.MUSKETEER_HAT.get();
				} else if (difficultyFactor == 3) {
					return ModItems.HORSEMANS_POT_HELM.get();
				} else if (difficultyFactor == 4) {
					return ModItems.HORSEMANS_POT_HELM.get();
				}
				return ModItems.HORSEMANS_POT_HELM.get();
			default:
				return null;
		}
	}
}
