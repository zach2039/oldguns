package com.zach2039.oldguns.capability.firearmempty;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.capability.empty.IFirearmEmpty;
import com.zach2039.oldguns.api.firearm.util.FirearmNBTHelper;
import com.zach2039.oldguns.capability.CapabilityContainerListenerManager;
import com.zach2039.oldguns.capability.SerializableCapabilityProvider;

import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on Github
 * 
 * @author Choonster
 *
 * With additions by:
 * @author grilled-salmon
 */
public final class FirearmEmptyCapability {
	public static final Capability<IFirearmEmpty> FIREARM_EMPTY_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});
	
	public static final Direction DEFAULT_FACING = null;
	
	public static final ResourceLocation ID = new ResourceLocation(OldGuns.MODID, "firearm_empty");
	
	public static void register(final RegisterCapabilitiesEvent event) {
		event.register(IFirearmEmpty.class);
		
		CapabilityContainerListenerManager.registerListenerFactory(FirearmEmptyContainerListener::new);
	}
	
	public static LazyOptional<IFirearmEmpty> getIsEmpty(final ItemStack itemStack) {
		return itemStack.getCapability(FIREARM_EMPTY_CAPABILITY, DEFAULT_FACING);
	}
	
	public static void update(final Player player, final ItemStack itemStack) {
		getIsEmpty(itemStack).ifPresent((isEmpty) -> {
			isEmpty.set(FirearmNBTHelper.peekNBTTagAmmoCount(itemStack) == 0);
		});
	}
	
	public static ICapabilityProvider createProvider(final IFirearmEmpty firearmEmpty) {
		return new SerializableCapabilityProvider<>(FIREARM_EMPTY_CAPABILITY, DEFAULT_FACING, firearmEmpty);
	}
	
	@Mod.EventBusSubscriber(modid = OldGuns.MODID)
	public static class EventHandler {
		
		@SubscribeEvent
		public static void itemCreate(final PlayerEvent.ItemCraftedEvent event) {
			final ItemStack itemStack = event.getCrafting();

			getIsEmpty(itemStack).ifPresent(isEmpty -> {
				update(event.getPlayer(), itemStack);
			});
		}
	}

}
