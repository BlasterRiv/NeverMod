package net.blaster.nevermod.Item.NeverItems.Gems;
import net.blaster.nevermod.util.NeverItemProp;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

public class TeleportGem extends Item implements GemsItemInterface {
    public TeleportGem(Properties pProperties) {
        super(pProperties);
    }


//    @Override
//    public InteractionResult useOn(UseOnContext pContext) {
//
//        return super.useOn(pContext);
//    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        //spawn a projectile going forwword
       // Player pPlayer = pContext.getPlayer();
       // Level pLevel=pContext.getLevel();
        if(pLevel instanceof ServerLevel serverlevel){
            pLevel.gameEvent(GameEvent.PROJECTILE_SHOOT, new Vec3(pPlayer.getX(),pPlayer.getY(),pPlayer.getZ()),GameEvent.Context.of(pPlayer));

            pPlayer.sendSystemMessage(Component.literal("used"));
            //pLevel.addFreshEntity();
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
    public int registerGem(ItemStack stack){
        return -1;
    }


//    @Override
//    public InteractionResult useOn(UseOnContext pContext) {
//
//
//        return InteractionResult.SUCCESS;
//    }
}
