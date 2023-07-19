package net.drugunMC.compound_origins.entity.projectile;

import net.drugunMC.compound_origins.CompoundOrigins;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
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

public class NatureAffinityProjectile extends ThrownItemEntity {
    public NatureAffinityProjectile(EntityType<? extends NatureAffinityProjectile> entityType, World world) {
        super(entityType, world);
    }

    public NatureAffinityProjectile(World world, LivingEntity owner) {
        super(CompoundOrigins.AFFINITY_NATURE_PROJECTILE, owner, world);
    }

    public NatureAffinityProjectile(World world, double x, double y, double z) {
        super(CompoundOrigins.AFFINITY_NATURE_PROJECTILE, x, y, z, world);
    }

    protected Item getDefaultItem() {
        return Items.SLIME_BALL;
    }





    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);

    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        for(int i = 0; i < 30; i++){
            this.getWorld().addParticle(ParticleTypes.ITEM_SLIME, this.getX()+2*(this.random.nextDouble()-0.5), this.getY()+2*(this.random.nextDouble()-0.5), this.getZ()+2*(this.random.nextDouble()-0.5), this.random.nextDouble()*0.2, this.random.nextDouble()*0.2, this.random.nextDouble()*0.2);
        }
        if (!this.getWorld().isClient) {
            Entity owner = this.getEffectCause();
            Box box = this.getBoundingBox().expand(1.5, 1.5, 1.5);
            List<LivingEntity> list = this.getWorld().getNonSpectatingEntities(LivingEntity.class, box);
            if (!list.isEmpty()) {
                for (LivingEntity e: list) {
                    e.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 20 * 4, 1), owner);
                    e.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 20 * 10, 2), owner);
                    if (e.getGroup() == EntityGroup.UNDEAD){
                        e.damage(getWorld().getDamageSources().thrown(this, owner), 10);
                    }
                }

            }
            Box box2 = this.getBoundingBox().expand(3.0, 2.0, 3.0);
            List<LivingEntity> list2 = this.getWorld().getNonSpectatingEntities(LivingEntity.class, box2);
            if (!list2.isEmpty()) {
                for (LivingEntity e: list2) {
                    e.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 20 * 6, 0), owner);
                    e.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20 * 6, 1), owner);
                    e.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 20 * 7, 1), owner);
                    if (e.getGroup() == EntityGroup.UNDEAD){
                        e.damage(getWorld().getDamageSources().thrown(this, owner), 5);
                    }
                }

            }
            this.discard();
        }

    }

}
