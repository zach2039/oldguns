package com.zach2039.oldguns.init;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.world.item.crafting.conditions.CanCraftExoticItemsCondition;
import com.zach2039.oldguns.world.item.crafting.conditions.CanCraftFlintlockWeaponsCondition;

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
		CraftingHelper.register(CanCraftExoticItemsCondition.Serializer.INSTANCE);
		CraftingHelper.register(CanCraftFlintlockWeaponsCondition.Serializer.INSTANCE);
	}
}