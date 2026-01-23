package net.blaster.nevermod.Item.NeverItems.Gems;

import net.blaster.nevermod.util.NeverItemProp;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.server.level.TicketType;
import net.minecraft.core.BlockPos;
import org.jetbrains.annotations.NotNull;
import net.minecraft.world.level.ChunkPos;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LinkingGem extends Item implements GemsItemInterface {
  private final Map<Integer, Mob> MobMap;
  // private static final BooleanProperty is;

  public LinkingGem(Properties pProperties) {
    super(pProperties);
    MobMap = new HashMap<Integer, Mob>();
  }

  @Override
  public @NotNull InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer,
      LivingEntity pInteractionTarget, InteractionHand pUsedHand) {
    if (pPlayer.level().isClientSide) {
      pPlayer.sendSystemMessage(Component.literal("Client"));
      return InteractionResult.PASS;
    }
    if (!(pInteractionTarget instanceof Mob)) {
      pPlayer.sendSystemMessage(Component.literal("Invalid Target"));
      return InteractionResult.FAIL;
    }
    pPlayer.sendSystemMessage(Component.literal("is Entity"));
    if (!pPlayer.isHolding(pStack.getItem())) {
      pPlayer.sendSystemMessage(Component.literal("not correct hand"));
      return InteractionResult.FAIL;
    }
    int stackId = pStack.get(NeverItemProp.GemId);
    pPlayer.sendSystemMessage(Component.literal("" + stackId));
    if (stackId == -1) {
      stackId = registerGem(pStack);
      pPlayer.sendSystemMessage(Component.literal("stackId: " + stackId));
    }

    if (getMob(stackId) == (null)) {
      setMob(stackId, (Mob) pInteractionTarget);
      pPlayer.sendSystemMessage(Component.literal("Target Linked " + getMob(stackId) + "stackId: " + stackId));
      pPlayer.getItemInHand(pUsedHand).set(NeverItemProp.DimensionHeld, pPlayer.level().dimension());
      return InteractionResult.SUCCESS;
    }
    if (pPlayer.level().getServer().getLevel(pStack.get(NeverItemProp.DimensionHeld)).dimensionType() != pPlayer.level()
        .dimensionType()) {
      pPlayer.sendSystemMessage(Component.literal("can only swap withen same diemension"));
      return InteractionResult.FAIL;
    }

    pPlayer.sendSystemMessage(Component.literal("stackId: " + stackId));
    pPlayer.sendSystemMessage(Component.literal("map: " + MobMap));
    setMob(stackId, swapMob(stackId, (Mob) pInteractionTarget, (ServerLevel) pPlayer.level()));
    pPlayer.sendSystemMessage(Component.literal("swap  " + getMob(stackId)));
    return InteractionResult.SUCCESS;

  }

  private Mob getMob(int id) {
    return MobMap.get(id);
  }

  private void setMob(int id, Mob mob) {
    MobMap.put(id, mob);
    // pPlayer.getItemInHand(pUsedHand).set(NeverItemProp.DimensionTypeHeld.get(),pPlayer.level().dimensionType());
  }

  private Mob swapMob(int id, Mob newMob, ServerLevel level) {
    Mob ogMob = getMob(id);
    double[] tempPostion = { newMob.getX(), newMob.getY(), newMob.getZ() };
    // event entity tetlport, ot place ticket if chuncks are not loading automatily

    // level.getServer().getPlayerList().getPlayerByName("BlasterRiv")
    // .sendSystemMessage(Component.literal(" to x: " + ogMob.getX() + "y: " +
    // ogMob.getY() + "z: " + ogMob.getZ()));
    // newMob.teleportTo(ogMob.getX(), ogMob.getY(), ogMob.getZ());
    // newMob.moveTo(ogMob.getX(), ogMob.getY(), ogMob.getZ());
    // level.getServer().getPlayerList().getPlayerByName("BlasterRiv").sendSystemMessage(Component.literal("move"));pPlayer.changeDimension(

    BlockPos blockPos = BlockPos.containing(ogMob.getX(), ogMob.getY(), ogMob.getZ());
    newMob.changeDimension(
        new DimensionTransition(level, blockPos.getCenter(),
            newMob.getDeltaMovement(), newMob.getRotationVector().y, newMob.getRotationVector().x,
            DimensionTransition.PLACE_PORTAL_TICKET));

    // level.getChunkSource().addRegionTicket(TicketType.POST_TELEPORT,
    // new ChunkPos(BlockPos.containing(ogMob.getX(), ogMob.getY(), ogMob.getZ())),
    // (int) newMob.distanceTo(ogMob), 31, true);
    // level.getServer().getPlayerList().getPlayerByName("BlasterRiv").sendSystemMessage(Component.literal("ticket"));
    newMob.teleportTo(tempPostion[0], tempPostion[1], tempPostion[2]);
    return newMob;
  }

  public int registerGem(ItemStack stack) {
    stack.set(NeverItemProp.GemId, MobMap.size());
    MobMap.put(MobMap.size(), null);
    return MobMap.size() - 1;
  }

  @Override
  public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
    if (!pLevel.isClientSide) {
      if (pPlayer.isCrouching()) {
        setMob(pPlayer.getMainHandItem().get(NeverItemProp.GemId), null);
        pPlayer.getItemInHand(pUsedHand).set(NeverItemProp.DimensionHeld.get(), null);
        pPlayer.sendSystemMessage(Component.literal("Cleared " + pPlayer.getMainHandItem().get(NeverItemProp.GemId)));
        return InteractionResultHolder.success(pPlayer.getItemInHand(pPlayer.getUsedItemHand()));
      }
    }
    return InteractionResultHolder.fail(pPlayer.getItemInHand(pPlayer.getUsedItemHand()));

  }

  @Override
  public void onDestroyed(ItemEntity itemEntity, DamageSource damageSource) {

    super.onDestroyed(itemEntity, damageSource);
  }

  @Override
  public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents,
      TooltipFlag pTooltipFlag) {
    pTooltipComponents.add(1, Component.translatable("tooltip.nevermod.linking_gem"));
    if (Screen.hasShiftDown()) {
      getMob(pStack.get(NeverItemProp.GemId));
      pTooltipComponents.set(1, Component
          .literal("GemId: " + pStack.get(NeverItemProp.GemId) + " " + getMob(pStack.get(NeverItemProp.GemId))));
    }
    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
  }

}
// try {
// Mob tempMob=pStack.get(NeverModItemProperties.Mobheld);
// double[] tempPostion= {pInteractionTarget.getX(), pInteractionTarget.getY(),
// pInteractionTarget.getZ()};
// pPlayer.sendSystemMessage(Component.literal("1"));
// if(tempMob==null){ pPlayer.sendSystemMessage(Component.literal("do nul " +
// pStack.get(NeverModItemProperties.Mobheld)));}
// pInteractionTarget.teleportToWithTicket(tempMob.getX(), tempMob.getY(),
// tempMob.getZ());
// pPlayer.sendSystemMessage(Component.literal("2"));
// tempMob.teleportToWithTicket(tempPostion[0],tempPostion[1],tempPostion[2]);
// //targetToBeSwapped =(Mob) pInteractionTarget;
// pStack.set(NeverModItemProperties.Mobheld,(Mob) pInteractionTarget);
// pPlayer.sendSystemMessage(Component.literal("Target Swapped"));
// return InteractionResult.SUCCESS;
// }catch (NullPointerException npe){
// pStack.set(NeverModItemProperties.Mobheld,(Mob) pInteractionTarget);
// //targetToBeSwapped = (Mob) pInteractionTarget;
// pPlayer.sendSystemMessage(Component.literal("Target Linked " +
// pStack.get(NeverModItemProperties.Mobheld)));
// return InteractionResult.SUCCESS;
// }
