package com.zach2039.oldguns.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.block.LiquidNiterCauldronBlock;
import com.zach2039.oldguns.block.NiterBeddingBlock;
import com.zach2039.oldguns.init.ModBlocks;
import com.zach2039.oldguns.util.EnumFaceRotation;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.LazyValue;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on Github
 * 
 * @author Choonster
 *
 * With additions by:
 * @author grilled-salmon
 */
public class OldGunsBlockStateProvider extends BlockStateProvider {
	private static final int DEFAULT_ANGLE_OFFSET = 180;

	private final List<String> errors = new ArrayList<>();

	/**
	 * Orientable models for each {@link EnumFaceRotation} value.
	 */
	private final LazyValue<Map<EnumFaceRotation, ModelFile>> rotatedOrientables = new LazyValue<>(() -> {
		Map<EnumFaceRotation, ModelFile> map = new EnumMap<>(EnumFaceRotation.class);
		map.put(EnumFaceRotation.UP, existingMcModel("orientable"));

		Arrays.stream(EnumFaceRotation.values())
				.filter(faceRotation -> faceRotation != EnumFaceRotation.UP)
				.forEach(faceRotation -> {
					final ModelFile cube = models().getBuilder("cube_rotated_" + faceRotation.getSerializedName())
							.parent(existingMcModel("block"))
							.element()
							.allFaces((direction, faceBuilder) ->
									faceBuilder
											.texture("#" + direction.getSerializedName())
											.cullface(direction)
							)
							.allFaces((direction, faceBuilder) -> {
								switch (faceRotation) {
									case LEFT:
										faceBuilder.rotation(ModelBuilder.FaceRotation.COUNTERCLOCKWISE_90);
										break;
									case RIGHT:
										faceBuilder.rotation(ModelBuilder.FaceRotation.CLOCKWISE_90);
										break;
									case DOWN:
										faceBuilder.rotation(ModelBuilder.FaceRotation.UPSIDE_DOWN);
										break;
								}
							})
							.end();

					final ModelFile orientableWithBottom = models().getBuilder("orientable_with_bottom_rotated_" + faceRotation.getSerializedName())
							.parent(cube)

							.transforms()

							.transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
							.rotation(0, 135, 0)
							.scale(0.40f)
							.end()

							.end()

							.texture("particle", "#front")
							.texture("down", "#bottom")
							.texture("up", "#top")
							.texture("north", "#front")
							.texture("east", "#side")
							.texture("south", "#side")
							.texture("west", "#side");

					final ModelFile orientable = models().getBuilder("orientable_rotated_" + faceRotation.getSerializedName())
							.parent(orientableWithBottom)
							.texture("bottom", "#top");

					map.put(faceRotation, orientable);
				});

		return ImmutableMap.copyOf(map);
	});

	public OldGunsBlockStateProvider(final DataGenerator gen, final ExistingFileHelper exFileHelper) {
		super(gen, OldGuns.MODID, exFileHelper);
	}

	@Override
	public String getName() {
		return "OldGunsBlockStates";
	}

