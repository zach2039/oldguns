package zach2039.oldguns.common;

import org.apache.logging.log4j.Logger;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import zach2039.oldguns.api.capability.casting.CapabilityCast;
import zach2039.oldguns.client.gui.ModGuiHandler;
import zach2039.oldguns.common.init.ModMessages;
import zach2039.oldguns.common.init.ModRecipes;
import zach2039.oldguns.common.init.ModSoundEvents;
import zach2039.oldguns.common.item.OldGunsCreativeTab;
import zach2039.oldguns.util.IProxy;

@Mod(
		modid = OldGuns.MODID,
		name = OldGuns.MODNAME,
		version = OldGuns.MODVERSION,
		acceptedMinecraftVersions = "[1.12.2]",
		dependencies = "required-after:forge@[14.23.5.2859,);required:patchouli@[10-23.6,);optional:jei@[10-23.6,);"
	)
public class OldGuns
{
    public static final String MODID = "oldguns";
    public static final String MODNAME = "Old Guns";
    public static final String MODVERSION = "@VERSION@";
    
    public static Logger LOGGER;
    
    @SidedProxy(clientSide = "zach2039.oldguns.client.proxy.ClientProxy", serverSide = "zach2039.oldguns.server.proxy.ServerProxy")
	public static IProxy proxy;
    
    @Instance(OldGuns.MODID)
	public static OldGuns instance;

    public static SimpleNetworkWrapper network;
    
    public static final CreativeTabs OLDGUNS_CREATIVE_TAB = new OldGunsCreativeTab();
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        LOGGER = event.getModLog();
        
        LOGGER.info("preInit()");
        
        /* PreInit sounds. */
        ModSoundEvents.RegistrationHandler.initializeSoundEvents();
   
        /* PreInit capabilities. */
        CapabilityCast.preInit();
        
        /* Call sided proxy. */
        proxy.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	LOGGER.info("init()");
        
    	/* Sync configuration settings. */
    	ConfigManager.sync(OldGuns.MODID, Type.INSTANCE);
    	
        /* Get network channel for mod. */
        network = NetworkRegistry.INSTANCE.newSimpleChannel(OldGuns.MODID);
        
        /* Register network messages. */
        ModMessages.registerMessages(network);
        
        /* Register Gui handler. */
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new ModGuiHandler());
        
        /* Register ore dictionary entries. */
        ModRecipes.registerOreDictEntries();
        
        /* Call sided proxy. */
        proxy.init();
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        LOGGER.info("postInit()");
        
        /* Call sided proxy. */
        proxy.postInit();
    }
    
    @SubscribeEvent
    public void onConfigChangedEvent(OnConfigChangedEvent event)
    {
        if (event.getModID().equals(OldGuns.MODID))
        {
            ConfigManager.sync(OldGuns.MODID, Type.INSTANCE);
        }
    }
}
