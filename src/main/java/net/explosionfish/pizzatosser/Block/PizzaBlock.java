package net.explosionfish.pizzatosser.Block;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;

public class PizzaBlock extends CakeBlock {
    private final Integer hunger;
    protected static final VoxelShape[] BITES_TO_SHAPE;

    public PizzaBlock(Settings settings, Integer hunger) {
        super(settings);
        this.hunger = hunger;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);

        if (world.isClient) {
            if (tryEat(world, pos, state, player, this.hunger).isAccepted()) {
                return ActionResult.SUCCESS;
            }

            if (itemStack.isEmpty()) {
                return ActionResult.CONSUME;
            }
        }

        return tryEat(world, pos, state, player, this.hunger);
    }

    protected static ActionResult tryEat(WorldAccess world, BlockPos pos, BlockState state, PlayerEntity player, Integer hunger) {
        if (!player.canConsume(false)) {
            return ActionResult.PASS;
        } else {
            player.getHungerManager().add(hunger, 0.6f);
            int i = (Integer)state.get(BITES);
            world.emitGameEvent(player, GameEvent.EAT, pos);
            if (i < 6) {
                world.setBlockState(pos, (BlockState)state.with(BITES, i + 1), 3);
            } else {
                world.removeBlock(pos, false);
                world.emitGameEvent(player, GameEvent.BLOCK_DESTROY, pos);
            }

            return ActionResult.SUCCESS;
        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return BITES_TO_SHAPE[(Integer)state.get(BITES)];
    }

    static {
        BITES_TO_SHAPE = new VoxelShape[]{Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 3.0, 15.0), Block.createCuboidShape(3.0, 0.0, 1.0, 15.0, 3.0, 15.0), Block.createCuboidShape(5.0, 0.0, 1.0, 15.0, 3.0, 15.0), Block.createCuboidShape(7.0, 0.0, 1.0, 15.0, 3.0, 15.0), Block.createCuboidShape(9.0, 0.0, 1.0, 15.0, 3.0, 15.0), Block.createCuboidShape(11.0, 0.0, 1.0, 15.0, 3.0, 15.0), Block.createCuboidShape(13.0, 0.0, 1.0, 15.0, 3.0, 15.0)};
    }
}
