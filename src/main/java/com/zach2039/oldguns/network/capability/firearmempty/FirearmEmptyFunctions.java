package com.zach2039.oldguns.network.capability.firearmempty;

import com.zach2039.oldguns.api.capability.empty.IFirearmEmpty;

import net.minecraft.network.FriendlyByteBuf;

/**
 * Some taken from TestMod3 on Github
 * @author grilled-salmon
 * @author Choonster
 */
class FirearmEmptyFunctions {
	static boolean convertFirearmEmptyToFirearmEmptyValue(final IFirearmEmpty firearmEmpty) {
		return firearmEmpty.get();
	}
	
	static boolean decodeFirearmEmptyValue(final FriendlyByteBuf buf) {
		return buf.readBoolean();
	}
	
	static void encodeFirearmEmptyValue(final boolean firearmEmptyValue, final FriendlyByteBuf buf) {
		buf.writeBoolean(firearmEmptyValue);
	}
	
	static void applyFirearmEmptyValueToFirearmEmpty(final IFirearmEmpty firearmEmpty, final boolean firearmEmptyValue) {
		firearmEmpty.set(firearmEmptyValue);
	}
}
