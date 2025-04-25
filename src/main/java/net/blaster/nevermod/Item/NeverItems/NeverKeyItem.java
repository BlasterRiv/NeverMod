package net.blaster.nevermod.Item.NeverItems;

import net.blaster.nevermod.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
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
        if(!pContext.getLevel().isClientSide()){
            //now we are on server side
            BlockPos clickedPos = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            if(pContext.getLevel().getBlockState(clickedPos).getBlock().getClass()==DoorBlock.class){
                if (player != null) {
                    player.sendSystemMessage(Component.literal("IS A DOOR"));
                    DoorBlock db= (DoorBlock) pContext.getLevel().getBlockState(clickedPos).getBlock();
                    if(db.isOpen(pContext.getLevel().getBlockState(clickedPos))){player.sendSystemMessage(Component.literal("DOOR MUST BE CLOSED"));  return InteractionResult.FAIL; }
                    if(ModBlocks.NeverDoor.value()== db){player.sendSystemMessage(Component.literal("Already Trasformed"));  return InteractionResult.FAIL; }
                    pContext.getLevel().setBlockAndUpdate(clickedPos,ModBlocks.NeverDoor.get().withPropertiesOf(pContext.getLevel().getBlockState(clickedPos)));

                    return InteractionResult.SUCCESS;
                }
            }
            else {
                player.sendSystemMessage(Component.literal("IS NOT A DOOR"));
            }

        }
        return InteractionResult.FAIL;
    }


}
