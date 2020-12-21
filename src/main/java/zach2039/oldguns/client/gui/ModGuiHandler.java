package zach2039.oldguns.client.gui;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import zach2039.oldguns.client.gui.inventory.GuiGunsmithsBench;
import zach2039.oldguns.client.gui.inventory.GuiMelter;
import zach2039.oldguns.common.inventory.ContainerGunsmithsBench;
import zach2039.oldguns.common.inventory.ContainerMelter;
import zach2039.oldguns.common.tile.TileEntityMelter;

public class ModGuiHandler implements IGuiHandler
{
	@Override
	@Nullable
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		final TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
		
		switch (ID) 
		{
			case ModGuiIDs.GUNSMITHS_BENCH:
			{
				return new ContainerGunsmithsBench(player.inventory, world, new BlockPos(x, y, z));
			}
			case ModGuiIDs.MELTER:
			{
				return new ContainerMelter(player.inventory, (TileEntityMelter) tileEntity);
			}
			default:
			{
				return null;
			}
		}
	}

	@Override
	@Nullable
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		final TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));

		switch (ID) 
		{
			case ModGuiIDs.GUNSMITHS_BENCH:
			{
				return new GuiGunsmithsBench(player.inventory, world);
			}
			case ModGuiIDs.MELTER:
			{
				return new GuiMelter(player.inventory, (TileEntityMelter) tileEntity);
			}
			default:
			{
				return null;
			}
		}
	}
}
