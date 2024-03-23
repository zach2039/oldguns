package com.zach2039.oldguns.world.item.crafting.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.zach2039.oldguns.init.ModCrafting;
import com.zach2039.oldguns.world.inventory.GunsmithsBenchCraftingContainer;
import com.zach2039.oldguns.world.item.crafting.GunsmithsBenchRecipe;
import com.zach2039.oldguns.world.item.tools.MortarAndPestleItem;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.event.EventHooks;

public class ShapelessGunsmithsBenchMortarAndPestleRecipe extends ShapelessGunsmithsBenchRecipe implements GunsmithsBenchRecipe {

	public ShapelessGunsmithsBenchMortarAndPestleRecipe(String group, ItemStack result, NonNullList<Ingredient> ingredients) {
		super(group, result, ingredients);
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(final GunsmithsBenchCraftingContainer inv) {
		final NonNullList<ItemStack> remainingItems = NonNullList.withSize(inv.getContainerSize(), ItemStack.EMPTY);

		for (int i = 0; i < remainingItems.size(); ++i) {
			final ItemStack itemstack = inv.getItem(i);

			if (!itemstack.isEmpty() && (itemstack.getItem() instanceof MortarAndPestleItem)) {
				remainingItems.set(i, damageItem(itemstack.copy()));
			} else {
				remainingItems.set(i, CommonHooks.getCraftingRemainingItem(itemstack));
			}
		}

		return remainingItems;
	}

	private ItemStack damageItem(final ItemStack stack) {
		final Player craftingPlayer = CommonHooks.getCraftingPlayer();

		Level level = (craftingPlayer != null) ? craftingPlayer.getCommandSenderWorld() : null;
		RandomSource rand = (level != null) ? level.random : RandomSource.create();
		if (stack.hurt(1, rand, craftingPlayer instanceof ServerPlayer ? (ServerPlayer) craftingPlayer : null)) {
			EventHooks.onPlayerDestroyItem(craftingPlayer, stack, null);
			return ItemStack.EMPTY;
		}

		return stack;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return ModCrafting.Recipes.GUNSMITHS_BENCH_MORTAR_AND_PESTLE_SHAPELESS.get();
	}

	public static class Serializer implements RecipeSerializer<ShapelessGunsmithsBenchMortarAndPestleRecipe> {
		static int maxWidth = 3;
		static int maxHeight = 3;
		private static final Codec<ShapelessGunsmithsBenchMortarAndPestleRecipe> CODEC = RecordCodecBuilder.create(
				instance -> instance.group(
								ExtraCodecs.strictOptionalField(Codec.STRING, "group", "").forGetter(mortarAndPestleRecipe -> mortarAndPestleRecipe.getGroup()),
								ExtraCodecs.strictOptionalField(ItemStack.ITEM_WITH_COUNT_CODEC, "result", ItemStack.EMPTY).forGetter(mortarAndPestleRecipe -> mortarAndPestleRecipe.result),
								Ingredient.CODEC_NONEMPTY
										.listOf()
										.fieldOf("ingredients")
										.flatXmap(
												ingredientList -> {
													Ingredient[] aingredient = ingredientList
															.toArray(Ingredient[]::new);
													if (aingredient.length == 0) {
														return DataResult.error(() -> "No ingredients for shapeless mortar and pestle recipe");
													} else {
														return aingredient.length > maxHeight * maxWidth
																? DataResult.error(() -> "Too many ingredients for shapeless mortar and pestle recipe. The maximum is: %s".formatted(maxHeight * maxWidth))
																: DataResult.success(NonNullList.of(Ingredient.EMPTY, aingredient));
													}
												},
												DataResult::success
										)
										.forGetter(mortarAndPestleRecipe -> mortarAndPestleRecipe.getIngredients())
						)
						.apply(instance, ShapelessGunsmithsBenchMortarAndPestleRecipe::new)
		);

		@Override
		public Codec<ShapelessGunsmithsBenchMortarAndPestleRecipe> codec() {
			return CODEC;
		}

		@Override
		public ShapelessGunsmithsBenchMortarAndPestleRecipe fromNetwork(FriendlyByteBuf buf) {
			String group = buf.readUtf();
			int i = buf.readVarInt();
			NonNullList<Ingredient> ingredients = NonNullList.withSize(i, Ingredient.EMPTY);

			for (int j = 0; j < ingredients.size(); ++j) {
				ingredients.set(j, Ingredient.fromNetwork(buf));
			}

			ItemStack result = buf.readItem();
			return new ShapelessGunsmithsBenchMortarAndPestleRecipe(group, result, ingredients);
		}

		@Override
		public void toNetwork(FriendlyByteBuf buf, ShapelessGunsmithsBenchMortarAndPestleRecipe mortarAndPestleRecipe) {
			buf.writeUtf(mortarAndPestleRecipe.getGroup());
			buf.writeVarInt(mortarAndPestleRecipe.getIngredients().size());

			for (Ingredient ingredient : mortarAndPestleRecipe.getIngredients()) {
				ingredient.toNetwork(buf);
			}

			buf.writeItem(mortarAndPestleRecipe.result);
		}
	}
}
