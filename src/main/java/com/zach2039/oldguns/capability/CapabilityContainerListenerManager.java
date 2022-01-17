package com.zach2039.oldguns.capability;

import java.awt.event.ContainerListener;
import java.util.HashSet;
import java.util.Set;

import com.google.common.base.Function;
import com.zach2039.oldguns.OldGuns;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Manages the {@link ContainerListener}s that handle syncing of each item capability.
 *
 * @author Choonster
 */
public class CapabilityContainerListenerManager {
	/**
	 * The {@link CapabilityContainerListener} factories.
	 */
	private static final Set<Function<ServerPlayer, CapabilityContainerListener<?>>> containerListenerFactories = new HashSet<>();

	/**
	 * Register a factory for a {@link CapabilityContainerListener}.
	 *
	 * @param factory The factory
	 */
	public static void registerListenerFactory(final Function<ServerPlayer, CapabilityContainerListener<?>> factory) {
		containerListenerFactories.add(factory);
	}

	@Mod.EventBusSubscriber(modid = OldGuns.MODID)
	@SuppressWarnings("unused")
	private static class EventHandler {

		/**
		 * Add the listeners to a {@link AbstractContainerMenu}.
		 *
		 * @param player    The player
		 * @param container The Container
		 */
		private static void addListeners(final ServerPlayer player, final AbstractContainerMenu container) {
			containerListenerFactories.forEach(
					factory -> container.addSlotListener(factory.apply(player))
			);
		}

		/**
		 * Add the listeners to {@link Player#inventoryMenu} when a {@link ServerPlayer} logs in.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void playerLoggedIn(final PlayerEvent.PlayerLoggedInEvent event) {
			if (event.getPlayer() instanceof final ServerPlayer player) {
				addListeners(player, player.inventoryMenu);
			}
		}

		/**
		 * Add the listeners to {@link Player#inventoryMenu} when a {@link ServerPlayer} is cloned.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void playerClone(final PlayerEvent.Clone event) {
			if (event.getPlayer() instanceof final ServerPlayer player) {
				addListeners(player, player.inventoryMenu);
			}
		}

		/**
		 * Add the listeners to an {@link AbstractContainerMenu} when it's opened by a {@link ServerPlayer}.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void containerOpen(final PlayerContainerEvent.Open event) {
			if (event.getPlayer() instanceof final ServerPlayer player) {
				addListeners(player, event.getContainer());
			}
		}
	}
}