package zach2039.oldguns.client.renderer.entity;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import zach2039.oldguns.client.renderer.block.RenderTileEntityStationaryCannon;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.entity.EntityArtilleryCannon;
import zach2039.oldguns.common.entity.EntityProjectile;
import zach2039.oldguns.common.tile.TileEntityStationaryCannon;

public class ModRenderers
{
	public static void registerRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityProjectile.class, renderManager -> new RenderEntityProjectile(renderManager, new ResourceLocation(OldGuns.MODID, "textures/entity/entity_projectile.png")));
		RenderingRegistry.registerEntityRenderingHandler(EntityArtilleryCannon.class, renderManager -> new RenderEntityArtilleryCannon(renderManager, new ResourceLocation(OldGuns.MODID, "textures/entity/entity_artillery_cannon.png")));
	}
	
	public static void registerBlockRenderers() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStationaryCannon.class, new RenderTileEntityStationaryCannon(new ResourceLocation(OldGuns.MODID, "textures/tile/stationary_cannon.png")));
	}
}
