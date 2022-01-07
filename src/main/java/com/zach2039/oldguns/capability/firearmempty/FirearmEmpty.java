package com.zach2039.oldguns.capability.firearmempty;

import javax.annotation.Nullable;

import com.zach2039.oldguns.api.capability.empty.IFirearmEmpty;

import net.minecraft.nbt.ByteTag;
import net.minecraft.nbt.LongTag;
import net.minecraftforge.common.util.INBTSerializable;

public class FirearmEmpty implements IFirearmEmpty, INBTSerializable<ByteTag> {
	private boolean isEmpty;
	private final boolean automaticUpdates;
	
	public FirearmEmpty(final boolean automaticUpdates) {
		this.automaticUpdates = automaticUpdates;
	}
	
	@Override
	public ByteTag serializeNBT() {
		return ByteTag.valueOf((byte) (this.isEmpty ? 1 : 0));
	}

	@Override
	public void deserializeNBT(final ByteTag tag) {
		this.isEmpty = (tag.getAsByte() == 1 ? true : false);
	}
	
	@Override
	public boolean get() {
		return this.isEmpty;
	}

	@Override
	public void set(final boolean isEmpty) {
		this.isEmpty = isEmpty;
	}
	
	@Override
	public boolean equals(@Nullable final Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;

		final FirearmEmpty that = (FirearmEmpty) obj;

		return this.isEmpty == that.isEmpty;
	}

	@Override
	public int hashCode() {
		return (int) ((isEmpty ? 1 : 0) ^ ((isEmpty ? 1 : 0) >>> 32));
	}
}
