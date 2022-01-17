package com.zach2039.oldguns.client.gui;

import com.zach2039.oldguns.OldGuns;

import net.minecraft.util.ResourceLocation;

public class ModGuiIDs {

	public static class Container {
		
	}

	public static class Client {
		public static final ResourceLocation GUNSMITHS_BENCH = id("gunsmiths_bench");
	}

	private static ResourceLocation id(final String id) {
		return new ResourceLocation(OldGuns.MODID, id);
	}
}
