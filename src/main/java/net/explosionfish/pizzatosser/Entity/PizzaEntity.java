package net.explosionfish.pizzatosser.Entity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.world.World;

public class PizzaEntity extends SnowballEntity {
    public PizzaEntity(World world, LivingEntity owner) {
        super(world, owner);
    }

    // TODO
    // Figure out how much damage pizza does
    // do we base damage on hunger
    // do we feed the target if player?
}
