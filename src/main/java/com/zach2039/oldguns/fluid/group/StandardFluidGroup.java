package com.zach2039.oldguns.fluid.group;

import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * A group consisting of a fluid type, a still and flowing fluid, a fluid block and a bucket item.
 * <p>
 * This class restricts the still and flowing fluids to subclasses of {@link FlowingFluid} and provides default
 * still, flowing, block and bucket factories.
 * <p>
 * The only required input is the {@link FluidType} factory ({@link StandardFluidGroup.Builder#typeFactory(Supplier)})}).
 *
 * @author Choonster
 */
public class StandardFluidGroup extends FluidGroup<FluidType, FlowingFluid, FlowingFluid, LiquidBlock, Item> {
	private StandardFluidGroup(final DeferredHolder<FluidType, ? extends FluidType> type, final DeferredHolder<Fluid, ? extends FlowingFluid> still, final DeferredHolder<Fluid, ? extends FlowingFluid> flowing, final DeferredHolder<LiquidBlock, ? extends LiquidBlock> block, final DeferredHolder<Item, ? extends Item> bucket) {
		super(type, still, flowing, block, bucket);
	}

	public static class Builder extends FluidGroup.Builder<FluidType, FlowingFluid, FlowingFluid, LiquidBlock, Item> {

		public Builder(final String name, final DeferredRegister<FluidType> fluidTypes, final DeferredRegister<Fluid> fluids, final DeferredRegister<Block> blocks, final DeferredRegister<Item> items) {
			super(name, fluidTypes, fluids, blocks, items);

			stillFactory = BaseFlowingFluid.Source::new;
			flowingFactory = BaseFlowingFluid.Flowing::new;

			blockFactory = LiquidBlock::new;

			bucketFactory = fluid -> new BucketItem(fluid, FluidGroup.defaultBucketProperties());
		}

		@Override
		public Builder typeFactory(final Supplier<FluidType> typeFactory) {
			return (Builder) super.typeFactory(typeFactory);
		}

		@Override
		public Builder stillFactory(final IFluidFactory<FlowingFluid> stillFactory) {
			return (Builder) super.stillFactory(stillFactory);
		}

		@Override
		public Builder flowingFactory(final IFluidFactory<FlowingFluid> flowingFactory) {
			return (Builder) super.flowingFactory(flowingFactory);
		}

		@Override
		public Builder blockFactory(final IBlockFactory<FlowingFluid, LiquidBlock> blockFactory) {
			return (Builder) super.blockFactory(blockFactory);
		}

		@Override
		public Builder bucketFactory(final IBucketFactory<FlowingFluid, Item> bucketFactory) {
			return (Builder) super.bucketFactory(bucketFactory);
		}

		@Override
		public Builder propertiesCustomiser(final Consumer<BaseFlowingFluid.Properties> propertiesCustomiser) {
			return (Builder) super.propertiesCustomiser(propertiesCustomiser);
		}

		@Override
		public Builder blockPropertiesCustomiser(final Consumer<BlockBehaviour.Properties> blockPropertiesCustomiser) {
			return (Builder) super.blockPropertiesCustomiser(blockPropertiesCustomiser);
		}

		@Override
		public StandardFluidGroup build() {
			return buildImpl(StandardFluidGroup::new);
		}
	}
}