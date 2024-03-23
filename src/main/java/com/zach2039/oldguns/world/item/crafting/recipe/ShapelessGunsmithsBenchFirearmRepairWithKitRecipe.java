package com.zach2039.oldguns.world.item.crafting.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.zach2039.oldguns.api.firearm.Firearm;
import com.zach2039.oldguns.api.firearm.FirearmCondition;
import com.zach2039.oldguns.api.firearm.util.FirearmNBTHelper;
import com.zach2039.oldguns.init.ModCrafting;
import com.zach2039.oldguns.world.inventory.GunsmithsBenchCraftingContainer;
import com.zach2039.oldguns.world.item.crafting.GunsmithsBenchRecipe;
import com.zach2039.oldguns.world.item.tools.RepairKitItem;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.event.EventHooks;

public class ShapelessGunsmithsBenchFirearmRepairWithKitRecipe extends ShapelessGunsmithsBenchRecipe implements GunsmithsBenchRecipe {

	public ShapelessGunsmithsBenchFirearmRepairWithKitRecipe(String group, ItemStack result, NonNullList<Ingredient> ingredients) {
		super(group, result, ingredients);
	}

	@Override
	public ItemStack assemble(final GunsmithsBenchCraftingContainer inv, RegistryAccess registryAccess) {
		ItemStack firearmStack = ItemStack.EMPTY;
		ItemStack repairKitStack = ItemStack.EMPTY;
		
		for (int i = 0; i < inv.getContainerSize(); i++) {
			ItemStack stack = inv.getItem(i);
			
			if (stack.getItem() instanceof Firearm) {				
				if (stack.isDamaged()) {
					firearmStack = stack.copy();
				}
			}
			
			if (stack.getItem() instanceof RepairKitItem) {
				repairKitStack = stack.copy();
			}
			
			if (!firearmStack.isEmpty() && !repairKitStack.isEmpty())
				break;
		}
		
		// Repair firearm by 25%
		if (!firearmStack.isEmpty() && !repairKitStack.isEmpty()) {
			int firearmDamage = firearmStack.getDamageValue();
			int repairAmount = Math.round((float)firearmStack.getMaxDamage() / (float)4);
			firearmStack.setDamageValue(Math.max(0, firearmDamage - repairAmount));
			
			if (FirearmNBTHelper.getNBTTagCondition(firearmStack) == FirearmCondition.BROKEN) {
				FirearmNBTHelper.setNBTTagCondition(firearmStack, FirearmCondition.VERY_POOR);
			}
			((Firearm)firearmStack.getItem()).initNBTTags(firearmStack);
			
			return firearmStack;
		}
		
		return ItemStack.EMPTY;
	}
	
	@Override
	public NonNullList<ItemStack> getRemainingItems(final GunsmithsBenchCraftingContainer inv) {
		final NonNullList<ItemStack> remainingItems = NonNullList.withSize(inv.getContainerSize(), ItemStack.EMPTY);

		for (int i = 0; i < remainingItems.size(); ++i) {
			final ItemStack itemstack = inv.getItem(i);

			if (!itemstack.isEmpty() && (itemstack.getItem() instanceof RepairKitItem)) {
				remainingItems.set(i, damageItem(itemstack.copy()));
			} else {
				remainingItems.set(i, CommonHooks.getCraftingRemainingItem(itemstack));
			}
		}

		return remainingItems;
	}
	
	private ItemStack damageItem(final ItemStack stack) {
		final Player craftingPlayer = CommonHooks.getCraftingPlayer();
		
		Level level = craftingPlayer.getCommandSenderWorld();
		if (stack.hurt(1, level.random, craftingPlayer instanceof ServerPlayer ? (ServerPlayer) craftingPlayer : null)) {
			EventHooks.onPlayerDestroyItem(craftingPlayer, stack, null);
			return ItemStack.EMPTY;
		}
		
		return stack;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return ModCrafting.Recipes.GUNSMITHS_BENCH_FIREARM_REPAIR_WITH_KIT_SHAPELESS.get();
	}

	public static class Serializer implements RecipeSerializer<ShapelessGunsmithsBenchFirearmRepairWithKitRecipe> {
		static int maxWidth = 3;
		static int maxHeight = 3;
		private static final Codec<ShapelessGunsmithsBenchFirearmRepairWithKitRecipe> CODEC = RecordCodecBuilder.create(
				instance -> instance.group(
								ExtraCodecs.strictOptionalField(Codec.STRING, "group", "").forGetter(firearmRepairWithKitRecipe -> firearmRepairWithKitRecipe.getGroup()),
								ExtraCodecs.strictOptionalField(ItemStack.ITEM_WITH_COUNT_CODEC, "result", ItemStack.EMPTY).forGetter(mortarAndPestleRecipe -> mortarAndPestleRecipe.result),
								Ingredient.CODEC_NONEMPTY
										.listOf()
										.fieldOf("ingredients")
										.flatXmap(
												ingredientList -> {
													Ingredient[] aingredient = ingredientList
															.toArray(Ingredient[]::new);
													if (aingredient.length == 0) {
														return DataResult.error(() -> "No ingredients for shapeless firearm repair recipe");
													} else {
														return aingredient.length > maxHeight * maxWidth
																? DataResult.error(() -> "Too many ingredients for shapeless firearm repair recipe. The maximum is: %s".formatted(maxHeight * maxWidth))
																: DataResult.success(NonNullList.of(Ingredient.EMPTY, aingredient));
													}
												},
												DataResult::success
										)
										.forGetter(firearmRepairWithKitRecipe -> firearmRepairWithKitRecipe.getIngredients())
						)
						.apply(instance, ShapelessGunsmithsBenchFirearmRepairWithKitRecipe::new)
		);

		@Override
		public Codec<ShapelessGunsmithsBenchFirearmRepairWithKitRecipe> codec() {
			return CODEC;
		}

		@Override
		public ShapelessGunsmithsBenchFirearmRepairWithKitRecipe fromNetwork(FriendlyByteBuf buf) {
			String group = buf.readUtf();
			int i = buf.readVarInt();
			NonNullList<Ingredient> ingredients = NonNullList.withSize(i, Ingredient.EMPTY);

			for (int j = 0; j < ingredients.size(); ++j) {
				ingredients.set(j, Ingredient.fromNetwork(buf));
			}

			ItemStack result = buf.readItem();
			return new ShapelessGunsmithsBenchFirearmRepairWithKitRecipe(group, result, ingredients);
		}

		@Override
		public void toNetwork(FriendlyByteBuf buf, ShapelessGunsmithsBenchFirearmRepairWithKitRecipe firearmRepairWithKitRecipe) {
			buf.writeUtf(firearmRepairWithKitRecipe.getGroup());
			buf.writeVarInt(firearmRepairWithKitRecipe.getIngredients().size());

			for (Ingredient ingredient : firearmRepairWithKitRecipe.getIngredients()) {
				ingredient.toNetwork(buf);
			}

			buf.writeItem(firearmRepairWithKitRecipe.result);
		}
	}
}
