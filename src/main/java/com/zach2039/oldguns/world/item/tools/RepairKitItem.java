package com.zach2039.oldguns.world.item.tools;

import com.zach2039.oldguns.OldGuns;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class RepairKitItem extends Item {

	public RepairKitItem() {
		super(new Properties()
				.defaultDurability(4)
				.tab(OldGuns.CREATIVE_MODE_TAB)
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
