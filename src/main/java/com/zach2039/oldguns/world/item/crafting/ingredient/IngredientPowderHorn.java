package com.zach2039.oldguns.world.item.crafting.ingredient;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.init.ModCrafting;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.AbstractIngredient;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IIngredientSerializer;
import net.minecraftforge.registries.ForgeRegistries;

public class IngredientPowderHorn extends AbstractIngredient {

	private final Set<Item> items;
	private final CompoundTag nbt;
	protected IngredientPowderHorn(Set<Item> items, CompoundTag nbt) {
		super(items.stream().map(item ->
        {
            ItemStack stack = new ItemStack(item);
            // copy NBT to prevent the stack from modifying the original, as capabilities or vanilla item durability will modify the tag
            stack.setTag(nbt.copy());
            return new Ingredient.ItemValue(stack);
        }));
	    if (items.isEmpty())
	    {
	        throw new IllegalArgumentException("Cannot create a IngredientAnyDesignNotes with no items");
	    }
	    this.items = Collections.unmodifiableSet(items);
	    this.nbt = nbt;
	}

	@Override
	public boolean isSimple() {
		return false;
	}

	@Override
	public IIngredientSerializer<? extends Ingredient> getSerializer() {
		return ModCrafting.Ingredients.POWDER_HORN;
	}

	@Override
	public JsonElement toJson() {
		JsonObject json = new JsonObject();
        json.addProperty("type", CraftingHelper.getID(ModCrafting.Ingredients.POWDER_HORN).toString());
        if (items.size() == 1)
        {
            json.addProperty("item", ForgeRegistries.ITEMS.getKey(items.iterator().next()).toString());
        }
        else
        {
            JsonArray items = new JsonArray();
            // ensure the order of items in the set is deterministic when saved to JSON
            this.items.stream().map(ForgeRegistries.ITEMS::getKey).sorted().forEach(name -> items.add(name.toString()));
            json.add("items", items);
        }
        json.addProperty("nbt", nbt.toString());
        return json;
	}
	
	@Override
	public boolean test(@Nullable ItemStack input)
	{
		if (input == null)
			return false;

		if (!items.contains(input.getItem()))
			return false;

		ItemStack powderStack = ItemStack.of(input.getOrCreateTag().getCompound("powder"));
		ItemStack powderStackTest = ItemStack.of(nbt.getCompound("powder"));

		if (powderStack.getItem() != powderStackTest.getItem())
			return false;

		if (powderStack.getCount() < powderStackTest.getCount())
			return false;

		return true;
	}

	public static IngredientPowderHorn of(CompoundTag nbt, ItemLike... items)
    {
        return new IngredientPowderHorn(Arrays.stream(items).map(ItemLike::asItem).collect(Collectors.toSet()), nbt);
    }

    public static IngredientPowderHorn of(ItemLike item, CompoundTag nbt)
    {
        return new IngredientPowderHorn(Set.of(item.asItem()), nbt);
    }
	
	public static class Serializer implements IIngredientSerializer<IngredientPowderHorn>
	{
		public static final Serializer INSTANCE = new Serializer();

		@Override
		public IngredientPowderHorn parse(JsonObject json)
		{
			// parse items
			Set<Item> items;
			if (json.has("item"))
				items = Set.of(CraftingHelper.getItem(GsonHelper.getAsString(json, "item"), true));
			else if (json.has("items"))
			{
				ImmutableSet.Builder<Item> builder = ImmutableSet.builder();
				JsonArray itemArray = GsonHelper.getAsJsonArray(json, "items");
				for (int i = 0; i < itemArray.size(); i++)
				{
					builder.add(CraftingHelper.getItem(GsonHelper.convertToString(itemArray.get(i), "items[" + i + ']'), true));
				}
				items = builder.build();
			}
			else
				throw new JsonSyntaxException("Must set either 'item' or 'items'");

			// parse NBT
			if (!json.has("nbt"))
				throw new JsonSyntaxException("Missing nbt, expected to find a String or JsonObject");
			CompoundTag nbt = CraftingHelper.getNBT(json.get("nbt"));

			return new IngredientPowderHorn(items, nbt);
		}

		@Override
		public IngredientPowderHorn parse(FriendlyByteBuf buffer)
		{
			Set<Item> items = Stream.generate(() -> buffer.readRegistryIdUnsafe(ForgeRegistries.ITEMS)).limit(buffer.readVarInt()).collect(Collectors.toSet());
			CompoundTag nbt = buffer.readNbt();
			return new IngredientPowderHorn(items, Objects.requireNonNull(nbt));
		}

		@Override
		public void write(FriendlyByteBuf buffer, IngredientPowderHorn ingredient)
		{
			buffer.writeVarInt(ingredient.items.size());
			for (Item item : ingredient.items)
				buffer.writeRegistryIdUnsafe(ForgeRegistries.ITEMS, item);
			buffer.writeNbt(ingredient.nbt);
		}
	}
}