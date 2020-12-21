package zach2039.oldguns.common.init;

import net.minecraftforge.oredict.OreDictionary;

public class ModRecipes
{
	public static void registerOreDictEntries()
	{	
		OreDictionary.registerOre("bulletSmallIronMusketBall", ModItems.SMALL_IRON_MUSKET_BALL);
		OreDictionary.registerOre("bulletSmallStoneMusketBall", ModItems.SMALL_STONE_MUSKET_BALL);
		OreDictionary.registerOre("barrelSmallIron", ModItems.SMALL_IRON_BARREL);
		OreDictionary.registerOre("handleSmallWooden", ModItems.SMALL_WOODEN_HANDLE);
		OreDictionary.registerOre("toolRepairKit", ModItems.REPAIR_KIT);
	}
}
