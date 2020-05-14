package zach2039.oldguns.client.gui.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.inventory.ContainerGunsmithsBench;

@SideOnly(Side.CLIENT)
public class GuiGunsmithsBench extends GuiContainer 
{
	private static final ResourceLocation GUNSMITHS_BENCH_GUI_TEXTURES = new ResourceLocation(OldGuns.MODID, "textures/gui/container/gunsmiths_bench.png");
	//private GuiButtonImage recipeButton;
	//private final GuiRecipeBook recipeBookGui;
	//private boolean widthTooNarrow;
	
	public GuiGunsmithsBench(InventoryPlayer playerInv, World worldIn)
	{
		this(playerInv, worldIn, BlockPos.ORIGIN);
	}
	
	public GuiGunsmithsBench(InventoryPlayer playerInv, World worldIn, BlockPos blockPos)
	{
		super(new ContainerGunsmithsBench(playerInv, worldIn, blockPos));
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
        this.fontRenderer.drawString(I18n.format("container.gunsmiths_bench"), 28, 6, 4210752);
        this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(GUNSMITHS_BENCH_GUI_TEXTURES);
        int i = this.guiLeft;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
	}
}
