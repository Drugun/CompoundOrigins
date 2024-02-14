package net.drugunMC.compound_origins.entity.projectile;

import net.drugunMC.compound_origins.CompoundOrigins;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class EarthAffinityProjectileWall extends BlockSpawningProjectile {





    public EarthAffinityProjectileWall(EntityType<? extends EarthAffinityProjectileWall> entityType, World world) {
        super(entityType, world);
    }

    public EarthAffinityProjectileWall(World world, LivingEntity owner) {
        super(CompoundOrigins.AFFINITY_EARTH_PROJECTILE_WALL, owner, world);
    }

    public EarthAffinityProjectileWall(World world, double x, double y, double z) {
        super(CompoundOrigins.AFFINITY_EARTH_PROJECTILE_WALL, x, y, z, world);
    }

    protected Item getDefaultItem() {
        return Items.FIREWORK_STAR;
    }




    @Override
    public void nextPhase(){
        if(phase == 1){
            World world = this.getWorld();
            world.syncWorldEvent(null, 59747848, impactPos, 0);
            //impactPos = dropPos(impactPos, 3);

            makePillar(4, dropPos(impactPos, 1), Registries.BLOCK.get(new Identifier(CompoundOrigins.ModID, "temporary_cobblestone")).getDefaultState(), false);
            phase = 2;
            lifetime = 2;
        }
        else if(phase == 2){
            if(impactHeading == Direction.NORTH || impactHeading == Direction.SOUTH){
                makePillar(4, dropPos(impactPos.add(1,0,0), 1), Registries.BLOCK.get(new Identifier(CompoundOrigins.ModID, "temporary_cobblestone")).getDefaultState(), false);
                makePillar(4, dropPos(impactPos.add(-1,0,0), 1), Registries.BLOCK.get(new Identifier(CompoundOrigins.ModID, "temporary_cobblestone")).getDefaultState(), false);
            }
            else{
                makePillar(4, dropPos(impactPos.add(0,0,1), 1), Registries.BLOCK.get(new Identifier(CompoundOrigins.ModID, "temporary_cobblestone")).getDefaultState(), false);
                makePillar(4, dropPos(impactPos.add(0,0,-1), 1), Registries.BLOCK.get(new Identifier(CompoundOrigins.ModID, "temporary_cobblestone")).getDefaultState(), false);
            }

            phase = 3;
            lifetime = 2;
        }
        else if(phase == 3){
            if(impactHeading == Direction.NORTH || impactHeading == Direction.SOUTH){
                makePillar(4, dropPos(impactPos.add(2,0,0), 1), Registries.BLOCK.get(new Identifier(CompoundOrigins.ModID, "temporary_cobblestone")).getDefaultState(), false);
                makePillar(4, dropPos(impactPos.add(-2,0,0), 1), Registries.BLOCK.get(new Identifier(CompoundOrigins.ModID, "temporary_cobblestone")).getDefaultState(), false);
            }
            else{
                makePillar(4, dropPos(impactPos.add(0,0,2), 1), Registries.BLOCK.get(new Identifier(CompoundOrigins.ModID, "temporary_cobblestone")).getDefaultState(), false);
                makePillar(4, dropPos(impactPos.add(0,0,-2), 1), Registries.BLOCK.get(new Identifier(CompoundOrigins.ModID, "temporary_cobblestone")).getDefaultState(), false);
            }

            phase = 4;
            lifetime = 2;
        }
        else if(phase == 4){
            if(impactHeading == Direction.NORTH || impactHeading == Direction.SOUTH){
                makePillar(4, dropPos(impactPos.add(3,0,0), 1), Registries.BLOCK.get(new Identifier(CompoundOrigins.ModID, "temporary_cobblestone")).getDefaultState(), false);
                makePillar(4, dropPos(impactPos.add(-3,0,0), 1), Registries.BLOCK.get(new Identifier(CompoundOrigins.ModID, "temporary_cobblestone")).getDefaultState(), false);
            }
            else{
                makePillar(4, dropPos(impactPos.add(0,0,3), 1), Registries.BLOCK.get(new Identifier(CompoundOrigins.ModID, "temporary_cobblestone")).getDefaultState(), false);
                makePillar(4, dropPos(impactPos.add(0,0,-3), 1), Registries.BLOCK.get(new Identifier(CompoundOrigins.ModID, "temporary_cobblestone")).getDefaultState(), false);
            }

            this.discard();
        }
    }

    @Override
    public int getDefaultLifetime() {
        return 80;
    }


}
