package com.zach2039.oldguns.fluid;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidType;

import java.util.function.Consumer;

/**
 * Basic implementation of {@link FluidType} that supports specifying still and flowing textures in the constructor.
 *
 * @author Choonster
 */
public class BasicFluidType extends FluidType {
	private final ResourceLocation stillTexture;
	private final ResourceLocation flowingTexture;

	public BasicFluidType(final ResourceLocation stillTexture, final ResourceLocation flowingTexture, final Properties properties) {
		super(properties);
		this.stillTexture = stillTexture;
		this.flowingTexture = flowingTexture;
	}

	public ResourceLocation getStillTexture() {
		return stillTexture;
	}

	public ResourceLocation getFlowingTexture() {
		return flowingTexture;
	}

	@Override
	public void initializeClient(final Consumer<IClientFluidTypeExtensions> consumer) {
		consumer.accept(new IClientFluidTypeExtensions() {
			@Override
			public ResourceLocation getStillTexture() {
				return stillTexture;
			}

			@Override
			public ResourceLocation getFlowingTexture() {
				return flowingTexture;
			}
		});
	}
}
