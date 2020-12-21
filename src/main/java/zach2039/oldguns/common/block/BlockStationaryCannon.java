package zach2039.oldguns.common.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.tile.TileEntityStationaryArtillery;
import zach2039.oldguns.common.tile.TileEntityStationaryCannon;
import zach2039.oldguns.common.tile.util.TileEntityHelpers;

public class BlockStationaryCannon extends BlockContainer {
	
	public static final IProperty<EnumFacing> FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	
	public BlockStationaryCannon() {
		super(Material.IRON);
		setRegistryName(OldGuns.MODID, "stationary_cannon");
		setUnlocalizedName("stationary_cannon");
		setCreativeTab(OldGuns.OLDGUNS_CREATIVE_TAB);
		setSoundType(SoundType.METAL);
	}

	@Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
	
	@Override
	public boolean isFullCube(final IBlockState state)
	{
		return false;
	}
	
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, FACING);
	}
	
	@Override
	public int getMetaFromState(final IBlockState state)
	{
		return state.getValue(FACING).getHorizontalIndex();
	}
	
	@Override
	public IBlockState withRotation(final IBlockState state, final Rotation rotation)
	{
		return state.withProperty(FACING, rotation.rotate(state.getValue(FACING)));
	}
	
	@Override
	public IBlockState withMirror(final IBlockState state, final Mirror mirror)
	{
		return state.withRotation(mirror.toRotation(state.getValue(FACING)));
	}
	
	@Override
	public IBlockState getStateForPlacement(final World world, final BlockPos pos, final EnumFacing facing, final float hitX, final float hitY, final float hitZ, final int meta, final EntityLivingBase placer, final EnumHand hand) 
	{
		return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand).withProperty(FACING, placer.getHorizontalFacing());
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityStationaryCannon();
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) 
		{
			TileEntity te = TileEntityHelpers.getTileEntity(world, pos);
			if (te != null && te instanceof TileEntityStationaryArtillery)
			{						
				TileEntityStationaryArtillery ten = (TileEntityStationaryArtillery) te;
				TileEntityStationaryArtillery.handleInteraction(world, ten, ten, pos, state, player, hand, side, hitX, hitY, hitZ);
				te.markDirty();
			}
		}
		return false;
	}
}
