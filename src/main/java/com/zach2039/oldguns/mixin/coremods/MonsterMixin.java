package com.zach2039.oldguns.mixin.coremods;

import com.zach2039.oldguns.world.item.firearm.FirearmItem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Monster.class)
public class MonsterMixin {
	/**
	 * Mixin to select projectile for firearm users.
	 * @param stack
	 * @param cir
	 */
	@Inject(method = "getProjectile", at = @At(value = "HEAD"), cancellable = true)
	private void getProjectile(ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
		if (stack.getItem() instanceof FirearmItem) {
			net.minecraft.world.item.Item ammoItem = ((FirearmItem)stack.getItem()).getDefaultAmmoItem();
			ItemStack projectile = (ammoItem != null) ? new ItemStack(ammoItem) : ItemStack.EMPTY;
			cir.setReturnValue(projectile);
		}
	}
}
