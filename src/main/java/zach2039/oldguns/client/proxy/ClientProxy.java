package zach2039.oldguns.client.proxy;

import zach2039.oldguns.client.renderer.entity.ModRenderers;
import zach2039.oldguns.util.IProxy;

public class ClientProxy implements IProxy
{
	@Override
	public void preInit()
	{
		/* Register client stuff. */
		ModRenderers.registerRenderers();
	}

	@Override
	public void init()
	{
		
	}

	@Override
	public void postInit()
	{
		
	}
}
