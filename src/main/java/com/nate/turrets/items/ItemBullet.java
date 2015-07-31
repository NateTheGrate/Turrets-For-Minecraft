package com.nate.turrets.items;

import com.nate.turrets.Reference;
import com.nate.turrets.init.ModObjects;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by Nathanael on 7/18/2015.
 */
public class ItemBullet extends Item {

    public ItemBullet(String name, int maxStack){
        setMaxStackSize(maxStack);
        setUnlocalizedName(Reference.MOD_ID + "_" + name);
        setCreativeTab(ModObjects.turretTabs);
        setTextureName(Reference.MOD_ID + ":" + name);
    }



}
