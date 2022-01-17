package com.zach2039.oldguns.init;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.zach2039.oldguns.OldGuns;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on Github
 * 
 * @author Choonster
 *
 * With additions by:
 * @author grilled-salmon
 */
public class ModLootTables {
	public static final ResourceLocation LOOT_TABLE_MECHANISM = RegistrationHandler.register("loot_table_mechanism");
	public static final ResourceLocation LOOT_TABLE_MATCHLOCK_FIREARM = RegistrationHandler.register("loot_table_matchlock_firearm");
	public static final ResourceLocation LOOT_TABLE_DESIGN_NOTES_MECHANISM = RegistrationHandler.register("loot_table_design_notes_mechanism");
	public static final ResourceLocation LOOT_TABLE_DESIGN_NOTES_MATCHLOCK = RegistrationHandler.register("loot_table_design_notes_matchlock");
	public static final ResourceLocation LOOT_TABLE_DESIGN_NOTES_WHEELLOCK = RegistrationHandler.register("loot_table_design_notes_wheellock");
	public static final ResourceLocation LOOT_TABLE_DESIGN_NOTES_FLINTLOCK = RegistrationHandler.register("loot_table_design_notes_flintlock");

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

