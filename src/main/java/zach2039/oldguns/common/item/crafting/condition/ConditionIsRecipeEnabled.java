package zach2039.oldguns.common.item.crafting.condition;

import java.util.function.BooleanSupplier;

import javax.annotation.Nonnull;

import com.google.gson.JsonObject;

import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.fml.common.Loader;
import zach2039.oldguns.common.OldGuns;

public class ConditionIsRecipeEnabled {

	public static class Factory implements IConditionFactory {

		@Nonnull
		@Override
		public BooleanSupplier parse(JsonContext context, JsonObject json) {
			String modid = JsonUtils.getString(json, "name");
            return () -> Loader.isModLoaded(modid);
		}
	}
	

}
