package zach2039.oldguns.common.init;

import net.minecraftforge.oredict.OreDictionary;
import zach2039.oldguns.common.OldGuns;

public class ModRecipes
{
	public static void registerOreDictEntries()
	{
		OldGuns.logger.info("Mod Item: " + ModItems.SMALL_IRON_MUSKET_BALL);
		
		OreDictionary.registerOre("bulletSmallMusketBall", ModItems.SMALL_IRON_MUSKET_BALL);
		OreDictionary.registerOre("barrelSmallIron", ModItems.SMALL_IRON_BARREL);
		OreDictionary.registerOre("handleSmallWooden", ModItems.SMALL_WOODEN_HANDLE);
		OreDictionary.registerOre("toolRepairKit", ModItems.REPAIR_KIT);
	}
}
