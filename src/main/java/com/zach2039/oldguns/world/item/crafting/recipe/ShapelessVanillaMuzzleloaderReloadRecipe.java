package com.zach2039.oldguns.world.item.crafting.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.zach2039.oldguns.api.ammo.Ammo;
import com.zach2039.oldguns.api.ammo.FirearmAmmo;
import com.zach2039.oldguns.api.firearm.Firearm;
import com.zach2039.oldguns.api.firearm.FirearmCondition;
import com.zach2039.oldguns.api.firearm.util.FirearmNBTHelper;
import com.zach2039.oldguns.capability.firearmempty.FirearmEmptyCapability;
import com.zach2039.oldguns.init.ModCrafting;
import com.zach2039.oldguns.world.item.firearm.FirearmItem;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class ShapelessVanillaMuzzleloaderReloadRecipe extends ShapelessRecipe
{
	private final ItemStack result;
	private final boolean isSimple;
	
	public ShapelessVanillaMuzzleloaderReloadRecipe(final String group, final ItemStack result, final NonNullList<Ingredient> ingredients) {
		super(group, CraftingBookCategory.MISC, result, ingredients);
		this.result = result;
		this.isSimple = ingredients.stream().allMatch(Ingredient::isSimple);
	}
	
	@Override
	public boolean matches(CraftingContainer craftingContainer, Level level) {
		StackedContents stackedcontents = new StackedContents();
		java.util.List<ItemStack> inputs = new java.util.ArrayList<>();
		int i = 0;

		for(int j = 0; j < craftingContainer.getContainerSize(); ++j) {
			ItemStack itemstack = craftingContainer.getItem(j);
			// Check if items are valid, but check status of firearms as well, since we don't want to try and reload a broken firearm
			if (!itemstack.isEmpty()) {
				if (!(itemstack.getItem() instanceof Firearm)) {
					++i;
		            if (isSimple)
		            stackedcontents.accountStack(itemstack, 1);
		            else inputs.add(itemstack);
				} else {
					if (
							FirearmNBTHelper.getNBTTagCondition(itemstack) != FirearmCondition.BROKEN && 
							FirearmNBTHelper.peekNBTTagAmmoCount(itemstack) < ((Firearm)itemstack.getItem()).getAmmoCapacity()
						)
					{
						++i;
			            if (isSimple)
			            stackedcontents.accountStack(itemstack, 1);
			            else inputs.add(itemstack);
					}
				}
			}
		}

		return i == this.getIngredients().size() && (isSimple ? stackedcontents.canCraft(this, (IntList)null) : net.neoforged.neoforge.common.util.RecipeMatcher.findMatches(inputs,  this.getIngredients()) != null);
	}
	
	@Override
	public ItemStack assemble(final CraftingContainer inv, RegistryAccess registryAccess)
	{
		/* Required itemstacks for proper nbt results. */
		ItemStack firearmStack = ItemStack.EMPTY;
		ItemStack ammoStack = ItemStack.EMPTY;
		
		/* Find input firearm and copy to output. */
		for (int i = 0; i < inv.getContainerSize(); i++)
		{
			ItemStack stack = inv.getItem(i);
			
			/* If item is a firearm instance and can reload, set output stack and break. */
			if (stack.getItem() instanceof Firearm)
			{
				Firearm firearmItem = (Firearm) stack.getItem();
				
				if (firearmItem.canReload(stack) && FirearmNBTHelper.getNBTTagCondition(stack) != FirearmCondition.BROKEN)
				{
					firearmStack = stack.copy();
					break;
				}
			}
		}
		
		/* Find input ammo and copy to output. */
		for (int i = 0; i < inv.getContainerSize(); i++)
		{
			ItemStack stack = inv.getItem(i);
			
			/* If item is a ammo instance, set input ammo stack and break. */
			if (stack.getItem() instanceof FirearmAmmo)
			{
				ammoStack = stack.copy();
				break;
			}
		}
		
		/* Increase ammo count by one on stack, if all itemstacks were found. */
		if (!firearmStack.isEmpty() && !ammoStack.isEmpty())
		{
			FirearmNBTHelper.pushNBTTagAmmo(firearmStack, ammoStack);
			
			FirearmEmptyCapability.updateFirearmEmpty(firearmStack);
			
			return firearmStack;
		}
		
		return ItemStack.EMPTY;
	}

	@Override
	@Nonnull
	public ItemStack getResultItem(RegistryAccess registryAccess)
	{
		ItemStack outputStack = result.copy();
		
		if (outputStack.getItem() instanceof FirearmItem) {
			FirearmItem firearmItem = (FirearmItem)outputStack.getItem();
			
			List<ItemStack> dummyAmmoStackList = new ArrayList<ItemStack>();
			
			this.getIngredients().forEach((ing) -> {
				if (ing.getItems()[0].getItem() instanceof Ammo) {
					dummyAmmoStackList.add(ing.getItems()[0]);
					return;
				}
			});
			
			if (dummyAmmoStackList.isEmpty()) {
				dummyAmmoStackList.add(firearmItem.getDefaultProjectileForFirearm());
			}
	
			FirearmNBTHelper.setNBTTagMagazineStack(outputStack, dummyAmmoStackList);
	
			((FirearmItem)outputStack.getItem()).initNBTTags(outputStack);
	
			FirearmEmptyCapability.updateFirearmEmpty(outputStack);
		}

		return outputStack;
	}
	
	@Override
	public RecipeSerializer<?> getSerializer() {
		return ModCrafting.Recipes.FIREARM_MUZZLELOADER_RELOAD_SHAPELESS.get();
	}

	public static class Serializer implements RecipeSerializer<ShapelessVanillaMuzzleloaderReloadRecipe> {
		static int maxWidth = 3;
		static int maxHeight = 3;
		private static final Codec<ShapelessVanillaMuzzleloaderReloadRecipe> CODEC = RecordCodecBuilder.create(
				instance -> instance.group(
								ExtraCodecs.strictOptionalField(Codec.STRING, "group", "").forGetter(muzzleloaderReloadRecipe -> muzzleloaderReloadRecipe.getGroup()),
								ExtraCodecs.strictOptionalField(ItemStack.ITEM_WITH_COUNT_CODEC, "result", ItemStack.EMPTY).forGetter(muzzleloaderReloadRecipe -> muzzleloaderReloadRecipe.result),
								Ingredient.CODEC_NONEMPTY
										.listOf()
										.fieldOf("ingredients")
										.flatXmap(
												ingredientList -> {
													Ingredient[] aingredient = ingredientList
															.toArray(Ingredient[]::new);
													if (aingredient.length == 0) {
														return DataResult.error(() -> "No ingredients for shapeless muzzle-loader reload recipe");
													} else {
														return aingredient.length > maxHeight * maxWidth
																? DataResult.error(() -> "Too many ingredients for shapeless pmuzzle-loader reload recipe. The maximum is: %s".formatted(maxHeight * maxWidth))
																: DataResult.success(NonNullList.of(Ingredient.EMPTY, aingredient));
													}
												},
												DataResult::success
										)
										.forGetter(muzzleloaderReloadRecipe -> muzzleloaderReloadRecipe.getIngredients())
						)
						.apply(instance, ShapelessVanillaMuzzleloaderReloadRecipe::new)
		);

		@Override
		public Codec<ShapelessVanillaMuzzleloaderReloadRecipe> codec() {
			return CODEC;
		}

		@Override
		public ShapelessVanillaMuzzleloaderReloadRecipe fromNetwork(FriendlyByteBuf buf) {
			String group = buf.readUtf();
			int i = buf.readVarInt();
			NonNullList<Ingredient> ingredients = NonNullList.withSize(i, Ingredient.EMPTY);

			for (int j = 0; j < ingredients.size(); ++j) {
				ingredients.set(j, Ingredient.fromNetwork(buf));
			}

			ItemStack result = buf.readItem();
			return new ShapelessVanillaMuzzleloaderReloadRecipe(group, result, ingredients);
		}

		@Override
		public void toNetwork(FriendlyByteBuf buf, ShapelessVanillaMuzzleloaderReloadRecipe muzzleloaderReloadRecipe) {
			buf.writeUtf(muzzleloaderReloadRecipe.getGroup());
			buf.writeVarInt(muzzleloaderReloadRecipe.getIngredients().size());

			for (Ingredient ingredient : muzzleloaderReloadRecipe.getIngredients()) {
				ingredient.toNetwork(buf);
			}

			buf.writeItem(muzzleloaderReloadRecipe.result);
		}
	}
}

