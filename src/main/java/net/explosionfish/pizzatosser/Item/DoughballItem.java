package net.explosionfish.pizzatosser.Item;

import net.explosionfish.pizzatosser.Entity.DoughballEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class DoughballItem extends Item {
    private final Integer tosses;
    public DoughballItem(Settings settings, Integer tosses) {
        super(settings);
        this.tosses = tosses;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F);
        if (!world.isClient) {
            DoughballEntity doughballEntity = new DoughballEntity(world, user, this.tosses);
            doughballEntity.setItem(itemStack);
            doughballEntity.setVelocity(user, -90f, user.getYaw(), 0F, 0.5F, 10F);
            world.spawnEntity(doughballEntity);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }

        return TypedActionResult.success(itemStack, world.isClient());
    }
}
