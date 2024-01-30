package net.drugunMC.compound_origins.entity.projectile;

import net.drugunMC.compound_origins.CompoundOrigins;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

import static net.drugunMC.compound_origins.CompoundOrigins.TEMPORARY_BLOCKS;

public class WaterAffinityProjectileWall extends BlockSpawningProjectile {





    public WaterAffinityProjectileWall(EntityType<? extends WaterAffinityProjectileWall> entityType, World world) {
        super(entityType, world);
    }

    public WaterAffinityProjectileWall(World world, LivingEntity owner) {
        super(CompoundOrigins.AFFINITY_WATER_PROJECTILE_WALL, owner, world);
    }

    public WaterAffinityProjectileWall(World world, double x, double y, double z) {
        super(CompoundOrigins.AFFINITY_WATER_PROJECTILE_WALL, x, y, z, world);
    }

    protected Item getDefaultItem() {
        return Items.SNOWBALL;
    }




    @Override
    public void nextPhase(){
        if(phase == 1){
            World world = this.getWorld();
            world.syncWorldEvent(null, 59747846, impactPos, 0);
            impactPos = dropPos(impactPos, 3);
            makePillar(4, impactPos, Registries.BLOCK.get(new Identifier(CompoundOrigins.ModID, "temporary_ice")).getDefaultState(), true);
            phase = 2;
            lifetime = 5;
        }
        else if(phase == 2){
            for(int i = -1; i <= 1; i++){
                for(int j = -1; j <= 1; j++){
                    if(!(i == 0 && j == 0)){
                        makePillar(4, impactPos.add(i, 0, j), Registries.BLOCK.get(new Identifier(CompoundOrigins.ModID, "temporary_ice")).getDefaultState(), true);
                    }
                }
            }
            this.discard();
        }
    }

    @Override
    public int getDefaultLifetime() {
        return 80;
    }


}
