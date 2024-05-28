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
            .destroyTime(30)));

    private static <T extends Block> DeferredBlock<Block> registerMyBlock(String name, Supplier<T> blockSupplier){
        DeferredBlock<Block> toReturn = BLOCS.register(name, blockSupplier);
        registerMyBlockItem(toReturn);
        return toReturn;
    }

    private static DeferredItem<BlockItem> registerMyBlockItem(DeferredBlock<Block> block){
        return ModItems.ITEMS.registerSimpleBlockItem(block);
    }
    //private static <T extends Block> <Item> registerMyBlockItem(){}
}
