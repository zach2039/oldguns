package zach2039.oldguns.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import zach2039.oldguns.common.tile.TileEntityStationaryArtillery;
import zach2039.oldguns.common.tile.util.TileEntityHelpers;

public class BlockStationaryArtillery extends Block implements ITileEntityProvider {
	
	public BlockStationaryArtillery(Material materialIn) {
		super(materialIn);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityStationaryArtillery();
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) 
		{
			TileEntityStationaryArtillery te = (TileEntityStationaryArtillery) TileEntityHelpers.getTileEntity(world, pos);
		}
		return true;
	}
}
