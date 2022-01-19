package com.zach2039.oldguns.client.util;

import javax.annotation.Nullable;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.fml.DistExecutor;

/**
 * Contains implementations of client-only methods designed to be called through {@link DistExecutor}.
 *
 * @author Choonster
 */
class ClientOnlyMethods {
	@Nullable
	public static PlayerEntity getClientPlayer() {
		return Minecraft.getInstance().player;
	}
}
