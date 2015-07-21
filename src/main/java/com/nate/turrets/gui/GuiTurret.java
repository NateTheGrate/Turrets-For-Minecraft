package com.nate.turrets.gui;

import com.nate.turrets.Reference;
import com.nate.turrets.containers.TurretContainer;
import com.nate.turrets.tileentities.Turret;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

/**
 * Created by Nathanael on 7/14/2015.
 */
public class GuiTurret extends GuiContainer{

    public GuiTurret (InventoryPlayer inventoryPlayer, Turret tileEntity) {
        super(new TurretContainer(inventoryPlayer, tileEntity));
    }


    @Override
    protected void drawGuiContainerForegroundLayer(int param1, int param2) {
        this.mc.fontRenderer.drawString("Turret", 70, 6, 10752);
        this.mc.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
    }


    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {

        ResourceLocation path = new ResourceLocation( Reference.MOD_ID + ":textures/gui/TurretInventory.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
       this.mc.getTextureManager().bindTexture(path);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }
}
