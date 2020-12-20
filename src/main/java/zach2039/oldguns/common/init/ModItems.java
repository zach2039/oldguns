package zach2039.oldguns.common.init;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.item.ammo.ItemLargeIronBirdshot;
import zach2039.oldguns.common.item.ammo.ItemLargeIronMusketBall;
import zach2039.oldguns.common.item.ammo.ItemLargeStoneBirdshot;
import zach2039.oldguns.common.item.ammo.ItemLargeStoneMusketBall;
import zach2039.oldguns.common.item.ammo.ItemMediumIronCannonball;
import zach2039.oldguns.common.item.ammo.ItemMediumIronHEShell;
import zach2039.oldguns.common.item.ammo.ItemMediumIronMusketBall;
import zach2039.oldguns.common.item.ammo.ItemMediumStoneMusketBall;
import zach2039.oldguns.common.item.ammo.ItemSmallIronMusketBall;
import zach2039.oldguns.common.item.ammo.ItemSmallStoneMusketBall;
import zach2039.oldguns.common.item.artillery.ItemArtilleryCannon;
import zach2039.oldguns.common.item.firearm.ItemFlintlockArquebus;
import zach2039.oldguns.common.item.firearm.ItemFlintlockBlunderbuss;
import zach2039.oldguns.common.item.firearm.ItemFlintlockCaliver;
import zach2039.oldguns.common.item.firearm.ItemFlintlockDerringer;
import zach2039.oldguns.common.item.firearm.ItemFlintlockLongMusket;
import zach2039.oldguns.common.item.firearm.ItemFlintlockMusket;
import zach2039.oldguns.common.item.firearm.ItemFlintlockMusketoon;
import zach2039.oldguns.common.item.firearm.ItemFlintlockPistol;
import zach2039.oldguns.common.item.firearm.ItemMatchlockArquebus;
import zach2039.oldguns.common.item.firearm.ItemMatchlockBlunderbuss;
import zach2039.oldguns.common.item.firearm.ItemMatchlockCaliver;
import zach2039.oldguns.common.item.firearm.ItemMatchlockDerringer;
import zach2039.oldguns.common.item.firearm.ItemMatchlockLongMusket;
import zach2039.oldguns.common.item.firearm.ItemMatchlockMusket;
import zach2039.oldguns.common.item.firearm.ItemMatchlockMusketoon;
import zach2039.oldguns.common.item.firearm.ItemMatchlockPistol;
import zach2039.oldguns.common.item.part.ItemFlintlockMechanism;
import zach2039.oldguns.common.item.part.ItemHugeIronBarrel;
import zach2039.oldguns.common.item.part.ItemHugeStoneBarrel;
import zach2039.oldguns.common.item.part.ItemLargeIronBarrel;
import zach2039.oldguns.common.item.part.ItemLargeStoneBarrel;
import zach2039.oldguns.common.item.part.ItemLargeWoodenHandle;
import zach2039.oldguns.common.item.part.ItemLargeWoodenStock;
import zach2039.oldguns.common.item.part.ItemMatchlockMechanism;
import zach2039.oldguns.common.item.part.ItemMediumIronBarrel;
import zach2039.oldguns.common.item.part.ItemMediumStoneBarrel;
import zach2039.oldguns.common.item.part.ItemMediumWoodenHandle;
import zach2039.oldguns.common.item.part.ItemMediumWoodenStock;
import zach2039.oldguns.common.item.part.ItemSmallIronBarrel;
import zach2039.oldguns.common.item.part.ItemSmallStoneBarrel;
import zach2039.oldguns.common.item.part.ItemSmallWoodenHandle;
import zach2039.oldguns.common.item.part.ItemSmallWoodenStock;
import zach2039.oldguns.common.item.part.ItemTinyIronBarrel;
import zach2039.oldguns.common.item.part.ItemTinyStoneBarrel;
import zach2039.oldguns.common.item.tools.ItemGunnersQuadrant;
import zach2039.oldguns.common.item.tools.ItemLargeMusketBallMold;
import zach2039.oldguns.common.item.tools.ItemLargeMusketBallMoldTool;
import zach2039.oldguns.common.item.tools.ItemLongMatch;
import zach2039.oldguns.common.item.tools.ItemMediumMusketBallMold;
import zach2039.oldguns.common.item.tools.ItemMediumMusketBallMoldTool;
import zach2039.oldguns.common.item.tools.ItemPowderCharge;
import zach2039.oldguns.common.item.tools.ItemRamRod;
import zach2039.oldguns.common.item.tools.ItemRepairKit;
import zach2039.oldguns.common.item.tools.ItemSmallMusketBallMold;
import zach2039.oldguns.common.item.tools.ItemSmallMusketBallMoldTool;

@ObjectHolder(OldGuns.MODID)
public class ModItems
{
	@ObjectHolder("artillery_cannon")
	public static final Item ARTILLERY_CANNON = null;
	
