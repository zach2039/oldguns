package zach2039.oldguns.common.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import zach2039.oldguns.client.gui.ModGuiIDs;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.tile.TileEntityMelter;

public class BlockMelter extends BlockContainer
{
	public static final IProperty<EnumFacing> FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	private final boolean isBurning;
	private static boolean keepInventory;
	
	public BlockMelter(boolean isBurning)
	{
		super(Material.ROCK);
		setRegistryName(OldGuns.MODID, "melter");
		setUnlocalizedName("melter");
		setCreativeTab(CreativeTabs.DECORATIONS);
		setSoundType(SoundType.STONE);	
		this.isBurning = isBurning;
	}
	
	public BlockMelter()
	{
		this(false);
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
	public IBlockState getStateFromMeta(final int meta)
	{
		return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
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
		return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand).withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		// Check if called on client or server.
		if (worldIn.isRemote)
		{
			// Client world. Do nothing.
		}
		else
		{
			// Server world. Open GUI for player.
			playerIn.openGui(OldGuns.instance, ModGuiIDs.MELTER, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		
		return true;
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) 
	{
		if (!keepInventory)
		{
			TileEntity tileEntity = worldIn.getTileEntity(pos);
			if (tileEntity instanceof IInventory) 
			{
				InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory)tileEntity);
				//worldIn.updateComparatorOutputLevel(pos, this);
			}
		}
		super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityMelter();
	}

	public static void setState(boolean active, World worldIn, BlockPos pos)
	{
		IBlockState iblockstate = worldIn.getBlockState(pos);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        keepInventory = true;

//        if (active)
//        {
//            worldIn.setBlockState(pos, ModBlocks.MELTER.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
//            worldIn.setBlockState(pos, ModBlocks.MELTER.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
//        }
//        else
//        {
//            worldIn.setBlockState(pos, ModBlocks.MELTER.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
//            worldIn.setBlockState(pos, ModBlocks.MELTER.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
//        }

        keepInventory = false;

        if (tileentity != null)
        {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
	}
	
	
	
//	// Nested class for Melter GUI.
//	public static class InterfaceMelter implements IInteractionObject
//	{
//		private final World world;
//		private final BlockPos position;
//		
//		public InterfaceMelter(World worldIn, BlockPos pos)
//		{
//			this.world = worldIn;
//			this.position = pos;
//		}
//		
//		@Override
//		public String getName()
//		{
//			return "melter";
//		}
//
//		@Override
//		public boolean hasCustomName()
//		{
//			return false;
//		}
//
//		@Override
//		public ITextComponent getDisplayName()
//		{
//			return new TextComponentTranslation(ModBlocks.MELTER.getUnlocalizedName() + ".name", new Object[0]);
//		}
//
//		@Override
//		public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
//		{
//			return new ContainerMelter(playerInventory, );
//		}
//
//		@Override
//		public String getGuiID()
//		{
//			return "oldguns:melter";
//		}
//	}


}
