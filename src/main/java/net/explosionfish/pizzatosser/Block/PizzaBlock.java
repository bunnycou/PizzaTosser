package net.explosionfish.pizzatosser.Block;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;

public class PizzaBlock extends CakeBlock {
    private final Integer hunger;

    public PizzaBlock(Settings settings, Integer hunger) {
        super(settings);
        this.hunger = hunger;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
//        Item item = itemStack.getItem();

//        if (itemStack.isIn(ItemTags.CANDLES) && (Integer)state.get(BITES) == 0) {
//            Block block = Block.getBlockFromItem(item);
//            if (block instanceof CandleBlock) {
//                if (!player.isCreative()) {
//                    itemStack.decrement(1);
//                }
//
//                world.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_CAKE_ADD_CANDLE, SoundCategory.BLOCKS, 1.0F, 1.0F);
//                world.setBlockState(pos, CandleCakeBlock.getCandleCakeFromCandle(block));
//                world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);
//                player.incrementStat(Stats.USED.getOrCreateStat(item));
//                return ActionResult.SUCCESS;
//            }
//        }

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
}
