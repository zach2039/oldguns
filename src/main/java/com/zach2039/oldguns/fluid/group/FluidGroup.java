package com.zach2039.oldguns.fluid.group;

import com.google.common.base.Preconditions;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * A group consisting of a fluid type, a still and flowing fluid, a fluid block and a bucket item.
 *
 * @author Choonster
 */
public class FluidGroup<TYPE extends FluidType, STILL extends Fluid, FLOWING extends Fluid, BLOCK extends LiquidBlock, BUCKET extends Item> {
	private final DeferredHolder<FluidType, ? extends TYPE> type;
	private final DeferredHolder<Fluid, ? extends STILL> still;
	private final DeferredHolder<Fluid, ? extends FLOWING> flowing;
	private final DeferredHolder<LiquidBlock, ? extends BLOCK> block;
	private final DeferredHolder<Item, ? extends BUCKET> bucket;

	protected FluidGroup(final DeferredHolder<FluidType, ? extends TYPE> type, final DeferredHolder<Fluid, ? extends STILL> still,
						 final DeferredHolder<Fluid, ? extends FLOWING> flowing, final DeferredHolder<LiquidBlock, ? extends BLOCK> block,
						 final DeferredHolder<Item, ? extends BUCKET> bucket) {
		this.type = type;
		this.still = still;
		this.flowing = flowing;
		this.block = block;
		this.bucket = bucket;
	}

	/**
	 * Gets the fluid type.
	 *
	 * @return THe fluid type.
	 */
	public DeferredHolder<FluidType, ? extends TYPE> getType() {
		return type;
	}

	/**
	 * Gets the still fluid.
	 *
	 * @return The still fluid.
	 */
	public DeferredHolder<Fluid, ? extends STILL> getStill() {
		return still;
	}

	/**
	 * Gets the flowing fluid.
	 *
	 * @return The flowing fluid.
	 */
	public DeferredHolder<Fluid, ? extends FLOWING> getFlowing() {
		return flowing;
	}

	/**
	 * Gets the fluid block.
	 *
	 * @return The fluid block.
	 */
	public DeferredHolder<LiquidBlock, ? extends BLOCK> getBlock() {
		return block;
	}

	/**
	 * Gets the bucket item.
	 *
	 * @return The bucket item.
	 */
	public DeferredHolder<Item, ? extends BUCKET> getBucket() {
		return bucket;
	}

	public static class Builder<TYPE extends FluidType, STILL extends Fluid, FLOWING extends Fluid, BLOCK extends LiquidBlock, BUCKET extends Item> {
		protected final DeferredRegister<FluidType> fluidTypes;
		protected final DeferredRegister<Fluid> fluids;
		protected final DeferredRegister<LiquidBlock> blocks;
		protected final DeferredRegister<Item> items;

		protected final String name;

		@Nullable
		private Supplier<TYPE> typeFactory;

		@Nullable
		protected IFluidFactory<STILL> stillFactory;

		@Nullable
		protected IFluidFactory<FLOWING> flowingFactory;

		@Nullable
		protected IBlockFactory<STILL, BLOCK> blockFactory;

		@Nullable
		protected IBucketFactory<STILL, BUCKET> bucketFactory;

		@Nullable
		protected Consumer<BaseFlowingFluid.Properties> propertiesCustomiser;

		@Nullable
		protected Consumer<BlockBehaviour.Properties> blockPropertiesCustomiser;

		@Nullable
		protected BaseFlowingFluid.Properties properties;

		public Builder(final String name, final DeferredRegister<FluidType> fluidTypes, final DeferredRegister<Fluid> fluids, final DeferredRegister<LiquidBlock> blocks, final DeferredRegister<Item> items) {
			this.name = name;
			this.fluidTypes = fluidTypes;
			this.fluids = fluids;
			this.blocks = blocks;
			this.items = items;
		}

		public Builder<TYPE, STILL, FLOWING, BLOCK, BUCKET> typeFactory(final Supplier<TYPE> typeFactory) {
			Preconditions.checkNotNull(typeFactory, "typeFactory");
			this.typeFactory = typeFactory;
			return this;
		}

		public Builder<TYPE, STILL, FLOWING, BLOCK, BUCKET> stillFactory(final IFluidFactory<STILL> stillFactory) {
			Preconditions.checkNotNull(stillFactory, "stillFactory");
			this.stillFactory = stillFactory;
			return this;
		}

		public Builder<TYPE, STILL, FLOWING, BLOCK, BUCKET> flowingFactory(final IFluidFactory<FLOWING> flowingFactory) {
			Preconditions.checkNotNull(flowingFactory, "flowingFactory");
			this.flowingFactory = flowingFactory;
			return this;
		}

		public Builder<TYPE, STILL, FLOWING, BLOCK, BUCKET> blockFactory(final IBlockFactory<STILL, BLOCK> blockFactory) {
			Preconditions.checkNotNull(blockFactory, "blockFactory");
			this.blockFactory = blockFactory;
			return this;
		}

