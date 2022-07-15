package com.zach2039.oldguns.world.item.tools;

import java.util.List;

import javax.annotation.Nullable;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.crafting.IDesignNotes;
import com.zach2039.oldguns.config.OldGunsConfig;

import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class DesignNotesItem extends Item implements IDesignNotes {
	
	public DesignNotesItem() {
		super(new Properties()
				.stacksTo(1)
				.rarity(Rarity.RARE)
				.tab(OldGuns.CREATIVE_MODE_TAB));
	}
	
	@Override
	public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> stackList) {
		if (this.allowedIn(tab)) {

			ItemStack stackBase = new ItemStack(this);
			OldGunsConfig.SERVER.recipeSettings.designNotesSettings.designNotesRequiredItems.get().forEach((e) -> {
				stackList.add(IDesignNotes.setDesignTagOnItem(stackBase, e));
			});
			
		}
	}
	
	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flagIn) {
		super.appendHoverText(stack, level, tooltip, flagIn);

		if (IDesignNotes.getDesign(stack) != "") {
			tooltip.add(Component.translatable("item." + IDesignNotes.getDesign(stack).replace(':', '.')));
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
