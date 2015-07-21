package com.nate.turrets.tileentities;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;

import java.util.ArrayList;

/**
 * Created by Nathanael on 7/6/2015.
 */
public class Turret extends TileEntity implements ITurret, IInventory{

    public ItemStack[] inventory;

    public double initialDirection;
    public float rotateX;
    public float rotateY;
    public float rotateZ;
    private int tick;

    public Turret(){
        inventory = new ItemStack[1];
        tick = 0;
    }

    @Override
    public void shoot(){

    }

    @Override
    public void target(){
        ArrayList<EntityMob> mobs = enemiesInRange(10);
        for(EntityMob mob : mobs){
            if (mob.equals(closestMob(mobs))) {
                float angleY = (float)Math.toDegrees(Math.atan2(xCoord - mob.posX, this.zCoord - mob.posZ) );
                rotateY = -angleY / 90;

            }
        }

    }

    @Override
    public boolean usesAmmo(){
        return true;
    }



    @Override
    public int getSizeInventory() {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return null;
    }

    @Override
    public ItemStack decrStackSize(int slot, int amt) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            if (stack.stackSize <= amt) {
                setInventorySlotContents(slot, null);
            } else {
                stack = stack.splitStack(amt);
                if (stack.stackSize == 0) {
                    setInventorySlotContents(slot, null);
                }
            }
        }
        return stack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            setInventorySlotContents(slot, null);
        }
        return stack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack item) {
        inventory[slot] = item;
        if (item != null && item.stackSize > getInventoryStackLimit()) {
            item.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName() {
        return null;
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack item) {
        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        NBTTagList tagList = compound.getTagList("Inventory", compound.getId() );
        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound tag = tagList.getCompoundTagAt(i);
            byte slot = tag.getByte("Slot");
            if (slot >= 0 && slot < inventory.length) {
                inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tagcompound)
    {
        NBTTagList itemList = new NBTTagList();
        for (int i = 0; i < inventory.length; i++) {
            ItemStack stack = inventory[i];
            if (stack != null) {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("Slot", (byte) i);
                stack.writeToNBT(tag);
                itemList.appendTag(tag);
            }
        }
        tagcompound.setTag("Inventory", itemList);
    }

    /**
     * @param range in blocks
     * @return An ArrayList of enemies within the given range
     */
    public ArrayList<EntityMob> enemiesInRange(int range){
        return (ArrayList<EntityMob>) this.worldObj.getEntitiesWithinAABB(EntityMob.class, AxisAlignedBB.getBoundingBox(this.xCoord - range, this.yCoord - range, this.zCoord - range, this.xCoord + range, this.yCoord + range, this.zCoord + range));
    }

    /**
     * @param mobs
     * @return the closest mob in a given arraylist
     */
    public EntityMob closestMob(ArrayList<EntityMob> mobs){
        EntityMob closest = mobs.get(0);
        for(EntityMob mob : mobs){
            if(distanceToEntityMob(mob) < distanceToEntityMob(closest) ){
                closest = mob;
            }
        }
        return closest;
    }

    /**
     * @param mob
     * @return the distance between this and a given mob
     */
    public float distanceToEntityMob(EntityMob mob ){
        float f = (float)(this.xCoord - mob.posX);
        float f1 = (float)(this.yCoord - mob.posY);
        float f2 = (float)(this.zCoord - mob.posZ);
        return MathHelper.sqrt_float(f * f + f1 * f1 + f2 * f2);
    }

    @Override
    public void updateEntity(){

        target();
        tick++;
    }


}
