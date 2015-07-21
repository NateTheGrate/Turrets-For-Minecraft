package com.nate.turrets.items;

import com.nate.turrets.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by Nathanael on 7/18/2015.
 */
public class bulletItem extends Item {

    public bulletItem(String name, int maxStack){
        setMaxStackSize(maxStack);
        setUnlocalizedName(Reference.MOD_ID + "_" + name);
        setCreativeTab(CreativeTabs.tabAllSearch);
        setTextureName(Reference.MOD_ID + ":" + name);
    }

}
