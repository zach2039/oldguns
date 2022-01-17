package com.zach2039.oldguns.client.gui.inventory;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.world.inventory.menu.GunsmithsBenchMenu;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GunsmithsBenchScreen extends AbstractContainerScreen<GunsmithsBenchMenu> {
	
	private static final ResourceLocation GUNSMITHS_BENCH_LOCATION = new ResourceLocation(OldGuns.MODID, "textures/gui/container/gunsmiths_bench.png");

	private boolean widthTooNarrow;

	public GunsmithsBenchScreen(GunsmithsBenchMenu p_98448_, Inventory p_98449_, Component p_98450_) {
		super(p_98448_, p_98449_, p_98450_);
	}

	protected void init() {
		super.init();
		this.widthTooNarrow = this.width < 379;
		this.titleLabelX = 29;
	}

	public void containerTick() {
		super.containerTick();
	}

	public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(matrixStack);
		super.render(matrixStack, mouseX, mouseY, partialTicks);
		this.renderTooltip(matrixStack, mouseX, mouseY);
	}

	protected void renderBg(PoseStack p_98474_, float p_98475_, int p_98476_, int p_98477_) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, GUNSMITHS_BENCH_LOCATION);
		int i = this.leftPos;
		int j = (this.height - this.imageHeight) / 2;
		this.blit(p_98474_, i, j, 0, 0, this.imageWidth, this.imageHeight);
	}

	protected boolean isHovering(int p_98462_, int p_98463_, int p_98464_, int p_98465_, double p_98466_, double p_98467_) {
		return (!this.widthTooNarrow) && super.isHovering(p_98462_, p_98463_, p_98464_, p_98465_, p_98466_, p_98467_);
	}

	public boolean mouseClicked(double p_98452_, double p_98453_, int p_98454_) {
		return this.widthTooNarrow ? true : super.mouseClicked(p_98452_, p_98453_, p_98454_);
	}

	protected boolean hasClickedOutside(double p_98456_, double p_98457_, int p_98458_, int p_98459_, int p_98460_) {
		boolean flag = p_98456_ < (double)p_98458_ || p_98457_ < (double)p_98459_ || p_98456_ >= (double)(p_98458_ + this.imageWidth) || p_98457_ >= (double)(p_98459_ + this.imageHeight);
		return flag;
	}

	protected void slotClicked(Slot p_98469_, int p_98470_, int p_98471_, ClickType p_98472_) {
		super.slotClicked(p_98469_, p_98470_, p_98471_, p_98472_);
	}

	public void recipesUpdated() {
	}

	public void removed() {
		super.removed();
	}
}
