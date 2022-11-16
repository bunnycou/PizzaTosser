package net.explosionfish.pizzatosser.Item;

import net.explosionfish.pizzatosser.Entity.PizzaEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class PizzaBlockItem extends BlockItem {

    private final Integer hunger;

    public PizzaBlockItem(Block block, Settings settings, Integer hunger) {
        super(block, settings);
        this.hunger = hunger;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user.isSneaking()) {
            ItemStack itemStack = user.getStackInHand(hand);
            world.playSound((PlayerEntity)null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
            if (!world.isClient) {
                PizzaEntity pizzaEntity = new PizzaEntity(world, user, itemStack, this.hunger);
                pizzaEntity.setItem(itemStack);
                pizzaEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
                world.spawnEntity(pizzaEntity);
            }

            user.incrementStat(Stats.USED.getOrCreateStat(this));
            if (!user.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }

            return TypedActionResult.success(itemStack, world.isClient());
        } else {
            return super.use(world, user, hand);
        }
    }
}
