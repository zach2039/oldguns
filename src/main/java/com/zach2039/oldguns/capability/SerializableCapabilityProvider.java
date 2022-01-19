package com.zach2039.oldguns.capability;

import javax.annotation.Nullable;

import com.google.common.base.Preconditions;

import net.minecraft.nbt.INBT;
import net.minecraft.tags.Tag;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * A simple implementation of {@link ICapabilityProvider} and {@link INBTSerializable} that supports a single {@link Capability} handler instance.
 * <p>
 * The handler instance must implement {@link INBTSerializable}.
 *
 * @author Choonster
 */
public class SerializableCapabilityProvider<HANDLER> extends SimpleCapabilityProvider<HANDLER> implements INBTSerializable<INBT> {
	private final INBTSerializable<INBT> serializableInstance;

	/**
	 * Create a provider for the specified handler instance.
	 *
	 * @param capability The Capability instance to provide the handler for
	 * @param facing     The Direction to provide the handler for
	 * @param instance   The handler instance to provide
	 */
	@SuppressWarnings("unchecked")
	public SerializableCapabilityProvider(final Capability<HANDLER> capability, @Nullable final Direction facing, final HANDLER instance) {
		super(capability, facing, instance);

		Preconditions.checkArgument(instance instanceof INBTSerializable, "instance must implement INBTSerializable");

		serializableInstance = (INBTSerializable<INBT>) instance;
	}

	@Override
	public INBT serializeNBT() {
		return serializableInstance.serializeNBT();
	}

	@Override
	public void deserializeNBT(final INBT tag) {
		serializableInstance.deserializeNBT(tag);
	}

}