package com.nate.turrets.render;


import com.nate.turrets.Reference;
import com.nate.turrets.models.ModelBasicTurret;
import com.nate.turrets.tileentities.TileEntityTurret;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

/**
 * Created by Nathanael on 7/17/2015.
 *
 */
public class RenderBasicTurret extends TileEntitySpecialRenderer{


    private final ModelBasicTurret model ;

    private String resourceLoctation;

    public RenderBasicTurret(String url) {
        model = new ModelBasicTurret();

        resourceLoctation = Reference.MOD_ID + url;
    }


    private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        GL11.glPushMatrix();
        GL11.glRotatef(meta * (-90), 0.0F, 0.0F, 1.0F);
        GL11.glPopMatrix();
    }


    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
        //The PushMatrix tells the renderer to "start" doing something.
        GL11.glPushMatrix();
        //This is setting the initial location.
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);

        ResourceLocation textures = (new ResourceLocation(resourceLoctation));

        Minecraft.getMinecraft().renderEngine.bindTexture(textures);

        //Handles Rotation
        TileEntityTurret entity = (TileEntityTurret) te;
        double initialDirection = entity.initialDirection;
        GL11.glRotatef((int)initialDirection, 0.0F, 1.0F, 0.0F);
        model.rotateTop(entity.rotateX, entity.rotateY, entity.rotateZ);
        //makes sure that it doesn't render upsidedown
        GL11.glPushMatrix();
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);

        model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
        GL11.glPopMatrix();


    }

    private void adjustLightFixture(World world, int i, int j, int k, Block block) {
        Tessellator tess = Tessellator.instance;

        float brightness = block.getLightValue(world, i, j, k);
        int skyLight = world.getLightBrightnessForSkyBlocks(i, j, k, 0);
        int modulousModifier = skyLight % 65536;
        int divModifier = skyLight / 65536;
        tess.setColorOpaque_F(brightness, brightness, brightness);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) modulousModifier, divModifier);
    }

}
