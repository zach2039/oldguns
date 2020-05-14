package zach2039.oldguns.common.item.util;

public class FirearmType
{
	public static enum FirearmEffect
	{
		SMALL_FIREARM_SHOOT,
		MEDIUM_FIREARM_SHOOT,
		LARGE_FIREARM_SHOOT,
		MISFIRE,
		MISFIRE_WET,
		BREAK
	}
	
	public static enum FirearmReloadType 
	{
		MUZZLELOADER,
		BREECHLOADER
	}
	
	public static enum FirearmAmmoType 
	{
		MUSKET_BALL,
		MUSKET_CARTRIDGE,
		RIMFIRE_CARTRIDGE,
		SHOT_AND_BALL,
		BIRDSHOT,
		BUCKSHOT,
		FLAMESHOT
	}
	
	public static enum FirearmSize
	{
		SMALL,
		MEDIUM,
		LARGE
	}
	
	public static enum FirearmCondition
	{
		VERY_GOOD,
		GOOD,
		FAIR,
		POOR,
		VERY_POOR,
		BROKEN
	}
	
	public static enum FirearmWaterResiliency
	{
		GOOD,
		FAIR,
		POOR
	}
	
	public static enum FirearmPart
	{
		SMALL_HANDLE,
		MEDIUM_HANDLE,
		LARGE_HANDLE,
		SMALL_STOCK,
		MEDIUM_STOCK,
		LARGE_STOCK,
		MATCHLOCK_MECHANISM,
		FLINTLOCK_MECHANISM,
		WHEELLOCK_MECHANISM,
		CAPLOCK_MECHANISM,
		SMALL_METAL_BARREL,
		MEDIUM_METAL_BARREL,
		LARGE_METAL_BARREL,
		SMALL_STONE_BARREL,
		MEDIUM_STONE_BARREL,
		LARGE_STONE_BARREL,
		SMALL_METAL_RIFLED_BARREL,
		MEDIUM_METAL_RIFLED_BARREL,
		LARGE_METAL_RIFLED_BARREL
	}
}
