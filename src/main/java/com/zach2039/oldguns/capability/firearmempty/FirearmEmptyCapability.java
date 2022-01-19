package com.zach2039.oldguns.capability.firearmempty;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.capability.firearmempty.IFirearmEmpty;
import com.zach2039.oldguns.api.firearm.util.FirearmNBTHelper;
import com.zach2039.oldguns.capability.CapabilityContainerListenerManager;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.ByteNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.zach2039.oldguns.util.ModInjectionUtil.Null;

/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on Github
 * 
 * @author Choonster
 *
 * With additions by:
 * @author grilled-salmon
 */
public final class FirearmEmptyCapability {

	@CapabilityInject(IFirearmEmpty.class)
	public static final Capability<IFirearmEmpty> FIREARM_EMPTY_CAPABILITY = Null();
	
	public static final Direction DEFAULT_FACING = null;
	
	public static final ResourceLocation ID = new ResourceLocation(OldGuns.MODID, "firearm_empty");
	
	public static void register() {
		CapabilityManager.INSTANCE.register(IFirearmEmpty.class, new Capability.IStorage<IFirearmEmpty>() {
			@Override
			public INBT writeNBT(final Capability<IFirearmEmpty> capability, final IFirearmEmpty instance, final Direction side) {
				return ByteNBT.valueOf(instance.isEmpty());
			}

			@Override
			public void readNBT(final Capability<IFirearmEmpty> capability, final IFirearmEmpty instance, final Direction side, final INBT nbt) {
				instance.setEmpty(((ByteNBT) nbt).getAsByte() != 0);
			}
		}, () -> new FirearmEmpty(true));
		
		CapabilityContainerListenerManager.registerListenerFactory(FirearmEmptyContainerListener::new);
	}
	
	public static LazyOptional<IFirearmEmpty> getFirearmEmpty(final ItemStack itemStack) {
		return itemStack.getCapability(FIREARM_EMPTY_CAPABILITY, DEFAULT_FACING);
	}
	
	public static void updateFirearmEmpty(final PlayerEntity player, final ItemStack itemStack) {
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
				updateFirearmEmpty(event.getPlayer(), itemStack);
			});
		}
	}
}
