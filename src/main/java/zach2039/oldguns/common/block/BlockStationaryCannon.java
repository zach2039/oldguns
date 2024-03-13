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
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.NonNullList;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.init.ModBlocks;
import zach2039.oldguns.common.init.ModItems;
import zach2039.oldguns.common.tile.TileEntityStationaryCannon;
import zach2039.oldguns.common.tile.util.TileEntityHelpers;

public class BlockStationaryCannon extends BlockContainer {
	
	public static final IProperty<EnumFacing> FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	
	public BlockStationaryCannon() {
		super(Material.IRON);
		setRegistryName(OldGuns.MODID, "stationary_cannon");
		setTranslationKey("stationary_cannon");
		setCreativeTab(OldGuns.OLDGUNS_CREATIVE_TAB);
		setSoundType(SoundType.METAL);
		setHardness(2.0F);
		setResistance(6.0F);
		setHarvestLevel("pickaxe", 1);
		
	}

	@Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
		drops.add(new ItemStack(ModItems.LARGE_IRON_CANNON_BARREL));
		drops.add(new ItemStack(ModItems.LARGE_WOODEN_CANNON_CARRIAGE));
		drops.add(new ItemStack(Blocks.WOODEN_BUTTON));
		drops.add(new ItemStack(Blocks.WOODEN_BUTTON));
    }
	
	@Override
	public boolean hasTileEntity(IBlockState state)
	{
		return true;
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(ModBlocks.STATIONARY_CANNON);
    }
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

	@Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

	@Override
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
    {
        return false;
    }
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
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
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		if (tileentity instanceof TileEntityStationaryCannon) { // prevent a crash if not the right type, or is null
			TileEntityStationaryCannon teCannon = (TileEntityStationaryCannon)tileentity;
			teCannon.setFacing(placer.getHorizontalFacing());
			teCannon.setBarrelYaw(teCannon.getDefaultYaw());
			teCannon.setBarrelPitch(teCannon.getDefaultPitch());
		}
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public BlockRenderLayer getRenderLayer()
	{
		return BlockRenderLayer.SOLID;
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		super.breakBlock(world, pos, state);
		world.removeTileEntity(pos);
		world.setBlockToAir(pos);
		world.markChunkDirty(pos, null);
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntity te = TileEntityHelpers.getTileEntity(world, pos);
		
		if(te == null || !(te instanceof TileEntityStationaryCannon) || (hand == EnumHand.OFF_HAND)) {
			return false;
		}
		
		return ((TileEntityStationaryCannon)te).processPlayerInteraction(world, pos, state, player, side, hitX, hitY, hitZ) && (player != null);
	}

	@Override
	public boolean eventReceived(IBlockState state, World world, BlockPos pos, int id, int param) {
		super.eventReceived(state, world, pos, id, param);
		TileEntityStationaryCannon tileentity = (TileEntityStationaryCannon) world.getTileEntity(pos);
		
		if(tileentity == null) {
			return false;
		}
		
		return tileentity.receiveClientEvent(param, param);
	}

}
