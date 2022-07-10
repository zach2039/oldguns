package com.zach2039.oldguns.fluid;

import java.util.function.Consumer;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;

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
