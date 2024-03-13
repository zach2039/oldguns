package zach2039.oldguns.compat.jei.breechloading;

import java.util.List;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.ICraftingGridHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.wrapper.ICustomCraftingRecipeWrapper;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.init.ModItems;
import zach2039.oldguns.compat.jei.JEIOldGunsUUIDs;

public class BreechloadingRecipeCategory implements IRecipeCategory<IRecipeWrapper> {

	private final ResourceLocation guiTextureLocation = new ResourceLocation(OldGuns.MODID, "textures/jei/breechloading_recipe_jei.png");
		
	public static final int WIDTH = 116;
	public static final int HEIGHT = 54;
	
	private static final int OUTPUT_SLOT = 0;
	private static final int INPUT_SLOT_FIRST = 1;
	
	private final String localizedName;
	private final IDrawable background;
	private final IDrawable icon;
	private final ICraftingGridHelper craftingGridHelper;
	
	public BreechloadingRecipeCategory(IGuiHelper guiHelper) {
		this.localizedName = I18n.format("jei.oldguns.breechloadingrecipe.name");
		this.background = guiHelper.createDrawable(guiTextureLocation, 23, 11, WIDTH, HEIGHT);
		this.icon = guiHelper.createDrawableIngredient(new ItemStack(ModItems.FLINTLOCK_BREECHLOADING_MUSKET));
		this.craftingGridHelper = guiHelper.createCraftingGridHelper(INPUT_SLOT_FIRST, OUTPUT_SLOT);
	}
	
	@Override
	public String getUid() {
		return JEIOldGunsUUIDs.BREECHLOADING;
	}

	@Override
	public String getTitle() {		
		return this.localizedName;
	}

	@Override
	public String getModName() {
		return OldGuns.MODNAME;
	}

	@Override
	public IDrawable getBackground() {
		return this.background;
	}
	
	@Override 
	public IDrawable getIcon()
	{
		return this.icon;
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		
		/* Initialize output slot. */
		guiItemStacks.init(OUTPUT_SLOT, false, 94, 18);

		for (int y = 0; y < 3; ++y) {
			for (int x = 0; x < 3; ++x) {
				int index = INPUT_SLOT_FIRST + x + (y * 3);
				guiItemStacks.init(index, true, x * 18, y * 18);
			}
		}
		
		if (recipeWrapper instanceof ICustomCraftingRecipeWrapper) {
			ICustomCraftingRecipeWrapper customWrapper = (ICustomCraftingRecipeWrapper) recipeWrapper;
			customWrapper.setRecipe(recipeLayout, ingredients);
			return;
		}
		
		List<List<ItemStack>> inputs = ingredients.getInputs(VanillaTypes.ITEM);
		List<List<ItemStack>> outputs = ingredients.getOutputs(VanillaTypes.ITEM);
		
		/* Set inputs using wrapper information, and set outputs. */
		craftingGridHelper.setInputs(guiItemStacks, inputs);
		recipeLayout.setShapeless();

		guiItemStacks.set(OUTPUT_SLOT, outputs.get(0));
		
		/*
		if (recipeWrapper instanceof ICraftingRecipeWrapper) 
		{
			ICraftingRecipeWrapper craftingRecipeWrapper = (ICraftingRecipeWrapper) recipeWrapper;
			ResourceLocation registryName = craftingRecipeWrapper.getRegistryName();
			if (registryName != null) 
			{
				guiItemStacks.addTooltipCallback((slotIndex, input, ingredient, tooltip) -> {
					if (slotIndex == OUTPUT_SLOT) 
					{
						String recipeModId = registryName.getResourceDomain();
						
						boolean modIdDifferent = false;
						ResourceLocation itemRegistryName = ingredient.getItem().getRegistryName();
						if (itemRegistryName != null) 
						{
							String itemModId = itemRegistryName.getResourceDomain();
							modIdDifferent = !recipeModId.equals(itemModId);
						}

						if (modIdDifferent) 
						{
							String modName = ForgeModIdHelper.getInstance().getFormattedModNameForModId(recipeModId);
							if (modName != null) {
								tooltip.add(TextFormatting.GRAY + Translator.translateToLocalFormatted("jei.tooltip.recipe.by", modName));
							}
						}

						boolean showAdvanced = Minecraft.getMinecraft().gameSettings.advancedItemTooltips || GuiScreen.isShiftKeyDown();
						if (showAdvanced) 
						{
							tooltip.add(TextFormatting.DARK_GRAY + Translator.translateToLocalFormatted("jei.tooltip.recipe.id", registryName.toString()));
						}
					}
				});
			}
		}
		*/
	}
}
