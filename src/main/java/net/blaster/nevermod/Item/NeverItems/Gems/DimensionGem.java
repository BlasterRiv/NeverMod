package net.blaster.nevermod.Item.NeverItems.Gems;

import net.blaster.nevermod.util.NeverItemProp;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.DimensionTransition;

import net.minecraft.core.BlockPos;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DimensionGem extends Item implements GemsItemInterface {
  private final Map<Integer, DimensionTransition> DimensionMap;

  public DimensionGem(Properties pProperties) {
    super(pProperties);
    DimensionMap = new HashMap<Integer, DimensionTransition>();
  }

  public int registerGem(ItemStack stack) {

    return -1;
  }

  @Override
  public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
    ItemStack stack = pPlayer.getItemInHand(pUsedHand);
    if (pLevel instanceof ServerLevel) {
      int gemid = stack.get(NeverItemProp.GemId);
      if (gemid == -1) {
        registerGem(stack);
      }

      if (pPlayer.isCrouching()) {
        stack.set(NeverItemProp.DimensionHeld, null);
        pPlayer.sendSystemMessage(Component.literal("Cleared " + gemid));
        return InteractionResultHolder.success(stack);
      }

      if (stack.get(NeverItemProp.DimensionHeld) == null) {
        stack.set(NeverItemProp.DimensionHeld, pLevel.dimension());
        stack.set(NeverItemProp.PositionHeld, pPlayer.blockPosition());
        pPlayer.sendSystemMessage(Component.literal("set dimension " + stack.get(NeverItemProp.DimensionHeld)));
        return InteractionResultHolder.success(stack);
      }

      ServerLevel target_dimension = pPlayer.getServer().getLevel(stack.get(NeverItemProp.DimensionHeld));
      if (target_dimension.dimension() == pLevel.dimension()) {
        pPlayer.sendSystemMessage(Component.literal("already in  " + stack.get(NeverItemProp.DimensionHeld)));
        return InteractionResultHolder.success(stack);
      }
      pPlayer.sendSystemMessage(Component.literal("go to dimension " + target_dimension));
      BlockPos blockPos = stack.get(NeverItemProp.PositionHeld).above();
      pPlayer.changeDimension(
          new DimensionTransition(target_dimension, blockPos.getCenter(),
              pPlayer.getDeltaMovement(), pPlayer.getRotationVector().y, pPlayer.getRotationVector().x,
              DimensionTransition.PLACE_PORTAL_TICKET));

      // pPlayer.teleportTo(blockPos.getX(), blockPos.getY(), blockPos.getZ());

      return InteractionResultHolder.success(stack);
    }
    return InteractionResultHolder.fail(stack);
  }

  @Override
  public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents,
      TooltipFlag pTooltipFlag) {
    pTooltipComponents.add(1, Component.translatable("tooltip.nevermod.dimension_gem"));
    if (Screen.hasShiftDown()) {
      pTooltipComponents.set(1, Component
          .literal("GemId: " + pStack.get(NeverItemProp.GemId) + " " + pStack.get(NeverItemProp.DimensionHeld)));
    }
    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
  }
}
