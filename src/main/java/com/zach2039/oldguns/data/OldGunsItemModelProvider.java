package com.zach2039.oldguns.data;

import java.util.function.Supplier;

import com.google.common.base.Preconditions;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.client.item.FirearmEmptyPropertyFunction;
import com.zach2039.oldguns.init.ModItems;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelBuilder.Perspective;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.loaders.DynamicBucketModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.util.Lazy;

/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on Github
 * 
 * @author Choonster
 *
 * With additions by:
 * @author grilled-salmon
 */
public class OldGunsItemModelProvider extends ItemModelProvider {
	private static final String LAYER_0 = "layer0";
	
	private final Supplier<ModelFile> simpleModel = Lazy.of(() ->
			withGeneratedParent("simple_model")
					.transforms()

					.transform(Perspective.THIRDPERSON_RIGHT)
					.rotation(-80, 260, -40)
					.translation(-1, -2, 2.5f)
					.scale(0.9f, 0.9f, 0.9f)
					.end()

					.transform(Perspective.THIRDPERSON_LEFT)
					.rotation(-80, -280, 40)
					.translation(-1, -2, 2.5f)
					.scale(0.9f, 0.9f, 0.9f)
					.end()

					.transform(Perspective.FIRSTPERSON_RIGHT)
					.rotation(0, -90, 25)
					.translation(1.13f, 3.2f, 1.13f)
					.scale(0.68f, 0.68f, 0.68f)
					.end()

					.transform(Perspective.FIRSTPERSON_LEFT)
					.rotation(0, 90, -25)
					.translation(1.13f, 3.2f, 1.13f)
					.scale(0.68f, 0.68f, 0.68f)
					.end()

					.end()
	);

	private final Supplier<ModelFile> simpleFirearmModel = Lazy.of(() ->
			withGeneratedParent("simple_model")
					.transforms()

					.transform(Perspective.THIRDPERSON_RIGHT)
					.rotation(-80, 260, -40)
					.translation(-1, 1, 0f)
					.scale(0.9f, 0.9f, 0.9f)
					.end()

					.transform(Perspective.THIRDPERSON_LEFT)
					.rotation(-80, -280, 40)
					.translation(-1, 1, 0f)
					.scale(0.9f, 0.9f, 0.9f)
					.end()

					.transform(Perspective.FIRSTPERSON_RIGHT)
					.rotation(0, -90, 25)
					.translation(1.13f, 3.2f, 1.13f)
					.scale(0.68f, 0.68f, 0.68f)
					.end()

					.transform(Perspective.FIRSTPERSON_LEFT)
					.rotation(0, 90, -25)
					.translation(1.13f, 3.2f, 1.13f)
					.scale(0.68f, 0.68f, 0.68f)
					.end()

					.end()
	);
	
	public OldGunsItemModelProvider(final DataGenerator generator, final ExistingFileHelper existingFileHelper) {
		super(generator, OldGuns.MODID, existingFileHelper);
	}
	
	/**
	 * Gets a name for this provider, to use in logging.
	 */
	@Override
	public String getName() {
		return "OldGunsItemModels";
	}
	
