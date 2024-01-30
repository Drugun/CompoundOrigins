package net.drugunMC.compound_origins.entity.projectile;

import net.drugunMC.compound_origins.CompoundOrigins;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class NatureAffinityProjectileWall extends BlockSpawningProjectile {





    public NatureAffinityProjectileWall(EntityType<? extends NatureAffinityProjectileWall> entityType, World world) {
        super(entityType, world);
    }

    public NatureAffinityProjectileWall(World world, LivingEntity owner) {
        super(CompoundOrigins.AFFINITY_NATURE_PROJECTILE_WALL, owner, world);
    }

    public NatureAffinityProjectileWall(World world, double x, double y, double z) {
        super(CompoundOrigins.AFFINITY_NATURE_PROJECTILE_WALL, x, y, z, world);
    }

    protected Item getDefaultItem() {
        return Items.SLIME_BALL;
    }

    @Override
    public void nextPhase(){
        if(phase == 1){
            World world = this.getWorld();
            world.syncWorldEvent(null, 59747847, impactPos, 0);
            impactPos = dropPos(impactPos, 2);

            makePillar(3, dropPos(impactPos, 1), Registries.BLOCK.get(new Identifier(CompoundOrigins.ModID, "temporary_roots")).getDefaultState(), false);

            Box box = this.getBoundingBox().expand(8.0, 3.0, 8.0);
            List<Entity> list = this.getWorld().getOtherEntities(this, box);
            if (!list.isEmpty()) {
                for (Entity e: list) {
                    if(e instanceof NatureAffinityProjectileWall){
                        if(((NatureAffinityProjectileWall) e).getOwner() == this.getOwner()){
                            world.syncWorldEvent(null, 59747847, ((NatureAffinityProjectileWall) e).impactPos, 0);
                            Vec3d posnew = impactPos.toCenterPos();
                            Vec3d posold = ((NatureAffinityProjectileWall) e).impactPos.toCenterPos();
                            Vec3d vec = posnew.relativize(posold);
                            BlockPos bp = null;
                            BlockPos bp2 = null;
                            for(int i = 0; i < 12; i++){
                                bp2 = BlockPos.ofFloored( posnew.add(vec.multiply((float)i / 12f)) );
                                if(bp2 != bp){
                                    makePillar(3, dropPos(bp2, 1), Registries.BLOCK.get(new Identifier(CompoundOrigins.ModID, "temporary_roots")).getDefaultState(), false);
                                    bp = bp2;
                                }
                            }
                        }
                    }
                }

            }

            phase = 2;
            lifetime = 100;
        }
        else if(phase == 2){
            this.discard();
        }
    }


    /*
    @Override
    public void nextPhase(){
        if(phase == 1){
            World world = this.getWorld();
            world.syncWorldEvent(null, 59747846, impactPos, 0);
            impactPos = dropPos(impactPos, 3);
            phase = 2;
            lifetime = 5;
        }
        else if(phase == 2){
            for(int i = -1; i <= 1; i++){
                for(int j = -1; j <= 1; j++){
                    if(!(i == 0 && j == 0)){
                        makePillar(3, impactPos.add(i, 0, j), Registries.BLOCK.get(new Identifier(CompoundOrigins.ModID, "temporary_leaves")).getDefaultState(), true);
                    }
                }
            }
            this.discard();
        }
    }*/

    @Override
    public int getDefaultLifetime() {
        return 80;
    }


}
