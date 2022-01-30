package com.zach2039.oldguns.world.item.equipment;

import java.util.function.Consumer;

import javax.annotation.Nullable;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.client.init.ModLayerDefinitions;
import com.zach2039.oldguns.client.model.armor.MusketeerHatModel;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.IItemRenderProperties;

public class MusketeerHatItem extends ArmorItem {
	
	private static final String DEFAULT_ARMOR_TEXTURE_LOCATION = OldGuns.MODID + ":textures/model/armor/musketeer_hat.png";
	
	public MusketeerHatItem() {
		super(ArmorMaterials.LEATHER, EquipmentSlot.HEAD, new Properties()
				.durability(128)
				.tab(OldGuns.CREATIVE_MODE_TAB));
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void initializeClient(Consumer<net.minecraftforge.client.IItemRenderProperties> consumer) {
		consumer.accept(new IItemRenderProperties() {
			@Nullable
			@Override
			public HumanoidModel<?> getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {

				MusketeerHatModel<LivingEntity> model = new MusketeerHatModel<>(ModLayerDefinitions.MUSKETEER_HAT.bakeRoot());
				
				model.crouching = _default.crouching;
				model.riding = _default.riding;
				model.young = _default.young;
						
				return model;
			}
		});
	}
	
	@Nullable
	@Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return DEFAULT_ARMOR_TEXTURE_LOCATION;
    }
}
