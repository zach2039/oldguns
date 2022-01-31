package com.zach2039.oldguns.event;

import com.zach2039.oldguns.OldGuns;

import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OldGuns.MODID)
public class EntityEventHandler {
	//private static final Method HURT = ObfuscationReflectionHelper.findMethod(LivingEntity.class, /* hurt */ "m_6469_", DamageSource.class, float.class);
	
	@SubscribeEvent
	public static void onEntityHurt(final LivingHurtEvent event) {
		
	}	
}
