package com.nate.turrets.init;

import com.nate.turrets.blocks.BlockTurret;
import com.nate.turrets.gui.GuiHandler;
import com.nate.turrets.items.ItemBullet;
import com.nate.turrets.tileentities.TileEntityTurret;
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

    public static BlockTurret turretBlock = new BlockTurret("basicTurret");

    public static ItemBullet bullet = new ItemBullet("bullet", 64);

    public static void init(){

        GameRegistry.registerBlock(turretBlock, "basicTurret");

        GameRegistry.registerTileEntity(TileEntityTurret.class, "Turret");

        GameRegistry.registerItem(bullet, "bullet");

        NetworkRegistry.INSTANCE.registerGuiHandler(turrets.instance, new GuiHandler() );
    }

}
