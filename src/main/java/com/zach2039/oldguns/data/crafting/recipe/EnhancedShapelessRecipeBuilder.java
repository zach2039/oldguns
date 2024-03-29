package com.zach2039.oldguns.data.crafting.recipe;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import com.google.common.base.Preconditions;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.zach2039.oldguns.util.ModRegistryUtil;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.item.crafting.ShapelessRecipe.Serializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

/**
 * An extension of {@link ShapelessRecipeBuilder} that allows the recipe result to have NBT and a custom group name for
 * the recipe advancement.
 *
 * @author Choonster
 */
public class EnhancedShapelessRecipeBuilder<
RECIPE extends Recipe<?>,
BUILDER extends EnhancedShapelessRecipeBuilder<RECIPE, BUILDER>
> extends ShapelessRecipeBuilder {
	private static final Method ENSURE_VALID = ObfuscationReflectionHelper.findMethod(ShapelessRecipeBuilder.class, /* ensureValid */ "m_126207_", ResourceLocation.class);
	private static final Field CATEGORY = ObfuscationReflectionHelper.findField(ShapelessRecipeBuilder.class, /* category */ "f_244182_");
	private static final Field ADVANCEMENT = ObfuscationReflectionHelper.findField(ShapelessRecipeBuilder.class, /* advancement */ "f_126176_");
	private static final Field GROUP = ObfuscationReflectionHelper.findField(ShapelessRecipeBuilder.class, /* group */ "f_126177_");
	private static final Field INGREDIENTS = ObfuscationReflectionHelper.findField(ShapelessRecipeBuilder.class, /* ingredients */ "f_126175_");

	protected final ItemStack result;
	protected final RecipeSerializer<? extends RECIPE> serializer;
	protected String itemGroup;
	protected boolean hasDummyOutput;
	protected final List<ResourceLocation> conditions;

	protected EnhancedShapelessRecipeBuilder(final ItemStack result, final RecipeSerializer<? extends RECIPE> serializer) {
		super(RecipeCategory.MISC, result.getItem(), result.getCount());
		this.result = result;
		this.serializer = serializer;
		this.conditions = new ArrayList<ResourceLocation>();
	}

	@SuppressWarnings("unchecked")
	public EnhancedShapelessRecipeBuilder(ItemStack result, Serializer serializer) {
		super(RecipeCategory.MISC, result.getItem(), result.getCount());
		this.result = result;
		this.serializer = (RecipeSerializer<? extends RECIPE>) serializer;
		this.conditions = new ArrayList<ResourceLocation>();
	}

	/**
	 * Sets the item group name to use for the recipe advancement. This allows the result to be an item without an
	 * item group, e.g. minecraft:spawner.
	 *
	 * @param group The group name
	 * @return This builder
	 */
	@SuppressWarnings("unchecked")
	public BUILDER itemGroup(final String group) {
		itemGroup = group;
		return (BUILDER) this;
	}

	@SuppressWarnings("unchecked")
	public BUILDER hasDummyOutput() {
		hasDummyOutput = true;
		return (BUILDER) this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BUILDER requires(final TagKey<Item> tagIn) {
		return (BUILDER) super.requires(tagIn);
	}
	
	@SuppressWarnings("unchecked")
	public BUILDER requires(final TagKey<Item> tagIn, final int quantity) {
		return (BUILDER) super.requires(Ingredient.of(tagIn), quantity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BUILDER requires(final ItemLike itemIn) {
		return (BUILDER) super.requires(itemIn);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BUILDER requires(final ItemLike itemIn, final int quantity) {
		return (BUILDER) super.requires(itemIn, quantity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BUILDER requires(final Ingredient ingredientIn) {
		return (BUILDER) super.requires(ingredientIn);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BUILDER requires(final Ingredient ingredientIn, final int quantity) {
		return (BUILDER) super.requires(ingredientIn, quantity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BUILDER unlockedBy(final String name, final CriterionTriggerInstance criterionIn) {
		return (BUILDER) super.unlockedBy(name, criterionIn);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BUILDER group(final String group) {
		return (BUILDER) super.group(group);
	}

	@SuppressWarnings("unchecked")
	public BUILDER condition(final ResourceLocation condition) {
		this.conditions.add(condition);
		return (BUILDER) this;
	}

	/**
	 * Builds this recipe into a {@link FinishedRecipe}.
	 *
	 * @param consumer The recipe consumer
	 */
	@Override
	public void save(final Consumer<FinishedRecipe> consumer) {
		save(consumer, ModRegistryUtil.getKey(result.getItem()));
	}

	/**
	 * Builds this recipe into a {@link FinishedRecipe}. Use {@link #save(Consumer)} if save is the same as the ID for
	 * the result.
	 *
	 * @param consumer The recipe consumer
	 * @param save     The ID to use for the recipe
	 */
	@Override
	public void save(final Consumer<FinishedRecipe> consumer, final String save) {
		final ResourceLocation resourcelocation = ModRegistryUtil.getKey(result.getItem());
		if (new ResourceLocation(save).equals(resourcelocation)) {
			throw new IllegalStateException("Enhanced Shapeless Recipe " + save + " should remove its 'save' argument");
		} else {
			save(consumer, new ResourceLocation(save));
		}
	}

	/**
	 * Builds this recipe into a {@link FinishedRecipe}.
	 *
	 * @param consumer The recipe consumer
	 * @param id       The ID to use for the recipe
	 */
	@Override
	public void save(final Consumer<FinishedRecipe> consumer, final ResourceLocation id) {
		try {
			// Perform the super class's validation
			ENSURE_VALID.invoke(this, id);

			// Perform our validation
			validate(id);

			// We can't call the super method directly because it throws an exception when the result is an item that
			// doesn't belong to an item group (e.g. Mob Spawners).

			final Advancement.Builder advancementBuilder = ((Advancement.Builder) ADVANCEMENT.get(this))
					.parent(new ResourceLocation("minecraft", "recipes/root"))
					.addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
					.rewards(AdvancementRewards.Builder.recipe(id))
					.requirements(RequirementsStrategy.OR);

			String group = (String) GROUP.get(this);
			if (group == null) {
				group = "";
			}

			final var category = (RecipeCategory) CATEGORY.get(this);

			final List<Ingredient> ingredients = getIngredients();

			final var baseRecipe = new Result(id, result.getItem(), result.getCount(), group, determineBookCategory(category), ingredients, advancementBuilder, id.withPrefix("recipes/" + category.getFolderName() + "/"));

			consumer.accept(new SimpleFinishedRecipe(baseRecipe, result, serializer));
		} catch (final IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException("Failed to build Enhanced Shapeless Recipe " + id, e);
		}
	}

	@SuppressWarnings("unchecked")
	protected List<Ingredient> getIngredients() {
		try {
			return (List<Ingredient>) INGREDIENTS.get(this);
		} catch (final IllegalAccessException e) {
			throw new RuntimeException("Failed to get shapeless recipe ingredients", e);
		}
	}

	protected void validate(final ResourceLocation id) {
		//if (itemGroup == null && result.getItem().getItemCategory() == null && !hasDummyOutput) {
		//	throw new IllegalStateException("Enhanced Shapeless Recipe " + id + " has result " + result + " with no item group - use EnhancedShapedRecipeBuilder.itemGroup to specify one");
		//}
	}

	public static class Vanilla extends EnhancedShapelessRecipeBuilder<ShapelessRecipe, Vanilla> {
		private Vanilla(final ItemStack result) {
			super(result, RecipeSerializer.SHAPELESS_RECIPE);
		}

		/**
		 * Creates a new builder for a Vanilla shapeless recipe with NBT and/or a custom item group.
		 *
		 * @param result The recipe result
		 * @return The builder
		 */
		public static Vanilla shapelessRecipe(final ItemStack result) {
			return new Vanilla(result);
		}

		@Override
		protected void validate(final ResourceLocation id) {
			super.validate(id);

			if (!result.hasTag() && itemGroup == null && !hasDummyOutput) {
				throw new IllegalStateException("Vanilla Shapeless Recipe " + id + " has no NBT and no custom item group - use ShapelessRecipeBuilder instead");
			}
		}
	}

	public static class ConditionedResult implements FinishedRecipe {
		private final ResourceLocation id;
		private final Item result;
		private final int count;
		private final String group;
		private final List<Ingredient> ingredients;
		private final Advancement.Builder advancement;
		private final ResourceLocation advancementId;
		private final List<ResourceLocation> conditions;

		public ConditionedResult(ResourceLocation p_126222_, Item p_126223_, int p_126224_, String p_126225_, List<Ingredient> p_126226_, Advancement.Builder p_126227_, ResourceLocation p_126228_, List<ResourceLocation> conditions) {
			this.id = p_126222_;
			this.result = p_126223_;
			this.count = p_126224_;
			this.group = p_126225_;
			this.ingredients = p_126226_;
			this.advancement = p_126227_;
			this.advancementId = p_126228_;
			this.conditions = conditions;
		}

		public void serializeRecipeData(JsonObject p_126230_) {
			if (!this.group.isEmpty()) {
				p_126230_.addProperty("group", this.group);
			}

			JsonArray condArray = new JsonArray();

			for(ResourceLocation l : this.conditions) {
				JsonObject typeObj = new JsonObject();
				typeObj.addProperty("type", l.toString());
				condArray.add(typeObj); 
			}

			p_126230_.add("conditions", condArray);

			JsonArray jsonarray = new JsonArray();

			for(Ingredient ingredient : this.ingredients) {
				jsonarray.add(ingredient.toJson());
			}	         

			p_126230_.add("ingredients", jsonarray);
			JsonObject jsonobject = new JsonObject();
			jsonobject.addProperty("item", BuiltInRegistries.ITEM.getKey(this.result).toString());
			if (this.count > 1) {
				jsonobject.addProperty("count", this.count);
			}

			p_126230_.add("result", jsonobject);
		}

		public RecipeSerializer<?> getType() {
			return RecipeSerializer.SHAPELESS_RECIPE;
		}

		public ResourceLocation getId() {
			return this.id;
		}

		@Nullable
		public JsonObject serializeAdvancement() {
			return this.advancement.serializeToJson();
		}

		@Nullable
		public ResourceLocation getAdvancementId() {
			return this.advancementId;
		}
	}
}
