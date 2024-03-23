package com.zach2039.oldguns.world.item.crafting.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.zach2039.oldguns.init.ModCrafting;
import com.zach2039.oldguns.world.inventory.GunsmithsBenchCraftingContainer;
import com.zach2039.oldguns.world.item.crafting.GunsmithsBenchRecipe;
import com.zach2039.oldguns.world.item.tools.HacksawItem;
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

public class ShapelessGunsmithsBenchHacksawRecipe extends ShapelessGunsmithsBenchRecipe implements GunsmithsBenchRecipe {

	public ShapelessGunsmithsBenchHacksawRecipe(String group, ItemStack result, NonNullList<Ingredient> ingredients) {
		super(group, result, ingredients);
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(final GunsmithsBenchCraftingContainer inv) {
		final NonNullList<ItemStack> remainingItems = NonNullList.withSize(inv.getContainerSize(), ItemStack.EMPTY);

		for (int i = 0; i < remainingItems.size(); ++i) {
			final ItemStack itemstack = inv.getItem(i);

			if (!itemstack.isEmpty() && (itemstack.getItem() instanceof HacksawItem)) {
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
		return ModCrafting.Recipes.GUNSMITHS_BENCH_HACKSAW_SHAPELESS.get();
	}

	public static class Serializer implements RecipeSerializer<ShapelessGunsmithsBenchHacksawRecipe> {
		static int maxWidth = 3;
		static int maxHeight = 3;
		private static final Codec<ShapelessGunsmithsBenchHacksawRecipe> CODEC = RecordCodecBuilder.create(
				instance -> instance.group(
								ExtraCodecs.strictOptionalField(Codec.STRING, "group", "").forGetter(hacksawRecipe -> hacksawRecipe.getGroup()),
								ExtraCodecs.strictOptionalField(ItemStack.ITEM_WITH_COUNT_CODEC, "result", ItemStack.EMPTY).forGetter(hacksawRecipe -> hacksawRecipe.result),
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
										.forGetter(hacksawRecipe -> hacksawRecipe.getIngredients())
						)
						.apply(instance, ShapelessGunsmithsBenchHacksawRecipe::new)
		);

		@Override
		public Codec<ShapelessGunsmithsBenchHacksawRecipe> codec() {
			return CODEC;
		}

		@Override
		public ShapelessGunsmithsBenchHacksawRecipe fromNetwork(FriendlyByteBuf buf) {
			String group = buf.readUtf();
			int i = buf.readVarInt();
			NonNullList<Ingredient> ingredients = NonNullList.withSize(i, Ingredient.EMPTY);

			for (int j = 0; j < ingredients.size(); ++j) {
				ingredients.set(j, Ingredient.fromNetwork(buf));
			}

			ItemStack result = buf.readItem();
			return new ShapelessGunsmithsBenchHacksawRecipe(group, result, ingredients);
		}

		@Override
		public void toNetwork(FriendlyByteBuf buf, ShapelessGunsmithsBenchHacksawRecipe hacksawRecipe) {
			buf.writeUtf(hacksawRecipe.getGroup());
			buf.writeVarInt(hacksawRecipe.getIngredients().size());

			for (Ingredient ingredient : hacksawRecipe.getIngredients()) {
				ingredient.toNetwork(buf);
			}

			buf.writeItem(hacksawRecipe.result);
		}
	}
}
