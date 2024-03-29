package com.zach2039.oldguns.init;

import com.google.common.base.Supplier;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.world.level.block.BlastingPowderStickBlock;
import com.zach2039.oldguns.world.level.block.CongreveRocketStandBlock;
import com.zach2039.oldguns.world.level.block.GunsmithsBenchBlock;
import com.zach2039.oldguns.world.level.block.HighGradeBlackPowderBlock;
import com.zach2039.oldguns.world.level.block.HighGradeBlackPowderCakeBlock;
import com.zach2039.oldguns.world.level.block.LiquidNiterCauldronBlock;
import com.zach2039.oldguns.world.level.block.LowGradeBlackPowderBlock;
import com.zach2039.oldguns.world.level.block.MediumGradeBlackPowderBlock;
import com.zach2039.oldguns.world.level.block.MediumNavalCannonBlock;
import com.zach2039.oldguns.world.level.block.NiterBeddingBlock;
import com.zach2039.oldguns.world.level.block.WallBlastingPowderStickBlock;
import com.zach2039.oldguns.world.level.block.WetHighGradeBlackPowderBlock;
import com.zach2039.oldguns.world.level.block.WetHighGradeBlackPowderCakeBlock;
import com.zach2039.oldguns.world.level.block.WetLowGradeBlackPowderBlock;
import com.zach2039.oldguns.world.level.block.WetMediumGradeBlackPowderBlock;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;

/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on Github
 * 
 * @author Choonster
 *
 * With additions by:
 * @author zach2039
 */
