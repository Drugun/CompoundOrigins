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

public class TemporaryLeavesBlock extends TemporaryBlock {

    private static final VoxelShape SHAPE_EMPTY = VoxelShapes.empty();


    public TemporaryLeavesBlock(Settings settings, int baseTicksToRemove, int maxExtraTicks, int chainBreakTicks) {
        super(settings, baseTicksToRemove, maxExtraTicks, chainBreakTicks);

    }

    public VoxelShape getSidesShape(BlockState state, BlockView world, BlockPos pos) {
        return SHAPE_EMPTY;
    }

    public int getOpacity(BlockState state, BlockView world, BlockPos pos) {
        return 1;
    }


}
