package net.blaster.nevermod.blocks.NeverBlocks;

import net.blaster.nevermod.util.BlockHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.Half;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.Nullable;

public class NeverDoor extends DoorBlock {
    public NeverDoor(BlockSetType p_272854_, Properties p_273303_) {
        super(p_272854_, p_273303_);
    }

    @Override
    public void setOpen(@Nullable Entity pEntity, Level pLevel, BlockState pState, BlockPos pPos, boolean pOpen) {
        int block = 0;
        if(pState.getValue(HALF) == DoubleBlockHalf.LOWER){
            block=1;
        }else block=-1;

        boolean isClockwiseReady =BlockHelper.testAndDestroyBlockVir(Blocks.END_STONE,pLevel,pPos.relative(pState.getValue(FACING).getClockWise()),block);
        boolean isCounterClockwiseReady =BlockHelper.testAndDestroyBlockVir(Blocks.END_STONE,pLevel,pPos.relative(pState.getValue(FACING).getCounterClockWise()),block);

        if(!(
                BlockHelper.testAndDestroyBlockVir(Blocks.END_STONE,pLevel,pPos.above((1 + block)+(int)(0.5f*(1-block))),0) && isClockwiseReady&& isCounterClockwiseReady
        )){return;}

        super.setOpen(pEntity, pLevel, pState, pPos, pOpen);

    }
//    protected boolean testAndDestroy(Level pLevel, BlockPos pPos,int chainCheck){
//        boolean isComplete;
//        if(pLevel.getBlockState(pPos).getBlock() != Blocks.END_STONE){pLevel.destroyBlock(pPos,true);isComplete= false; }
//        else isComplete=true;
//
//        if(chainCheck>0){return (isComplete && testAndDestroy(pLevel, pPos.above(),chainCheck-1));}
//        if(chainCheck<0){return (isComplete && testAndDestroy(pLevel, pPos.below(),chainCheck+1));}
//        return isComplete;
//
//    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if(pEntity instanceof Player){
            Player player = (Player) pEntity;
        }
        else pEntity.igniteForTicks(5);
        super.stepOn(pLevel, pPos, pState, pEntity);

    }
}
