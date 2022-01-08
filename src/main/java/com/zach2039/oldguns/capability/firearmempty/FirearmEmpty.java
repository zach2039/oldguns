package com.zach2039.oldguns.capability.firearmempty;

import javax.annotation.Nullable;

import com.zach2039.oldguns.api.capability.empty.IFirearmEmpty;

import net.minecraft.nbt.ByteTag;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * Some taken from TestMod3 on Github
 * @author grilled-salmon
 * @author Choonster
 */
public class FirearmEmpty implements IFirearmEmpty, INBTSerializable<ByteTag> {
	private boolean isEmpty;
	
	public FirearmEmpty() {}
	
	@Override
	public boolean get() {
		return this.isEmpty;
	}
	
	@Override
	public void set(final boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	@Override
	public ByteTag serializeNBT() {
		return ByteTag.valueOf(this.isEmpty);
	}

	@Override
	public void deserializeNBT(final ByteTag tag) {
		this.isEmpty = (tag.getAsByte() != 0);
	}
	
	@Override
	public boolean equals(@Nullable final Object obj) {
		if (this == obj) return true;
		if (obj == null || this.getClass() != obj.getClass()) return false;
		
		final FirearmEmpty that = (FirearmEmpty) obj;
		
		return this.isEmpty == that.isEmpty;
	}
	
	@Override
	public int hashCode() {
		int val = this.isEmpty ? 1 : 0;
		return (val ^ (val >>> 32));
	}
}
