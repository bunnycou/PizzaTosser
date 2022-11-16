package net.explosionfish.pizzatosser.Entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class PizzaEntity extends SnowballEntity {

    private final Integer hunger;
    private final Float damage;
    private final ItemStack item;

    public PizzaEntity(World world, LivingEntity owner, ItemStack item, Integer hunger) {
        super(world, owner);
        this.item = item;
        this.hunger = hunger;
        this.damage = Float.valueOf(hunger);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        entity.damage(DamageSource.thrownProjectile(this, this.getOwner()), this.damage);
        if (entity.isPlayer()) {
            PlayerEntity player = (PlayerEntity) entityHitResult.getEntity();
            HungerManager hunger = player.getHungerManager();
            hunger.add(this.hunger, 0.6f);
        }
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.world.isClient) {
            this.world.sendEntityStatus(this, (byte)3);
            if (hitResult.getType() != HitResult.Type.ENTITY) {
                this.dropStack(this.item);
            }
        }
    }
}
