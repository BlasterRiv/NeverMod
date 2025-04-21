package net.blaster.nevermod.Item.NeverItems.Gems;
import net.blaster.nevermod.util.NeverItemProp;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LinkingGem extends Item implements GemsItemInterface {
    private final Map<Integer,Mob> MobMap;

    public LinkingGem(Properties pProperties) {
        super(pProperties);
        MobMap=new HashMap<Integer,Mob>();
    }


    @Override
    public @NotNull InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand){
        if(pPlayer.level().isClientSide){
            pPlayer.sendSystemMessage(Component.literal("Client"));
            return InteractionResult.PASS;}
        if(!(pInteractionTarget instanceof Mob)){
            pPlayer.sendSystemMessage(Component.literal("Invalid Target"));
            return InteractionResult.FAIL;
        }
        pPlayer.sendSystemMessage(Component.literal("is Entity"));
        if(!pPlayer.isHolding(pStack.getItem())){
            pPlayer.sendSystemMessage(Component.literal("not correct hand"));
            return InteractionResult.FAIL;
        }

        int stackId=pStack.get(NeverItemProp.GemId);
        pPlayer.sendSystemMessage(Component.literal(""+stackId));
        if(stackId == -1  ){registerGem(pStack);}
        if(getMob(stackId) == (null)){
            setMob(stackId,(Mob) pInteractionTarget);
            pPlayer.sendSystemMessage(Component.literal("Target Linked " + getMob(stackId)));
            return InteractionResult.SUCCESS;
        }

        setMob(stackId,swapMob(stackId, (Mob) pInteractionTarget));
        pPlayer.sendSystemMessage(Component.literal("swap  " + getMob(stackId)));
        return InteractionResult.SUCCESS;

    }
    private Mob getMob(int id){
        return MobMap.get(id);
    }
    private void setMob(int id,Mob mob){
        MobMap.put(id,mob);
    }
    private Mob swapMob(int id,Mob newMob){
        Mob ogMob=getMob(id);
        double[] tempPostion= {newMob.getX(), newMob.getY(), newMob.getZ()};
        newMob.teleportToWithTicket(ogMob.getX(), ogMob.getY(), ogMob.getZ());
        ogMob.teleportToWithTicket(tempPostion[0],tempPostion[1],tempPostion[2]);
        return newMob;
    }
    public int registerGem(ItemStack stack){
        MobMap.put(MobMap.size(), null);
        return stack.set(NeverItemProp.GemId,MobMap.size());
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide) {
            if (pPlayer.isCrouching()) {
                setMob(pPlayer.getMainHandItem().get(NeverItemProp.GemId),null);
                pPlayer.sendSystemMessage(Component.literal("Cleared "+pPlayer.getMainHandItem().get(NeverItemProp.GemId)));
                return InteractionResultHolder.success(pPlayer.getItemInHand(pPlayer.getUsedItemHand()));
            }
        }
        return InteractionResultHolder.fail(pPlayer.getItemInHand(pPlayer.getUsedItemHand()));

    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        pTooltipComponents.add(1,Component.translatable("tooltip.nevermod.linking_gem"));
        if(Screen.hasShiftDown()){
            getMob(pStack.get(NeverItemProp.GemId));
            pTooltipComponents.set(1,Component.literal(""+ getMob(pStack.get(NeverItemProp.GemId))));
        }
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }


}
//        try {
//            Mob tempMob=pStack.get(NeverModItemProperties.Mobheld);
//            double[] tempPostion= {pInteractionTarget.getX(), pInteractionTarget.getY(), pInteractionTarget.getZ()};
//            pPlayer.sendSystemMessage(Component.literal("1"));
//            if(tempMob==null){ pPlayer.sendSystemMessage(Component.literal("do nul " + pStack.get(NeverModItemProperties.Mobheld)));}
//            pInteractionTarget.teleportToWithTicket(tempMob.getX(), tempMob.getY(), tempMob.getZ());
//            pPlayer.sendSystemMessage(Component.literal("2"));
//            tempMob.teleportToWithTicket(tempPostion[0],tempPostion[1],tempPostion[2]);
//            //targetToBeSwapped =(Mob) pInteractionTarget;
//            pStack.set(NeverModItemProperties.Mobheld,(Mob) pInteractionTarget);
//            pPlayer.sendSystemMessage(Component.literal("Target Swapped"));
//            return InteractionResult.SUCCESS;
//        }catch (NullPointerException npe){
//            pStack.set(NeverModItemProperties.Mobheld,(Mob) pInteractionTarget);
//            //targetToBeSwapped = (Mob) pInteractionTarget;
//            pPlayer.sendSystemMessage(Component.literal("Target Linked " + pStack.get(NeverModItemProperties.Mobheld)));
//            return InteractionResult.SUCCESS;
//        }