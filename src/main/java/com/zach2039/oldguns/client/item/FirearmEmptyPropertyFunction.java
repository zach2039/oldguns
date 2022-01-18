package com.zach2039.oldguns.client.item;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.firearm.util.FirearmNBTHelper;
import com.zach2039.oldguns.capability.firearmempty.FirearmEmptyCapability;

import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on Github
 * 
 * @author Choonster
 *
 * With additions by:
 * @author grilled-salmon
 */
public class FirearmEmptyPropertyFunction {

	public static final ResourceLocation ID = new ResourceLocation(OldGuns.MODID, "firearm_empty");
	
	private static final ClampedItemPropertyFunction GETTER = (stack, level, entity, seed) -> 
	{
		final World world = level != null ? level : entity != null ? entity.getCommandSenderWorld() : null;

		if (world == null) {
			return 1f;
		}
		
		return FirearmEmptyCapability.getFirearmEmpty(stack)
			.map(isEmpty -> (float)(FirearmNBTHelper.peekNBTTagAmmoCount(stack) == 0 ? 1f : 0f))
			.orElse(1f);

	};
		
		
	public static void registerForItem(final Item item) {
		ItemProperties.register(item, ID, GETTER);
	}
}
