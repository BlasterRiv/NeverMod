package net.blaster.nevermod.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class BlockHelper {
    public static boolean testAndDestroyBlockVir(Block blockToTestFor, Level pLevel, BlockPos pPos, int chainCheck){
        boolean isComplete;
        if(pLevel.getBlockState(pPos).getBlock() != blockToTestFor){pLevel.destroyBlock(pPos,true);isComplete= false; }
        else isComplete=true;

        if(chainCheck>0){return (testAndDestroyBlockVir(blockToTestFor,pLevel, pPos.above(),chainCheck-1) && isComplete);}
        if(chainCheck<0){return (testAndDestroyBlockVir(blockToTestFor,pLevel, pPos.below(),chainCheck+1) && isComplete);}
        return isComplete;
    }
}
