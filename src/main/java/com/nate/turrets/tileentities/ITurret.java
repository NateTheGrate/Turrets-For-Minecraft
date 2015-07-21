package com.nate.turrets.tileentities;

import net.minecraft.entity.monster.EntityMob;

import java.util.ArrayList;

/**
 * Created by Nathanael on 7/6/2015.
 */
public interface ITurret {

    public void shoot();

    public void target();

    public boolean usesAmmo();


}
