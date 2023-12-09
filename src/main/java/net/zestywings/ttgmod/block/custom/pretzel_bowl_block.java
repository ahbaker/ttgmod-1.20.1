package net.zestywings.ttgmod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.zestywings.ttgmod.item.ModItems;
import org.jetbrains.annotations.Nullable;


public class pretzel_bowl_block extends Block {

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public static final IntProperty BOWL_STATE = IntProperty.of("bowl_state", 0,8);
    public static final int DEFAULT_COMPARATOR_OUTPUT = pretzel_bowl_block.getComparatorOutput(0);


    public pretzel_bowl_block(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(BOWL_STATE, 8));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING).add(BOWL_STATE);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerLookDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    private static final VoxelShape BOWL_SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 3.0, 12.0);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return BOWL_SHAPE;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        if (player.getStackInHand(hand) != ItemStack.EMPTY) {
            return ActionResult.PASS;
        }
        if (!world.isClient && hand == Hand.MAIN_HAND && player.canConsume((true))) {
            int i = state.get(BOWL_STATE);
            if(i <= 0){
                return ActionResult.PASS;
            }else{
                player.getHungerManager().add(1, 0.1f);
                world.emitGameEvent((Entity)player, GameEvent.EAT, pos );
                if(Math.random()>.95){
                    world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS,1f,1f);
                }else {
                    world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_GENERIC_EAT, SoundCategory.PLAYERS, 1f, 1f);
                }
                world.setBlockState(pos, state.with(BOWL_STATE, i - 1), Block.NOTIFY_ALL);

                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS;
    }


    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return pretzel_bowl_block.getComparatorOutput(state.get(BOWL_STATE));
    }

    public static int getComparatorOutput(int pretzels) {
        if (pretzels == 8) {
            return 15;
        } else {
            return (2*pretzels);
        }
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return true;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {

        if (!world.isClient) {
            if (state.isOf(newState.getBlock())) {
                return;
            }
            ItemStack bowl = new ItemStack(Items.BOWL, 1);
            ItemStack pretzel = new ItemStack(ModItems.PRETZEL, state.get(BOWL_STATE));
            dropStack(world, pos, pretzel);
            dropStack(world, pos, bowl);
        }
    }
}




