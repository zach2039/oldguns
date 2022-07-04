package com.zach2039.oldguns.init;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.fluid.BasicFluidType;
import com.zach2039.oldguns.fluid.group.FluidGroup;
import com.zach2039.oldguns.fluid.group.StandardFluidGroup;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on Github
 * 
 * @author Choonster
 *
 * With additions by:
 * @author zach2039
 */
public class ModFluids {
	private static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, OldGuns.MODID);
	private static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, OldGuns.MODID);
	private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, OldGuns.MODID);
	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, OldGuns.MODID);

	private static boolean isInitialized = false;

	public static final FluidGroup<FluidType, FlowingFluid, FlowingFluid, LiquidBlock, Item> LIQUID_NITER = standardGroup("normal")
			.typeFactory(() -> new BasicFluidType(
					new ResourceLocation(OldGuns.MODID, "block/liquid_niter_still"),
					new ResourceLocation(OldGuns.MODID, "block/liquid_niter_flow"),
					FluidType.Properties.create()
						.lightLevel(1)
						.density(1000)
						.viscosity(1500)
						.sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
						.sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
						.sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
					))
				.blockMaterial(ModMaterials.LIQUID_NITER)
				.build();			
			

	/**
	 * Registers the {@link DeferredRegister} instances with the mod event bus.
	 * <p>
	 * This should be called during mod construction.
	 *
	 * @param modEventBus The mod event bus
	 */
	public static void initialize(final IEventBus modEventBus) {
		if (isInitialized) {
			throw new IllegalStateException("Already initialized");
		}

		FLUID_TYPES.register(modEventBus);
		FLUIDS.register(modEventBus);
		BLOCKS.register(modEventBus);
		ITEMS.register(modEventBus);

		isInitialized = true;
	}
	
	private static StandardFluidGroup.Builder standardGroup(final String name) {
		return new StandardFluidGroup.Builder(name, FLUID_TYPES, FLUIDS, BLOCKS, ITEMS);
	}
}
