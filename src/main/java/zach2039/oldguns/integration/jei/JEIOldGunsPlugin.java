package zach2039.oldguns.integration.jei;

import java.util.List;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import zach2039.oldguns.client.gui.inventory.GuiGunsmithsBench;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.init.ModBlocks;
import zach2039.oldguns.common.inventory.ContainerGunsmithsBench;
import zach2039.oldguns.common.item.crafting.BreechloadingReloadRecipe;
import zach2039.oldguns.common.item.crafting.ShapedGunsmithsBenchRecipe;
import zach2039.oldguns.common.item.crafting.ShapelessGunsmithsBenchRecipe;
import zach2039.oldguns.integration.jei.breechloading.BreechloadingRecipeCategory;
import zach2039.oldguns.integration.jei.breechloading.BreechloadingRecipeChecker;
import zach2039.oldguns.integration.jei.breechloading.BreechloadingRecipeWrapper;
import zach2039.oldguns.integration.jei.gunsmithsbench.GunsmithsBenchRecipeCategory;
import zach2039.oldguns.integration.jei.gunsmithsbench.GunsmithsBenchRecipeChecker;
import zach2039.oldguns.integration.jei.gunsmithsbench.ShapedGunsmithsBenchRecipeWrapper;
import zach2039.oldguns.integration.jei.gunsmithsbench.ShapelessGunsmithsBenchRecipeWrapper;

@JEIPlugin
public class JEIOldGunsPlugin implements IModPlugin {

    public static IJeiHelpers jeiHelpers = null;
    public static IJeiRuntime jeiRuntime = null;
	
	@Override 
	public void register(IModRegistry registry)
	{
		jeiHelpers = registry.getJeiHelpers();
		
		List<IRecipe> gunsmithBenchRecipes = GunsmithsBenchRecipeChecker.getValidRecipes(jeiHelpers);
		List<IRecipe> breechloadingRecipes = BreechloadingRecipeChecker.getValidRecipes(jeiHelpers);
		
		OldGuns.logger.info("numvalid gunsmithBenchRecipes : " + gunsmithBenchRecipes.size());
		OldGuns.logger.info("numvalid breechloadingRecipes : " + breechloadingRecipes.size());

		registry.addRecipes(gunsmithBenchRecipes, JEIOldGunsUUIDs.GUNSMITHS_BENCH);
		registry.addRecipes(breechloadingRecipes, JEIOldGunsUUIDs.BREECHLOADING);
		
		registry.handleRecipes(ShapelessGunsmithsBenchRecipe.class, recipe -> new ShapelessGunsmithsBenchRecipeWrapper(jeiHelpers, recipe), JEIOldGunsUUIDs.GUNSMITHS_BENCH);
		registry.handleRecipes(ShapedGunsmithsBenchRecipe.class, recipe -> new ShapedGunsmithsBenchRecipeWrapper(jeiHelpers, recipe), JEIOldGunsUUIDs.GUNSMITHS_BENCH);
		registry.handleRecipes(BreechloadingReloadRecipe.class, recipe -> new BreechloadingRecipeWrapper(jeiHelpers, recipe), JEIOldGunsUUIDs.BREECHLOADING);
		
		registry.addRecipeClickArea(GuiGunsmithsBench.class, 88, 32, 28, 23, JEIOldGunsUUIDs.GUNSMITHS_BENCH);
		
		IRecipeTransferRegistry recipeTransferRegistry = registry.getRecipeTransferRegistry();
		
		recipeTransferRegistry.addRecipeTransferHandler(ContainerGunsmithsBench.class, JEIOldGunsUUIDs.GUNSMITHS_BENCH, 1, 9, 10, 36);
		
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.GUNSMITHS_BENCH), JEIOldGunsUUIDs.GUNSMITHS_BENCH);
	}
	
	@Override
	public void registerCategories(IRecipeCategoryRegistration registry)
	{
		IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();
		
		registry.addRecipeCategories(new GunsmithsBenchRecipeCategory(guiHelper), new BreechloadingRecipeCategory(guiHelper));
	}
	
    @Override
    public void onRuntimeAvailable(IJeiRuntime iJeiRuntime) {
        jeiRuntime = iJeiRuntime;
    }

}
