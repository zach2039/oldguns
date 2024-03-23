package com.zach2039.oldguns;

import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.init.*;
import com.zach2039.oldguns.init.ModCauldronInteractions.OldGunsCauldronInteraction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.event.lifecycle.InterModEnqueueEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(OldGuns.MODID)
public class OldGuns
{
    public static Logger LOGGER = LogManager.getLogger(OldGuns.MODID);
	
	public static final String MODID = "oldguns";
	public static final String NAME = "Old Guns Mod";

	// No more SimpleChannel with NeoForge :(
	//public static final SimpleChannel NETWORK = ModNetwork.getNetworkChannel();

	@SuppressWarnings("depreciated")
    public OldGuns(IEventBus modEventBus) {
    	OldGunsConfig.register(ModLoadingContext.get());

		modEventBus.addListener(this::onCommonSetup);
		
		ModFluids.initialize(modEventBus);
		ModBlocks.initialize(modEventBus);
		ModItems.initialize(modEventBus);
		ModRecipeTypes.initialize(modEventBus);
		ModMenuTypes.initialize(modEventBus);
		ModAttributes.initialize(modEventBus);
		ModEntities.initialize(modEventBus);
		ModLootModifierCodecs.initialize(modEventBus);
		ModPotions.initialize(modEventBus);
		ModConditions.initialize(modEventBus);
		ModCrafting.Recipes.initialize(modEventBus);
		ModSoundEvents.initialize(modEventBus);
		ModBlockEntities.initialize(modEventBus);
		ModLootConditionTypes.initialize(modEventBus);
		ModCreativeTabs.initialize(modEventBus);
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
