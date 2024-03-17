package com.zach2039.oldguns.init;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.world.item.crafting.conditions.*;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.common.Mod.EventBusSubscriber.Bus;
import net.neoforged.neoforge.common.crafting.CraftingHelper;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = OldGuns.MODID, bus = Bus.MOD)
public class ModConditions {
	
	@SubscribeEvent
	public static void registerConditions(final RegisterEvent event) {
		if (event.getRegistryKey().equals(Registries.RECIPE_SERIALIZER))
        {
			CraftingHelper.register(CanCraftMatchlockWeaponsCondition.Serializer.INSTANCE);
			CraftingHelper.register(CanCraftWheellockWeaponsCondition.Serializer.INSTANCE);
			CraftingHelper.register(CanCraftFlintlockWeaponsCondition.Serializer.INSTANCE);
			CraftingHelper.register(CanCraftCaplockWeaponsCondition.Serializer.INSTANCE);
			
			CraftingHelper.register(CanCraftPaperCartridgesCondition.Serializer.INSTANCE);

			CraftingHelper.register(CanCraftIronFirearmAmmoCondition.Serializer.INSTANCE);
			CraftingHelper.register(CanCraftLeadFirearmAmmoCondition.Serializer.INSTANCE);
			CraftingHelper.register(CanCraftStoneFirearmAmmoCondition.Serializer.INSTANCE);
			
			CraftingHelper.register(CanCraftNavalCannonArtilleryCondition.Serializer.INSTANCE);
			CraftingHelper.register(CanCraftCongreveRocketStandArtilleryCondition.Serializer.INSTANCE);
			
			CraftingHelper.register(CanCraftArtilleryPowderChargesCondition.Serializer.INSTANCE);
			
			CraftingHelper.register(CanCraftIronArtilleryAmmoCondition.Serializer.INSTANCE);
			CraftingHelper.register(CanCraftLeadArtilleryAmmoCondition.Serializer.INSTANCE);
			CraftingHelper.register(CanCraftStoneArtilleryAmmoCondition.Serializer.INSTANCE);
			
			CraftingHelper.register(CanCraftBlastingPowderSticksCondition.Serializer.INSTANCE);
			
			CraftingHelper.register(CanCraftTntFromBlastingPowderSticksCondition.Serializer.INSTANCE);
			
			CraftingHelper.register(CanCraftMatchCordFromBarkStrandsCondition.Serializer.INSTANCE);
        }
	}
}
