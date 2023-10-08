package net.drugunMC.compound_origins.block;


import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class TemporaryLavaBlock extends TemporaryBlock {

    final int damage;
    private static final VoxelShape SHAPE = VoxelShapes.cuboid(0.0, 0.0, 0.0, 1.0, 1/16f, 1.0);
    private static final VoxelShape SHAPE_EMPTY = VoxelShapes.empty();


    public TemporaryLavaBlock(Settings settings, int baseTicksToRemove, int maxExtraTicks, int damagePerTick) {
        super(settings, baseTicksToRemove, maxExtraTicks);
        damage = damagePerTick;

    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE_EMPTY;
    }

    @Override
    public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCullingShape(BlockState state, BlockView world, BlockPos pos) {
        return SHAPE;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    public float getVelocityMultiplier(){
        return 0.9f;
    }

    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity){
        entity.damage(world.getDamageSources().lava(), damage);
    }

    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }





}
