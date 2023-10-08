package net.drugunMC.compound_origins.entity.projectile;

import net.drugunMC.compound_origins.CompoundOrigins;
import net.drugunMC.compound_origins.block.TemporaryLavaBlock;
import net.fabricmc.fabric.api.block.v1.FabricBlock;
import net.minecraft.block.AirBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FireAffinityProjectileLava extends ThrownItemEntity {
    public FireAffinityProjectileLava(EntityType<? extends net.drugunMC.compound_origins.entity.projectile.FireAffinityProjectileLava> entityType, World world) {
        super(entityType, world);
    }

    public FireAffinityProjectileLava(World world, LivingEntity owner) {
        super(CompoundOrigins.AFFINITY_FIRE_PROJECTILE_LAVA, owner, world);
    }

    public FireAffinityProjectileLava(World world, double x, double y, double z) {
        super(CompoundOrigins.AFFINITY_FIRE_PROJECTILE_LAVA, x, y, z, world);
    }

    protected Item getDefaultItem() {
        return Items.FIRE_CHARGE;
    }





    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);

    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);

        /*
        for(int n = 0; n < 30; n++){
            this.getWorld().addParticle(ParticleTypes.FLAME, this.getX()+2*(this.random.nextDouble()-0.5), this.getY()+2*(this.random.nextDouble()-0.5), this.getZ()+2*(this.random.nextDouble()-0.5), this.random.nextDouble()*0.2, this.random.nextDouble()*0.2, this.random.nextDouble()*0.2);
        }
        */

        if (!this.getWorld().isClient) {
            BlockPos pos = this.getBlockPos();
            BlockPos pos2;
            World world = this.getWorld();
            for(int i = -2; i <= 2; i++){
                for(int j = -2; j <= 2; j++){
                    for(int k = -2; k <= 2; k++){
                        pos2 = pos.add(i, j, k);
                        if(world.getBlockState(pos2).isAir() && world.getBlockState(pos2.down()).isFullCube(world, pos2.down())){
                            this.getWorld().setBlockState(pos2, Registries.BLOCK.get(new Identifier(CompoundOrigins.ModID, "temporary_lava")).getDefaultState());
                        }
                    }
                }
            }
            this.discard();
        }



    }





}