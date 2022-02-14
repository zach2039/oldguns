package com.zach2039.oldguns.init;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.fluid.LiquidNiterFluid;
import com.zach2039.oldguns.fluid.LiquidNiterFluidBlock;
import com.zach2039.oldguns.fluid.group.FluidGroup;
import com.zach2039.oldguns.fluid.group.StandardFluidGroup;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
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
	private static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, OldGuns.MODID);
	private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, OldGuns.MODID);
	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, OldGuns.MODID);

	private static boolean isInitialized = false;

	public static final FluidGroup<FlowingFluid, FlowingFluid, LiquidBlock, Item> LIQUID_NITER = new StandardFluidGroup.Builder("liquid_niter", FLUIDS, BLOCKS, ITEMS)
			.stillFactory(LiquidNiterFluid.Source::new)
			.flowingFactory(LiquidNiterFluid.Flowing::new)
			.attributes(
					FluidAttributes.builder(new ResourceLocation(OldGuns.MODID, "block/liquid_niter_still"), new ResourceLocation(OldGuns.MODID, "block/liquid_niter_flow"))
							.rarity(Rarity.UNCOMMON)
			)
			.blockFactory(LiquidNiterFluidBlock::new)
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

		FLUIDS.register(modEventBus);
		BLOCKS.register(modEventBus);
		ITEMS.register(modEventBus);

		isInitialized = true;
	}
}
