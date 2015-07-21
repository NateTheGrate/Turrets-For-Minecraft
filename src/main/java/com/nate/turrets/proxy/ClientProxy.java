package com.nate.turrets.proxy;

import com.nate.turrets.render.BasicTurretRender;
import com.nate.turrets.tileentities.Turret;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

/**
 * Created by Nathanael on 7/6/2015.
 */
public class ClientProxy extends CommonProxy{

    @Override
    public void registerRenderThings() {

        ClientRegistry.bindTileEntitySpecialRenderer(Turret.class, new BasicTurretRender(":textures/models/BasicTurret.png"));

    }

}
