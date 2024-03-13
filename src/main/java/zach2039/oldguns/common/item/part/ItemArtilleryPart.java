package zach2039.oldguns.common.item.part;

import net.minecraft.item.Item;
import zach2039.oldguns.api.artillery.ArtilleryPart;
import zach2039.oldguns.common.OldGuns;

public abstract class ItemArtilleryPart extends Item
{
	/**
	 * Part type of this artillery ammo item instance.
	 */
	protected ArtilleryPart partType = ArtilleryPart.CANNON_BARREL;
	
	public ItemArtilleryPart(String name, ArtilleryPart partType)
	{
		setRegistryName(OldGuns.MODID, name);
		setTranslationKey(name);
		setMaxStackSize(1);
		setCreativeTab(OldGuns.OLDGUNS_CREATIVE_TAB);
		setPartType(partType);
	}
	
	public ArtilleryPart getPartType()
	{
		return this.partType;
	}

	public void setPartType(ArtilleryPart partType)
	{
		this.partType = partType;
	}
}
