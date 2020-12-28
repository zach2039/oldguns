package zach2039.oldguns.common.init;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.item.ammo.ItemLargeIronBirdshot;
import zach2039.oldguns.common.item.ammo.ItemLargeIronBirdshotPaperCartridge;
import zach2039.oldguns.common.item.ammo.ItemLargeIronBuckshot;
import zach2039.oldguns.common.item.ammo.ItemLargeIronBuckshotPaperCartridge;
import zach2039.oldguns.common.item.ammo.ItemLargeIronMusketBall;
import zach2039.oldguns.common.item.ammo.ItemLargeIronMusketBallPaperCartridge;
import zach2039.oldguns.common.item.ammo.ItemLargeStoneBirdshot;
import zach2039.oldguns.common.item.ammo.ItemLargeStoneMusketBall;
import zach2039.oldguns.common.item.ammo.ItemMediumIronBirdshot;
import zach2039.oldguns.common.item.ammo.ItemMediumIronBirdshotPaperCartridge;
import zach2039.oldguns.common.item.ammo.ItemMediumIronBuckshot;
import zach2039.oldguns.common.item.ammo.ItemMediumIronBuckshotPaperCartridge;
import zach2039.oldguns.common.item.ammo.ItemMediumIronCannonball;
import zach2039.oldguns.common.item.ammo.ItemMediumIronHEShell;
import zach2039.oldguns.common.item.ammo.ItemMediumIronMusketBall;
import zach2039.oldguns.common.item.ammo.ItemMediumIronMusketBallPaperCartridge;
import zach2039.oldguns.common.item.ammo.ItemMediumStoneBirdshot;
import zach2039.oldguns.common.item.ammo.ItemMediumStoneMusketBall;
import zach2039.oldguns.common.item.ammo.ItemSmallIronBirdshot;
import zach2039.oldguns.common.item.ammo.ItemSmallIronBirdshotPaperCartridge;
import zach2039.oldguns.common.item.ammo.ItemSmallIronBuckshot;
import zach2039.oldguns.common.item.ammo.ItemSmallIronBuckshotPaperCartridge;
import zach2039.oldguns.common.item.ammo.ItemSmallIronMusketBall;
import zach2039.oldguns.common.item.ammo.ItemSmallIronMusketBallPaperCartridge;
import zach2039.oldguns.common.item.ammo.ItemSmallStoneBirdshot;
import zach2039.oldguns.common.item.ammo.ItemSmallStoneMusketBall;
import zach2039.oldguns.common.item.artillery.ItemArtilleryCannon;
import zach2039.oldguns.common.item.firearm.ItemCaplockArquebus;
import zach2039.oldguns.common.item.firearm.ItemCaplockBlunderbuss;
import zach2039.oldguns.common.item.firearm.ItemCaplockCaliver;
import zach2039.oldguns.common.item.firearm.ItemCaplockDerringer;
import zach2039.oldguns.common.item.firearm.ItemCaplockLongMusket;
import zach2039.oldguns.common.item.firearm.ItemCaplockMusket;
import zach2039.oldguns.common.item.firearm.ItemCaplockMusketoon;
import zach2039.oldguns.common.item.firearm.ItemCaplockPistol;
import zach2039.oldguns.common.item.firearm.ItemFlintlockArquebus;
import zach2039.oldguns.common.item.firearm.ItemFlintlockBlunderbuss;
import zach2039.oldguns.common.item.firearm.ItemFlintlockBreechloadingArquebus;
import zach2039.oldguns.common.item.firearm.ItemFlintlockBreechloadingCaliver;
import zach2039.oldguns.common.item.firearm.ItemFlintlockBreechloadingLongMusket;
import zach2039.oldguns.common.item.firearm.ItemFlintlockBreechloadingMusket;
import zach2039.oldguns.common.item.firearm.ItemFlintlockBreechloadingPistol;
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
import zach2039.oldguns.common.item.part.ItemBreechBlock;
import zach2039.oldguns.common.item.part.ItemCaplockMechanism;
import zach2039.oldguns.common.item.part.ItemFlintlockMechanism;
import zach2039.oldguns.common.item.part.ItemHugeIronBarrel;
import zach2039.oldguns.common.item.part.ItemHugeStoneBarrel;
import zach2039.oldguns.common.item.part.ItemLargeIronBarrel;
import zach2039.oldguns.common.item.part.ItemLargeIronCannonBarrel;
import zach2039.oldguns.common.item.part.ItemLargeStoneBarrel;
import zach2039.oldguns.common.item.part.ItemLargeWoodenCannonCarriage;
import zach2039.oldguns.common.item.part.ItemLargeWoodenCannonWheel;
import zach2039.oldguns.common.item.part.ItemLargeWoodenHandle;
import zach2039.oldguns.common.item.part.ItemLargeWoodenStock;
import zach2039.oldguns.common.item.part.ItemMatchlockMechanism;
import zach2039.oldguns.common.item.part.ItemMediumIronBarrel;
import zach2039.oldguns.common.item.part.ItemMediumStoneBarrel;
import zach2039.oldguns.common.item.part.ItemMediumWoodenHandle;
import zach2039.oldguns.common.item.part.ItemMediumWoodenStock;
import zach2039.oldguns.common.item.part.ItemPercussionCapCone;
import zach2039.oldguns.common.item.part.ItemSmallIronBarrel;
import zach2039.oldguns.common.item.part.ItemSmallStoneBarrel;
import zach2039.oldguns.common.item.part.ItemSmallWoodenHandle;
import zach2039.oldguns.common.item.part.ItemSmallWoodenStock;
import zach2039.oldguns.common.item.part.ItemTinyIronBarrel;
import zach2039.oldguns.common.item.part.ItemTinyStoneBarrel;
import zach2039.oldguns.common.item.tools.ItemGunnersQuadrant;
import zach2039.oldguns.common.item.tools.ItemHackSaw;
import zach2039.oldguns.common.item.tools.ItemLargeMusketBallMold;
import zach2039.oldguns.common.item.tools.ItemLargeMusketBallMoldTool;
import zach2039.oldguns.common.item.tools.ItemLongMatch;
import zach2039.oldguns.common.item.tools.ItemMediumMusketBallMold;
import zach2039.oldguns.common.item.tools.ItemMediumMusketBallMoldTool;
import zach2039.oldguns.common.item.tools.ItemPercussionCap;
import zach2039.oldguns.common.item.tools.ItemPercussionPowder;
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
	
	@ObjectHolder("large_iron_cannon_barrel")
	public static final Item LARGE_IRON_CANNON_BARREL = null;
	@ObjectHolder("large_wooden_cannon_wheel")
	public static final Item LARGE_WOODEN_CANNON_WHEEL = null;
	@ObjectHolder("large_wooden_cannon_carriage")
	public static final Item LARGE_WOODEN_CANNON_CARRIAGE = null;
	
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
	
	@ObjectHolder("flintlock_breechloading_pistol")
	public static final Item FLINTLOCK_BREECHLOADING_PISTOL = null;
	@ObjectHolder("flintlock_breechloading_arquebus")
	public static final Item FLINTLOCK_BREECHLOADING_ARQUEBUS = null;
	@ObjectHolder("flintlock_breechloading_caliver")
	public static final Item FLINTLOCK_BREECHLOADING_CALIVER = null;
	@ObjectHolder("flintlock_breechloading_musket")
	public static final Item FLINTLOCK_BREECHLOADING_MUSKET = null;
	@ObjectHolder("flintlock_breechloading_long_musket")
	public static final Item FLINTLOCK_BREECHLOADING_LONG_MUSKET = null;
	
	/* Caplock Firearms */
	@ObjectHolder("caplock_derringer")
	public static final Item CAPLOCK_DERRINGER = null;
	@ObjectHolder("caplock_pistol")
	public static final Item CAPLOCK_PISTOL = null;
	@ObjectHolder("caplock_arquebus")
	public static final Item CAPLOCK_ARQUEBUS = null;
	@ObjectHolder("caplock_caliver")
	public static final Item CAPLOCK_CALIVER = null;
	@ObjectHolder("caplock_musket")
	public static final Item CAPLOCK_MUSKET = null;
	@ObjectHolder("caplock_long_musket")
	public static final Item CAPLOCK_LONG_MUSKET = null;
	
	/* Caplock Specialty Firearms */
	@ObjectHolder("caplock_musketoon")
	public static final Item CAPLOCK_MUSKETOON = null;
	@ObjectHolder("caplock_blunderbuss")
	public static final Item CAPLOCK_BLUNDERBUSS = null;
	
	@ObjectHolder("small_stone_musket_ball")
	public static final Item SMALL_STONE_MUSKET_BALL = null;
	@ObjectHolder("medium_stone_musket_ball")
	public static final Item MEDIUM_STONE_MUSKET_BALL = null;
	@ObjectHolder("large_stone_musket_ball")
	public static final Item LARGE_STONE_MUSKET_BALL = null;
	
	@ObjectHolder("small_stone_birdshot")
	public static final Item SMALL_STONE_BIRDSHOT = null;
	@ObjectHolder("medium_stone_birdshot")
	public static final Item MEDIUM_STONE_BIRDSHOT = null;
	@ObjectHolder("large_stone_birdshot")
	public static final Item LARGE_STONE_BIRDSHOT = null;
	
	@ObjectHolder("small_iron_musket_ball")
	public static final Item SMALL_IRON_MUSKET_BALL = null;
	@ObjectHolder("medium_iron_musket_ball")
	public static final Item MEDIUM_IRON_MUSKET_BALL = null;
	@ObjectHolder("large_iron_musket_ball")
	public static final Item LARGE_IRON_MUSKET_BALL = null;
	
	@ObjectHolder("small_iron_birdshot")
	public static final Item SMALL_IRON_BIRDSHOT = null;
	@ObjectHolder("medium_iron_birdshot")
	public static final Item MEDIUM_IRON_BIRDSHOT = null;
	@ObjectHolder("large_iron_birdshot")
	public static final Item LARGE_IRON_BIRDSHOT = null;
	
	@ObjectHolder("small_iron_buckshot")
	public static final Item SMALL_IRON_BUCKSHOT = null;
	@ObjectHolder("medium_iron_buckshot")
	public static final Item MEDIUM_IRON_BUCKSHOT = null;
	@ObjectHolder("large_iron_buckshot")
	public static final Item LARGE_IRON_BUCKSHOT = null;
	
	@ObjectHolder("small_iron_musket_ball_paper_cartridge")
	public static final Item SMALL_IRON_MUSKET_BALL_PAPER_CARTRIDGE = null;
	@ObjectHolder("medium_iron_musket_ball_paper_cartridge")
	public static final Item MEDIUM_IRON_MUSKET_BALL_PAPER_CARTRIDGE = null;
	@ObjectHolder("large_iron_musket_ball_paper_cartridge")
	public static final Item LARGE_IRON_MUSKET_BALL_PAPER_CARTRIDGE = null;
	
	@ObjectHolder("small_iron_birdshot_paper_cartridge")
	public static final Item SMALL_IRON_BIRDSHOT_PAPER_CARTRIDGE = null;
	@ObjectHolder("medium_iron_birdshot_paper_cartridge")
	public static final Item MEDIUM_IRON_BIRDSHOT_PAPER_CARTRIDGE = null;
	@ObjectHolder("large_iron_birdshot_paper_cartridge")
	public static final Item LARGE_IRON_BIRDSHOT_PAPER_CARTRIDGE = null;
	
	@ObjectHolder("small_iron_buckshot_paper_cartridge")
	public static final Item SMALL_IRON_BUCKSHOT_PAPER_CARTRIDGE = null;
	@ObjectHolder("medium_iron_buckshot_paper_cartridge")
	public static final Item MEDIUM_IRON_BUCKSHOT_PAPER_CARTRIDGE = null;
	@ObjectHolder("large_iron_buckshot_paper_cartridge")
	public static final Item LARGE_IRON_BUCKSHOT_PAPER_CARTRIDGE = null;
	
	@ObjectHolder("percussion_cap")
	public static final Item PERCUSSION_CAP = null;
	@ObjectHolder("percussion_powder")
	public static final Item PERCUSSION_POWDER = null;
	
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
	@ObjectHolder("caplock_mechanism")
	public static final Item CAPLOCK_MECHANISM = null;
	
	@ObjectHolder("breech_block")
	public static final Item BREECH_BLOCK = null;
	@ObjectHolder("percussion_cap_cone")
	public static final Item PERCUSSION_CAP_CONE = null;
	
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
	@ObjectHolder("hack_saw")
	public static final Item HACK_SAW = null;
	
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
					
					new ItemLargeIronCannonBarrel(),
					new ItemLargeWoodenCannonWheel(),
					new ItemLargeWoodenCannonCarriage(),
					
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
					
					new ItemFlintlockBreechloadingPistol(),
					new ItemFlintlockBreechloadingArquebus(),
					new ItemFlintlockBreechloadingCaliver(),
					new ItemFlintlockBreechloadingMusket(),
					new ItemFlintlockBreechloadingLongMusket(),
					
					new ItemCaplockDerringer(),
					new ItemCaplockPistol(),
					new ItemCaplockArquebus(),
					new ItemCaplockCaliver(),
					new ItemCaplockMusket(),
					new ItemCaplockLongMusket(),
					
					new ItemCaplockMusketoon(),
					new ItemCaplockBlunderbuss(),
					
					new ItemSmallStoneMusketBall(),
					new ItemMediumStoneMusketBall(),
					new ItemLargeStoneMusketBall(),
					
					new ItemSmallStoneBirdshot(),
					new ItemMediumStoneBirdshot(),
					new ItemLargeStoneBirdshot(),
					
					new ItemSmallIronMusketBall(),
					new ItemMediumIronMusketBall(),
					new ItemLargeIronMusketBall(),
					
					new ItemSmallIronBirdshot(),
					new ItemMediumIronBirdshot(),
					new ItemLargeIronBirdshot(),
					
					new ItemSmallIronBuckshot(),
					new ItemMediumIronBuckshot(),
					new ItemLargeIronBuckshot(),
					
					new ItemSmallIronMusketBallPaperCartridge(),
					new ItemMediumIronMusketBallPaperCartridge(),
					new ItemLargeIronMusketBallPaperCartridge(),
					
					new ItemSmallIronBirdshotPaperCartridge(),
					new ItemMediumIronBirdshotPaperCartridge(),
					new ItemLargeIronBirdshotPaperCartridge(),
					
					new ItemSmallIronBuckshotPaperCartridge(),
					new ItemMediumIronBuckshotPaperCartridge(),
					new ItemLargeIronBuckshotPaperCartridge(),					
					
					new ItemPercussionCap(),
					new ItemPercussionPowder(),
					
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
					new ItemCaplockMechanism(),
					
					new ItemBreechBlock(),
					new ItemPercussionCapCone(),
					
					new ItemSmallMusketBallMold(),
					new ItemMediumMusketBallMold(),
					new ItemLargeMusketBallMold(),
					
					new ItemSmallMusketBallMoldTool(),
					new ItemMediumMusketBallMoldTool(),
					new ItemLargeMusketBallMoldTool(),
					
					new ItemRepairKit(),
					new ItemHackSaw()
				};
			
			event.getRegistry().registerAll(items);
		}
	}
}
