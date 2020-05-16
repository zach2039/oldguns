package zach2039.oldguns.api.capability.casting;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import zach2039.oldguns.api.capability.DummyStorage;

public final class CapabilityCast
{
	@CapabilityInject(ICast.class)
    public static Capability<ICast> CAST_CAPABILITY;
	
	public static void preInit()
    {
        CapabilityManager.INSTANCE.register(ICast.class, new DummyStorage<>(), CastHandler::new);
    }
}
