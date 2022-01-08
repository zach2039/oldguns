package com.zach2039.oldguns.client.item;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.capability.firearmempty.FirearmEmptyCapability;

import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

/**
 * Some taken from TestMod3 on Github
 * @author grilled-salmon
 * @author Choonster
 */
public class FirearmEmptyPropertyFunction {

	public static final ResourceLocation ID = new ResourceLocation(OldGuns.MODID, "firearm_empty");
	
	private static final ClampedItemPropertyFunction GETTER = (stack, level, entity, seed) ->
	{
		final Level world = level != null ? level : entity != null ? entity.getCommandSenderWorld() : null;

		if (world == null) {
			return 0f;
		}

		return FirearmEmptyCapability.getIsEmpty(stack)
				.map(isEmpty -> (float) (isEmpty.get() ? 1f : 0f))
				.orElse(0f);
	};
	
	public static void registerForItem(final Item item) {
		ItemProperties.register(item, ID, GETTER);
	}
}
