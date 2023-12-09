package net.zestywings.ttgmod.screen;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.zestywings.ttgmod.block.entity.BrewKegBlockEntity;
import net.zestywings.ttgmod.screen.slot.ModContainerSlot;
import net.zestywings.ttgmod.screen.slot.ModLiquidSlot;
import net.zestywings.ttgmod.screen.slot.ModResultSlot;



public class BrewKegScreenHandler extends ScreenHandler {

    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    public final BrewKegBlockEntity blockEntity;


    public BrewKegScreenHandler(int syncId, PlayerInventory inventory, PacketByteBuf buf){
        this(syncId, inventory, inventory.player.getWorld().getBlockEntity(buf.readBlockPos()),
                new ArrayPropertyDelegate(5));
    }

    public BrewKegScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity, PropertyDelegate arrayPropertyDelegate){
        super(ModScreenHandlers.BREW_KEG_SCREEN_HANDLER, syncId);
        checkSize((Inventory) blockEntity, 7);
        this.inventory = ((Inventory) blockEntity);
        inventory.onOpen(playerInventory.player);
        this.propertyDelegate = arrayPropertyDelegate;
        this.blockEntity = ((BrewKegBlockEntity) blockEntity);

        this.addSlot(new Slot(inventory, 0,36,17));
        this.addSlot(new Slot(inventory, 1,54,17));
        this.addSlot(new Slot(inventory, 2,36,35));
        this.addSlot(new Slot(inventory, 3,54,35));
        this.addSlot(new ModLiquidSlot(inventory, 4,45,58));
        this.addSlot(new ModContainerSlot(inventory, 5,94,58));
        this.addSlot(new ModResultSlot(inventory, 6,137,58));

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addProperties(arrayPropertyDelegate);


    }

    private void addPlayerInventory(PlayerInventory playerInventory){
        for(int i=0;i<3;i++) { //rows
            for (int l = 0; l < 9; l++) {//columns
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 86 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for(int i=0;i<9;i++){
            this.addSlot(new Slot(playerInventory, i, 8+i*18, 144));
        }
    }




    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if(slot != null && slot.hasStack()){
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if(invSlot < this.inventory.size()){
                if(!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)){
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()){
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    public boolean isCrafting(){
        return propertyDelegate.get(0) > 0;
    }
    public boolean isEmpty(){
        return propertyDelegate.get(2) == 0;
    }

    public int getScaledProgress() {
        int progress = this.propertyDelegate.get(0);
        int maxProgress = this.propertyDelegate.get(1);
        int progressBarSize = 62;

        return maxProgress != 0 && progress != 0 ? progress * progressBarSize / maxProgress : 0;
    }

        public int getScaledFullness(){
            int fluidUnits = this.propertyDelegate.get(2);
            int maxFluid = this.propertyDelegate.get(3);
            int fullBarSize = 62;

            return maxFluid !=0 && fluidUnits != 0 ? fluidUnits * fullBarSize / maxFluid : 0;
    }



    public int getTicksLeft(){
        return this.propertyDelegate.get(1) - this.propertyDelegate.get(0);
    }






    /*
    public BrewKegScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate delegate) {
        super(ModScreenHandlers.BREW_KEG_SCREEN_HANDLER, syncId);
        checkSize(inventory,7);
        this.inventory = inventory;
        inventory.onOpen(playerInventory.player);
        this.propertyDelegate = delegate;

        this.addSlot(new Slot(inventory, 0,36,17));
        this.addSlot(new Slot(inventory, 1,54,17));
        this.addSlot(new Slot(inventory, 2,36,35));
        this.addSlot(new Slot(inventory, 3,54,35));
        this.addSlot(new ModLiquidSlot(inventory, 4,45,58));
        this.addSlot(new ModContainerSlot(inventory, 5,94,58));
        this.addSlot(new ModResultSlot(inventory, 6,137,58));

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addProperties(delegate);

    }










    public ItemStack transferSlot(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);

        if(slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if(invSlot < this.inventory.size()) {
                if(!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            }else if(!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if(originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            }else {
                slot.markDirty();
            }
        }

        return newStack;
    }



     */

}
