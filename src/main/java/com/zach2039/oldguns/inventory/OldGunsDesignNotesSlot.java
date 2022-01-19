package com.zach2039.oldguns.inventory;

import com.zach2039.oldguns.item.tools.DesignNotesItem;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class OldGunsDesignNotesSlot extends Slot {
	
	public OldGunsDesignNotesSlot(IInventory p_40223_, int p_40224_, int p_40225_, int p_40226_) {
		super(p_40223_, p_40224_, p_40225_, p_40226_);
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		return (stack.getItem() instanceof DesignNotesItem);
	}

	@Override
	public int getMaxStackSize() {
		return 1;
	}	   
}
