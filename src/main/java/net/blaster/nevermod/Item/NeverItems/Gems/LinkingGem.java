package net.blaster.nevermod.Item.NeverItems.Gems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForgeConfig;
import org.jetbrains.annotations.NotNull;

public class LinkingGem extends Item implements GemsItemInterface {
    private Mob targetToBeSwapped=null;

    public LinkingGem(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand){
        pPlayer.sendSystemMessage(Component.literal("is Entity"));

            if(!(pInteractionTarget instanceof Mob)){
                pPlayer.sendSystemMessage(Component.literal("Invalid Target"));
                return InteractionResult.FAIL;
            }

            if(targetToBeSwapped == null){
                targetToBeSwapped = (Mob) pInteractionTarget;
                pPlayer.sendSystemMessage(Component.literal("Target Linked"));
                return InteractionResult.SUCCESS;
            }

            double[] tempPostion= {pInteractionTarget.getX(), pInteractionTarget.getY(), pInteractionTarget.getZ()};
            pInteractionTarget.teleportToWithTicket(targetToBeSwapped.getX(), targetToBeSwapped.getY(), targetToBeSwapped.getZ());
            targetToBeSwapped.teleportToWithTicket(tempPostion[0],tempPostion[1],tempPostion[2]);
            //targetToBeSwapped =null;
            pPlayer.sendSystemMessage(Component.literal("Target Swapped"));
            return InteractionResult.SUCCESS;


    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        if(!pLevel.isClientSide()){
            pPlayer.sendSystemMessage(Component.literal("not Implemented,currntly, emptys"));
            targetToBeSwapped =null;
        }

       return super.use(pLevel, pPlayer, pUsedHand);
    }
}
