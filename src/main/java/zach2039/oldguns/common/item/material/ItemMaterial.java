package zach2039.oldguns.common.item.material;

import net.minecraft.item.Item;
import zach2039.oldguns.common.OldGuns;

public abstract class ItemMaterial extends Item
{	
	public ItemMaterial(String name, int maxStackSize)
	{
		setRegistryName(OldGuns.MODID, name);
		//setUnlocalizedName(name);
		setMaxStackSize(maxStackSize);
		setCreativeTab(OldGuns.OLDGUNS_CREATIVE_TAB);
	}
}
