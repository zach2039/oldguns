package zach2039.oldguns.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import zach2039.oldguns.common.OldGuns;

public class GuiAimingBar extends GuiIngame {

	public GuiAimingBar(Minecraft mcIn) {
		super(mcIn);
	}

	public void renderAimingBar(ScaledResolution scaledRes, int x, float multiplier)
    {
        this.mc.profiler.startSection("aimBar");
        this.mc.getTextureManager().bindTexture(Gui.ICONS);
        float f = multiplier;
        int i = 182;
        int j = (int)(f * 30.0F);
        int k = scaledRes.getScaledHeight() - 32 + 3;
        this.drawTexturedModalRect(x, k, 0, 64, 182, 5);

        if (j > 0)
        {
        	OldGuns.LOGGER.info("j : " + j);
        	this.drawTexturedModalRect(x + 64, k, 0, 69, j, 5);
        	this.drawTexturedModalRect(x - 64, k, 0, 69, -j, 5);
        }

        this.mc.profiler.endSection();
    }
}
