package com.zach2039.oldguns.api.capability.empty;

import javax.annotation.Nullable;

public interface IFirearmEmpty {
	/**
	 * Get if empty firearm.
	 *
	 * @return If firearm is empty.
	 */
	boolean get();

	/**
	 * Set empty state of firearm.
	 *
	 * @param isEmpty Firearm empty state
	 */
	void set(final boolean isEmpty);

	/**
	 * Should this object's last use time be updated automatically?
	 *
	 * @return Whether to receive automatic updates
	 */
	default boolean automaticUpdates() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Implementations must override {@link Object#equals(Object)} to perform a value comparison instead of a reference
	 * comparison.
	 */
	@Override
	boolean equals(@Nullable final Object obj);

	/**
	 * {@inheritDoc}
	 * <p>
	 * Implementations must override {@link Object#hashCode()} to generate a hash code based on the values used in
	 * {@link #equals(Object)}, as per the base method's contract.
	 */
	@Override
	int hashCode();
}
