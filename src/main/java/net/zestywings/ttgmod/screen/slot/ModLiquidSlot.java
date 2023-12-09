package net.zestywings.ttgmod.screen.slot;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.Slot;

public class ModLiquidSlot extends Slot {
    public ModLiquidSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return( stack.isOf(Items.BUCKET) ||
                stack.isOf(Items.WATER_BUCKET) ||
                stack.isOf(Items.LAVA_BUCKET)  ||
                stack.isOf(Items.POWDER_SNOW_BUCKET) ||
                stack.isOf(Items.MILK_BUCKET)
        );
    }

    @Override
    public int getMaxItemCount(ItemStack stack) {
        return super.getMaxItemCount(stack);
    }

}