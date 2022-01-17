package com.zach2039.oldguns.init;

import com.google.common.base.Supplier;
import com.zach2039.oldguns.OldGuns;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlockEntities {
	private static final DeferredRegister<TileEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, OldGuns.MODID);

	private static boolean isInitialized;

	/**
	 * Registers the {@link DeferredRegister} instance with the mod event bus.
	 * <p>
	 * This should be called during mod construction.
	 *
	 * @param modEventBus The mod event bus
	 */
	public static void initialise(final IEventBus modEventBus) {
		if (isInitialized) {
			throw new IllegalStateException("Already initialised");
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
	private static <T extends TileEntity> RegistryObject<TileEntityType<T>> registerBlockEntityType(final String name, final Supplier<T> blockEntitySupplier, final RegistryObject<? extends Block> validBlock) {
		return BLOCK_ENTITY_TYPES.register(name, () -> {
			@SuppressWarnings("ConstantConditions")
			// dataFixerType will always be null until mod data fixers are implemented
			final TileEntityType<T> blockEntityType = TileEntityType.Builder
					.of(blockEntitySupplier, validBlock.get())
					.build(null);

			return blockEntityType;
		});
	}
}
