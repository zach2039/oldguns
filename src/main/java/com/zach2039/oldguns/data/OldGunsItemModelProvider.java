package com.zach2039.oldguns.data;

import java.util.function.Supplier;

import com.google.common.base.Preconditions;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.client.item.FirearmEmptyPropertyFunction;
import com.zach2039.oldguns.fluid.group.FluidGroup;
import com.zach2039.oldguns.init.ModBlocks;
import com.zach2039.oldguns.init.ModFluids;
import com.zach2039.oldguns.init.ModItems;
import com.zach2039.oldguns.util.ModRegistryUtil;

import net.minecraft.client.renderer.block.model.ItemTransforms.TransformType;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.loaders.DynamicFluidContainerModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.util.Lazy;

/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on Github
 * 
 * @author Choonster
 *
 * With additions by:
 * @author zach2039
 */
public class OldGunsItemModelProvider extends ItemModelProvider {
	private static final String LAYER_0 = "layer0";
	
	private final Supplier<ModelFile> simpleModel = Lazy.of(() ->
			withGeneratedParent("simple_model")
					.transforms()

					.transform(TransformType.THIRD_PERSON_RIGHT_HAND)
					.rotation(-80, 260, -40)
					.translation(-1, -2, 2.5f)
					.scale(0.9f, 0.9f, 0.9f)
					.end()

					.transform(TransformType.THIRD_PERSON_LEFT_HAND)
					.rotation(-80, -280, 40)
					.translation(-1, -2, 2.5f)
					.scale(0.9f, 0.9f, 0.9f)
					.end()

					.transform(TransformType.FIRST_PERSON_RIGHT_HAND)
					.rotation(0, -90, 25)
					.translation(1.13f, 3.2f, 1.13f)
					.scale(0.68f, 0.68f, 0.68f)
					.end()

					.transform(TransformType.FIRST_PERSON_LEFT_HAND)
					.rotation(0, 90, -25)
					.translation(1.13f, 3.2f, 1.13f)
					.scale(0.68f, 0.68f, 0.68f)
					.end()

					.end()
	);

