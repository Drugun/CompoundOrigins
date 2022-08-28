package net.drugunMC.compound_origins.entity.projectile;

import net.drugunMC.compound_origins.CompoundOrigins;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class FireAffinityProjectile extends ThrownItemEntity {
    public FireAffinityProjectile(EntityType<? extends net.drugunMC.compound_origins.entity.projectile.FireAffinityProjectile> entityType, World world) {
        super(entityType, world);
    }

    public FireAffinityProjectile(World world, LivingEntity owner) {
        super(CompoundOrigins.AFFINITY_FIRE_PROJECTILE, owner, world);
    }

    public FireAffinityProjectile(World world, double x, double y, double z) {
        super(CompoundOrigins.AFFINITY_FIRE_PROJECTILE, x, y, z, world);
    }

    protected Item getDefaultItem() {
        return Items.FIRE_CHARGE;
    }





    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (!this.world.isClient) {
            Entity entity = entityHitResult.getEntity();
            Entity entity2 = this.getOwner();
            entity.damage(DamageSource.thrownProjectile(this, entity2), 2.0F);
            if (entity2 instanceof LivingEntity) {
                this.applyDamageEffects((LivingEntity)entity2, entity);
            }

        }
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.world.isClient) {
            this.world.createExplosion(null, this.getX(), this.getY(), this.getZ(), 1.5f, true, Explosion.DestructionType.NONE);
            this.discard();
        }

    }
}
