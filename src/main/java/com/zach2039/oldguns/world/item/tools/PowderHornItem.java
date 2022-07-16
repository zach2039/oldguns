package com.zach2039.oldguns.world.item.tools;

import java.util.List;

import javax.annotation.Nullable;

import org.checkerframework.checker.nullness.qual.NonNull;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.firearm.Firearm;
import com.zach2039.oldguns.api.firearm.util.PowderHornNBTHelper;
import com.zach2039.oldguns.init.ModItems;
import com.zach2039.oldguns.init.ModTags;

import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class PowderHornItem extends Item {

	public static final int MAX_CAPACITY = 127;
	
	public PowderHornItem() {
		super(new Properties()
				.stacksTo(1)
				.tab(OldGuns.CREATIVE_MODE_TAB)
				);
	}
	
	// FIXME: This is bad
	public static TagKey<Item> getPowderTag(Firearm firearm) {
		switch(firearm.getMechanismType()) {
			case MATCHLOCK:
				return ModTags.Items.MATCHLOCK_SUITABLE_POWDER;
			case WHEELLOCK:
				return ModTags.Items.WHEELLOCK_SUITABLE_POWDER;
			case FLINTLOCK:
				return ModTags.Items.FLINTLOCK_SUITABLE_POWDER;
			case CAPLOCK:
				return ModTags.Items.CAPLOCK_SUITABLE_POWDER;
			default:
				return null;
		}
	}
	
	@NonNull
	public static ItemStack getDefaultPowderForTag(TagKey<Item> tag) {
		if (tag == ModTags.Items.MATCHLOCK_SUITABLE_POWDER)
			return new ItemStack(Items.GUNPOWDER);
		
		if (tag == ModTags.Items.WHEELLOCK_SUITABLE_POWDER)
				return new ItemStack(ModItems.MEDIUM_GRADE_BLACK_POWDER.get());
		
		if (tag == ModTags.Items.FLINTLOCK_SUITABLE_POWDER)
				return new ItemStack(ModItems.HIGH_GRADE_BLACK_POWDER.get());
		
		if (tag == ModTags.Items.CAPLOCK_SUITABLE_POWDER)
				return new ItemStack(ModItems.HIGH_GRADE_BLACK_POWDER.get());
		
		
		return ItemStack.EMPTY;
	}
	
	@Override
	public void appendHoverText(ItemStack stackIn, @Nullable Level level, List<Component> tooltip, TooltipFlag flagIn) {
		super.appendHoverText(stackIn, level, tooltip, flagIn);

		initNBTTags(stackIn);
		
		ItemStack powderStack = PowderHornNBTHelper.peekPowderStack(stackIn);
		
		tooltip.add(Component.literal(""));
		tooltip.add(Component.literal("Contains:").withStyle(ChatFormatting.GRAY));
		
		if (!powderStack.isEmpty()) {
			MutableComponent powderMsg = powderStack.getHoverName().copy().withStyle(ChatFormatting.DARK_GRAY);
			MutableComponent countMsg = Component.literal(" x" + powderStack.getCount()).withStyle(ChatFormatting.DARK_GRAY);
			tooltip.add(Component.literal("☼ ").withStyle(ChatFormatting.GRAY).append(powderMsg).append(countMsg));
		} else {
			tooltip.add(Component.literal("☼ ").withStyle(ChatFormatting.GRAY));
		}
	}
	
	@Override
	public boolean isBarVisible(ItemStack stack) {
		return true;
	}

	@Override
	public int getBarWidth(ItemStack stack) {
		initNBTTags(stack);
		
		int powderAmount = PowderHornNBTHelper.peekPowderCount(stack);
		if (powderAmount > 0 && powderAmount < 5) {
			// Force show a bit of bar to display that some powder is present in horn
			return Math.round(13.0F - (float)(MAX_CAPACITY - 5) * 13.0F / (float)MAX_CAPACITY);
		}
		
		return Math.round(13.0F - (float)(MAX_CAPACITY - powderAmount) * 13.0F / (float)MAX_CAPACITY);
	}
	
	@Override
	public int getBarColor(ItemStack pStack) {
		return Mth.hsvToRgb(1.0F, 0.1F, 1.0F);
	}
	
	public static void initNBTTags(ItemStack stackIn)
	{
		if (!stackIn.hasTag()) {
			stackIn.setTag(new CompoundTag());
		}
		
		if (!stackIn.getTag().contains("powder")) {
			stackIn.getTag().put("powder", ItemStack.EMPTY.serializeNBT());
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
