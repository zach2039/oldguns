package zach2039.oldguns.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;
import zach2039.oldguns.client.gui.ModGuiIDs;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.init.ModBlocks;
import zach2039.oldguns.common.inventory.ContainerGunsmithsBench;

public class BlockGunsmithsBench extends Block
{
	public static final IProperty<EnumFacing> FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	
	public BlockGunsmithsBench()
	{
		super(Material.WOOD);
		setRegistryName(OldGuns.MODID, "gunsmiths_bench");
		//setUnlocalizedName("gunsmiths_bench");
		setCreativeTab(OldGuns.OLDGUNS_CREATIVE_TAB);
		setSoundType(SoundType.WOOD);	
		setHardness(2.5F);
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
		return this.getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta));
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
			playerIn.openGui(OldGuns.instance, ModGuiIDs.GUNSMITHS_BENCH, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		
		return true;
	}
	
	// Nested class for Gunsmith's Bench GUI.
	public static class InterfaceGunsmithsBench implements IInteractionObject
	{
		private final World world;
		private final BlockPos position;
		
		public InterfaceGunsmithsBench(World worldIn, BlockPos pos)
		{
			this.world = worldIn;
			this.position = pos;
		}
		
		@Override
		public String getName()
		{
			return "gunsmiths_bench";
		}

		@Override
		public boolean hasCustomName()
		{
			return false;
		}

		@Override
		public ITextComponent getDisplayName()
		{
			return new TextComponentTranslation(ModBlocks.GUNSMITHS_BENCH.getRegistryName() + ".name", new Object[0]);
		}

		@Override
		public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
		{
			return new ContainerGunsmithsBench(playerInventory, this.world, this.position);
		}

		@Override
		public String getGuiID()
		{
			return "oldguns:gunsmiths_bench";
		}
	}
}
