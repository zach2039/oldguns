package com.zach2039.oldguns.api.firearm.util;

import java.util.ArrayList;
import java.util.List;

import com.zach2039.oldguns.api.firearm.FirearmType.FirearmCondition;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;

public class FirearmNBTHelper {
	
	public static void setNBTTagMagazineStack(ItemStack stackIn, List<ItemStack> firearmAmmoList)
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
		
		/* Set tag list on firearm stack. */
		stackIn.getTag().put("ammoList", ammoStackNBTTagList);
	}
	
	public static List<ItemStack> getNBTTagMagazineStack(ItemStack stackIn)
	{
		/* Create new NBT tag and set value to 0 if no NBT tag. */
		if (!stackIn.hasTag())
			setNBTTagMagazineStack(stackIn, new ArrayList<ItemStack>());
		
		/* Get tag list from firearm item. */
		if (!stackIn.getTag().contains("ammoList"))
			stackIn.getTag().put("ammoList", new ListTag());
			
		ListTag ammoStackNBTTagList = stackIn.getTag().getList("ammoList", Tag.TAG_COMPOUND);
		
		/* Populate list from deserialized itemstacks. */
		List<ItemStack> firearmAmmoList = new ArrayList<ItemStack>();
		ammoStackNBTTagList.forEach((t) -> 
				{
					firearmAmmoList.add(ItemStack.of((CompoundTag) t)); 
				}
			);
		
		return firearmAmmoList;
	}
	
	public static void emptyNBTTagAmmo(ItemStack stackIn)
	{
		/* Set ammo list on itemstack to empty list. */
		setNBTTagMagazineStack(stackIn, new ArrayList<ItemStack>());
	}
	
	public static void pushNBTTagAmmo(ItemStack stackIn, ItemStack ammoStack)
	{
		/* Get ammo list from stack. */
		List<ItemStack> ammoStackList = getNBTTagMagazineStack(stackIn);
		
		/* Add item to list. */
		ammoStackList.add(ammoStack);
		
		/* Set ammo list on itemstack. */
		setNBTTagMagazineStack(stackIn, ammoStackList);
	}
	
	public static ItemStack peekNBTTagAmmo(ItemStack stackIn)
	{
		/* Ammo output itemstack. */
		ItemStack ammoStackOutput = ItemStack.EMPTY;
		
		/* Get ammo list from stack. */
		List<ItemStack> ammoStackList = getNBTTagMagazineStack(stackIn);
		
		/* Get ammo count of firearm stack. */
		int ammoCount = ammoStackList.size();
		
		/* Don't return anything from internal magazine stack if empty. */
		if (ammoCount > 0)
		{
			/* Get copy of topmost item. */
			ammoStackOutput = ammoStackList.get(ammoStackList.size() - 1).copy();
		}
		
		return ammoStackOutput;
	}
	
	public static int peekNBTTagAmmoCount(ItemStack stackIn)
	{
		/* Get ammo list from stack. */
		List<ItemStack> ammoStackList = getNBTTagMagazineStack(stackIn);
		
		/* Get ammo count of firearm stack. */
		int ammoCount = ammoStackList.size();
		
		return ammoCount;
	}
	
	public static ItemStack popNBTTagAmmo(ItemStack stackIn)
	{
		/* Ammo output itemstack. */
		ItemStack ammoStackOutput = ItemStack.EMPTY;
		
		/* Get ammo list from stack. */
		List<ItemStack> ammoStackList = getNBTTagMagazineStack(stackIn);
		
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
			setNBTTagMagazineStack(stackIn, ammoStackList);
		}
		
		return ammoStackOutput;
	}
	
	/**
	 * Sets the condition state of the firearm itemstack instance.
	 * @param stackIn
	 * @param ammoCount
	 */
	public static void setNBTTagCondition(ItemStack stackIn, FirearmCondition condition)
	{
		/* Create new NBT tag if none. */
		if (!stackIn.hasTag())
			stackIn.setTag(new CompoundTag());
		
		stackIn.getTag().putInt("condition", condition.ordinal());
	}
	
	/**
	 * Gets the condition state of the firearm itemstack instance.
	 * @param stackIn
	 * @param ammoCount
	 */
	public static FirearmCondition getNBTTagCondition(ItemStack stackIn)
	{
		/* Create new NBT tag and set value if no NBT tag. */
		if (!stackIn.getTag().contains("condition"))
			setNBTTagCondition(stackIn, FirearmCondition.VERY_GOOD);

		return FirearmCondition.values()[stackIn.getTag().getInt("condition")];
	}
	
	/**
	 * Refreshes the condition of a firearm itemstack instance.
	 * @param stackIn
	 */
	public static void refreshFirearmCondition(ItemStack stackIn)
	{
		float damageLevel = (float)((float)stackIn.getDamageValue() / (float)stackIn.getMaxDamage());
		
		/* If firearm is broken, don't bother setting state. */
		if (FirearmNBTHelper.getNBTTagCondition(stackIn) == FirearmCondition.BROKEN)
			return;
		
		/* Set condition of firearm based on damage percentage. */
		if (damageLevel < 0.10f)
		{
			/* Very good condition. */
			FirearmNBTHelper.setNBTTagCondition(stackIn, FirearmCondition.VERY_GOOD);
		}
		else if (damageLevel < 0.25f)
		{
			/* Good condition. */
			FirearmNBTHelper.setNBTTagCondition(stackIn, FirearmCondition.GOOD);
		}
		else if (damageLevel < 0.75f)
		{
			/* Fair condition. */
			FirearmNBTHelper.setNBTTagCondition(stackIn, FirearmCondition.FAIR);
		}
		else if (damageLevel < 0.90f)
		{
			/* Poor condition. */
			FirearmNBTHelper.setNBTTagCondition(stackIn, FirearmCondition.POOR);
		}
		else if (stackIn.getDamageValue() != stackIn.getMaxDamage())
		{
			/* Very poor condition. */
			FirearmNBTHelper.setNBTTagCondition(stackIn, FirearmCondition.VERY_POOR);
		}
		else
		{
			FirearmNBTHelper.setNBTTagCondition(stackIn, FirearmCondition.BROKEN);
		}
	}
}
