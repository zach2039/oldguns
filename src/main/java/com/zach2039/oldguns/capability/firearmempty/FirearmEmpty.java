package com.zach2039.oldguns.capability.firearmempty;

import com.zach2039.oldguns.api.capability.firearmempty.IFirearmEmpty;

import net.minecraft.nbt.ByteTag;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on Github
 * 
 * @author Choonster
 *
 * With additions by:
 * @author grilled-salmon
 */
public class FirearmEmpty implements IFirearmEmpty, INBTSerializable<ByteTag> {
	private boolean isEmpty;
	
	public FirearmEmpty(final boolean isEmpty) {
		this.isEmpty = isEmpty;
	}
	
	@Override
	public boolean isEmpty() {
		return isEmpty;
	}
	
	@Override
	public void setEmpty(final boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	@Override
	public ByteTag serializeNBT() {
		return ByteTag.valueOf((byte)(this.isEmpty ? 1 : 0));
	}

	@Override
	public void deserializeNBT(final ByteTag tag) {
		this.isEmpty = (tag.getAsByte() != 0);
	}
}
