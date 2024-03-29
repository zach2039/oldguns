package com.zach2039.oldguns;

import java.util.UUID;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.init.ModAttributes;
import com.zach2039.oldguns.init.ModBlockEntities;
import com.zach2039.oldguns.init.ModBlocks;
import com.zach2039.oldguns.init.ModCrafting;
import com.zach2039.oldguns.init.ModEntities;
import com.zach2039.oldguns.init.ModFluids;
import com.zach2039.oldguns.init.ModItems;
import com.zach2039.oldguns.init.ModLootConditionTypes;
import com.zach2039.oldguns.init.ModLootModifierCodecs;
import com.zach2039.oldguns.init.ModLootTables;
import com.zach2039.oldguns.init.ModMenuTypes;
import com.zach2039.oldguns.init.ModNetwork;
import com.zach2039.oldguns.init.ModPotions;
import com.zach2039.oldguns.init.ModRecipeTypes;
import com.zach2039.oldguns.init.ModSoundEvents;
import com.zach2039.oldguns.init.ModSpawnPlacements;
import com.zach2039.oldguns.init.ModCauldronInteractions.OldGunsCauldronInteraction;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.simple.SimpleChannel;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(OldGuns.MODID)
public class OldGuns
{
    public static Logger LOGGER = LogManager.getLogger(OldGuns.MODID);
	
	public static final String MODID = "oldguns";
	public static final String NAME = "Old Guns Mod";

	public static final SimpleChannel NETWORK = ModNetwork.getNetworkChannel();

    public OldGuns() {
    	OldGunsConfig.register(ModLoadingContext.get());

		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);
		
		ModFluids.initialize(modEventBus);
		ModBlocks.initialize(modEventBus);
		ModItems.initialize(modEventBus);
		ModRecipeTypes.initialize(modEventBus);
		ModMenuTypes.initialize(modEventBus);
		ModAttributes.initialize(modEventBus);
		ModEntities.initialize(modEventBus);
		ModLootModifierCodecs.initialize(modEventBus);
		ModPotions.initialize(modEventBus);
		ModCrafting.Recipes.initialize(modEventBus);
		ModSoundEvents.initialize(modEventBus);
		ModBlockEntities.initialize(modEventBus);
		ModLootConditionTypes.initialize(modEventBus);
    }

	private void onCommonSetup(final FMLCommonSetupEvent event) {
    	LOGGER.warn("****************************************");
		LOGGER.warn("Random UUID: {}", UUID.randomUUID());
		LOGGER.warn("****************************************");

		event.enqueueWork(() -> {
			ModCrafting.Ingredients.register();
			ModCrafting.Brewing.register();
			ModLootTables.registerLootTables();
			ModSpawnPlacements.register();
			OldGunsCauldronInteraction.bootStrap();
		});
	}

	@SubscribeEvent
	public static void enqueue(final InterModEnqueueEvent event) {

	}

	public static void printDebug(String message) {
		if (OldGunsConfig.COMMON.printDebugMessages.get()) {
			OldGuns.LOGGER.info(message);
		} else {
			OldGuns.LOGGER.debug(message);
		}
	}
}