		public Builder<TYPE, STILL, FLOWING, BLOCK, BUCKET> bucketFactory(final IBucketFactory<STILL, BUCKET> bucketFactory) {
			Preconditions.checkNotNull(bucketFactory, "bucketFactory");
			this.bucketFactory = bucketFactory;
			return this;
		}

		public Builder<TYPE, STILL, FLOWING, BLOCK, BUCKET> propertiesCustomiser(final Consumer<BaseFlowingFluid.Properties> propertiesCustomiser) {
			Preconditions.checkNotNull(propertiesCustomiser, "propertiesCustomiser");
			this.propertiesCustomiser = propertiesCustomiser;
			return this;
		}

		public Builder<TYPE, STILL, FLOWING, BLOCK, BUCKET> blockPropertiesCustomiser(final Consumer<BlockBehaviour.Properties> blockPropertiesCustomiser) {
			Preconditions.checkNotNull(blockPropertiesCustomiser, "blockPropertiesCustomiser");
			this.blockPropertiesCustomiser = blockPropertiesCustomiser;
			return this;
		}

		public FluidGroup<TYPE, STILL, FLOWING, BLOCK, BUCKET> build() {
			return buildImpl(FluidGroup::new);
		}

		protected <GROUP extends FluidGroup<TYPE, STILL, FLOWING, BLOCK, BUCKET>> GROUP buildImpl(final IFluidGroupFactory<GROUP, TYPE, STILL, FLOWING, BLOCK, BUCKET> factory) {
			Preconditions.checkState(typeFactory != null, "Type Factory not provided");
			Preconditions.checkState(stillFactory != null, "Still Factory not provided");
			Preconditions.checkState(flowingFactory != null, "Flowing factory not provided");
			Preconditions.checkState(blockFactory != null, "Block Factory not provided");
			Preconditions.checkState(bucketFactory != null, "Bucket Factory not provided");

			final DeferredHolder<FluidType, ? extends TYPE> type = fluidTypes.register(name, typeFactory);

			final DeferredHolder<Fluid, ? extends STILL> still = fluids.register(name, () -> stillFactory.create(Objects.requireNonNull(properties)));
			final DeferredHolder<Fluid, ? extends FLOWING> flowing = fluids.register("flowing_" + name, () -> flowingFactory.create(Objects.requireNonNull(properties)));

			final var blockProperties = defaultBlockProperties();

			if (blockPropertiesCustomiser != null) {
				blockPropertiesCustomiser.accept(blockProperties);
			}

			final DeferredHolder<LiquidBlock, ? extends BLOCK> block = blocks.register(name, () -> blockFactory.create(still, blockProperties));
			final DeferredHolder<Item, ? extends BUCKET> bucket = items.register(name + "_bucket", () -> bucketFactory.create(still));

			properties = new BaseFlowingFluid.Properties(type, still, flowing)
					.block(block)
					.bucket(bucket);

			if (propertiesCustomiser != null) {
				propertiesCustomiser.accept(properties);
			}

			return factory.create(type, still, flowing, block, bucket);
		}

		@FunctionalInterface
		protected interface IFluidGroupFactory<
				GROUP extends FluidGroup<TYPE, STILL, FLOWING, BLOCK, BUCKET>,
				TYPE extends FluidType, STILL extends Fluid, FLOWING extends Fluid, BLOCK extends LiquidBlock, BUCKET extends Item
				> {
			GROUP create(DeferredHolder<FluidType, ? extends TYPE> type, DeferredHolder<Fluid, ? extends STILL> still,
						 DeferredHolder<Fluid, ? extends FLOWING> flowing, DeferredHolder<LiquidBlock, ? extends BLOCK> block,
						 DeferredHolder<Item, ? extends BUCKET> bucket);
		}
	}

	public static Block.Properties defaultBlockProperties() {
		return Block.Properties.of()
				.replaceable()
				.noCollission()
				.strength(100)
				.pushReaction(PushReaction.DESTROY)
				.noLootTable()
				.liquid()
				.sound(SoundType.EMPTY);
	}

	public static Item.Properties defaultBucketProperties() {
		return new Item.Properties()
				.craftRemainder(Items.BUCKET)
				.stacksTo(1);
	}

	@FunctionalInterface
	public interface IFluidFactory<FLUID extends Fluid> {
		FLUID create(BaseFlowingFluid.Properties properties);
	}

	@FunctionalInterface
	public interface IBlockFactory<STILL extends Fluid, BLOCK extends LiquidBlock> {
		BLOCK create(Supplier<? extends STILL> fluid, BlockBehaviour.Properties properties);
	}

	@FunctionalInterface
	public interface IBucketFactory<STILL extends Fluid, BUCKET extends Item> {
		BUCKET create(Supplier<? extends STILL> fluid);
	}
}