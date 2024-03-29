package net.drugunMC.compound_origins.entity.projectile;

import net.drugunMC.compound_origins.CompoundOrigins;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class EarthAffinityProjectile extends TimedProjectile {





    public EarthAffinityProjectile(EntityType<? extends EarthAffinityProjectile> entityType, World world) {
        super(entityType, world);
    }

    public EarthAffinityProjectile(World world, LivingEntity owner) {
        super(CompoundOrigins.AFFINITY_EARTH_PROJECTILE, owner, world);
    }

    public EarthAffinityProjectile(World world, double x, double y, double z) {
        super(CompoundOrigins.AFFINITY_EARTH_PROJECTILE, x, y, z, world);
    }

    protected Item getDefaultItem() {
        return Items.FIREWORK_STAR;
    }




    @Override
    public int getDefaultLifetime() {
        return 200;
    }

    @Override
    public void onTimeout() {
        this.getWorld().syncWorldEvent(null, 59747845, this.getBlockPos(), 0);
        this.discard();
    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (!this.getWorld().isClient) {
            Entity entity = entityHitResult.getEntity();
            Entity entity2 = this.getOwner();
            entity.damage(getWorld().getDamageSources().thrown(this, entity2), 3.0F);
            if (entity2 instanceof LivingEntity) {
                this.applyDamageEffects((LivingEntity)entity2, entity);
            }

        }
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if(!this.getWorld().isClient){
            this.getWorld().syncWorldEvent(null, 59747845, this.getBlockPos(), 0);
            this.discard();
        }
    }
}
