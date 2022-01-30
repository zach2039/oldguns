package com.zach2039.oldguns.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.init.ModItems;
import com.zach2039.oldguns.world.damagesource.OldGunsDamageSourceIndirectEntity;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingPackSizeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

@Mod.EventBusSubscriber(modid = OldGuns.MODID)
public class EntityEventHandler {
	private static final Method ACTUALLY_HURT = ObfuscationReflectionHelper.findMethod(LivingEntity.class, /* actuallyHurt */ "m_6475_", DamageSource.class, float.class);
	
	@SubscribeEvent
	public static void onEntityHurt(final LivingHurtEvent event) {
		// Handle percentage based armor bypass damage
		if (event.getSource() instanceof OldGunsDamageSourceIndirectEntity) {
			OldGunsDamageSourceIndirectEntity damagesource = (OldGunsDamageSourceIndirectEntity) event.getSource();
			LivingEntity target = event.getEntityLiving();
			if (target != null) {
				float totalDamage = event.getAmount();
				float damageBypassingArmor = totalDamage * damagesource.getPercentBypassArmor();
				float damage = totalDamage * (1 - damagesource.getPercentBypassArmor());
				
				DamageSource newDamageSource = new IndirectEntityDamageSource(damagesource.msgId, damagesource.getDirectEntity(), damagesource.getEntity()).setProjectile();
				
				try {
					ACTUALLY_HURT.invoke(target, newDamageSource.bypassArmor(), damageBypassingArmor);
					ACTUALLY_HURT.invoke(target, newDamageSource, damage);
				} catch (final IllegalAccessException | InvocationTargetException e) {
					OldGuns.LOGGER.error("Failed to damage entity " + target, e);
				} finally {
					OldGuns.LOGGER.debug("totalDamage: " + (totalDamage));
					OldGuns.LOGGER.debug("damage: " + damage);
					OldGuns.LOGGER.debug("damageBypassingArmor: " + damageBypassingArmor);
					
					event.setAmount(0.0f);
				}
			}
		}
	}	
}
