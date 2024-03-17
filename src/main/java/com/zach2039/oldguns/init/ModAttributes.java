package com.zach2039.oldguns.init;

import com.zach2039.oldguns.OldGuns;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod.EventBusSubscriber(modid = OldGuns.MODID)
public class ModAttributes {
	public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(Registries.ATTRIBUTE, OldGuns.MODID);
	
	public static final DeferredHolder<?, ? extends Attribute> ARMOR_PIERCE = ATTRIBUTES.register("armor_pierce", () -> (Attribute) new RangedAttribute("attribute.name.oldguns.generic.bullet_armor_pierce", 0.1D, 0.0D, 1.0D).setSyncable(true));
	public static final DeferredHolder<?, ? extends Attribute> MOUNTED_ACCURACY = ATTRIBUTES.register("mounted_accuracy", () -> (Attribute) new RangedAttribute("attribute.name.oldguns.generic.mounted_firearm_accuracy", 1.0D, 0.0D, 1.0D).setSyncable(true));
		
	private static boolean isInitialized;
	
	/**
	 * Registers the {@link DeferredRegister} instance with the mod event bus.
	 * <p>
	 * This should be called during mod construction.
	 *
	 * @param modEventBus The mod event bus
	 */
	public static void initialize(final IEventBus modEventBus) {
		if (isInitialized) {
			throw new IllegalStateException("Already initialized");
		}

		ATTRIBUTES.register(modEventBus);

		isInitialized = true;
	}
}