	private final Supplier<ModelFile> simpleFirearmModel = Lazy.of(() ->
			withGeneratedParent("simple_model")
					.transforms()

					.transform(TransformType.THIRD_PERSON_RIGHT_HAND)
					.rotation(-80, 260, -40)
					.translation(-1, 1, 0f)
					.scale(0.9f, 0.9f, 0.9f)
					.end()

					.transform(TransformType.THIRD_PERSON_LEFT_HAND)
					.rotation(-80, -280, 40)
					.translation(-1, 1, 0f)
					.scale(0.9f, 0.9f, 0.9f)
					.end()

					.transform(TransformType.FIRST_PERSON_RIGHT_HAND)
					.rotation(0, -90, 25)
					.translation(1.13f, 3.2f, 1.13f)
					.scale(0.68f, 0.68f, 0.68f)
					.end()

					.transform(TransformType.FIRST_PERSON_LEFT_HAND)
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
		withGeneratedParentAndDefaultTexture(ModBlocks.LIQUID_NITER_CAULDRON.get().asItem());
		
		withGeneratedParentAndDefaultTexture(ModItems.MEDIUM_IRON_EXPLOSIVE_ROCKET.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.MEDIUM_IRON_CANNONBALL.get());
		withGeneratedParentAndDefaultTexture(ModItems.MEDIUM_IRON_EXPLOSIVE_SHELL.get());
		withGeneratedParentAndDefaultTexture(ModItems.MEDIUM_IRON_GRAPESHOT.get());
		withGeneratedParentAndDefaultTexture(ModItems.MEDIUM_IRON_CANISTER_SHOT.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.SMALL_POWDER_CHARGE.get());
		withGeneratedParentAndDefaultTexture(ModItems.MEDIUM_POWDER_CHARGE.get());
		withGeneratedParentAndDefaultTexture(ModItems.LARGE_POWDER_CHARGE.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.SMALL_IRON_CANNON_BARREL.get());
		withGeneratedParentAndDefaultTexture(ModItems.MEDIUM_IRON_CANNON_BARREL.get());
		withGeneratedParentAndDefaultTexture(ModItems.LARGE_IRON_CANNON_BARREL.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.SMALL_WOODEN_NAVAL_CARRIAGE.get());
		withGeneratedParentAndDefaultTexture(ModItems.MEDIUM_WOODEN_NAVAL_CARRIAGE.get());
		withGeneratedParentAndDefaultTexture(ModItems.LARGE_WOODEN_NAVAL_CARRIAGE.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.TINY_WOODEN_CARRIAGE_WHEEL.get());
		withGeneratedParentAndDefaultTexture(ModItems.SMALL_WOODEN_CARRIAGE_WHEEL.get());
		withGeneratedParentAndDefaultTexture(ModItems.MEDIUM_WOODEN_CARRIAGE_WHEEL.get());
		withGeneratedParentAndDefaultTexture(ModItems.LARGE_WOODEN_CARRIAGE_WHEEL.get());
		
		firearmMuzzleloaderItem(ModItems.MATCHLOCK_DERRINGER.get());
		firearmMuzzleloaderItem(ModItems.MATCHLOCK_PISTOL.get());
		firearmMuzzleloaderItem(ModItems.MATCHLOCK_ARQUEBUS.get());
		firearmMuzzleloaderItem(ModItems.MATCHLOCK_CALIVER.get());
		firearmMuzzleloaderItem(ModItems.MATCHLOCK_MUSKETOON.get());
		firearmMuzzleloaderItem(ModItems.MATCHLOCK_MUSKET.get());
		firearmMuzzleloaderItem(ModItems.MATCHLOCK_LONG_MUSKET.get());
		firearmMuzzleloaderItem(ModItems.MATCHLOCK_BLUNDERBUSS_PISTOL.get());
		firearmMuzzleloaderItem(ModItems.MATCHLOCK_BLUNDERBUSS.get());
		
		firearmMuzzleloaderItem(ModItems.WHEELLOCK_DERRINGER.get());
		firearmMuzzleloaderItem(ModItems.WHEELLOCK_PISTOL.get());
		firearmMuzzleloaderItem(ModItems.WHEELLOCK_DOUBLEBARREL_PISTOL.get());
		firearmMuzzleloaderItem(ModItems.WHEELLOCK_ARQUEBUS.get());
		firearmMuzzleloaderItem(ModItems.WHEELLOCK_CALIVER.get());
		firearmMuzzleloaderItem(ModItems.WHEELLOCK_MUSKETOON.get());
		firearmMuzzleloaderItem(ModItems.WHEELLOCK_MUSKET.get());
		firearmMuzzleloaderItem(ModItems.WHEELLOCK_LONG_MUSKET.get());
		firearmMuzzleloaderItem(ModItems.WHEELLOCK_BLUNDERBUSS_PISTOL.get());
		firearmMuzzleloaderItem(ModItems.WHEELLOCK_BLUNDERBUSS.get());
		firearmMuzzleloaderItem(ModItems.WHEELLOCK_HAND_MORTAR.get());
		
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
		
		firearmMuzzleloaderItem(ModItems.CAPLOCK_DERRINGER.get());
		firearmMuzzleloaderItem(ModItems.CAPLOCK_DUCKFOOT_DERRINGER.get());
		firearmMuzzleloaderItem(ModItems.CAPLOCK_PISTOL.get());
		firearmMuzzleloaderItem(ModItems.CAPLOCK_PEPPERBOX_PISTOL.get());
		firearmMuzzleloaderItem(ModItems.CAPLOCK_ARQUEBUS.get());
		firearmMuzzleloaderItem(ModItems.CAPLOCK_CALIVER.get());
		firearmMuzzleloaderItem(ModItems.CAPLOCK_MUSKETOON.get());
		firearmMuzzleloaderItem(ModItems.CAPLOCK_MUSKET.get());
		firearmMuzzleloaderItem(ModItems.CAPLOCK_LONG_MUSKET.get());
		firearmMuzzleloaderItem(ModItems.CAPLOCK_BLUNDERBUSS_PISTOL.get());
		firearmMuzzleloaderItem(ModItems.CAPLOCK_BLUNDERBUSS.get());
		firearmMuzzleloaderItem(ModItems.CAPLOCK_DOUBLEBARREL_BLUNDERBUSS.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.SMALL_STONE_MUSKET_BALL.get());
		withGeneratedParent(ModItems.SMALL_STONE_MUSKET_BALL_LOW_GRADE_PAPER_CARTRIDGE.get(), new ResourceLocation(OldGuns.MODID, "item/small_stone_musket_ball_paper_cartridge"));
		withGeneratedParent(ModItems.SMALL_STONE_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE.get(), new ResourceLocation(OldGuns.MODID, "item/small_stone_musket_ball_paper_cartridge"));
		withGeneratedParentAndDefaultTexture(ModItems.MEDIUM_STONE_MUSKET_BALL.get());
		withGeneratedParent(ModItems.MEDIUM_STONE_MUSKET_BALL_LOW_GRADE_PAPER_CARTRIDGE.get(), new ResourceLocation(OldGuns.MODID, "item/medium_stone_musket_ball_paper_cartridge"));
		withGeneratedParent(ModItems.MEDIUM_STONE_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE.get(), new ResourceLocation(OldGuns.MODID, "item/medium_stone_musket_ball_paper_cartridge"));
		withGeneratedParentAndDefaultTexture(ModItems.LARGE_STONE_MUSKET_BALL.get());
		withGeneratedParent(ModItems.LARGE_STONE_MUSKET_BALL_LOW_GRADE_PAPER_CARTRIDGE.get(), new ResourceLocation(OldGuns.MODID, "item/large_stone_musket_ball_paper_cartridge"));
		withGeneratedParent(ModItems.LARGE_STONE_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE.get(), new ResourceLocation(OldGuns.MODID, "item/large_stone_musket_ball_paper_cartridge"));
		
		withGeneratedParentAndDefaultTexture(ModItems.SMALL_STONE_BIRDSHOT.get());
		withGeneratedParentAndDefaultTexture(ModItems.MEDIUM_STONE_BIRDSHOT.get());
		withGeneratedParentAndDefaultTexture(ModItems.LARGE_STONE_BIRDSHOT.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.SMALL_IRON_MUSKET_BALL.get());
		withGeneratedParent(ModItems.SMALL_IRON_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE.get(), new ResourceLocation(OldGuns.MODID, "item/small_iron_musket_ball_paper_cartridge"));
		withGeneratedParent(ModItems.SMALL_IRON_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE.get(), new ResourceLocation(OldGuns.MODID, "item/small_iron_musket_ball_paper_cartridge"));
		withGeneratedParentAndDefaultTexture(ModItems.MEDIUM_IRON_MUSKET_BALL.get());
		withGeneratedParent(ModItems.MEDIUM_IRON_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE.get(), new ResourceLocation(OldGuns.MODID, "item/medium_iron_musket_ball_paper_cartridge"));
		withGeneratedParent(ModItems.MEDIUM_IRON_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE.get(), new ResourceLocation(OldGuns.MODID, "item/medium_iron_musket_ball_paper_cartridge"));
		withGeneratedParentAndDefaultTexture(ModItems.LARGE_IRON_MUSKET_BALL.get());
		withGeneratedParent(ModItems.LARGE_IRON_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE.get(), new ResourceLocation(OldGuns.MODID, "item/large_iron_musket_ball_paper_cartridge"));
		withGeneratedParent(ModItems.LARGE_IRON_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE.get(), new ResourceLocation(OldGuns.MODID, "item/large_iron_musket_ball_paper_cartridge"));
		
		withGeneratedParentAndDefaultTexture(ModItems.SMALL_IRON_BUCKSHOT.get());
		withGeneratedParentAndDefaultTexture(ModItems.MEDIUM_IRON_BUCKSHOT.get());
		withGeneratedParentAndDefaultTexture(ModItems.LARGE_IRON_BUCKSHOT.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.SMALL_IRON_BIRDSHOT.get());
		withGeneratedParentAndDefaultTexture(ModItems.MEDIUM_IRON_BIRDSHOT.get());
		withGeneratedParentAndDefaultTexture(ModItems.LARGE_IRON_BIRDSHOT.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.SMALL_LEAD_MUSKET_BALL.get());
		withGeneratedParent(ModItems.SMALL_LEAD_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE.get(), new ResourceLocation(OldGuns.MODID, "item/small_lead_musket_ball_paper_cartridge"));
		withGeneratedParent(ModItems.SMALL_LEAD_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE.get(), new ResourceLocation(OldGuns.MODID, "item/small_lead_musket_ball_paper_cartridge"));
		withGeneratedParentAndDefaultTexture(ModItems.MEDIUM_LEAD_MUSKET_BALL.get());
		withGeneratedParent(ModItems.MEDIUM_LEAD_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE.get(), new ResourceLocation(OldGuns.MODID, "item/medium_lead_musket_ball_paper_cartridge"));
		withGeneratedParent(ModItems.MEDIUM_LEAD_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE.get(), new ResourceLocation(OldGuns.MODID, "item/medium_lead_musket_ball_paper_cartridge"));
		withGeneratedParentAndDefaultTexture(ModItems.LARGE_LEAD_MUSKET_BALL.get());
		withGeneratedParent(ModItems.LARGE_LEAD_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE.get(), new ResourceLocation(OldGuns.MODID, "item/large_lead_musket_ball_paper_cartridge"));
		withGeneratedParent(ModItems.LARGE_LEAD_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE.get(), new ResourceLocation(OldGuns.MODID, "item/large_lead_musket_ball_paper_cartridge"));
		
		withGeneratedParentAndDefaultTexture(ModItems.SMALL_LEAD_BUCKSHOT.get());
		withGeneratedParentAndDefaultTexture(ModItems.MEDIUM_LEAD_BUCKSHOT.get());
		withGeneratedParentAndDefaultTexture(ModItems.LARGE_LEAD_BUCKSHOT.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.SMALL_LEAD_BIRDSHOT.get());
		withGeneratedParentAndDefaultTexture(ModItems.MEDIUM_LEAD_BIRDSHOT.get());
		withGeneratedParentAndDefaultTexture(ModItems.LARGE_LEAD_BIRDSHOT.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.MATCHLOCK_MECHANISM.get());
		withGeneratedParentAndDefaultTexture(ModItems.WHEELLOCK_MECHANISM.get());
		withGeneratedParentAndDefaultTexture(ModItems.FLINTLOCK_MECHANISM.get());
		withGeneratedParentAndDefaultTexture(ModItems.CAPLOCK_MECHANISM.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.SMALL_WOODEN_HANDLE.get());
		withGeneratedParentAndDefaultTexture(ModItems.MEDIUM_WOODEN_HANDLE.get());
		withGeneratedParentAndDefaultTexture(ModItems.LARGE_WOODEN_HANDLE.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.SMALL_WOODEN_STOCK.get());
		withGeneratedParentAndDefaultTexture(ModItems.MEDIUM_WOODEN_STOCK.get());
		withGeneratedParentAndDefaultTexture(ModItems.LARGE_WOODEN_STOCK.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.TINY_STONE_BARREL.get());
		withGeneratedParentAndDefaultTexture(ModItems.SMALL_STONE_BARREL.get());
		withGeneratedParentAndDefaultTexture(ModItems.MEDIUM_STONE_BARREL.get());
		withGeneratedParentAndDefaultTexture(ModItems.LARGE_STONE_BARREL.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.TINY_IRON_BARREL.get());
		withGeneratedParentAndDefaultTexture(ModItems.SMALL_IRON_BARREL.get());
		withGeneratedParentAndDefaultTexture(ModItems.MEDIUM_IRON_BARREL.get());
		withGeneratedParentAndDefaultTexture(ModItems.LARGE_IRON_BARREL.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.TINY_BRASS_BARREL.get());
		withGeneratedParentAndDefaultTexture(ModItems.SMALL_BRASS_BARREL.get());
		withGeneratedParentAndDefaultTexture(ModItems.MEDIUM_BRASS_BARREL.get());
		withGeneratedParentAndDefaultTexture(ModItems.LARGE_BRASS_BARREL.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.SMALL_STONE_FLARED_BARREL.get());
		withGeneratedParentAndDefaultTexture(ModItems.MEDIUM_STONE_FLARED_BARREL.get());
		withGeneratedParentAndDefaultTexture(ModItems.LARGE_STONE_FLARED_BARREL.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.SMALL_IRON_FLARED_BARREL.get());
		withGeneratedParentAndDefaultTexture(ModItems.MEDIUM_IRON_FLARED_BARREL.get());
		withGeneratedParentAndDefaultTexture(ModItems.LARGE_IRON_FLARED_BARREL.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.SMALL_BRASS_FLARED_BARREL.get());
		withGeneratedParentAndDefaultTexture(ModItems.MEDIUM_BRASS_FLARED_BARREL.get());
		withGeneratedParentAndDefaultTexture(ModItems.LARGE_BRASS_FLARED_BARREL.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.WOOD_GEAR_SET.get());
		withGeneratedParentAndDefaultTexture(ModItems.IRON_GEAR_SET.get());
		withGeneratedParentAndDefaultTexture(ModItems.GOLD_GEAR_SET.get());
		withGeneratedParentAndDefaultTexture(ModItems.DIAMOND_GEAR_SET.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.WOOD_TRIGGER_ASSEMBLY.get());
		withGeneratedParentAndDefaultTexture(ModItems.IRON_TRIGGER_ASSEMBLY.get());
		withGeneratedParentAndDefaultTexture(ModItems.GOLD_TRIGGER_ASSEMBLY.get());
		withGeneratedParentAndDefaultTexture(ModItems.DIAMOND_TRIGGER_ASSEMBLY.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.PERCUSSION_CAP_CONE.get());
		
		bucketItem(ModFluids.LIQUID_NITER);
		
		withGeneratedParentAndDefaultTexture(ModItems.IRON_BITS.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.LEAD_INGOT.get());
		withGeneratedParentAndDefaultTexture(ModItems.LEAD_NUGGET.get());
		withGeneratedParentAndDefaultTexture(ModItems.LEAD_BITS.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.BRASS_INGOT.get());
		withGeneratedParentAndDefaultTexture(ModItems.BRASS_NUGGET.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.MERCURY_INGOT.get());
		withGeneratedParentAndDefaultTexture(ModItems.MERCURY_NUGGET.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.NITRATE_SOIL.get());
		withGeneratedParentAndDefaultTexture(ModItems.LIQUID_NITER_BOTTLE.get());
		withGeneratedParentAndDefaultTexture(ModItems.NITER.get());
		withGeneratedParentAndDefaultTexture(ModItems.SULFUR.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.ROCKET_POWDER.get());
		withGeneratedParentAndDefaultTexture(ModItems.BLASTING_POWDER.get());
		withGeneratedParentAndDefaultTexture(ModItems.PERCUSSION_POWDER.get());
		withGeneratedParentAndDefaultTexture(ModItems.MEDIUM_GRADE_BLACK_POWDER.get());
		withGeneratedParentAndDefaultTexture(ModItems.HIGH_GRADE_BLACK_POWDER.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.FUSE.get());
		withGeneratedParentAndDefaultTexture(ModItems.MATCH_CORD.get());
				
		withGeneratedParentAndDefaultTexture(ModItems.WAXED_PAPER.get());
		withGeneratedParentAndDefaultTexture(ModItems.PERCUSSION_CAP.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.BARK_STRANDS.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.MATCHLOCK_REPAIR_PARTS.get());
		withGeneratedParentAndDefaultTexture(ModItems.WHEELLOCK_REPAIR_PARTS.get());
		withGeneratedParentAndDefaultTexture(ModItems.FLINTLOCK_REPAIR_PARTS.get());
		withGeneratedParentAndDefaultTexture(ModItems.CAPLOCK_REPAIR_PARTS.get());
		withGeneratedParentAndDefaultTexture(ModItems.REPAIR_KIT.get());
		withGeneratedParentAndDefaultTexture(ModItems.MORTAR_AND_PESTLE.get());
		withGeneratedParentAndDefaultTexture(ModItems.HACKSAW.get());
		withGeneratedParentAndDefaultTexture(ModItems.DESIGN_NOTES.get());
		withGeneratedParentAndDefaultTexture(ModItems.POWDER_HORN.get());
		
		withGeneratedParentAndDefaultTexture(ModItems.GUNNERS_QUADRANT.get());
		withGeneratedParentAndDefaultTexture(ModItems.RAM_ROD.get());
		withGeneratedParentAndDefaultTexture(ModItems.LONG_MATCH.get());
		
		withGeneratedParent(ModItems.BLASTING_POWDER_STICK.get(), new ResourceLocation(OldGuns.MODID, "block/blasting_powder_stick"));
		withGeneratedParentAndDefaultTexture(ModItems.MUSKETEER_HAT.get());
		withGeneratedParentAndDefaultTexture(ModItems.HORSEMANS_POT_HELM.get());
		
		//spawnEggItem(ModItems.MUSKETEER_SKELETON_SPAWN_EGG.get());
		//spawnEggItem(ModItems.HARQUEBUSIER_SKELETON_SPAWN_EGG.get());
	}


	private ResourceLocation registryName(final Item item) {
		return Preconditions.checkNotNull(ModRegistryUtil.getKey(item), "Item %s has a null registry name", item);
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

	private ItemModelBuilder withGeneratedParent(final Item item, final ResourceLocation texture) {
		return withGeneratedParent(name(item))
				.texture(LAYER_0, texture);
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

	private void bucketItem(final FluidGroup<?, ?, ?, ?, ?> fluidGroup) {
		final Item item = fluidGroup.getBucket().get();
		final Fluid fluid = item instanceof BucketItem ? ((BucketItem) item).getFluid() : Fluids.EMPTY;

		getBuilder(name(item))
				.parent(getExistingFile(new ResourceLocation("forge", "bucket")))
				.customLoader(DynamicFluidContainerModelBuilder::begin)
				.fluid(fluid)
				.flipGas(true)
				.end();
	}

	private void bucketItem(final Item item) {
		getBuilder(name(item))
				.parent(getExistingFile(new ResourceLocation("forge", "bucket")))
				.texture("base", itemTexture(item) + "_base")
				.customLoader(DynamicFluidContainerModelBuilder::begin)
				.fluid(Fluids.EMPTY)
				.flipGas(true)
				.end();
	}
}
