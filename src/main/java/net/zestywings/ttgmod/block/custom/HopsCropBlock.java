package net.zestywings.ttgmod.block.custom;

import net.minecraft.block.*;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.zestywings.ttgmod.TTGMod;
import net.zestywings.ttgmod.block.ModBlocks;
import net.zestywings.ttgmod.item.ModItems;

public class HopsCropBlock extends CropBlock implements Fertilizable {

    public static final int FIRST_STAGE_MAX_AGE = 3;
    public static final int SECOND_STAGE_MAX_AGE = 4;

    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
            Block.createCuboidShape(0, 0, 0, 16, 2, 16),
            Block.createCuboidShape(0, 0, 0, 16, 10, 16),
            Block.createCuboidShape(0, 0, 0, 16, 13, 16),
            Block.createCuboidShape(0, 0, 0, 16, 16, 16),
            Block.createCuboidShape(0, 0, 0, 16, 4, 16),
            Block.createCuboidShape(0, 0, 0, 16, 13, 16),
            Block.createCuboidShape(0, 0, 0, 16, 16, 16),
            Block.createCuboidShape(0, 0, 0, 16, 17, 16)
    };

    public static final IntProperty AGE = IntProperty.of("age", 0,FIRST_STAGE_MAX_AGE + SECOND_STAGE_MAX_AGE);
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return AGE_TO_SHAPE[this.getAge(state)];
    }

    @Override
    public void applyGrowth(World world, BlockPos pos, BlockState state) {
        int nextAge = this.getAge(state) +1;
        int maxAge = this.getMaxAge();
        if(nextAge > maxAge) {
            nextAge = maxAge;
        }

        if(this.getAge(state) == FIRST_STAGE_MAX_AGE && world.getBlockState(pos.up(1)).isOf(Blocks.AIR)) {
            world.setBlockState(pos.up(1), this.withAge(nextAge), 2);
        }else if (this.getAge(state) == FIRST_STAGE_MAX_AGE && world.getBlockState(pos.up(1)).isOf(ModBlocks.HOPS_CROP_BLOCK)){
            world.setBlockState(pos.up(1), this.withAge(   Math.min(world.getBlockState(pos.up(1)).get(CropBlock.AGE)+1,this.getMaxAge())   ));
        } else {
            world.setBlockState(pos, this.withAge(nextAge), 2);
        }
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if(world.getBaseLightLevel(pos,0) >= 9){
            int currentAge = this.getAge(state);
            if(currentAge < this.getMaxAge()){
                float f = getAvailableMoisture(this, world, pos);
                if(random.nextInt((int)(25.0f / f)+1) == 0){
                    this.applyGrowth(world, pos, state);
                }
            }
        }
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return  (world.getBlockState(pos.down(1)).isOf(Blocks.FARMLAND) ) ||
                ((world.getBlockState(pos.down(1)).isOf(this)) && world.getBlockState(pos.down(1)).get(AGE) >= 4);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.HOPS_SEEDS;
    }


    @Override
    public int getMaxAge() {
        return FIRST_STAGE_MAX_AGE + SECOND_STAGE_MAX_AGE;
    }

    public HopsCropBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(AGE, 0));
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);

        world.spawnEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.HOPS_SEEDS,1)));
        if(state.get(AGE) == this.getMaxAge()){
            world.spawnEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.HOPS_ITEM,1)));
        }

    }

    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {

        /*
        World tempWorld = (World) world;
        world.spawnEntity(new ItemEntity(tempWorld, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.HOPS_SEEDS,1)));
        if(state.get(AGE) == this.getMaxAge()){
            world.spawnEntity(new ItemEntity(tempWorld, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.HOPS_ITEM,1)));
        }

         */

    }


    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if ((world.getBlockState(pos.down()).isOf(Blocks.FARMLAND) || world.getBlockState(pos.down()).isOf(this) )){
            return state;
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }



}
