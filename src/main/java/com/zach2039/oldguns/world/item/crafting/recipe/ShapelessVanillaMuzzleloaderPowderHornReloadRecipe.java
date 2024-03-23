package com.zach2039.oldguns.world.item.crafting.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.ammo.Ammo;
import com.zach2039.oldguns.api.ammo.FirearmAmmo;
import com.zach2039.oldguns.api.firearm.Firearm;
import com.zach2039.oldguns.api.firearm.FirearmCondition;
import com.zach2039.oldguns.api.firearm.FirearmSize;
import com.zach2039.oldguns.api.firearm.util.FirearmNBTHelper;
import com.zach2039.oldguns.api.firearm.util.PowderHornNBTHelper;
import com.zach2039.oldguns.capability.firearmempty.FirearmEmptyCapability;
import com.zach2039.oldguns.init.ModCrafting;
import com.zach2039.oldguns.world.item.firearm.FirearmItem;
import com.zach2039.oldguns.world.item.tools.PowderHornItem;
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
import net.neoforged.neoforge.common.CommonHooks;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class ShapelessVanillaMuzzleloaderPowderHornReloadRecipe extends ShapelessRecipe
{
	private final ItemStack result;
	private final boolean isSimple;
	
	public ShapelessVanillaMuzzleloaderPowderHornReloadRecipe(final String group, final ItemStack result, final NonNullList<Ingredient> ingredients) {
		super(group, CraftingBookCategory.MISC, result, ingredients);
		this.result = result;
		this.isSimple = ingredients.stream().allMatch(Ingredient::isSimple); 
	}
	
	private int getPowderAmount() {
		return (((Firearm)this.result.getItem()).getFirearmSize() != FirearmSize.SMALL) ? 2 : 1;
	}
		
	@Override
	public NonNullList<ItemStack> getRemainingItems(final CraftingContainer inv) {
		final NonNullList<ItemStack> remainingItems = NonNullList.withSize(inv.getContainerSize(), ItemStack.EMPTY);

		for (int i = 0; i < remainingItems.size(); ++i) {
			final ItemStack itemstack = inv.getItem(i);

			if (!itemstack.isEmpty() && (itemstack.getItem() instanceof PowderHornItem)) {
				ItemStack powderHornStack = itemstack.copy();
				// FIXME: Need to set powder amount and not try to check for output firearm size
				ItemStack powderStack = PowderHornNBTHelper.peekPowderStack(powderHornStack);
				powderStack.shrink(getPowderAmount());
				PowderHornNBTHelper.setPowderStack(powderHornStack, powderStack);
				remainingItems.set(i, powderHornStack);
			} else {
				remainingItems.set(i, CommonHooks.getCraftingRemainingItem(itemstack));
			}
		}

		return remainingItems;
	}
	
	@Override
	public boolean matches(CraftingContainer container, Level level) {
		StackedContents stackedcontents = new StackedContents();
		java.util.List<ItemStack> inputs = new java.util.ArrayList<>();
		int i = 0;
		
		for(int j = 0; j < container.getContainerSize(); ++j) {
			ItemStack itemstack = container.getItem(j);
			// Check if items are valid, but check status of firearms as well, since we don't want to try and reload a broken firearm
			if (!itemstack.isEmpty()) {
				if (!(itemstack.getItem() instanceof Firearm) && !(itemstack.getItem() instanceof PowderHornItem)) {
					++i;
		            if (isSimple)
		            stackedcontents.accountStack(itemstack, 1);
		            else inputs.add(itemstack);
				} else if (itemstack.getItem() instanceof PowderHornItem) {		
					int count = PowderHornNBTHelper.peekPowderCount(itemstack);
					boolean hasTag = PowderHornNBTHelper.hasPowderOfTag(itemstack, PowderHornItem.getPowderTag((((Firearm)this.result.getItem()))));
					int powderAmount = this.getPowderAmount();
					if (count >= powderAmount && hasTag) {
						++i;
			            if (isSimple)
			            stackedcontents.accountStack(itemstack, 1);
			            else inputs.add(itemstack);
			            OldGuns.LOGGER.debug("added " + itemstack);
					}
				} else {
					if (
							FirearmNBTHelper.getNBTTagCondition(itemstack) != FirearmCondition.BROKEN && 
							FirearmNBTHelper.peekNBTTagAmmoCount(itemstack) < ((Firearm)itemstack.getItem()).getAmmoCapacity()
						) {
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
		ItemStack powderHornStack = ItemStack.EMPTY;
		
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
		
		/* Find powder horn and copy to output. */
		for (int i = 0; i < inv.getContainerSize(); i++)
		{
			ItemStack stack = inv.getItem(i);
			
			/* If item is a ammo instance, set input ammo stack and break. */
			if (stack.getItem() instanceof PowderHornItem)
			{
				powderHornStack = stack.copy();
				break;
			}
		}
		
		/* Increase ammo count by one on stack, if all itemstacks were found and powder horn is of proper powder type. */
		if (!firearmStack.isEmpty() && !ammoStack.isEmpty() && !powderHornStack.isEmpty())
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
		return ModCrafting.Recipes.FIREARM_MUZZLELOADER_POWDER_HORN_RELOAD_SHAPELESS.get();
	}

	public static class Serializer implements RecipeSerializer<ShapelessVanillaMuzzleloaderPowderHornReloadRecipe> {
		static int maxWidth = 3;
		static int maxHeight = 3;
		private static final Codec<ShapelessVanillaMuzzleloaderPowderHornReloadRecipe> CODEC = RecordCodecBuilder.create(
				instance -> instance.group(
						ExtraCodecs.strictOptionalField(Codec.STRING, "group", "").forGetter(powderHornReloadRecipe -> powderHornReloadRecipe.getGroup()),
								ExtraCodecs.strictOptionalField(ItemStack.ITEM_WITH_COUNT_CODEC, "result", ItemStack.EMPTY).forGetter(powderHornReloadRecipe -> powderHornReloadRecipe.result),
								Ingredient.CODEC_NONEMPTY
										.listOf()
										.fieldOf("ingredients")
										.flatXmap(
												ingredientList -> {
													Ingredient[] aingredient = ingredientList
															.toArray(Ingredient[]::new);
													if (aingredient.length == 0) {
														return DataResult.error(() -> "No ingredients for shapeless powder horn reload recipe");
													} else {
														return aingredient.length > maxHeight * maxWidth
																? DataResult.error(() -> "Too many ingredients for shapeless powder horn reload recipe. The maximum is: %s".formatted(maxHeight * maxWidth))
																: DataResult.success(NonNullList.of(Ingredient.EMPTY, aingredient));
													}
												},
												DataResult::success
										)
										.forGetter(powderHornReloadRecipe -> powderHornReloadRecipe.getIngredients())
						)
						.apply(instance, ShapelessVanillaMuzzleloaderPowderHornReloadRecipe::new)
		);

		@Override
		public Codec<ShapelessVanillaMuzzleloaderPowderHornReloadRecipe> codec() {
			return CODEC;
		}

		@Override
		public ShapelessVanillaMuzzleloaderPowderHornReloadRecipe fromNetwork(FriendlyByteBuf buf) {
			String group = buf.readUtf();
			int i = buf.readVarInt();
			NonNullList<Ingredient> ingredients = NonNullList.withSize(i, Ingredient.EMPTY);

			for (int j = 0; j < ingredients.size(); ++j) {
				ingredients.set(j, Ingredient.fromNetwork(buf));
			}

			ItemStack result = buf.readItem();
			return new ShapelessVanillaMuzzleloaderPowderHornReloadRecipe(group, result, ingredients);
		}

		@Override
		public void toNetwork(FriendlyByteBuf buf, ShapelessVanillaMuzzleloaderPowderHornReloadRecipe powderHornReloadRecipe) {
			buf.writeUtf(powderHornReloadRecipe.getGroup());
			buf.writeVarInt(powderHornReloadRecipe.getIngredients().size());

			for (Ingredient ingredient : powderHornReloadRecipe.getIngredients()) {
				ingredient.toNetwork(buf);
			}

			buf.writeItem(powderHornReloadRecipe.result);
		}
	}
}

