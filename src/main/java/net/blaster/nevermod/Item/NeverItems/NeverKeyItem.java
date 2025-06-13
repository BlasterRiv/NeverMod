package net.blaster.nevermod.Item.NeverItems;

import net.blaster.nevermod.blocks.ModBlocks;
import net.blaster.nevermod.blocks.NeverBlocks.NeverDoor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.compress.compressors.lz77support.LZ77Compressor;

public class NeverKeyItem extends Item {
    public NeverKeyItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(pContext.getLevel().isClientSide()){return InteractionResult.PASS;}//now we are on server side

        BlockPos clickedPos = pContext.getClickedPos();
        Player player = pContext.getPlayer();
        Level level = pContext.getLevel();
        if(!(level.getBlockState(clickedPos).getBlock() instanceof DoorBlock)){player.sendSystemMessage(Component.literal("IS NOT A DOOR"));return InteractionResult.FAIL;}

        player.sendSystemMessage(Component.literal("IS A DOOR"));
        DoorBlock db= (DoorBlock) level.getBlockState(clickedPos).getBlock();
        if (db instanceof NeverDoor) {
            if(db.isOpen(level.getBlockState(clickedPos))){return InteractionResult.FAIL;}
            player.sendSystemMessage(Component.literal("Already Trasformed"));
            db.setOpen(player,level,level.getBlockState(clickedPos), clickedPos,true);
            return InteractionResult.SUCCESS;
        }
        if(db.isOpen(level.getBlockState(clickedPos))){player.sendSystemMessage(Component.literal("DOOR MUST BE CLOSED"));  return InteractionResult.FAIL; }
        else if(!((level.getBlockState(clickedPos.below()).getBlock() == Blocks.END_STONE) || ((level.getBlockState(clickedPos.below()).getBlock() == ModBlocks.NeverStone.get())))){
            level.setBlockAndUpdate(clickedPos,ModBlocks.NeverDoor.get().withPropertiesOf(level.getBlockState(clickedPos)));
            level.destroyBlock(clickedPos.below(),true);
            return InteractionResult.SUCCESS;
        }
        level.setBlockAndUpdate(clickedPos,ModBlocks.NeverDoor.get().withPropertiesOf(level.getBlockState(clickedPos)));
        level.setBlockAndUpdate(clickedPos.below(),ModBlocks.NeverStone.get().defaultBlockState());
        return InteractionResult.SUCCESS;

    }


}
