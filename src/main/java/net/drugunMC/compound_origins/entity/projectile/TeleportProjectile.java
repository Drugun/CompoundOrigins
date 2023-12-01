package net.drugunMC.compound_origins.entity.projectile;

import io.github.apace100.apoli.component.PowerHolderComponent;
import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.PowerTypeRegistry;
import io.github.apace100.apoli.power.VariableIntPower;
import net.drugunMC.compound_origins.CompoundOrigins;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class TeleportProjectile extends ThrownItemEntity {

    private int lifetime = 40;
    private static final double maxDist = 20;
    private static final int nominalCost = 5;



    public TeleportProjectile(EntityType<? extends TeleportProjectile> entityType, World world) {
        super(entityType, world);
        setNoGravity(true);
    }

    public TeleportProjectile(World world, LivingEntity owner) {
        super(CompoundOrigins.TELEPORT_PROJECTILE, owner, world);
        setNoGravity(true);
    }

    public TeleportProjectile(World world, double x, double y, double z) {
        super(CompoundOrigins.TELEPORT_PROJECTILE, x, y, z, world);
        setNoGravity(true);
    }

    protected Item getDefaultItem() {
        return Items.AIR;
    }





    @Override
    public void tick() {
        super.tick();
        if(lifetime <= 0){
            if(!this.getWorld().isClient){
                this.discard();
            }
        }
        else{
            lifetime--;
        }
    }



    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        Entity owner = this.getOwner();
        if (!this.getWorld().isClient && owner != null) {
            Vec3d pos = hitResult.getPos();
            Vec3d veln = this.getVelocity().normalize().multiply(0.1f);
            if( pos.x == (int)pos.x){
                pos = pos.add(veln.x,0,0);
            }
            if( pos.y == (int)pos.y){
                pos = pos.add(0,veln.y,0);
            }
            if( pos.z == (int)pos.z){
                pos = pos.add(0,0,veln.z);
            }
            double dist = pos.distanceTo(owner.getPos());
            if(dist <= maxDist){
                BlockPos bp = BlockPos.ofFloored(pos);
                World world = this.getWorld();
                if( !( world.getBlockState(bp.up(1)).isFullCube(world, bp.up(1)) || world.getBlockState(bp.up(2)).isFullCube(world, bp.up(2)) ) && !( world.getBlockState(bp.north(1)).isFullCube(world, bp.north(1)) && world.getBlockState(bp.east(1)).isFullCube(world, bp.east(1)) && world.getBlockState(bp.south(1)).isFullCube(world, bp.south(1)) && world.getBlockState(bp.west(1)).isFullCube(world, bp.west(1)) ) ){
                    Vec3d pos2 = Vec3d.ofCenter(bp.up());
                    teleport(pos2, owner, (int)dist);
                }
                else{
                    Vec3d pos2 = pos.add(pos.relativize(owner.getPos()).normalize().multiply(0.5f));
                    if(world.getBlockState(BlockPos.ofFloored(pos2)).isFullCube(world, BlockPos.ofFloored(pos2))){
                        pos2 = new Vec3d(pos2.x, MathHelper.floor(pos2.y)+1.1f, pos2.z);
                    }
                    else if(world.getBlockState(BlockPos.ofFloored(pos2).down()).isAir()){
                        pos2 = new Vec3d(pos2.x, MathHelper.floor(pos2.y)-0.9f, pos2.z);
                    }
                    else{
                        pos2 = new Vec3d(pos2.x, MathHelper.floor(pos2.y)+0.1f, pos2.z);
                    }
                    teleport(pos2, owner, (int)dist);
                }

            }
            this.discard();
        }

    }


    private void drainResource(int drain, Entity target){
        PowerType<?> powerType = PowerTypeRegistry.get(new Identifier(CompoundOrigins.ModID, "teleport_resource"));
        Power power = PowerHolderComponent.KEY.get(target).getPower(powerType);
        if (power instanceof VariableIntPower vIntPower){
            vIntPower.setValue(vIntPower.getValue() - (drain + nominalCost));
            PowerHolderComponent.syncPower(target, powerType);
        }

    }

    private void teleport(Vec3d pos, Entity entity, int drain){
        this.getWorld().syncWorldEvent(null, 59747841, entity.getBlockPos(), 0);
        this.getWorld().syncWorldEvent(null, 59747841, BlockPos.ofFloored(pos), 0);
        entity.teleport(pos.getX(), pos.getY(), pos.getZ());
        drainResource(drain, entity);

    }



}
