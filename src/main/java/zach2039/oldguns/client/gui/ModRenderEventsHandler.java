package zach2039.oldguns.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.item.tools.ItemGunnersQuadrant;

@EventBusSubscriber(value = Side.CLIENT, modid = OldGuns.MODID)
public class ModRenderEventsHandler
{
	
	@SubscribeEvent
    public static void onRenderGui(RenderGameOverlayEvent.Pre event)
    {
		EntityPlayerSP thePlayer = Minecraft.getMinecraft().player;
		ScaledResolution scaled = new ScaledResolution(Minecraft.getMinecraft());
		int screenWidth = scaled.getScaledWidth();
		int screenHeight = scaled.getScaledHeight();
		int k1 = screenWidth / 2 - 91;
		
		if (event.getType() == RenderGameOverlayEvent.Pre.ElementType.TEXT) 
		{
			/* Render Gunner's Quadrant overlay. */		
			if (thePlayer.isSneaking() && thePlayer.getHeldItemMainhand().getItem() instanceof ItemGunnersQuadrant)
			{
				float playerPitch = -thePlayer.rotationPitch % 360;
				float playerYaw = Math.abs(thePlayer.rotationYaw % 360);
				String displayText = String.format("P: %6.2f, Y: %6.2f", playerPitch, playerYaw);
				Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(displayText, screenWidth/2 + 10, screenHeight/2 + 10, Integer.parseInt("FFFFFF", 16));
			}
		}
		
//		if (event.getType() == RenderGameOverlayEvent.Pre.ElementType.EXPERIENCE) 
//		{
//			if (thePlayer.getActiveItemStack().getItem() instanceof IFirearm)
//			{
//				GuiAimingBar guiAim =  new GuiAimingBar(Minecraft.getMinecraft());
//				
//				ItemStack firearmStack = thePlayer.getActiveItemStack();
//				if (FirearmNBTHelper.peekNBTTagAmmoCount(firearmStack) > 0) 
//				{
//					int ticksUsed = thePlayer.getItemInUseMaxCount();
//					float devMultiplier = ItemFirearm.getSnapshotDeviationMultiplier(ticksUsed);
//					guiAim.renderAimingBar(scaled, k1, devMultiplier);
//				}
//			}
//		}
    }
	
	
}
