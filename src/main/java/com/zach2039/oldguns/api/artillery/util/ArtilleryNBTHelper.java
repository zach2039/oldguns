package com.zach2039.oldguns.api.artillery.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;

public class ArtilleryNBTHelper {
	public static void setNBTTagMagazineStack(CompoundTag tag, List<ItemStack> firearmAmmoList)
	{		
		/* Accumulate list of firearm ammo objects into NBT form. */
		ListTag ammoStackNBTTagList = new ListTag();
		for (ItemStack ammoStack : firearmAmmoList)
		{
			/* Serialize ammo itemstack. */
			CompoundTag ammoStackNBTForm = ammoStack.serializeNBT();
			
			/* Store in TagList. */
			ammoStackNBTTagList.add(ammoStackNBTForm);
		}
		
		/* Set tag list on tag. */
		tag.put("itemList", ammoStackNBTTagList);
	}
	
	public static List<ItemStack> getNBTTagMagazineStack(CompoundTag tag)
	{		
		/* Get tag list from tag. */
		if (!tag.contains("itemList"))
			tag.put("itemList", new ListTag());
			
		ListTag ammoStackNBTTagList = tag.getList("itemList", Tag.TAG_COMPOUND);
		
		/* Populate list from deserialized itemstacks. */
		List<ItemStack> artilleryAmmoList = new ArrayList<ItemStack>();
		ammoStackNBTTagList.forEach((t) -> 
				{
					artilleryAmmoList.add(ItemStack.of((CompoundTag) t)); 
				}
			);
		
		return artilleryAmmoList;
	}
	
	public static void emptyNBTTagAmmo(CompoundTag tag)
	{
		/* Set ammo list on itemstack to empty list. */
		setNBTTagMagazineStack(tag, new ArrayList<ItemStack>());
	}
	
	public static void pushNBTTagAmmo(CompoundTag tag, ItemStack ammoStack)
	{
		/* Get ammo list from stack. */
		List<ItemStack> ammoStackList = getNBTTagMagazineStack(tag);
		
		/* Add item to list. */
		ammoStackList.add(ammoStack);
		
		/* Set ammo list on tag. */
		setNBTTagMagazineStack(tag, ammoStackList);
	}
	
	public static ItemStack peekNBTTagAmmo(CompoundTag tag)
	{
		/* Ammo output itemstack. */
		ItemStack ammoStackOutput = ItemStack.EMPTY;
		
		/* Get ammo list from stack. */
		List<ItemStack> ammoStackList = getNBTTagMagazineStack(tag);
		
		/* Get ammo count of tag. */
		int ammoCount = ammoStackList.size();
		
		/* Don't return anything from internal magazine stack if empty. */
		if (ammoCount > 0)
		{
			/* Get copy of topmost item. */
			ammoStackOutput = ammoStackList.get(ammoStackList.size() - 1).copy();
		}
		
		return ammoStackOutput;
	}
	
	public static int peekNBTTagAmmoCount(CompoundTag tag)
	{
		/* Get ammo list from stack. */
		List<ItemStack> ammoStackList = getNBTTagMagazineStack(tag);
		
		/* Get ammo count of firearm stack. */
		int ammoCount = ammoStackList.size();
		
		return ammoCount;
	}
	
	public static ItemStack popNBTTagAmmo(CompoundTag tag)
	{
		/* Ammo output itemstack. */
		ItemStack ammoStackOutput = ItemStack.EMPTY;
		
		/* Get ammo list from stack. */
		List<ItemStack> ammoStackList = getNBTTagMagazineStack(tag);
		
		/* Get ammo count of firearm stack. */
		int ammoCount = ammoStackList.size();
		
		/* Don't pop anything from internal magazine stack if empty. */
		if (ammoCount > 0)
		{
			/* Get copy of topmost item. */
			ammoStackOutput = ammoStackList.get(ammoStackList.size() - 1).copy();
			
			/* Remove topmost item from list. */
			ammoStackList.remove(ammoStackList.size() - 1);
			
			/* Set ammo list on itemstack. */
			setNBTTagMagazineStack(tag, ammoStackList);
		}
		
		return ammoStackOutput;
	}
}
