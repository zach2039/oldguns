package com.zach2039.oldguns.init;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.world.item.crafting.conditions.CanCraftArtilleryPowderChargesCondition;
import com.zach2039.oldguns.world.item.crafting.conditions.CanCraftBlastingPowderSticksCondition;
import com.zach2039.oldguns.world.item.crafting.conditions.CanCraftCaplockWeaponsCondition;
import com.zach2039.oldguns.world.item.crafting.conditions.CanCraftCongreveRocketStandArtilleryCondition;
import com.zach2039.oldguns.world.item.crafting.conditions.CanCraftFlintlockWeaponsCondition;
import com.zach2039.oldguns.world.item.crafting.conditions.CanCraftIronArtilleryAmmoCondition;
import com.zach2039.oldguns.world.item.crafting.conditions.CanCraftIronFirearmAmmoCondition;
import com.zach2039.oldguns.world.item.crafting.conditions.CanCraftLeadArtilleryAmmoCondition;
import com.zach2039.oldguns.world.item.crafting.conditions.CanCraftLeadFirearmAmmoCondition;
import com.zach2039.oldguns.world.item.crafting.conditions.CanCraftMatchCordFromBarkStrandsCondition;
import com.zach2039.oldguns.world.item.crafting.conditions.CanCraftMatchlockWeaponsCondition;
import com.zach2039.oldguns.world.item.crafting.conditions.CanCraftNavalCannonArtilleryCondition;
import com.zach2039.oldguns.world.item.crafting.conditions.CanCraftPaperCartridgesCondition;
import com.zach2039.oldguns.world.item.crafting.conditions.CanCraftStoneArtilleryAmmoCondition;
import com.zach2039.oldguns.world.item.crafting.conditions.CanCraftStoneFirearmAmmoCondition;
import com.zach2039.oldguns.world.item.crafting.conditions.CanCraftTntFromBlastingPowderSticksCondition;
import com.zach2039.oldguns.world.item.crafting.conditions.CanCraftWheellockWeaponsCondition;

import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = OldGuns.MODID, bus = Bus.MOD)
public class ModConditions {
	
	@SubscribeEvent
	public static void registerConditions(final RegisterEvent event) {
		if (event.getRegistryKey().equals(ForgeRegistries.Keys.RECIPE_SERIALIZERS))
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
