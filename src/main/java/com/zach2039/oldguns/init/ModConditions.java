package com.zach2039.oldguns.init;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.crafting.conditions.CanCraftFlintlockWeaponsCondition;
import com.zach2039.oldguns.crafting.conditions.CanCraftMatchlockWeaponsCondition;
import com.zach2039.oldguns.crafting.conditions.CanCraftWheellockWeaponsCondition;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = OldGuns.MODID, bus = Bus.MOD)
public class ModConditions {
	@SubscribeEvent
	public static void registerConditions(final RegistryEvent.Register<RecipeSerializer<?>> event) {
		CraftingHelper.register(CanCraftMatchlockWeaponsCondition.Serializer.INSTANCE);
		CraftingHelper.register(CanCraftWheellockWeaponsCondition.Serializer.INSTANCE);
		CraftingHelper.register(CanCraftFlintlockWeaponsCondition.Serializer.INSTANCE);
	}
}
