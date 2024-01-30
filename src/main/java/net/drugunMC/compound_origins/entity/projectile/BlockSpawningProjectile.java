package net.drugunMC.compound_origins.entity.projectile;

import net.minecraft.block.AirBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

import static net.drugunMC.compound_origins.CompoundOrigins.TEMPORARY_BLOCKS;

public abstract class BlockSpawningProjectile extends TimedProjectile {




    protected int phase = 0;

    protected BlockPos impactPos;

    protected Direction impactHeading;







    public BlockSpawningProjectile(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public BlockSpawningProjectile(EntityType<? extends ThrownItemEntity> entityType, double d, double e, double f, World world) {
        super(entityType, d, e, f, world);
    }

    public BlockSpawningProjectile(EntityType<? extends ThrownItemEntity> entityType, LivingEntity livingEntity, World world) {
        super(entityType, livingEntity, world);
    }


    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            this.impact();
        }

    }



    @Override
    public void onTimeout() {
        if(phase == 0){
            this.impact();
        }
        else{
            nextPhase();
        }
    }

    public void impact(){
        phase = 1;
        lifetime = 0;
        this.setNoGravity(true);
        this.setInvisible(true);
        this.setInvulnerable(true);
        this.setVelocity(0,0,0);
        this.setItem(ItemStack.EMPTY);
        impactPos = this.getBlockPos();
        impactHeading = this.getMovementDirection();

        nextPhase();

    }

    public abstract void nextPhase();

    public abstract int getDefaultLifetime();

    protected void makePillar(int height, BlockPos pos, BlockState state, boolean ignoreEntityOccupiedPos){
        World world = this.getWorld();
        if(ignoreEntityOccupiedPos){
            Box box = Box.from(Vec3d.of(pos)).expand(2);
            List<LivingEntity> list = this.getWorld().getNonSpectatingEntities(LivingEntity.class, box);
            if (!list.isEmpty()) {
                for (LivingEntity e: list) {
                    for(int i = 0; i < height; i++){
                        if(e.getBlockPos().equals(pos.up(i))){
                            return;
                        }
                    }
                }

            }
        }
        if(world.getBlockState(pos.down()).isAir() || world.getBlockState(pos.down()).isIn(TEMPORARY_BLOCKS)){
            return;
        }
        for(int i = 0; i < height; i++){
            if(world.getBlockState(pos.up(i)).isAir()){
                this.getWorld().setBlockState(pos.up(i), state);
            }
        }
    }

    public BlockPos dropPos(BlockPos pos, int range){
        World world = this.getWorld();
        for(int i = 0; i < range; i++){
            if(world.getBlockState(pos.down()).isAir()){
                pos = pos.down();
            }
        }
        return pos;
    }


    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("phase", phase);
        if(impactPos != null){
            nbt.putInt("impactPosX", impactPos.getX());
            nbt.putInt("impactPosY", impactPos.getY());
            nbt.putInt("impactPosZ", impactPos.getZ());
        }
        if(impactHeading != null){
            nbt.putInt("impactHeading", impactHeading.getId());
        }

    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if(nbt.contains("phase")){
            phase = nbt.getInt("phase");
        }
        if(nbt.contains("impactPosX") && nbt.contains("impactPosY") && nbt.contains("impactPosZ")){
            impactPos = new BlockPos(nbt.getInt("impactPosX"), nbt.getInt("impactPosY"), nbt.getInt("impactPosZ"));
        }
        if(nbt.contains("impactHeading")){
            impactHeading = Direction.byId(nbt.getInt("impactHeading"));
        }
    }


}
