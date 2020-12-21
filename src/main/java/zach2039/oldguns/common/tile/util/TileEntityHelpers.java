package zach2039.oldguns.common.tile.util;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class TileEntityHelpers {
	
	public static TileEntity getTileEntity(IBlockAccess world, BlockPos pos) 
	{
		TileEntity te = world.getTileEntity(pos);
		return te;
	}
		
}

