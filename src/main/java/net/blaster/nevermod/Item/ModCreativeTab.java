package net.blaster.nevermod.Item;

import net.blaster.nevermod.NeverMod;
import net.blaster.nevermod.blocks.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeTab {
    public static final DeferredRegister<CreativeModeTab> MOD_CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, NeverMod.MOD_ID);

    public static final DeferredHolder<CreativeModeTab,CreativeModeTab> CREATIVE_MODE_TAB_DEFERRED_HOLDER =
             MOD_CREATIVE_MODE_TAB.register(
                     "never_tab",
                     ()->CreativeModeTab.builder()
                             .icon(()->new ItemStack(ModItems.NeverKey.get()))
                             .title(Component.translatable("creativetab.never_tab"))
                             .displayItems((pParameters, pOutput) ->{
                                 pOutput.accept(ModItems.NeverKey.get());
                                 pOutput.accept(ModBlocks.NeverBlock.get());
                                 pOutput.accept(ModItems.AncientGold.get());
                                 pOutput.accept(ModItems.RawAncientGold.get());

                             })
                             .build()
             );

}

