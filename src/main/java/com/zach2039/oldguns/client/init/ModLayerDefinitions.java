package com.zach2039.oldguns.client.init;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.client.model.BulletProjectileModel;
import com.zach2039.oldguns.client.model.CongreveRocketStandModel;
import com.zach2039.oldguns.client.model.NavalCannonModel;
import com.zach2039.oldguns.client.model.RocketProjectileModel;
import com.zach2039.oldguns.client.model.armor.HorsemansPotHelmModel;
import com.zach2039.oldguns.client.model.armor.MusketeerHatModel;

import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = OldGuns.MODID, value = Dist.CLIENT, bus = Bus.MOD)
public class ModLayerDefinitions {
	
	public static final LayerDefinition MUSKETEER_HAT = MusketeerHatModel.createBodyLayer();
	
	public static final LayerDefinition HORSEMANS_POT_HELM = HorsemansPotHelmModel.createBodyLayer();
	
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		// Entities
		event.registerLayerDefinition(BulletProjectileModel.LAYER_LOCATION, BulletProjectileModel::createBodyLayer);
		event.registerLayerDefinition(RocketProjectileModel.LAYER_LOCATION, RocketProjectileModel::createBodyLayer);
		
		// Tile Entities
		event.registerLayerDefinition(NavalCannonModel.LAYER_LOCATION, NavalCannonModel::createBodyLayer);
		event.registerLayerDefinition(CongreveRocketStandModel.LAYER_LOCATION, CongreveRocketStandModel::createBodyLayer);
		
		// Armor
		event.registerLayerDefinition(MusketeerHatModel.LAYER_LOCATION, MusketeerHatModel::createBodyLayer);
		event.registerLayerDefinition(HorsemansPotHelmModel.LAYER_LOCATION, HorsemansPotHelmModel::createBodyLayer);
	}	
}
