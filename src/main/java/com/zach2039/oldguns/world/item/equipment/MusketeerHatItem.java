package com.zach2039.oldguns.world.item.equipment;

import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.client.init.ModLayerDefinitions;
import com.zach2039.oldguns.client.model.armor.MusketeerHatModel;
import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.config.OldGunsConfig.EquipmentSettings;
import com.zach2039.oldguns.init.ModAttributes;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public class MusketeerHatItem extends ArmorItem {
	
	private static final UUID[] ARMOR_MODIFIER_UUID_PER_SLOT = new UUID[]{UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};
	
	private static final String DEFAULT_ARMOR_TEXTURE_LOCATION = OldGuns.MODID + ":textures/model/armor/musketeer_hat.png";
	
	private Multimap<Attribute, AttributeModifier> defaultModifiers;
	
	public MusketeerHatItem() {
		super(ArmorMaterials.LEATHER, EquipmentSlot.HEAD, new Properties()
				.durability(128)
				.tab(OldGuns.CREATIVE_MODE_TAB));
		
		initAttributes();
	}
	
	private void initAttributes() {
		UUID uuid = ARMOR_MODIFIER_UUID_PER_SLOT[slot.getIndex()];
		Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
		builder.put(Attributes.ARMOR, new AttributeModifier(uuid, "Armor modifier", (double)this.getDefense(), AttributeModifier.Operation.ADDITION));
		builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness", (double)this.getToughness(), AttributeModifier.Operation.ADDITION));
		if (this.knockbackResistance > 0) {
			builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "Armor knockback resistance", (double)this.knockbackResistance, AttributeModifier.Operation.ADDITION));
		}
		
		EquipmentSettings equipSettings = OldGunsConfig.SERVER.equipmentSettings;
		if ((boolean) OldGunsConfig.getServer(equipSettings.allowEquipmentEffects)) {
			if ((boolean) OldGunsConfig.getServer(equipSettings.musketeerHatSettings.allowEffects)) {
				double pierceAmount = (Double) OldGunsConfig.getServer(equipSettings.musketeerHatSettings.percentArmorBypassIncrease);
				builder.put(ModAttributes.ARMOR_PIERCE.get(), new AttributeModifier(uuid, "Bullet armor pierce", pierceAmount, AttributeModifier.Operation.MULTIPLY_BASE));
			}
		}
		
		this.defaultModifiers = builder.build();
	}
	
	@Override
	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
		return slot == this.slot ? this.defaultModifiers : super.getDefaultAttributeModifiers(slot);
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void initializeClient(java.util.function.Consumer<net.minecraftforge.client.extensions.common.IClientItemExtensions> consumer) {
		consumer.accept(new IClientItemExtensions() {
			@Nullable
			@Override
			public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {

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
