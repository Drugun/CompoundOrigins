package net.drugunMC.compound_origins.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import static net.drugunMC.compound_origins.CompoundOrigins.TEMPORARY_BLOCKS;


public class TemporaryBlock extends Block {

    final int removeDelay;
    final int removeDelayExtra;

    public TemporaryBlock(Settings settings, int baseTicksToRemove, int maxExtraTicks) {
        super(settings);
        removeDelay = baseTicksToRemove;
        removeDelayExtra = maxExtraTicks;

    }



    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if(world.getBlockState(pos).isIn(TEMPORARY_BLOCKS)){
            world.breakBlock(pos, false);
        }

    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        super.onBlockAdded(state, world, pos, oldState, notify);
        world.scheduleBlockTick(pos, state.getBlock(), removeDelay + world.random.nextInt(removeDelayExtra));
    }
}
