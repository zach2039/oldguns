package com.zach2039.oldguns.world.item.tools;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.firearm.FirearmCondition;
import com.zach2039.oldguns.api.firearm.util.FirearmNBTHelper;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PowderHornItem extends Item {

	public PowderHornItem() {
		super(new Properties()
				.defaultDurability(64)
				.setNoRepair()
				.tab(OldGuns.CREATIVE_MODE_TAB)
				);
	}
	
	public void initNBTTags(ItemStack stackIn)
	{
		if (!stackIn.getTag().contains("powder")) {
			stackIn.getTag().put("powder", ItemStack.EMPTY.serializeNBT());
			stackIn.setDamageValue(64);
		}
	}
	
	@Override
	public void onCraftedBy(ItemStack stackIn, Level worldIn, Player playerIn)	{
		super.onCraftedBy(stackIn, worldIn, playerIn);

		initNBTTags(stackIn);
	}

	@Override
	public ItemStack getDefaultInstance() {
		ItemStack stackIn = new ItemStack(this);

		initNBTTags(stackIn);

		return stackIn;
	}
	
	@Override
	public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> stackList) {
		if (this.allowedIn(tab)) {
			ItemStack hornStack = new ItemStack(this);
			initNBTTags(hornStack);
			
			stackList.add(hornStack);
		}
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
