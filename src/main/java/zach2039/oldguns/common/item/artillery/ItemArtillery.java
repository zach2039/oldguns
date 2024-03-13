package zach2039.oldguns.common.item.artillery;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import zach2039.oldguns.api.artillery.ArtilleryType;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.entity.EntityArtillery;

public abstract class ItemArtillery extends Item
{
	protected ArtilleryType artilleryType = ArtilleryType.CANNON;
	
	public ItemArtillery(String name, ArtilleryType artilleryType)
	{
		setRegistryName(OldGuns.MODID, name);
		//setUnlocalizedName(name);
		setMaxStackSize(1);
		setCreativeTab(OldGuns.OLDGUNS_CREATIVE_TAB);
		setArtilleryType(artilleryType);
	}
	
	public ArtilleryType getArtilleryType()
	{
		return this.artilleryType;
	}
	
	public void setArtilleryType(ArtilleryType artilleryType)
	{
		this.artilleryType = artilleryType;
	}
	
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {

        ItemStack itemstack = player.getHeldItem(hand);

        if (!worldIn.isRemote)
        {

            EntityArtillery entityartillery = EntityArtillery.create(worldIn, (double)pos.getX() + 0.5D, (double)pos.getY() + 1D, (double)pos.getZ() + 0.5D, artilleryType);

            if (itemstack.hasDisplayName())
            {
            	entityartillery.setCustomNameTag(itemstack.getDisplayName());
            }

            entityartillery.rotationYaw = (player.getRotationYawHead());
            worldIn.spawnEntity(entityartillery);
        }

        itemstack.shrink(1);
        return EnumActionResult.SUCCESS;
    }
}
