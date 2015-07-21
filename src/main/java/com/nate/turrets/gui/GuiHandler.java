package com.nate.turrets.gui;

import com.nate.turrets.containers.TurretContainer;
import com.nate.turrets.tileentities.Turret;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Nathanael on 7/14/2015.
 */
public class GuiHandler implements IGuiHandler{

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if(tileEntity instanceof Turret){
            return new TurretContainer(player.inventory, (Turret) tileEntity);
        }
        return null;
    }


    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if(tileEntity instanceof Turret){
            return new GuiTurret(player.inventory, (Turret) tileEntity);
        }
        return null;

    }

}
