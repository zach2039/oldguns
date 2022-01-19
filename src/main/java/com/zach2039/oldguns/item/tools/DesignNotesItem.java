package com.zach2039.oldguns.item.tools;

import java.util.List;

import javax.annotation.Nullable;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.crafting.IDesignNotes;
import com.zach2039.oldguns.config.OldGunsConfig;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class DesignNotesItem extends Item implements IDesignNotes {
	
	public DesignNotesItem() {
		super(new Item.Properties()
				.stacksTo(1)
				.rarity(Rarity.RARE)
				.tab(OldGuns.ITEM_GROUP));
	}
	
	@Override
	public void fillItemCategory(ItemGroup tab, NonNullList<ItemStack> stackList) {
		if (this.allowdedIn(tab)) {

			ItemStack stackBase = new ItemStack(this);
			OldGunsConfig.SERVER.recipeSettings.designNotesSettings.designNotesRequiredItems.get().forEach((e) -> {
				stackList.add(IDesignNotes.setDesignTagOnItem(stackBase, e));
			});
			
		}
	}
	
	@Override
	public void appendHoverText(ItemStack stack, @Nullable World level, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		super.appendHoverText(stack, level, tooltip, flagIn);

		if (IDesignNotes.getDesign(stack) != "") {
			tooltip.add(new TranslationTextComponent("item." + IDesignNotes.getDesign(stack).replace(':', '.')));
		}
	}
	
	@Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
        
    }
    
    @Override
    public ItemStack getContainerItem(ItemStack itemstack) {
        final ItemStack copy = itemstack.copy();
       
        return copy;
    }
}
