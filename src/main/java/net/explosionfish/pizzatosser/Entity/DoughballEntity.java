package net.explosionfish.pizzatosser.Entity;

import net.explosionfish.pizzatosser.PizzaTosser;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class DoughballEntity extends ThrownItemEntity {

    private Integer tosses;

    public DoughballEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public DoughballEntity(World world, LivingEntity owner, Integer tosses) {
        super(PizzaTosser.DOUGHBALL_ENTITY, owner, world);
        this.tosses = tosses;
    }

    @Override
    protected Item getDefaultItem() {
        return PizzaTosser.DOUGHBALL_ITEM;
    }

    @Environment(EnvType.CLIENT)
    private ParticleEffect getParticleParameters() {
        ItemStack itemStack = this.getItem();
        return itemStack.isEmpty() ? ParticleTypes.CRIT : new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack);
    }

    @Environment(EnvType.CLIENT)
    public void handleStatus(byte status) {
        if (status == 3) {
            ParticleEffect particleEffect = this.getParticleParameters();

            for(int i = 0; i < 8; ++i) {
                this.world.addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0d, 0.0d, 0.0d);
            }
        }
    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();

        if (entity instanceof PlayerEntity playerEntity) {
            ItemStack item = switch (this.tosses) {
                case 1 -> new ItemStack(PizzaTosser.DOUGHBALL_ITEM2);
                case 2 -> new ItemStack(PizzaTosser.DOUGHBALL_ITEM3);
                case 3 -> new ItemStack(PizzaTosser.PIZZADOUGH_ITEM);
                default -> new ItemStack(Items.DIRT);
            };
            playerEntity.giveItemStack(item);
        }
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.world.isClient) {
            this.world.sendEntityStatus(this, (byte) 3);
            this.discard();

            if (hitResult.getType() != HitResult.Type.ENTITY) {
                this.dropStack(new ItemStack(Items.DIRT));
                this.playSound(SoundEvents.ENTITY_SLIME_SQUISH, 0.6f, 1f);
            }
        }
    }
}
