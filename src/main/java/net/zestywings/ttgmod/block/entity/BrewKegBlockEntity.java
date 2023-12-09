package net.zestywings.ttgmod.block.entity;


import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import net.zestywings.ttgmod.TTGMod;
import net.zestywings.ttgmod.recipe.BrewKegRecipe;
import net.zestywings.ttgmod.screen.BrewKegScreenHandler;

import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class BrewKegBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {

    //update this to correct shape later
    private static final VoxelShape SHAPE = Block.createCuboidShape(0,0,0,16,16,16);

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(7, ItemStack.EMPTY);
    private final DefaultedList<ItemStack> storageInv = DefaultedList.ofSize(7,ItemStack.EMPTY);

    private static final int LIQUID_SLOT = 4;
    private static final int CONTAINER_SLOT = 5;
    private static final int OUTPUT_SLOT = 6;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 864000;
    private int tankUnits = 0;
    private int fullTankUnits = 0;
    private int outputNum = 0;
    private ItemStack outputItem;
    private ItemStack container;

    private List<BrewKegRecipe> matches;

    public BrewKegBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BREW_KEG_ENTITY, pos, state);

        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> BrewKegBlockEntity.this.progress;
                    case 1 -> BrewKegBlockEntity.this.maxProgress;
                    case 2 -> BrewKegBlockEntity.this.tankUnits;
                    case 3 -> BrewKegBlockEntity.this.fullTankUnits;
                    case 4 -> BrewKegBlockEntity.this.outputNum;
                    default -> 0;
                };
            }
            @Override
            public void set(int index, int value) {
                switch (index){
                    case 0 -> BrewKegBlockEntity.this.progress = value;
                    case 1 -> BrewKegBlockEntity.this.maxProgress = value;
                    case 2 -> BrewKegBlockEntity.this.tankUnits = value;
                    case 3 -> BrewKegBlockEntity.this.fullTankUnits = value;
                    case 4 -> BrewKegBlockEntity.this.outputNum = value;
                }

            }
            @Override
            public int size() {
                return 5;
            }
        };

    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Brewing Keg");
    }

    public void setInventory(DefaultedList<ItemStack> inventory){
        for(int i=0; i < inventory.size(); i++){
            this.inventory.set(i, inventory.get(i));
        }
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new BrewKegScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);

        DefaultedList<ItemStack> inv = DefaultedList.ofSize(17, ItemStack.EMPTY);
        for(int i=0; i < 7; i++){
            inv.set(i, inventory.get(i));
        }
        inv.set(7, ItemStack.EMPTY);
        inv.set(8, ItemStack.EMPTY);
        inv.set(9, ItemStack.EMPTY);
        for(int i=10; i < 17; i++){
            inv.set(i, storageInv.get(i-10));
        }

        Inventories.writeNbt(nbt, inv);
        nbt.putInt("brew_keg.progress", progress);
        nbt.putInt("fullTankUnits", fullTankUnits);
        nbt.putInt("tankUnits", tankUnits);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        DefaultedList<ItemStack> inv = DefaultedList.ofSize(17, ItemStack.EMPTY);
        Inventories.readNbt(nbt, inv);
        for(int i=0; i < 7; i++){
            inventory.set(i, inv.get(i));
        }
        for(int i=10; i < 17; i++){
            storageInv.set(i-10, inv.get(i));
        }
        progress = nbt.getInt("brew_keg.progress");
        fullTankUnits = nbt.getInt("fullTankUnits");
        tankUnits = nbt.getInt("tankUnits");

    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }


    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf){
        buf.writeBlockPos(this.pos);
    }


    public static void tick(World world, BlockPos blockPos, BlockState state, BrewKegBlockEntity entity) {
        if (entity.tankUnits == 0) {
            if (hasRecipe(entity, entity.inventory) ){
                entity.progress++;
                if(entity.progress >= entity.maxProgress){
                    storeInventory(entity, state);
                    fillTank(entity);
                    entity.resetProgress();
                }
            }
        }
        else { /*tankUnits != 0 */
            if(hasValidContainer(entity)) {
                tryFillContainer(entity, state);
            }
        }
        markDirty(world, blockPos, state);
    }


    //NOT DONE
    private void craftItem() {
        this.removeStack(0,1);
        this.removeStack(1,1);
        this.removeStack(2,1);
        this.removeStack(3,1);


        this.removeStack(LIQUID_SLOT,1);
        if(this.getStack(LIQUID_SLOT).isEmpty()) {
            this.setStack(LIQUID_SLOT, new ItemStack(Items.BUCKET, 1));
        }


    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static boolean hasRecipe(BrewKegBlockEntity entity, DefaultedList<ItemStack> checkInv) {
        SimpleInventory inventory = new SimpleInventory(checkInv.size());
        for (int i = 0; i < inventory.size(); i++) {
            inventory.setStack(i, checkInv.get(i));
        }
        if (Objects.requireNonNull(entity.world).getRecipeManager() != null) {
            List<BrewKegRecipe> matchList = entity.getWorld().getRecipeManager().getAllMatches(BrewKegRecipe.Type.INSTANCE, inventory, entity.getWorld());

            if (!matchList.isEmpty()) {
                entity.maxProgress = matchList.get(0).getTime();
                entity.fullTankUnits = matchList.get(0).getAmount();
            }
            return !matchList.isEmpty();
        }
        return false;
    }


    private static void storeInventory(BrewKegBlockEntity entity, BlockState state){
        for(int i = 0; i < 5; i++){
            entity.storageInv.set(i, new ItemStack(entity.inventory.get(i).getItem(),1));
            entity.inventory.get(i).decrement(1);
        }
        markDirty(Objects.requireNonNull(entity.getWorld()), entity.getPos(), state);
    }

    private static void fillTank(BrewKegBlockEntity entity){
        entity.tankUnits = entity.fullTankUnits;
    }

    //checks if the stored inventory, which represents the tank's contents, is matching the supplied container
    private static boolean hasValidContainer(BrewKegBlockEntity entity) {
        boolean isValidContainer = false;
        SimpleInventory inv = new SimpleInventory(entity.storageInv.size());
        for (int i = 0; i < inv.size(); i++) {
            inv.setStack(i, entity.storageInv.get(i));
        }

        if (Objects.requireNonNull(entity.getWorld()).getRecipeManager() != null) {
            List<BrewKegRecipe> matchList = Objects.requireNonNull(entity.world).getRecipeManager().getAllMatches(BrewKegRecipe.Type.INSTANCE, inv, entity.getWorld());

            if (!matchList.isEmpty()) {

                for (int i = 0; i < matchList.size(); i++) {
                    if (entity.inventory.get(CONTAINER_SLOT).getItem().equals(matchList.get(i).getContainer().getItem())) {
                        isValidContainer = true;
                        entity.container = matchList.get(i).getContainer();
                        entity.outputItem = matchList.get(i).getOutput((DynamicRegistryManager) entity.getWorld().getRecipeManager());
                    }
                }
            }
        }
        return isValidContainer;
    }

    private static void tryFillContainer(BrewKegBlockEntity entity, BlockState state){
        if(hasValidContainer(entity)){
            if(entity.tankUnits !=0) {
                if (entity.inventory.get(6) == entity.outputItem && entity.inventory.get(6).getCount() < entity.inventory.get(6).getItem().getMaxCount()) {
                    entity.inventory.get(6).increment(1);
                    entity.inventory.get(5).decrement(1);
                    entity.tankUnits--;
                } else if (entity.inventory.get(6).isEmpty()) {
                    entity.inventory.set(6, entity.outputItem);
                    entity.inventory.get(5).decrement(1);
                    entity.tankUnits--;
                }
            }
            if(entity.tankUnits == 0){
                Collections.fill(entity.storageInv, ItemStack.EMPTY);
            }
        }
        markDirty(Objects.requireNonNull(entity.world), entity.getPos(), state);
    }




}

