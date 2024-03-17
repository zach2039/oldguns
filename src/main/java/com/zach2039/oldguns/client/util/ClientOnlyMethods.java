package com.zach2039.oldguns.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.neoforged.fml.DistExecutor;

import javax.annotation.Nullable;

/**
 * Contains implementations of client-only methods designed to be called through {@link DistExecutor}.
 *
 * @author Choonster
 */
class ClientOnlyMethods {
	@Nullable
	public static Player getClientPlayer() {
		return Minecraft.getInstance().player;
	}
}
