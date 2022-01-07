package com.zach2039.oldguns.client.init;

import com.google.common.base.Preconditions;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.client.gui.ClientScreenManager;
import com.zach2039.oldguns.client.gui.ModGuiIDs;
import com.zach2039.oldguns.client.gui.inventory.GunsmithsBenchScreen;
import com.zach2039.oldguns.init.ModMenuTypes;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * Registers this mod's {@link MenuScreens.ScreenConstructor} and {@link ClientScreenManager.IScreenConstructor} implementations.
 *
 * @author Choonster
 */
@Mod.EventBusSubscriber(modid = OldGuns.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class ModScreenFactories {
	@SubscribeEvent
	public static void registerConstructors(final FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			registerMenuScreenConstructors();
			registerClientScreenConstructors();
		});
	}

	private static void registerMenuScreenConstructors() {
		MenuScreens.register(ModMenuTypes.GUNSMITHS_BENCH.get(), GunsmithsBenchScreen::new);
	}

	private static void registerClientScreenConstructors() {}

	@SuppressWarnings("unchecked")
	private static <T extends BlockEntity> T getBlockEntity(final BlockPos pos, final Class<T> blockEntityClass) {
		final ClientLevel level = getClientLevel();

		final BlockEntity blockEntity = level.getBlockEntity(pos);

		Preconditions.checkNotNull(blockEntity, "No BlockEntity found at %s", pos);
		Preconditions.checkState(blockEntityClass.isInstance(blockEntity), "Invalid BlockEntity at %s: expected %s, got %s", pos, blockEntityClass, blockEntity.getClass());

		return (T) blockEntity;
	}

	private static ClientLevel getClientLevel() {
		return Preconditions.checkNotNull(Minecraft.getInstance().level, "Client level is null");
	}
}
