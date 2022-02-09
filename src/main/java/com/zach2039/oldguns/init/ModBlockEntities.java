package com.zach2039.oldguns.init;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.world.level.block.entity.BlastingPowderStickBlockEntity;
import com.zach2039.oldguns.world.level.block.entity.CongreveRocketStandBlockEntity;
import com.zach2039.oldguns.world.level.block.entity.MediumNavalCannonBlockEntity;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
	
	private static Block[] getBlastingPowderStickBlockEntityValidBlocks() {
		return new Block[] { ModBlocks.BLASTING_POWDER_STICK_BLOCK.get(), ModBlocks.WALL_BLASTING_POWDER_STICK_BLOCK.get() };
	}
	
	private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, OldGuns.MODID);

	private static boolean isInitialized;

	// Artillery
	public static final RegistryObject<BlockEntityType<MediumNavalCannonBlockEntity>> MEDIUM_NAVAL_CANNON = registerBlockEntityType("medium_naval_cannon",
			MediumNavalCannonBlockEntity::new,
			ModBlocks.MEDIUM_NAVAL_CANNON
	);
	
	public static final RegistryObject<BlockEntityType<CongreveRocketStandBlockEntity>> CONGREVE_ROCKET_STAND = registerBlockEntityType("congreve_rocket_stand",
			CongreveRocketStandBlockEntity::new,
			ModBlocks.CONGREVE_ROCKET_STAND
	);
	
	// Equipment
	public static final RegistryObject<BlockEntityType<BlastingPowderStickBlockEntity>> BLASTING_POWDER_STICK = BLOCK_ENTITY_TYPES.register("blasting_powder_stick", () -> BlockEntityType.Builder.of(BlastingPowderStickBlockEntity::new, getBlastingPowderStickBlockEntityValidBlocks()).build(null));


	/**
	 * Registers the {@link DeferredRegister} instance with the mod event bus.
	 * <p>
	 * This should be called during mod construction.
	 *
	 * @param modEventBus The mod event bus
	 */
	public static void initialize(final IEventBus modEventBus) {
		if (isInitialized) {
			throw new IllegalStateException("Already initialized");
		}

		BLOCK_ENTITY_TYPES.register(modEventBus);

		isInitialized = true;
	}

	/**
	 * Registers a block entity type with the specified block entity factory and valid block.
	 *
	 * @param name                The registry name of the block entity type
	 * @param blockEntitySupplier The factory used to create the block entity instances
	 * @param validBlock          The valid block for the block entity
	 * @param <T>                 The block entity class
	 * @return A RegistryObject reference to the block entity type
	 */
	private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> registerBlockEntityType(final String name, final BlockEntityType.BlockEntitySupplier<T> blockEntitySupplier, final RegistryObject<? extends Block> validBlock) {
		return BLOCK_ENTITY_TYPES.register(name, () -> {
			@SuppressWarnings("ConstantConditions")
			// dataFixerType will always be null until mod data fixers are implemented
			final BlockEntityType<T> blockEntityType = BlockEntityType.Builder
					.of(blockEntitySupplier, validBlock.get())
					.build(null);

			return blockEntityType;
		});
	}
}
