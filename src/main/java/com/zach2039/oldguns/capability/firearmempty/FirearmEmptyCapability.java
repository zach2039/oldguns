package com.zach2039.oldguns.capability.firearmempty;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.capability.firearmempty.IFirearmEmpty;
import com.zach2039.oldguns.api.firearm.util.FirearmNBTHelper;
import com.zach2039.oldguns.capability.CapabilityContainerListenerManager;

import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on Github
 * 
 * @author Choonster
 *
 * With additions by:
 * @author zach2039
 */
public final class FirearmEmptyCapability {
	public static final Capability<IFirearmEmpty> FIREARM_EMPTY_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});
	
	public static final Direction DEFAULT_FACING = null;
	
	public static final ResourceLocation ID = new ResourceLocation(OldGuns.MODID, "firearm_empty");
	
	public static void register(final RegisterCapabilitiesEvent event) {
		event.register(IFirearmEmpty.class);
		
		CapabilityContainerListenerManager.registerListenerFactory(FirearmEmptyContainerListener::new);
	}
	
	public static LazyOptional<IFirearmEmpty> getFirearmEmpty(final ItemStack itemStack) {
		return itemStack.getCapability(FIREARM_EMPTY_CAPABILITY, DEFAULT_FACING);
	}
	
	public static void updateFirearmEmpty(final Player player, final ItemStack itemStack) {
		getFirearmEmpty(itemStack).ifPresent((firearmEmpty) -> {
			boolean empty = FirearmNBTHelper.peekNBTTagAmmoCount(itemStack) == 0;
			firearmEmpty.setEmpty(empty);
		});
	}
	
	public static void updateFirearmEmpty(final ItemStack itemStack) {
		getFirearmEmpty(itemStack).ifPresent((firearmEmpty) -> {
			boolean empty = FirearmNBTHelper.peekNBTTagAmmoCount(itemStack) == 0;
			firearmEmpty.setEmpty(empty);
		});
	}
	
	@Mod.EventBusSubscriber(modid = OldGuns.MODID)
	public static class EventHandler {
		/**
		 * Update the {@link ILastUseTime} of the player's held item when they right-click.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void playerInteract(final PlayerInteractEvent.RightClickItem event) {
			final ItemStack itemStack = event.getItemStack();

			getFirearmEmpty(itemStack).ifPresent(firearmEmpty -> {
				updateFirearmEmpty(event.getEntity(), itemStack);
			});
		}
	}
}
