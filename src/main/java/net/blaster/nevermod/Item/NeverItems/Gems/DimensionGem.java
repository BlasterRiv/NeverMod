package net.blaster.nevermod.Item.NeverItems.Gems;
import net.blaster.nevermod.util.NeverItemProp;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DimensionGem extends Item implements GemsItemInterface {
    private final Map<Integer, ServerLevel> DimensionMap;
    public DimensionGem(Properties pProperties) {
        super(pProperties);
        DimensionMap=new HashMap<Integer, ServerLevel>();
    }

    public int registerGem(ItemStack stack){

        return -1;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        if(pLevel instanceof ServerLevel serverlevel){
            int gemid=stack.get(NeverItemProp.GemId);
            if(gemid==-1){registerGem(stack);}

        if(pPlayer.isCrouching()){
            //DimensionMap.put(gemid,null);
            stack.set(NeverItemProp.ServerLevelHeld,null);
            //pPlayer.getItemInHand(pUsedHand).set(NeverItemProp.DimensionTypeHeld.get(),null);
            pPlayer.sendSystemMessage(Component.literal("Cleared "+gemid));
            return InteractionResultHolder.success(stack);
        }

        if(stack.get(NeverItemProp.ServerLevelHeld)==null){
            //DimensionMap.put(gemid,));
            stack.set(NeverItemProp.ServerLevelHeld,pLevel.dimension());
            pPlayer.sendSystemMessage(Component.literal("set dimension "+stack.get(NeverItemProp.ServerLevelHeld)));
            return InteractionResultHolder.success(stack);
        }
            if(stack.get(NeverItemProp.ServerLevelHeld)==pLevel.dimension()){
                pPlayer.sendSystemMessage(Component.literal("already in  "+stack.get(NeverItemProp.ServerLevelHeld)));
                return InteractionResultHolder.success(stack);
            }
        pPlayer.sendSystemMessage(Component.literal("go to dimension "+stack.get(NeverItemProp.ServerLevelHeld)));
        pPlayer.changeDimension(pPlayer.getServer().getLevel(stack.get(NeverItemProp.ServerLevelHeld.get())));


        return InteractionResultHolder.success(stack);
        }
        return InteractionResultHolder.fail(stack);
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        pTooltipComponents.add(1,Component.translatable("tooltip.nevermod.dimension_gem"));
        if(Screen.hasShiftDown()){
            pTooltipComponents.set(1,Component.literal("GemId: "+pStack.get(NeverItemProp.GemId)+" "+ pStack.get(NeverItemProp.ServerLevelHeld)));
        }
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
