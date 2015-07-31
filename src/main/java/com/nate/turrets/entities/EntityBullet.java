package com.nate.turrets.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
 * Created by Nathanael on 7/21/2015.
 */
public class EntityBullet extends EntityThrowable{


    public EntityBullet(World world){
        super(world);
    }

    public EntityBullet(World world, EntityLivingBase player){
        super(world, player);
    }

    public EntityBullet(World world, double x, double y, double z){
        super(world, x, y, z);
    }

    /**
     * called when bullet hits something
     * @param objectPosition
     */
    @Override
    public void onImpact(MovingObjectPosition objectPosition){

        if(objectPosition.entityHit != null){

            objectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower() ), 5);

        }

        if(!worldObj.isRemote){
            this.setDead();
        }

    }

}
