package net.blaster.nevermod.blocks;

import net.blaster.nevermod.Item.ModItems;
import net.blaster.nevermod.NeverMod;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.*;

import java.util.function.Supplier;


public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCS = DeferredRegister.createBlocks(NeverMod.MOD_ID);

    //public static final  DeferredBlock<Block> NeverBlock = BLOCS.register("neverkey",()->new Block( BlockBehaviour.Properties.of()));

    public static final  DeferredBlock<Block> NeverBlock = registerMyBlock("never_stone",()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE)
            .destroyTime(99)));

    private static <T extends Block> DeferredBlock<Block> registerMyBlock(String name, Supplier<T> blockSupplier){
        DeferredBlock<Block> toReturn = BLOCS.register(name, blockSupplier);
        registerMyBlockItem(name,toReturn);
        return toReturn;
    }

    private static DeferredHolder<Item,Item> registerMyBlockItem(String name, DeferredBlock<Block> block){
        return ModItems.ITEMS.register("",()-> new BlockItem(block.get(),new Item.Properties()));
    }
    //private static <T extends Block> <Item> registerMyBlockItem(){}
}