	@Override
	protected void registerStatesAndModels() {
		
		final BlockModelBuilder niterBeddingEmpty = models()
				.cubeAll(name(ModBlocks.NITER_BEDDING.get()) + "_empty", modLoc("block/niter_bedding_empty"));
		
		final BlockModelBuilder niterBeddingFull = models()
				.cubeAll(name(ModBlocks.NITER_BEDDING.get()) + "_full", modLoc("block/niter_bedding_full"));
		
		getVariantBuilder(ModBlocks.NITER_BEDDING.get()).forAllStates(state -> {
			if (state.getValue(NiterBeddingBlock.REFUSE_AMOUNT) != NiterBeddingBlock.MAX_REFUSE_AMOUNT) {
				return ConfiguredModel.builder().modelFile(niterBeddingEmpty).build();
			}
			
			return ConfiguredModel.builder().modelFile(niterBeddingFull).build();
		});

		simpleBlockItem(ModBlocks.NITER_BEDDING.get(), niterBeddingEmpty);
		
//		{
//			  "parent": "minecraft:block/template_cauldron_level1",
//			  "textures": {
//			    "content": "minecraft:block/water_still",
//			    "inside": "minecraft:block/cauldron_inner",
//			    "particle": "minecraft:block/cauldron_side",
//			    "top": "minecraft:block/cauldron_top",
//			    "bottom": "minecraft:block/cauldron_bottom",
//			    "side": "minecraft:block/cauldron_side"
//			  }
//			}
		
		final ModelFile liquidNiterCauldron1 = models()
				.withExistingParent(name(ModBlocks.LIQUID_NITER_CAULDRON.get()) + "_level1", mcLoc("template_cauldron_level1"))
				.texture("content", modLoc("block/liquid_niter_still"))
				.texture("inside", mcLoc("block/cauldron_inner"))
				.texture("particle", mcLoc("block/cauldron_side"))
				.texture("top", mcLoc("block/cauldron_top"))
				.texture("bottom", mcLoc("block/cauldron_bottom"))
				.texture("side", mcLoc("block/cauldron_side"));
		
		final ModelFile liquidNiterCauldron2 = models()
				.withExistingParent(name(ModBlocks.LIQUID_NITER_CAULDRON.get()) + "_level2", mcLoc("template_cauldron_level2"))
				.texture("content", modLoc("block/liquid_niter_still"))
				.texture("inside", mcLoc("block/cauldron_inner"))
				.texture("particle", mcLoc("block/cauldron_side"))
				.texture("top", mcLoc("block/cauldron_top"))
				.texture("bottom", mcLoc("block/cauldron_bottom"))
				.texture("side", mcLoc("block/cauldron_side"));
		
		
		final ModelFile liquidNiterCauldronFull = models()
				.withExistingParent(name(ModBlocks.LIQUID_NITER_CAULDRON.get()) + "_full", mcLoc("template_cauldron_full"))
				.texture("content", modLoc("block/liquid_niter_still"))
				.texture("inside", mcLoc("block/cauldron_inner"))
				.texture("particle", mcLoc("block/cauldron_side"))
				.texture("top", mcLoc("block/cauldron_top"))
				.texture("bottom", mcLoc("block/cauldron_bottom"))
				.texture("side", mcLoc("block/cauldron_side"));

		getVariantBuilder(ModBlocks.LIQUID_NITER_CAULDRON.get())
				.partialState()
				.with(LiquidNiterCauldronBlock.LEVEL, 1)
				.modelForState()
				.modelFile(liquidNiterCauldron1)
				.addModel()
				
				.partialState()
				.with(LiquidNiterCauldronBlock.LEVEL, 2)
				.modelForState()
				.modelFile(liquidNiterCauldron2)
				.addModel()
				
				.partialState()
				.with(LiquidNiterCauldronBlock.LEVEL, 3)
				.modelForState()
				.modelFile(liquidNiterCauldronFull)
				.addModel();

		simpleBlockItem(ModBlocks.LIQUID_NITER_CAULDRON.get(), liquidNiterCauldronFull);
		
		final ModelFile mediumGradeBlackPowderBlock = models().cubeAll(
				name(ModBlocks.MEDIUM_GRADE_BLACK_POWDER_BLOCK.get()),
				modLoc("block/medium_grade_black_powder_dry")
		);
		
		simpleBlock(ModBlocks.MEDIUM_GRADE_BLACK_POWDER_BLOCK.get(), mediumGradeBlackPowderBlock);
		simpleBlockItem(ModBlocks.MEDIUM_GRADE_BLACK_POWDER_BLOCK.get(), mediumGradeBlackPowderBlock);
		
		final ModelFile wetMediumGradeBlackPowderBlock = models().cubeAll(
				name(ModBlocks.WET_MEDIUM_GRADE_BLACK_POWDER_BLOCK.get()),
				modLoc("block/medium_grade_black_powder_wet")
		);
		
		simpleBlock(ModBlocks.WET_MEDIUM_GRADE_BLACK_POWDER_BLOCK.get(), wetMediumGradeBlackPowderBlock);
		simpleBlockItem(ModBlocks.WET_MEDIUM_GRADE_BLACK_POWDER_BLOCK.get(), wetMediumGradeBlackPowderBlock);
		
		final ModelFile highGradeBlackPowderBlock = models().cubeAll(
				name(ModBlocks.HIGH_GRADE_BLACK_POWDER_BLOCK.get()),
				modLoc("block/high_grade_black_powder_dry")
		);
		
		simpleBlock(ModBlocks.HIGH_GRADE_BLACK_POWDER_BLOCK.get(), highGradeBlackPowderBlock);
		simpleBlockItem(ModBlocks.HIGH_GRADE_BLACK_POWDER_BLOCK.get(), highGradeBlackPowderBlock);
		
		final ModelFile wetHighGradeBlackPowderBlock = models().cubeAll(
				name(ModBlocks.WET_HIGH_GRADE_BLACK_POWDER_BLOCK.get()),
				modLoc("block/high_grade_black_powder_wet")
		);
		
		simpleBlock(ModBlocks.WET_HIGH_GRADE_BLACK_POWDER_BLOCK.get(), wetHighGradeBlackPowderBlock);
		simpleBlockItem(ModBlocks.WET_HIGH_GRADE_BLACK_POWDER_BLOCK.get(), wetHighGradeBlackPowderBlock);
	}

