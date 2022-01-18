package com.zach2039.oldguns.api.artillery.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.common.util.Constants;

public class ArtilleryNBTHelper {
	public static void setNBTTagMagazineStack(CompoundNBT tag, List<ItemStack> firearmAmmoList)
	{		
		/* Accumulate list of firearm ammo objects into NBT form. */
		ListNBT ammoStackNBTTagList = new ListNBT();
		for (ItemStack ammoStack : firearmAmmoList)
		{
			/* Serialize ammo itemstack. */
			CompoundNBT ammoStackNBTForm = ammoStack.serializeNBT();
			
			/* Store in TagList. */
			ammoStackNBTTagList.add(ammoStackNBTForm);
		}
		
		/* Set tag list on tag. */
		tag.put("itemList", ammoStackNBTTagList);
	}
	
	public static List<ItemStack> getNBTTagMagazineStack(CompoundNBT tag)
	{		
		/* Get tag list from tag. */
		if (!tag.contains("itemList"))
			tag.put("itemList", new ListNBT());
			
		ListNBT ammoStackNBTTagList = tag.getList("itemList", Constants.NBT.TAG_COMPOUND);
		
		/* Populate list from deserialized itemstacks. */
		List<ItemStack> artilleryAmmoList = new ArrayList<ItemStack>();
		ammoStackNBTTagList.forEach((t) -> 
				{
					artilleryAmmoList.add(ItemStack.of((CompoundNBT) t)); 
				}
			);
		
		return artilleryAmmoList;
	}
	
	public static void emptyNBTTagAmmo(CompoundNBT tag)
	{
		/* Set ammo list on itemstack to empty list. */
		setNBTTagMagazineStack(tag, new ArrayList<ItemStack>());
	}
	
	public static void pushNBTTagAmmo(CompoundNBT tag, ItemStack ammoStack)
	{
		/* Get ammo list from stack. */
		List<ItemStack> ammoStackList = getNBTTagMagazineStack(tag);
		
		/* Add item to list. */
		ammoStackList.add(ammoStack);
		
		/* Set ammo list on tag. */
		setNBTTagMagazineStack(tag, ammoStackList);
	}
	
	public static ItemStack peekNBTTagAmmo(CompoundNBT tag)
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
	
	public static int peekNBTTagAmmoCount(CompoundNBT tag)
	{
		/* Get ammo list from stack. */
		List<ItemStack> ammoStackList = getNBTTagMagazineStack(tag);
		
		/* Get ammo count of firearm stack. */
		int ammoCount = ammoStackList.size();
		
		return ammoCount;
	}
	
	public static ItemStack popNBTTagAmmo(CompoundNBT tag)
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
