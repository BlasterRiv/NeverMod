package net.blaster.mymodtest.Item;

import net.blaster.mymodtest.MyModTest;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB_DEFERRED_REGISTER =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MyModTest.MOD_ID);

    public static final DeferredHolder<CreativeModeTab,CreativeModeTab> CREATIVE_MODE_TAB_DEFERRED_HOLDER =
             CREATIVE_MODE_TAB_DEFERRED_REGISTER.register(
                     "never_tab",
                     ()->CreativeModeTab.builder()
                             .icon(()->new ItemStack(ModItems.NeverKey.get()))
                             .title(Component.translatable("creativetab.never_tab"))
                             .displayItems((pParameters, pOutput) ->{
                                 pOutput.accept(ModItems.NeverKey.get());
                             })
                             .build()
             );

}

