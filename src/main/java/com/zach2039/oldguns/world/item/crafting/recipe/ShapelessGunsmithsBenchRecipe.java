package com.zach2039.oldguns.world.item.crafting.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.zach2039.oldguns.api.crafting.IDesignNotes;
import com.zach2039.oldguns.api.firearm.Firearm;
import com.zach2039.oldguns.init.ModCrafting;
import com.zach2039.oldguns.util.ModRegistryUtil;
import com.zach2039.oldguns.world.inventory.GunsmithsBenchCraftingContainer;
import com.zach2039.oldguns.world.inventory.menu.GunsmithsBenchMenu;
import com.zach2039.oldguns.world.item.crafting.GunsmithsBenchRecipe;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;

public class ShapelessGunsmithsBenchRecipe implements GunsmithsBenchRecipe {

	final String group;
	final ItemStack result;
	final NonNullList<Ingredient> ingredients;
	protected final boolean isSimple;

	public ShapelessGunsmithsBenchRecipe(String group, ItemStack result, NonNullList<Ingredient> ingredients) {
		this.group = group;
		this.result = result;
		this.ingredients = ingredients;
		this.isSimple = ingredients.stream().allMatch(Ingredient::isSimple);
	}
	
	public ItemStack getResult() {
		return this.result;
	}

	@Override
	public boolean matches(GunsmithsBenchCraftingContainer craftinv, Level level) {
		StackedContents stackedcontents = new StackedContents();
		java.util.List<ItemStack> inputs = new java.util.ArrayList<>();
		int i = 0;

		for(int j = 0; j < craftinv.getContainerSize(); ++j) {
			ItemStack itemstack = craftinv.getItem(j);
			if (!itemstack.isEmpty()) {
				++i;
				if (isSimple)
					stackedcontents.accountStack(itemstack, 1);
				else inputs.add(itemstack);
			}
		}

		return i == this.ingredients.size() && (isSimple ? stackedcontents.canCraft(this, (IntList)null) : net.neoforged.neoforge.common.util.RecipeMatcher.findMatches(inputs,  this.ingredients) != null);
	}
	
	@Override
	public ItemStack assemble(final GunsmithsBenchCraftingContainer craftinv, RegistryAccess registryAccess) {
		ItemStack resultStack = this.result.copy();
		
		if (requiresDesignNotes(resultStack.getItem())) {
			ItemStack item = craftinv.getItem(GunsmithsBenchMenu.NOTES_SLOT);
			if (!(item.getItem() instanceof IDesignNotes))
				return ItemStack.EMPTY;
			
			String designName = IDesignNotes.getDesign(item);
			String resultName = ModRegistryUtil.getKey(resultStack.getItem()).toString();
			if (!designName.equals(resultName))
				return ItemStack.EMPTY;
		}
		
		if (resultStack.getItem() instanceof Firearm)
			((Firearm)this.result.getItem()).initNBTTags(resultStack);
		
		return resultStack;
	}

	@Override
	public boolean canCraftInDimensions(int x, int y) {
		return x * y >= this.ingredients.size();
	}
	
	@Override	
	public String getGroup() {
		return this.group;
	}
	
	@Override
	public NonNullList<Ingredient> getIngredients() {
		return this.ingredients;
	}
	
	@Override
    @Nonnull
    public ItemStack getResultItem(RegistryAccess registryAccess)
	{
		ItemStack outputStack = this.result;
		
		if (outputStack.getItem() instanceof Firearm)
			((Firearm)outputStack.getItem()).initNBTTags(outputStack);
		
		return outputStack;
	}
	
	@Override
	public RecipeSerializer<?> getSerializer() {
		return ModCrafting.Recipes.GUNSMITHS_BENCH_SHAPELESS.get();
	}

	public static class Serializer implements RecipeSerializer<ShapelessGunsmithsBenchRecipe> {
		static int maxWidth = 3;
		static int maxHeight = 3;
		private static final Codec<ShapelessGunsmithsBenchRecipe> CODEC = RecordCodecBuilder.create(
				instance -> instance.group(
								ExtraCodecs.strictOptionalField(Codec.STRING, "group", "").forGetter(gunsmithsBenchRecipe -> gunsmithsBenchRecipe.getGroup()),
								ExtraCodecs.strictOptionalField(ItemStack.ITEM_WITH_COUNT_CODEC, "result", ItemStack.EMPTY).forGetter(gunsmithsBenchRecipe -> gunsmithsBenchRecipe.result),
								Ingredient.CODEC_NONEMPTY
										.listOf()
										.fieldOf("ingredients")
										.flatXmap(
												ingredientList -> {
													Ingredient[] aingredient = ingredientList
															.toArray(Ingredient[]::new);
													if (aingredient.length == 0) {
														return DataResult.error(() -> "No ingredients for shapeless gunsmiths bench recipe");
													} else {
														return aingredient.length > maxHeight * maxWidth
																? DataResult.error(() -> "Too many ingredients for shapeless gunsmiths bench recipe. The maximum is: %s".formatted(maxHeight * maxWidth))
																: DataResult.success(NonNullList.of(Ingredient.EMPTY, aingredient));
													}
												},
												DataResult::success
										)
										.forGetter(gunsmithsBenchRecipe -> gunsmithsBenchRecipe.getIngredients())
						)
						.apply(instance, ShapelessGunsmithsBenchMortarAndPestleRecipe::new)
		);

		@Override
		public Codec<ShapelessGunsmithsBenchRecipe> codec() {
			return CODEC;
		}

		@Override
		public ShapelessGunsmithsBenchRecipe fromNetwork(FriendlyByteBuf buf) {
			String group = buf.readUtf();
			int i = buf.readVarInt();
			NonNullList<Ingredient> ingredients = NonNullList.withSize(i, Ingredient.EMPTY);

			for (int j = 0; j < ingredients.size(); ++j) {
				ingredients.set(j, Ingredient.fromNetwork(buf));
			}

			ItemStack result = buf.readItem();
			return new ShapelessGunsmithsBenchRecipe(group, result, ingredients);
		}

		@Override
		public void toNetwork(FriendlyByteBuf buf, ShapelessGunsmithsBenchRecipe gunsmithsBenchRecipe) {
			buf.writeUtf(gunsmithsBenchRecipe.getGroup());
			buf.writeVarInt(gunsmithsBenchRecipe.getIngredients().size());

			for (Ingredient ingredient : gunsmithsBenchRecipe.getIngredients()) {
				ingredient.toNetwork(buf);
			}

			buf.writeItem(gunsmithsBenchRecipe.result);
		}
	}
}
