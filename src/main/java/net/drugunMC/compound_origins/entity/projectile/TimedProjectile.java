package net.drugunMC.compound_origins.entity.projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;


public abstract class TimedProjectile extends ThrownItemEntity {



    protected int lifetime = getDefaultLifetime();








    public TimedProjectile(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public TimedProjectile(EntityType<? extends ThrownItemEntity> entityType, double d, double e, double f, World world) {
        super(entityType, d, e, f, world);
    }

    public TimedProjectile(EntityType<? extends ThrownItemEntity> entityType, LivingEntity livingEntity, World world) {
        super(entityType, livingEntity, world);
    }




    @Override
    public void tick() {
        super.tick();
        if(lifetime > 0){
            lifetime--;
        }
        else{
            if(!this.getWorld().isClient){
                this.onTimeout();
            }

        }

    }


    public abstract int getDefaultLifetime();

    public abstract void onTimeout();



    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("lifetime", lifetime);

    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if(nbt.contains("lifetime")){
            lifetime = nbt.getInt("lifetime");
        }

    }



}
