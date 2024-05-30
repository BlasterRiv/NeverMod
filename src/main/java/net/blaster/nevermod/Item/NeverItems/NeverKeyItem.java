package net.blaster.nevermod.Item.NeverItems;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
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
