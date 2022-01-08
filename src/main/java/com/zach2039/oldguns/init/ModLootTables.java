package com.zach2039.oldguns.init;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.zach2039.oldguns.OldGuns;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

public class ModLootTables {
	public static final ResourceLocation LOOT_TABLE_FIREARMS = RegistrationHandler.register("loot_table_firearms");
	public static final ResourceLocation LOOT_TABLE_AMMO = RegistrationHandler.register("loot_table_ammo");

	public static void registerLootTables() {}

	public static class RegistrationHandler {
		private static final Method REGISTER = ObfuscationReflectionHelper.findMethod(BuiltInLootTables.class, /* register */ "m_78769_", ResourceLocation.class);

		public static ResourceLocation register(final String name) {
			final ResourceLocation id = new ResourceLocation(OldGuns.MODID, name);

			try {
				return (ResourceLocation) REGISTER.invoke(null, id);
			} catch (final IllegalAccessException | InvocationTargetException e) {
				throw new RuntimeException("Failed to register loot table " + id, e);
			}
		}
	}
}

