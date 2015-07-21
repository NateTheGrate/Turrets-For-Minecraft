package com.nate.turrets.blocks;


import com.nate.turrets.Reference;
import com.nate.turrets.init.ModObjects;
import com.nate.turrets.tileentities.Turret;
import com.nate.turrets.turrets;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * Created by Nathanael on 7/13/2015.
 */
public class TurretBlock extends BlockContainer{

    public TurretBlock(String name){
        super(Material.iron);
        super.setCreativeTab(ModObjects.turretTabs);
        setBlockName(Reference.MOD_ID + "_" + name);
        setBlockTextureName(Reference.MOD_ID + ":" + name);
    }


    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float i, float d, float k) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity == null || player.isSneaking()) {
            return false;
        }
        player.openGui(turrets.instance, 0, world, x, y, z);
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int n) {
        return new Turret();
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack)
    {
        if (entity == null)
        {
            return;
        }
        Turret tile = (Turret) world.getTileEntity(x, y, z);
        double direction = MathHelper.floor_double((double) (entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        if(direction * 90 + 180 == 270 || direction * 90 + 180 == 450){
            tile.initialDirection = direction * 90;
        }else{
            tile.initialDirection = direction * 90 + 180;
        }
    }


}
