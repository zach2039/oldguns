package zach2039.oldguns.integration.jei;

import java.util.List;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import zach2039.oldguns.client.gui.inventory.GuiGunsmithsBench;
import zach2039.oldguns.common.init.ModBlocks;
import zach2039.oldguns.common.inventory.ContainerGunsmithsBench;
import zach2039.oldguns.common.item.crafting.ShapedGunsmithsBenchRecipe;
import zach2039.oldguns.common.item.crafting.ShapelessGunsmithsBenchRecipe;
import zach2039.oldguns.integration.jei.gunsmithsbench.GunsmithsBenchRecipeCategory;
import zach2039.oldguns.integration.jei.gunsmithsbench.GunsmithsBenchRecipeChecker;
import zach2039.oldguns.integration.jei.gunsmithsbench.ShapedGunsmithsBenchRecipeWrapper;
import zach2039.oldguns.integration.jei.gunsmithsbench.ShapelessGunsmithsBenchRecipeWrapper;

@JEIPlugin
public class JEIOldGunsPlugin implements IModPlugin {

	@Override 
	public void register(IModRegistry registry)
	{
		IJeiHelpers jeiHelpers = registry.getJeiHelpers();
		
		List<IRecipe> gunsmithBenchRecipes = GunsmithsBenchRecipeChecker.getValidRecipes(jeiHelpers);

		registry.addRecipes(gunsmithBenchRecipes, JEIOldGunsUUIDs.GUNSMITHS_BENCH);
		
		registry.handleRecipes(ShapelessGunsmithsBenchRecipe.class, recipe -> new ShapelessGunsmithsBenchRecipeWrapper(jeiHelpers, recipe), JEIOldGunsUUIDs.GUNSMITHS_BENCH);
		registry.handleRecipes(ShapedGunsmithsBenchRecipe.class, recipe -> new ShapedGunsmithsBenchRecipeWrapper(jeiHelpers, recipe), JEIOldGunsUUIDs.GUNSMITHS_BENCH);
		
		registry.addRecipeClickArea(GuiGunsmithsBench.class, 88, 32, 28, 23, JEIOldGunsUUIDs.GUNSMITHS_BENCH);
		
		IRecipeTransferRegistry recipeTransferRegistry = registry.getRecipeTransferRegistry();
		
		recipeTransferRegistry.addRecipeTransferHandler(ContainerGunsmithsBench.class, JEIOldGunsUUIDs.GUNSMITHS_BENCH, 1, 9, 10, 36);
		
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.GUNSMITHS_BENCH), JEIOldGunsUUIDs.GUNSMITHS_BENCH);
	}
	
	@Override
	public void registerCategories(IRecipeCategoryRegistration registry)
	{
		IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();
		
		registry.addRecipeCategories(new GunsmithsBenchRecipeCategory(guiHelper));
	}
	
	@Override
	public void onRuntimeAvailable(IJeiRuntime run)
	{
		
	}
}
