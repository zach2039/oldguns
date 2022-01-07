package com.zach2039.oldguns.capability.firearmempty;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.capability.empty.IFirearmEmpty;
import com.zach2039.oldguns.api.firearm.util.FirearmNBTHelper;
import com.zach2039.oldguns.capability.SerializableCapabilityProvider;

import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class FirearmEmptyCapability {
	/**
	 * The {@link Capability} instance.
	 */
	public static final Capability<IFirearmEmpty> FIREARM_EMPTY_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
	});

	/**
	 * The default {@link Direction} to use for this capability.
	 */
	public static final Direction DEFAULT_FACING = null;

	/**
	 * The ID of this capability.
	 */
	public static final ResourceLocation ID = new ResourceLocation(OldGuns.MODID, "firearm_empty");

	public static void register(final RegisterCapabilitiesEvent event) {
		event.register(IFirearmEmpty.class);
	}

	/**
	 * Get the {@link IFirearmEmpty} from the specified {@link ItemStack}'s capabilities, if any.
	 *
	 * @param itemStack The ItemStack
	 * @return A lazy optional containing the ILastUseTime, if any
	 */
	public static LazyOptional<IFirearmEmpty> getIsEmpty(final ItemStack itemStack) {
		return itemStack.getCapability(FIREARM_EMPTY_CAPABILITY, DEFAULT_FACING);
	}

	/**
	 * Update the last use time of the player's held firearm.
	 *
	 * @param player    The player
	 * @param itemStack The held ItemStack
	 */
	public static void updateIsEmpty(final Player player, final ItemStack itemStack) {
		getIsEmpty(itemStack).ifPresent((isEmpty) -> {
			isEmpty.set(FirearmNBTHelper.getNBTTagMagazineStack(itemStack).isEmpty());
		});
	}

	/**
	 * Create a provider for the specified {@link IFirearmEmpty} instance.
	 *
	 * @param lastUseTime The ILastUseTime
	 * @return The provider
	 */
	public static ICapabilityProvider createProvider(final IFirearmEmpty isEmpty) {
		return new SerializableCapabilityProvider<>(FIREARM_EMPTY_CAPABILITY, DEFAULT_FACING, isEmpty);
	}
}
