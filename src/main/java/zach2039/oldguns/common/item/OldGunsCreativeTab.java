package zach2039.oldguns.common.item;

import java.util.Collections;
import java.util.Comparator;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.init.ModItems;

/**
 * Took this from a forum post on tabs.
 * @author Choonster	 
 */
public class OldGunsCreativeTab extends CreativeTabs {

	private ItemSorter itemSorter = new ItemSorter();

	public OldGunsCreativeTab() {
		super(OldGuns.MODID + ".all");
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(ModItems.MATCHLOCK_PISTOL);
	}

	@Override
	public void displayAllRelevantItems(NonNullList<ItemStack> items) {
		super.displayAllRelevantItems(items);

		// Sort the item list using the ItemSorter instance
		Collections.sort(items, itemSorter);
	}

	// Sorts items in alphabetical order using their display names
	private class ItemSorter implements Comparator<ItemStack> {

		@Override
		public int compare(ItemStack o1, ItemStack o2) {
			Item item1 = o1.getItem();
			Item item2 = o2.getItem();

			// If item1 is a block and item2 isn't, sort item1 before item2
			if (((item1 instanceof ItemBlock)) && (!(item2 instanceof ItemBlock))) {
				return -1;
			}

			// If item2 is a block and item1 isn't, sort item1 after item2
			if (((item2 instanceof ItemBlock)) && (!(item1 instanceof ItemBlock))) {
				return 1;
			}

			String displayName1 = o1.getDisplayName();
			String displayName2 = o2.getDisplayName();

			int result = displayName1.compareToIgnoreCase(displayName2);
			OldGuns.LOGGER.debug("sorter: \"%s\" \"%s\" - %d", displayName1, displayName2, result);
			return result;
		}
	}
}

