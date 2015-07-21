package com.nate.turrets.init;

import com.nate.turrets.blocks.TurretBlock;
import com.nate.turrets.gui.GuiHandler;
import com.nate.turrets.tileentities.Turret;
import com.nate.turrets.turrets;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * Created by Nathanael on 7/14/2015.
 */
public class ModObjects {

    public static CreativeTabs turretTabs = new CreativeTabs("Turrets") {
        @Override
        public Item getTabIconItem() {
            return Items.cake;
        }
    };

    public static TurretBlock turretBlock = new TurretBlock("Basic Turret");

    public static void init(){

        GameRegistry.registerBlock(turretBlock, "Basic Turret");

        GameRegistry.registerTileEntity(Turret.class, "Turret");

        NetworkRegistry.INSTANCE.registerGuiHandler(turrets.instance, new GuiHandler() );
    }

}
