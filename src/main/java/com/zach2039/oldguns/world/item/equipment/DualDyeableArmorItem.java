package com.zach2039.oldguns.world.item.equipment;

import com.zach2039.oldguns.api.equipment.DualColorDyeableItem;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;

public abstract class DualDyeableArmorItem extends ArmorItem implements DualColorDyeableItem {

	public DualDyeableArmorItem(ArmorMaterial materal, EquipmentSlot slot, Item.Properties builder) {
		super(materal, slot, builder);
	}
}
