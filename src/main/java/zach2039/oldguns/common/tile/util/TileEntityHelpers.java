package zach2039.oldguns.common.tile.util;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class TileEntityHelpers {
	
	public static TileEntity getTileEntity(IBlockAccess world, BlockPos pos) 
	{
		TileEntity te = world.getTileEntity(pos);
		return te;
	}
		
	public static void setState(World worldIn, BlockPos pos)
	{
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity != null)
        {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
	}
}

