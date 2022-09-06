package net.drugunMC.compound_origins.entity.projectile;

import net.drugunMC.compound_origins.CompoundOrigins;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import java.util.List;

public class WaterAffinityProjectile extends ThrownItemEntity {
    public WaterAffinityProjectile(EntityType<? extends WaterAffinityProjectile> entityType, World world) {
        super(entityType, world);
    }

    public WaterAffinityProjectile(World world, LivingEntity owner) {
        super(CompoundOrigins.AFFINITY_WATER_PROJECTILE, owner, world);
    }

    public WaterAffinityProjectile(World world, double x, double y, double z) {
        super(CompoundOrigins.AFFINITY_WATER_PROJECTILE, x, y, z, world);
    }

    protected Item getDefaultItem() {
        return Items.SNOWBALL;
    }





    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        for(int i = 0; i < 30; i++){
            this.world.addParticle(ParticleTypes.SNOWFLAKE, this.getX()+2*(this.random.nextDouble()-0.5), this.getY()+2*(this.random.nextDouble()-0.5), this.getZ()+2*(this.random.nextDouble()-0.5), this.random.nextDouble()*0.2, this.random.nextDouble()*0.2, this.random.nextDouble()*0.2);
        }
        if (!this.world.isClient) {
            Entity owner = this.getEffectCause();
            Box box = this.getBoundingBox().expand(2.0, 1.5, 2.0);
            List<LivingEntity> list = this.world.getNonSpectatingEntities(LivingEntity.class, box);
            if (!list.isEmpty()) {
                for (LivingEntity e: list) {
                    e.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20 * 6, 3), owner);
                }

            }
            this.discard();
        }

    }
}
