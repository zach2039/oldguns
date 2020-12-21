package zach2039.oldguns.client.gui.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.inventory.ContainerMelter;
import zach2039.oldguns.common.tile.TileEntityMelter;

@SideOnly(Side.CLIENT)
public class GuiMelter extends GuiContainer 
{
	private static final ResourceLocation MELTER_GUI_TEXTURES = new ResourceLocation(OldGuns.MODID, "textures/gui/container/melter.png");

	/** The player inventory bound to this GUI. */
    private final InventoryPlayer playerInventory;
    private final TileEntityMelter tileEntityMelter;

    public GuiMelter(InventoryPlayer playerInv, TileEntityMelter tileEntityMelter)
    {
        super(new ContainerMelter(playerInv, tileEntityMelter));
        this.playerInventory = playerInv;
        this.tileEntityMelter = tileEntityMelter;
    }

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();

        super.drawScreen(mouseX, mouseY, partialTicks);

        this.renderHoveredToolTip(mouseX, mouseY);
    }
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
		String s = this.tileEntityMelter.getDisplayName().getUnformattedText();
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(MELTER_GUI_TEXTURES);
        int i = this.guiLeft;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
        
        if (this.tileEntityMelter.isBurning())
        {
            int k = this.getBurnLeftScaled(13);
            this.drawTexturedModalRect(i + 56, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);
        }

        int l = this.getCookProgressScaled(24);
        this.drawTexturedModalRect(i + 79, j + 34, 176, 14, l + 1, 16);
	}
	
	private int getCookProgressScaled(int pixels)
    {
        int i = this.tileEntityMelter.getField(2);
        int j = this.tileEntityMelter.getField(3);
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }

    private int getBurnLeftScaled(int pixels)
    {
        int i = this.tileEntityMelter.getField(1);

        if (i == 0)
        {
            i = 200;
        }

        return this.tileEntityMelter.getField(0) * pixels / i;
    }
}
