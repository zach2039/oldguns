package com.zach2039.oldguns.init;

import com.zach2039.oldguns.OldGuns;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OldGuns.MODID)
public class ModAttributes {
	public static final Attribute ARMOR_PIERCE = new RangedAttribute("attribute.name.oldguns.generic.bullet_armor_pierce", 0.1D, 0.0D, 1.0D).setRegistryName(OldGuns.MODID, "bullet_armor_pierce").setSyncable(true);
	public static final Attribute MOUNTED_ACCURACY = new RangedAttribute("attribute.name.oldguns.generic.mounted_firearm_accuracy", 1.0D, 0.0D, 1.0D).setRegistryName(OldGuns.MODID, "mounted_firearm_accuracy").setSyncable(true);
		
	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Attribute> event) {
	    event.getRegistry().register(ARMOR_PIERCE);
	}
}
