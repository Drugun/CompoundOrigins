package net.drugunMC.compound_origins.block;


import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class TemporaryRootsBlock extends TemporaryBlock {

    private static final VoxelShape SHAPE_EMPTY = VoxelShapes.empty();


    public TemporaryRootsBlock(Settings settings, int baseTicksToRemove, int maxExtraTicks, int chainBreakTicks) {
        super(settings, baseTicksToRemove, maxExtraTicks, chainBreakTicks);

    }

    public VoxelShape getSidesShape(BlockState state, BlockView world, BlockPos pos) {
        return SHAPE_EMPTY;
    }

    public int getOpacity(BlockState state, BlockView world, BlockPos pos) {
        return 1;
    }

    public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        return stateFrom.isOf(Blocks.MANGROVE_ROOTS) && direction.getAxis() == Direction.Axis.Y;
    }

}
