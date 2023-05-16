package com.zach2039.oldguns.world.item.tools;

import com.zach2039.oldguns.OldGuns;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class HacksawItem extends Item {

	public HacksawItem() {
		super(new Properties()
				.defaultDurability(256)
				);
	}
	
	@Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
        
    }
    
    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemstack) {
        final ItemStack copy = itemstack.copy();
       
        return copy;
    }
}