	@ObjectHolder("medium_iron_cannonball")
	public static final Item MEDIUM_IRON_CANNONBALL = null;
	@ObjectHolder("medium_iron_he_shell")
	public static final Item MEDIUM_IRON_HE_SHELL = null;
	
	@ObjectHolder("gunners_quadrant")
	public static final Item GUNNERS_QUADRANT = null;
	@ObjectHolder("ram_rod")
	public static final Item RAM_ROD = null;
	@ObjectHolder("long_match")
	public static final Item LONG_MATCH = null;
	@ObjectHolder("powder_charge")
	public static final Item POWDER_CHARGE = null;
	
	/* Matchlock Firearms */
	@ObjectHolder("matchlock_derringer")
	public static final Item MATCHLOCK_DERRINGER = null;
	@ObjectHolder("matchlock_pistol")
	public static final Item MATCHLOCK_PISTOL = null;
	@ObjectHolder("matchlock_arquebus")
	public static final Item MATCHLOCK_ARQUEBUS = null;
	@ObjectHolder("matchlock_caliver")
	public static final Item MATCHLOCK_CALIVER = null;
	@ObjectHolder("matchlock_musket")
	public static final Item MATCHLOCK_MUSKET = null;
	@ObjectHolder("matchlock_long_musket")
	public static final Item MATCHLOCK_LONG_MUSKET = null;
	
	/* Matchlock Specialty Firearms */
	@ObjectHolder("matchlock_musketoon")
	public static final Item MATCHLOCK_MUSKETOON = null;
	@ObjectHolder("matchlock_blunderbuss")
	public static final Item MATCHLOCK_BLUNDERBUSS = null;
	
	/* Flintlock Firearms */
	@ObjectHolder("flintlock_derringer")
	public static final Item FLINTLOCK_DERRINGER = null;
	@ObjectHolder("flintlock_pistol")
	public static final Item FLINTLOCK_PISTOL = null;
	@ObjectHolder("flintlock_arquebus")
	public static final Item FLINTLOCK_ARQUEBUS = null;
	@ObjectHolder("flintlock_caliver")
	public static final Item FLINTLOCK_CALIVER = null;
	@ObjectHolder("flintlock_musket")
	public static final Item FLINTLOCK_MUSKET = null;
	@ObjectHolder("flintlock_long_musket")
	public static final Item FLINTLOCK_LONG_MUSKET = null;
	
	/* Flintlock Specialty Firearms */
	@ObjectHolder("flintlock_musketoon")
	public static final Item FLINTLOCK_MUSKETOON = null;
	@ObjectHolder("flintlock_blunderbuss")
	public static final Item FLINTLOCK_BLUNDERBUSS = null;
	
	@ObjectHolder("small_stone_musket_ball")
	public static final Item SMALL_STONE_MUSKET_BALL = null;
	@ObjectHolder("medium_stone_musket_ball")
	public static final Item MEDIUM_STONE_MUSKET_BALL = null;
	@ObjectHolder("large_stone_musket_ball")
	public static final Item LARGE_STONE_MUSKET_BALL = null;
	
	@ObjectHolder("large_stone_birdshot")
	public static final Item LARGE_STONE_BIRDSHOT = null;
	
	@ObjectHolder("small_iron_musket_ball")
	public static final Item SMALL_IRON_MUSKET_BALL = null;
	@ObjectHolder("medium_iron_musket_ball")
	public static final Item MEDIUM_IRON_MUSKET_BALL = null;
	@ObjectHolder("large_iron_musket_ball")
	public static final Item LARGE_IRON_MUSKET_BALL = null;
	
	@ObjectHolder("large_iron_birdshot")
	public static final Item LARGE_IRON_BIRDSHOT = null;
	
	@ObjectHolder("tiny_iron_barrel")
	public static final Item TINY_IRON_BARREL = null;
	@ObjectHolder("small_iron_barrel")
	public static final Item SMALL_IRON_BARREL = null;
	@ObjectHolder("medium_iron_barrel")
	public static final Item MEDIUM_IRON_BARREL = null;
	@ObjectHolder("large_iron_barrel")
	public static final Item LARGE_IRON_BARREL = null;
	@ObjectHolder("huge_iron_barrel")
	public static final Item HUGE_IRON_BARREL = null;
	
	@ObjectHolder("tiny_stone_barrel")
	public static final Item TINY_STONE_BARREL = null;
	@ObjectHolder("small_stone_barrel")
	public static final Item SMALL_STONE_BARREL = null;
	@ObjectHolder("medium_stone_barrel")
	public static final Item MEDIUM_STONE_BARREL = null;
	@ObjectHolder("large_stone_barrel")
	public static final Item LARGE_STONE_BARREL = null;
	@ObjectHolder("huge_stone_barrel")
	public static final Item HUGE_STONE_BARREL = null;
	
