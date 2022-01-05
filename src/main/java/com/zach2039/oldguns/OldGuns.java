package com.zach2039.oldguns;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.init.ModCrafting;
import com.zach2039.oldguns.init.ModEntities;
import com.zach2039.oldguns.init.ModItems;
import com.zach2039.oldguns.init.ModNetwork;
import com.zach2039.oldguns.init.ModSoundEvents;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.simple.SimpleChannel;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(OldGuns.MODID)
public class OldGuns
{
	// Directly reference a log4j logger.
    public static Logger LOGGER;
	
	public static final String MODID = "oldguns";
	public static final String NAME = "Old Guns Mod";

	public static final OldGunsCreativeModeTab CREATIVE_MODE_TAB = new OldGunsCreativeModeTab();

	public static final SimpleChannel network = ModNetwork.getNetworkChannel();

    public OldGuns() {
    	LOGGER = LogManager.getLogger(OldGuns.MODID);
    			 
    	OldGunsConfig.register(ModLoadingContext.get());

		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		ModItems.initialize(modEventBus);
		ModEntities.initialize(modEventBus);
		ModCrafting.Recipes.initialize(modEventBus);
		ModSoundEvents.initialize(modEventBus);
    }

    @SubscribeEvent
	public static void commonSetup(final FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			ModCrafting.Ingredients.register();
		});
	}

	@SubscribeEvent
	public static void enqueue(final InterModEnqueueEvent event) {

	}
}
