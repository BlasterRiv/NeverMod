package net.blaster.nevermod.Item.NeverItems.Gems;
import net.blaster.nevermod.util.NeverItemProp;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeleportGem extends Item implements GemsItemInterface {
    private final Map<Integer, BlockPos> BlockPosMap;

    public TeleportGem(Properties pProperties) {
        super(pProperties);
        BlockPosMap= new HashMap<Integer, BlockPos>();
    }


//    @Override
//    public InteractionResult useOn(UseOnContext pContext) {
//
//        return super.useOn(pContext);
//    }


    @Override
    public InteractionResult useOn(UseOnContext pContext) {

        Player pPlayer= pContext.getPlayer();
        Level pLevel = pContext.getLevel();
        InteractionHand pUsedHand = pContext.getHand();
        if(pLevel instanceof ServerLevel serverlevel){
            int gemid=pPlayer.getItemInHand(pUsedHand).get(NeverItemProp.GemId);
            if(gemid==-1){registerGem(pPlayer.getItemInHand(pUsedHand));}

            if(pPlayer.isCrouching()){
                BlockPosMap.put(gemid,pContext.getClickedPos().above());
                pPlayer.sendSystemMessage(Component.literal("set on "+BlockPosMap.get(gemid)));
                return InteractionResult.SUCCESS;
            }

        }

        return InteractionResult.FAIL;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        //spawn a projectile going forwword
       // Player pPlayer = pContext.getPlayer();
       // Level pLevel=pContext.getLevel();
        if(pLevel instanceof ServerLevel serverlevel){
            int gemid=pPlayer.getItemInHand(pUsedHand).get(NeverItemProp.GemId);
            if(gemid==-1){registerGem(pPlayer.getItemInHand(pUsedHand));}

            if(pPlayer.isCrouching()){
                BlockPosMap.put(gemid,null);
                pPlayer.sendSystemMessage(Component.literal("Cleared "+gemid));
                return InteractionResultHolder.success(pPlayer.getItemInHand(pPlayer.getUsedItemHand()));
            }
            BlockPos blockPos=BlockPosMap.get(gemid);
            pPlayer.teleportTo(blockPos.getX(),blockPos.getY(),blockPos.getZ());
            //pLevel.gameEvent(GameEvent.PROJECTILE_SHOOT, new Vec3(pPlayer.getX(),pPlayer.getY(),pPlayer.getZ()),GameEvent.Context.of(pPlayer));
            pPlayer.sendSystemMessage(Component.literal("used"));
            //pLevel.addFreshEntity();
            return InteractionResultHolder.success(pPlayer.getItemInHand(pPlayer.getUsedItemHand()));
        }

        return InteractionResultHolder.fail(pPlayer.getItemInHand(pPlayer.getUsedItemHand()));
    }
    public int registerGem(ItemStack stack){
        BlockPosMap.put(BlockPosMap.size(), null);
        return stack.set(NeverItemProp.GemId,BlockPosMap.size());
    }
    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        pTooltipComponents.add(1,Component.translatable("tooltip.nevermod.teleport_gem"));
        if(Screen.hasShiftDown()){
            BlockPosMap.get(pStack.get(NeverItemProp.GemId));
            pTooltipComponents.set(1,Component.literal(""+ BlockPosMap.get(pStack.get(NeverItemProp.GemId))));
        }
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }


//    @Override
//    public InteractionResult useOn(UseOnContext pContext) {
//
//
//        return InteractionResult.SUCCESS;
//    }
}
