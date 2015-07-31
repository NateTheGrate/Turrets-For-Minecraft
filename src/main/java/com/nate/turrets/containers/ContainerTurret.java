package com.nate.turrets.containers;

import com.nate.turrets.tileentities.TileEntityTurret;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Nathanael on 7/14/2015.
 */
public class ContainerTurret extends Container {

    private TileEntityTurret tileEntity;

    public ContainerTurret(InventoryPlayer playerInventory, TileEntityTurret turret){
        //adds turret inventory to gui
        tileEntity= turret;
        addSlotToContainer(new Slot(tileEntity, 0, 80, 35));
        bindPlayerInventory(playerInventory);

    }

    /**
     * adds player inventory to gui
     * @param inventoryPlayer
     */
    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
        }
    }

    public boolean canInteractWith(EntityPlayer player)
    {
        return this.tileEntity.isUseableByPlayer(player);
    }


    /**
     * Called when a player shift-clicks on a slot. must override this or you will crash when someone does that.
     * @param player
     * @param slot
     * @return updated stack
     */
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
        ItemStack stack = null;
        Slot slotObject = (Slot) inventorySlots.get(slot);

        //null checks and checks if the item can be stacked (maxStackSize > 1)
        if (slotObject != null && slotObject.getHasStack()) {
            ItemStack stackInSlot = slotObject.getStack();
            stack = stackInSlot.copy();

            //merges the item into player inventory since its in the tileEntity
            if (slot < tileEntity.getSizeInventory()) {
                if (!this.mergeItemStack(stackInSlot, tileEntity.getSizeInventory(), 36 + tileEntity.getSizeInventory(), true)) {
                    return null;
                }
            }
            //places it into the tileEntity is possible since its in the player inventory
            else if (!this.mergeItemStack(stackInSlot, 0, tileEntity.getSizeInventory(), false)) {
                return null;
            }

            if (stackInSlot.stackSize == 0) {
                slotObject.putStack(null);
            } else {
                slotObject.onSlotChanged();
            }

            if (stackInSlot.stackSize == stack.stackSize) {
                return null;
            }
            slotObject.onPickupFromSlot(player, stackInSlot);
        }
        return stack;
    }
}
