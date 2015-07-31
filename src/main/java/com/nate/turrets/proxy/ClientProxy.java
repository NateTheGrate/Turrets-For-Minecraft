package com.nate.turrets.proxy;

import com.nate.turrets.render.RenderBasicTurret;
import com.nate.turrets.tileentities.TileEntityTurret;
import cpw.mods.fml.client.registry.ClientRegistry;

/**
 * Created by Nathanael on 7/6/2015.
 */
public class ClientProxy extends CommonProxy{

    @Override
    public void registerRenderThings() {

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTurret.class, new RenderBasicTurret(":textures/models/BasicTurret.png"));

    }

}
