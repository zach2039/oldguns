package com.zach2039.oldguns.api.capability.empty;

import javax.annotation.Nullable;

public interface IFirearmEmpty {

	boolean get();

	void set(final boolean isEmpty);
	
	@Override
	boolean equals(@Nullable final Object obj);
	
	@Override
	int hashCode();
	
}
