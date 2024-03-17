package com.zach2039.oldguns.world.item.tools;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class MortarAndPestleItem extends Item {

	public MortarAndPestleItem() {
		super(new Properties()
				.defaultDurability(512)
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
