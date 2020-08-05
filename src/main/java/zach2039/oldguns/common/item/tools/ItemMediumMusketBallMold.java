package zach2039.oldguns.common.item.tools;

import javax.annotation.Nullable;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import zach2039.oldguns.api.capability.casting.CastHandler;
import zach2039.oldguns.common.OldGuns;

public class ItemMediumMusketBallMold extends Item
{
	public ItemMediumMusketBallMold()
	{
		setRegistryName(OldGuns.MODID, "medium_musket_ball_mold");
		setUnlocalizedName("medium_musket_ball_mold");
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.COMBAT);
		
		// Set max uses of cast in recipes via damage value.
		setMaxDamage(63);
	}
	
	@Override
	public Item getContainerItem()
	{
		return this;
	}
	
	@Nullable
	@Override
    public net.minecraftforge.common.capabilities.ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt)
    {
		// Give item ability to be used as cast in melter.
        return new CastHandler();
    }
}
