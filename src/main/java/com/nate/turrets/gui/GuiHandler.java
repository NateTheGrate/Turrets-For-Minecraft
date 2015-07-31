package com.nate.turrets.gui;

import com.nate.turrets.containers.ContainerTurret;
import com.nate.turrets.tileentities.TileEntityTurret;
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
        if(tileEntity instanceof TileEntityTurret){
            return new ContainerTurret(player.inventory, (TileEntityTurret) tileEntity);
        }
        return null;
    }


    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if(tileEntity instanceof TileEntityTurret){
            return new GuiTurret(player.inventory, (TileEntityTurret) tileEntity);
        }
        return null;

    }

}