public class ModBlocks {
	private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, OldGuns.MODID);
	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, OldGuns.MODID);

	private static boolean isInitialized = false;

	public static final RegistryObject<GunsmithsBenchBlock> GUNSMITHS_BENCH = registerBlock("gunsmiths_bench",
			() -> new GunsmithsBenchBlock()
	);
	
	public static final RegistryObject<NiterBeddingBlock> NITER_BEDDING = registerBlock("niter_bedding",
			() -> new NiterBeddingBlock()
	);
	
	public static final RegistryObject<LiquidNiterCauldronBlock> LIQUID_NITER_CAULDRON = registerBlock("liquid_niter_cauldron",
			() -> new LiquidNiterCauldronBlock()
	);
	
	public static final RegistryObject<WetHighGradeBlackPowderCakeBlock> WET_HIGH_GRADE_BLACK_POWDER_CAKE = registerBlock("wet_high_grade_black_powder_cake",
			() -> new WetHighGradeBlackPowderCakeBlock()
	);
	public static final RegistryObject<HighGradeBlackPowderCakeBlock> HIGH_GRADE_BLACK_POWDER_CAKE = registerBlock("high_grade_black_powder_cake",
			() -> new HighGradeBlackPowderCakeBlock()
	);
	
	public static final RegistryObject<WetHighGradeBlackPowderBlock> WET_HIGH_GRADE_BLACK_POWDER_BLOCK = registerBlock("wet_high_grade_black_powder_block",
			() -> new WetHighGradeBlackPowderBlock()
	);
	public static final RegistryObject<HighGradeBlackPowderBlock> HIGH_GRADE_BLACK_POWDER_BLOCK = registerBlock("high_grade_black_powder_block",
			() -> new HighGradeBlackPowderBlock()
	);
	
	public static final RegistryObject<WetMediumGradeBlackPowderBlock> WET_MEDIUM_GRADE_BLACK_POWDER_BLOCK = registerBlock("wet_medium_grade_black_powder_block",
			() -> new WetMediumGradeBlackPowderBlock()
	);
	public static final RegistryObject<MediumGradeBlackPowderBlock> MEDIUM_GRADE_BLACK_POWDER_BLOCK = registerBlock("medium_grade_black_powder_block",
			() -> new MediumGradeBlackPowderBlock()
	);
	
	public static final RegistryObject<WetLowGradeBlackPowderBlock> WET_LOW_GRADE_BLACK_POWDER_BLOCK = registerBlock("wet_low_grade_black_powder_block",
			() -> new WetLowGradeBlackPowderBlock()
	);
	public static final RegistryObject<LowGradeBlackPowderBlock> LOW_GRADE_BLACK_POWDER_BLOCK = registerBlock("low_grade_black_powder_block",
			() -> new LowGradeBlackPowderBlock()
	);
	
	// Artillery
	public static final RegistryObject<MediumNavalCannonBlock> MEDIUM_NAVAL_CANNON = registerBlock("medium_naval_cannon", () -> new MediumNavalCannonBlock());
	public static final RegistryObject<CongreveRocketStandBlock> CONGREVE_ROCKET_STAND = registerBlock("congreve_rocket_stand", () -> new CongreveRocketStandBlock());
	
	// Equipment
	public static final RegistryObject<BlastingPowderStickBlock> BLASTING_POWDER_STICK_BLOCK = registerItemlessBlock("blasting_powder_stick_block", () -> new BlastingPowderStickBlock());
	public static final RegistryObject<WallBlastingPowderStickBlock> WALL_BLASTING_POWDER_STICK_BLOCK = registerItemlessBlock("wall_blasting_powder_stick_block", () -> new WallBlastingPowderStickBlock());
	
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

		BLOCKS.register(modEventBus);
		ITEMS.register(modEventBus);

		isInitialized = true;
	}

	/**
	 * Registers a block with a standard {@link BlockItem} as its block item.
	 *
	 * @param name         The registry name of the block
	 * @param blockFactory The factory used to create the block
	 * @param <BLOCK>      The block type
	 * @return A RegistryObject reference to the block
	 */
	private static <BLOCK extends Block> RegistryObject<BLOCK> registerBlock(final String name, final Supplier<BLOCK> blockFactory) {
		return registerBlock(name, blockFactory, block -> new BlockItem(block, defaultItemProperties()));
	}

	/**
	 * Registers a block and its block item.
	 *
	 * @param name         The registry name of the block
	 * @param blockFactory The factory used to create the block
	 * @param itemFactory  The factory used to create the block item
	 * @param <BLOCK>      The block type
	 * @return A RegistryObject reference to the block
	 */
	private static <BLOCK extends Block> RegistryObject<BLOCK> registerBlock(final String name, final Supplier<BLOCK> blockFactory, final IBlockItemFactory<BLOCK> itemFactory) {
		final RegistryObject<BLOCK> block = BLOCKS.register(name, blockFactory);

		ITEMS.register(name, () -> itemFactory.create(block.get()));

		return block;
	}
	
	/**
	 * Registers a block without a blockItem.
	 *
	 * @param name         The registry name of the block
	 * @param blockFactory The factory used to create the block
	 * @param itemFactory  The factory used to create the block item
	 * @param <BLOCK>      The block type
	 * @return A RegistryObject reference to the block
	 */
	private static <BLOCK extends Block> RegistryObject<BLOCK> registerItemlessBlock(final String name, final Supplier<BLOCK> blockFactory) {
		final RegistryObject<BLOCK> block = BLOCKS.register(name, blockFactory);

		return block;
	}

	/**
	 * Gets an {@link Item.Properties} instance with the {@link CreativeModeTab} set to {@link OldGuns#CREATIVE_MODE_TAB}.
	 *
	 * @return The item properties
	 */
	private static Item.Properties defaultItemProperties() {
		return new Item.Properties();
	}

	/**
	 * A factory function used to create block items.
	 *
	 * @param <BLOCK> The block type
	 */
	@FunctionalInterface
	private interface IBlockItemFactory<BLOCK extends Block> {
		Item create(BLOCK block);
	}

	static Collection<RegistryObject<Item>> orderedItems() {
		return ITEMS.getEntries();
	}
}
