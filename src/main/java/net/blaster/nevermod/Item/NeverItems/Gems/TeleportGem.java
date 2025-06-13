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
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeleportGem extends Item implements GemsItemInterface {
    //private final Map<Integer, BlockPos> BlockPosMap;
    //private static final IntegerProperty;
    //private static final EnumProperty<> Dimention = EnumProperty.create("",Class<DimensionType> DimensionType);
    //private static final Property<?> f= EnumProperty.create("",DIMEN);

    public TeleportGem(Properties pProperties) {
        super(pProperties);
        //BlockPosMap= new HashMap<Integer, BlockPos>();
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
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        if(pLevel instanceof ServerLevel serverlevel){
            int gemid=stack.get(NeverItemProp.GemId);
            if(gemid==-1){registerGem(pPlayer.getItemInHand(pUsedHand));}

            if(pPlayer.isCrouching()){
                stack.set(NeverItemProp.PositionHeld,pContext.getClickedPos().above());
                stack.set(NeverItemProp.DimensionTypeHeld,pLevel.dimensionType());
                pPlayer.sendSystemMessage(Component.literal("set on "+stack.get(NeverItemProp.PositionHeld)));
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

        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        if(pLevel instanceof ServerLevel serverlevel){
            int gemid=stack.get(NeverItemProp.GemId);
            if(gemid==-1){registerGem(pPlayer.getItemInHand(pUsedHand));}

            if(pPlayer.isCrouching()){
                stack.set(NeverItemProp.PositionHeld,null);
                stack.set(NeverItemProp.DimensionTypeHeld.get(),null);
                pPlayer.sendSystemMessage(Component.literal("Cleared "+gemid));
                return InteractionResultHolder.success(pPlayer.getItemInHand(pPlayer.getUsedItemHand()));
            }
            if(stack.get(NeverItemProp.DimensionTypeHeld.get())!=pLevel.dimensionType()) {
                pPlayer.sendSystemMessage(Component.literal("can only teleport withen same diemension"));
                return InteractionResultHolder.fail(pPlayer.getItemInHand(pPlayer.getUsedItemHand()));
            }
            BlockPos blockPos=stack.get(NeverItemProp.PositionHeld);
            pPlayer.teleportTo(blockPos.getX(),blockPos.getY(),blockPos.getZ());
            pPlayer.sendSystemMessage(Component.literal(pPlayer.level().dimension().toString()));
            return InteractionResultHolder.success(pPlayer.getItemInHand(pPlayer.getUsedItemHand()));
        }

        return InteractionResultHolder.fail(pPlayer.getItemInHand(pPlayer.getUsedItemHand()));
    }
//    public int registerGem(ItemStack stack){
//        BlockPosMap.put(BlockPosMap.size(), null);
//        return stack.set(NeverItemProp.GemId,BlockPosMap.size());
//    }
   public int registerGem(ItemStack stack){
       return -1;
   }
    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        pTooltipComponents.add(1,Component.translatable("tooltip.nevermod.teleport_gem"));
        if(Screen.hasShiftDown()){
            //BlockPosMap.get(pStack.get(NeverItemProp.GemId));
            pTooltipComponents.set(1,Component.literal("GemId: "+pStack.get(NeverItemProp.GemId)+" "+ pStack.get(NeverItemProp.PositionHeld)));
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
