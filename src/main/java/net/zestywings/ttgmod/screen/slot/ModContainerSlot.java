package net.zestywings.ttgmod.screen.slot;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.zestywings.ttgmod.item.ModItems;


public class ModContainerSlot extends Slot {
    public ModContainerSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return(stack.isOf(ModItems.EMPTY_WOOD_MUG) || stack.isOf(ModItems.EMPTY_GLASS_PINT));
    }

    @Override
    public int getMaxItemCount(ItemStack stack) {
        return super.getMaxItemCount(stack);
    }

}
