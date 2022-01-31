package com.zach2039.oldguns.network.capability.firearmempty;

import com.zach2039.oldguns.api.capability.firearmempty.IFirearmEmpty;

import net.minecraft.network.FriendlyByteBuf;

/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on Github
 * 
 * @author Choonster
 *
 * With additions by:
 * @author zach2039
 */
class FirearmEmptyFunctions {
	static boolean convertFirearmEmptyToFirearmEmptyValue(final IFirearmEmpty firearmEmpty) {
		return firearmEmpty.isEmpty();
	}
	
	static boolean decodeFirearmEmptyValue(final FriendlyByteBuf buf) {
		return buf.readBoolean();
	}
	
	static void encodeFirearmEmptyValue(final boolean firearmEmptyValue, final FriendlyByteBuf buf) {
		buf.writeBoolean(firearmEmptyValue);
	}
	
	static void applyFirearmEmptyValueToFirearmEmpty(final IFirearmEmpty firearmEmpty, final boolean firearmEmptyValue) {
		firearmEmpty.setEmpty(firearmEmptyValue);
	}
}