	private void blockstateError(final Block block, final String fmt, final Object... args) {
		errors.add("Generated blockstate for block " + block + " " + String.format(fmt, args));
	}

	private ResourceLocation registryName(final Block block) {
		return Preconditions.checkNotNull(block.getRegistryName(), "Block %s has a null registry name", block);
	}

	private ResourceLocation registryName(final Item item) {
		return Preconditions.checkNotNull(item.getRegistryName(), "Item %s has a null registry name", item);
	}

	private String name(final Block block) {
		return registryName(block).getPath();
	}

	/**
	 * Gets an existing model with the block's registry name.
	 * <p>
	 * Note: This isn't guaranteed to be the actual model used by the block.
	 *
	 * @param block The block
	 * @return The model
	 */
	private ModelFile existingModel(final Block block) {
		return models().getExistingFile(registryName(block));
	}

	/**
	 * Gets an existing model with the item's registry name.
	 * <p>
	 * Note: This isn't guaranteed to be the actual model used by the item.
	 *
	 * @param item The item
	 * @return The model
	 */
	private ModelFile existingModel(final Item item) {
		return itemModels().getExistingFile(registryName(item));
	}

	/**
	 * Gets an existing model in the {@code minecraft} namespace with the specified name.
	 *
	 * @param name The model name
	 * @return The model
	 */
	private ModelFile existingMcModel(final String name) {
		return models().getExistingFile(new ResourceLocation("minecraft", name));
	}

	private void simpleBlockItem(final Block block) {
		simpleBlockItem(block, existingModel(block));
	}

	private void simpleBlockItemWithExistingParent(final Block block, final Item item) {
		simpleBlockItem(block, existingModel(item));
	}

	private ModelFile orientableSingle(final String name, final ResourceLocation side, final ResourceLocation front) {
		return models().orientable(name, side, front, side);
	}

	private void simpleBlockWithExistingParent(final Block block, final Block parentBlock) {
		simpleBlockWithExistingParent(block, existingModel(parentBlock));
	}

	private void simpleBlockWithExistingParent(final Block block, final ModelFile parentModel) {
		simpleBlock(
				block,
				models().getBuilder(name(block))
						.parent(parentModel)
		);
	}

	protected void directionalBlockUvLock(final Block block, final ModelFile model) {
		directionalBlockUvLock(block, $ -> model);
	}

	protected void directionalBlockUvLock(final Block block, final Function<BlockState, ModelFile> modelFunc) {
		getVariantBuilder(block)
				.forAllStates(state -> {
					final Direction direction = state.getValue(BlockStateProperties.FACING);
					return ConfiguredModel.builder()
							.modelFile(modelFunc.apply(state))
							.rotationX(getRotationX(direction))
							.rotationY(getRotationY(direction))
							.build();
				});
	}

	private int getRotationX(final Direction direction) {
		return direction == Direction.DOWN ? 90 : direction.getAxis().isHorizontal() ? 0 : -90;
	}

	private int getRotationY(final Direction direction) {
		return getRotationY(direction, DEFAULT_ANGLE_OFFSET);
	}

	private int getRotationY(final Direction direction, final int offset) {
		return direction.getAxis().isVertical() ? 0 : (((int) direction.toYRot()) + offset) % 360;
	}
}