	@Override
	protected void registerModels() {
		firearmMuzzleloaderItem(ModItems.FLINTLOCK_DERRINGER.get());
		firearmMuzzleloaderItem(ModItems.FLINTLOCK_DUCKFOOT_DERRINGER.get());
		firearmMuzzleloaderItem(ModItems.FLINTLOCK_PISTOL.get());
		firearmMuzzleloaderItem(ModItems.FLINTLOCK_PEPPERBOX_PISTOL.get());
		firearmMuzzleloaderItem(ModItems.FLINTLOCK_ARQUEBUS.get());
		firearmMuzzleloaderItem(ModItems.FLINTLOCK_CALIVER.get());
		firearmMuzzleloaderItem(ModItems.FLINTLOCK_MUSKETOON.get());
		firearmMuzzleloaderItem(ModItems.FLINTLOCK_MUSKET.get());
		firearmMuzzleloaderItem(ModItems.FLINTLOCK_NOCK_GUN.get());
		firearmMuzzleloaderItem(ModItems.FLINTLOCK_LONG_MUSKET.get());
		firearmMuzzleloaderItem(ModItems.FLINTLOCK_BLUNDERBUSS_PISTOL.get());
		firearmMuzzleloaderItem(ModItems.FLINTLOCK_BLUNDERBUSS.get());
		firearmMuzzleloaderItem(ModItems.FLINTLOCK_DOUBLEBARREL_BLUNDERBUSS.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.SMALL_IRON_MUSKET_BALL.get());
		withGeneratedParentAndDefaultTexture(ModItems.MEDIUM_IRON_MUSKET_BALL.get());
		withGeneratedParentAndDefaultTexture(ModItems.LARGE_IRON_MUSKET_BALL.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.SMALL_IRON_BUCKSHOT.get());
		withGeneratedParentAndDefaultTexture(ModItems.MEDIUM_IRON_BUCKSHOT.get());
		withGeneratedParentAndDefaultTexture(ModItems.LARGE_IRON_BUCKSHOT.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.SMALL_IRON_BIRDSHOT.get());
		withGeneratedParentAndDefaultTexture(ModItems.MEDIUM_IRON_BIRDSHOT.get());
		withGeneratedParentAndDefaultTexture(ModItems.LARGE_IRON_BIRDSHOT.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.SMALL_LEAD_MUSKET_BALL.get());
		withGeneratedParentAndDefaultTexture(ModItems.MEDIUM_LEAD_MUSKET_BALL.get());
		withGeneratedParentAndDefaultTexture(ModItems.LARGE_LEAD_MUSKET_BALL.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.SMALL_LEAD_BUCKSHOT.get());
		withGeneratedParentAndDefaultTexture(ModItems.MEDIUM_LEAD_BUCKSHOT.get());
		withGeneratedParentAndDefaultTexture(ModItems.LARGE_LEAD_BUCKSHOT.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.SMALL_LEAD_BIRDSHOT.get());
		withGeneratedParentAndDefaultTexture(ModItems.MEDIUM_LEAD_BIRDSHOT.get());
		withGeneratedParentAndDefaultTexture(ModItems.LARGE_LEAD_BIRDSHOT.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.FLINTLOCK_MECHANISM.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.SMALL_WOODEN_HANDLE.get());
		withGeneratedParentAndDefaultTexture(ModItems.MEDIUM_WOODEN_HANDLE.get());
		withGeneratedParentAndDefaultTexture(ModItems.LARGE_WOODEN_HANDLE.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.SMALL_WOODEN_STOCK.get());
		withGeneratedParentAndDefaultTexture(ModItems.MEDIUM_WOODEN_STOCK.get());
		withGeneratedParentAndDefaultTexture(ModItems.LARGE_WOODEN_STOCK.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.TINY_IRON_BARREL.get());
		withGeneratedParentAndDefaultTexture(ModItems.SMALL_IRON_BARREL.get());
		withGeneratedParentAndDefaultTexture(ModItems.MEDIUM_IRON_BARREL.get());
		withGeneratedParentAndDefaultTexture(ModItems.LARGE_IRON_BARREL.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.TINY_BRASS_BARREL.get());
		withGeneratedParentAndDefaultTexture(ModItems.SMALL_BRASS_BARREL.get());
		withGeneratedParentAndDefaultTexture(ModItems.MEDIUM_BRASS_BARREL.get());
		withGeneratedParentAndDefaultTexture(ModItems.LARGE_BRASS_BARREL.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.SMALL_IRON_FLARED_BARREL.get());
		withGeneratedParentAndDefaultTexture(ModItems.MEDIUM_IRON_FLARED_BARREL.get());
		withGeneratedParentAndDefaultTexture(ModItems.LARGE_IRON_FLARED_BARREL.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.SMALL_BRASS_FLARED_BARREL.get());
		withGeneratedParentAndDefaultTexture(ModItems.MEDIUM_BRASS_FLARED_BARREL.get());
		withGeneratedParentAndDefaultTexture(ModItems.LARGE_BRASS_FLARED_BARREL.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.IRON_BITS.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.LEAD_INGOT.get());
		withGeneratedParentAndDefaultTexture(ModItems.LEAD_NUGGET.get());
		withGeneratedParentAndDefaultTexture(ModItems.LEAD_BITS.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.BRASS_INGOT.get());
		withGeneratedParentAndDefaultTexture(ModItems.BRASS_NUGGET.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.REPAIR_KIT.get());
		
		//withGeneratedParentAndDefaultTexture(ModItems.ARROW.get());
	}