	@ObjectHolder("small_wooden_handle")
	public static final Item SMALL_WOODEN_HANDLE = null;
	@ObjectHolder("medium_wooden_handle")
	public static final Item MEDIUM_WOODEN_HANDLE = null;
	@ObjectHolder("large_wooden_handle")
	public static final Item LARGE_WOODEN_HANDLE = null;

	@ObjectHolder("small_wooden_stock")
	public static final Item SMALL_WOODEN_STOCK = null;
	@ObjectHolder("medium_wooden_stock")
	public static final Item MEDIUM_WOODEN_STOCK = null;
	@ObjectHolder("large_wooden_stock")
	public static final Item LARGE_WOODEN_STOCK = null;
	
	@ObjectHolder("matchlock_mechanism")
	public static final Item MATCHLOCK_MECHANISM = null;
	@ObjectHolder("flintlock_mechanism")
	public static final Item FLINTLOCK_MECHANISM = null;
	
	@ObjectHolder("small_musket_ball_mold")
	public static final Item SMALL_MUSKET_BALL_MOLD = null;
	@ObjectHolder("medium_musket_ball_mold")
	public static final Item MEDIUM_MUSKET_BALL_MOLD = null;
	@ObjectHolder("large_musket_ball_mold")
	public static final Item LARGE_MUSKET_BALL_MOLD = null;

	@ObjectHolder("small_musket_ball_mold_tool")
	public static final Item SMALL_MUSKET_BALL_MOLD_TOOL = null;
	@ObjectHolder("medium_musket_ball_mold_tool")
	public static final Item MEDIUM_MUSKET_BALL_MOLD_TOOL = null;
	@ObjectHolder("large_musket_ball_mold_tool")
	public static final Item LARGE_MUSKET_BALL_MOLD_TOOL = null;
	
	@ObjectHolder("repair_kit")
	public static final Item REPAIR_KIT = null;
	
	@EventBusSubscriber(modid = OldGuns.MODID)
	public static class RegistrationHandler
	{
		@SubscribeEvent
		public static void registerItems(Register<Item> event)
		{
			final Item[] items =
				{					
					new ItemArtilleryCannon(),
					
					new ItemMediumIronCannonball(),
					new ItemMediumIronHEShell(),
					
					new ItemGunnersQuadrant(),
					new ItemRamRod(),
					new ItemLongMatch(),
					new ItemPowderCharge(),
					
					new ItemMatchlockDerringer(),
					new ItemMatchlockPistol(),
					new ItemMatchlockArquebus(),
					new ItemMatchlockCaliver(),
					new ItemMatchlockMusket(),
					new ItemMatchlockLongMusket(),
					
					new ItemMatchlockMusketoon(),
					new ItemMatchlockBlunderbuss(),
					
					new ItemFlintlockDerringer(),
					new ItemFlintlockPistol(),
					new ItemFlintlockArquebus(),
					new ItemFlintlockCaliver(),
					new ItemFlintlockMusket(),
					new ItemFlintlockLongMusket(),
					
					new ItemFlintlockMusketoon(),
					new ItemFlintlockBlunderbuss(),
					
					new ItemSmallStoneMusketBall(),
					new ItemMediumStoneMusketBall(),
					new ItemLargeStoneMusketBall(),
					
					new ItemLargeStoneBirdshot(),
					
					new ItemSmallIronMusketBall(),
					new ItemMediumIronMusketBall(),
					new ItemLargeIronMusketBall(),
					
					new ItemLargeIronBirdshot(),
					
					new ItemTinyStoneBarrel(),
					new ItemSmallStoneBarrel(),
					new ItemMediumStoneBarrel(),
					new ItemLargeStoneBarrel(),
					new ItemHugeStoneBarrel(),
					
					new ItemTinyIronBarrel(),
					new ItemSmallIronBarrel(),
					new ItemMediumIronBarrel(),
					new ItemLargeIronBarrel(),
					new ItemHugeIronBarrel(),
					
					new ItemSmallWoodenHandle(),
					new ItemMediumWoodenHandle(),
					new ItemLargeWoodenHandle(),
					
					new ItemSmallWoodenStock(),
					new ItemMediumWoodenStock(),
					new ItemLargeWoodenStock(),
					
					new ItemMatchlockMechanism(),
					new ItemFlintlockMechanism(),
					
					new ItemSmallMusketBallMold(),
					new ItemMediumMusketBallMold(),
					new ItemLargeMusketBallMold(),
					
					new ItemSmallMusketBallMoldTool(),
					new ItemMediumMusketBallMoldTool(),
					new ItemLargeMusketBallMoldTool(),
					
					new ItemRepairKit(),
				};

			event.getRegistry().registerAll(items);
		}
	}
}
