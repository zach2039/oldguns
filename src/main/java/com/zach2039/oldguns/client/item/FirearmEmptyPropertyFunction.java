package com.zach2039.oldguns.client.item;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.capability.firearmempty.FirearmEmptyCapability;

import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class FirearmEmptyPropertyFunction {
	/**
	 * The ID of this function.
	 */
	public static final ResourceLocation ID = new ResourceLocation(OldGuns.MODID, "firearm_empty");

	/**
	 * The function.
	 */
	private static final ClampedItemPropertyFunction GETTER = (stack, level, entity, seed) -> // TODO: This may be clamped
	{
		final Level world = level != null ? level : entity != null ? entity.getCommandSenderWorld() : null;

		if (world == null) {
			return 0f;
		}

		return FirearmEmptyCapability.getIsEmpty(stack)
				.map(isEmpty -> (isEmpty.get() ? 1f : 0f) )
				.orElse(0f);
	};

	/**
	 * Add this getter to an {@link Item}.
	 *
	 * @param item The item
	 */
	public static void registerForItem(final Item item) {
		ItemProperties.register(item, ID, GETTER);
	}
}