	private ResourceLocation registryName(final Item item) {
		return Preconditions.checkNotNull(item.getRegistryName(), "Item %s has a null registry name", item);
	}

	private String name(final Item item) {
		return registryName(item).getPath();
	}

	private ResourceLocation itemTexture(final Item item) {
		final ResourceLocation name = registryName(item);
		return new ResourceLocation(name.getNamespace(), ITEM_FOLDER + "/" + name.getPath());
	}

	private ItemModelBuilder withExistingParent(final Item item, final Item modelItem) {
		return withExistingParent(name(item), registryName(modelItem));
	}

	private ItemModelBuilder withGeneratedParentAndDefaultTexture(final Item item) {
		return withGeneratedParent(name(item))
				.texture(LAYER_0, itemTexture(item));
	}

	private ItemModelBuilder withGeneratedParent(final String name) {
		return withExistingParent(name, mcLoc("generated"));
	}

	private ItemModelBuilder withSimpleParentAndDefaultTexture(final Item item) {
		return withSimpleParent(item, itemTexture(item));
	}

	private ItemModelBuilder withSimpleParent(final Item item, final ResourceLocation texture) {
		return withSimpleParent(item, texture.toString());
	}

	private ItemModelBuilder withSimpleParent(final Item item, final String texture) {
		return withSimpleParent(name(item))
				.texture(LAYER_0, texture);
	}

	private ItemModelBuilder withSimpleParent(final String name) {
		return getBuilder(name)
				.parent(simpleModel.get());
	}

	private ItemModelBuilder withSimpleFirearmParent(final String name) {
		return getBuilder(name)
				.parent(simpleFirearmModel.get());
	}
	
	private ItemModelBuilder withSimpleFirearmParent(final Item item, final String texture) {
		return withSimpleFirearmParent(name(item))
				.texture(LAYER_0, texture);
	}
	
	private ItemModelBuilder withSimpleFirearmParent(final Item item, final ResourceLocation texture) {
		return withSimpleFirearmParent(item, texture.toString());
	}
	
	private void firearmMuzzleloaderItem(final Item item) {
		// Create the parent model
		final ItemModelBuilder firearm = withSimpleFirearmParent(item, itemTexture(item));

		ItemModelBuilder firearmEmpty = getBuilder(name(item) + "_empty")
				.parent(firearm)
				.texture(LAYER_0, itemTexture(item) + "_empty");
		
		firearm
			.override()
			.predicate(FirearmEmptyPropertyFunction.ID, 1f)
			.model(firearmEmpty)
			.end()
			;
	}

	private void spawnEggItem(final Item item) {
		withExistingParent(name(item), mcLoc("template_spawn_egg"));
	}

//	private void bucketItem(final FluidGroup<?, ?, ?, ?> fluidGroup) {
//		final Item item = fluidGroup.getBucket().get();
//		final Fluid fluid = item instanceof BucketItem ? ((BucketItem) item).getFluid() : Fluids.EMPTY;
//
//		getBuilder(name(item))
//				.parent(getExistingFile(new ResourceLocation("forge", "bucket")))
//				.customLoader(DynamicBucketModelBuilder::begin)
//				.fluid(fluid)
//				.flipGas(true)
//				.end();
//	}

	private void bucketItem(final Item item) {
		getBuilder(name(item))
				.parent(getExistingFile(new ResourceLocation("forge", "bucket")))
				.texture("base", itemTexture(item) + "_base")
				.customLoader(DynamicBucketModelBuilder::begin)
				.fluid(Fluids.EMPTY)
				.flipGas(true)
				.end();
	}
}
