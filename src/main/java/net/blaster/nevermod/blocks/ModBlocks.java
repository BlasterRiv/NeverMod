package net.blaster.nevermod.blocks;

import net.blaster.nevermod.Item.ModItems;
import net.blaster.nevermod.NeverMod;
import net.minecraft.core.Registry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.neoforged.neoforge.registries.*;

import java.util.function.Supplier;


public class ModBlocks {
    //register
    public static final DeferredRegister.Blocks BLOCS = DeferredRegister.createBlocks(NeverMod.MOD_ID);

    //blocks
    public static final  DeferredBlock<Block> NeverBlock = registerMyBlock("never_stone",()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE)
            .destroyTime(50)

    ));

    public static final DeferredBlock<DoorBlock> NeverDoor = registerMyBlock("never_door",()->new DoorBlock(BlockSetType.IRON,BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_DOOR)));


    //helper methods
    private static <T extends Block> DeferredBlock<T> registerMyBlock(String name, Supplier<T> blockSupplier){
        DeferredBlock<T> toReturn = BLOCS.register(name, blockSupplier);
        registerMyBlockItem(toReturn);
        return toReturn;
    }

    private static <T extends Block> DeferredItem<BlockItem> registerMyBlockItem(DeferredBlock<T> block){
        return ModItems.ITEMS.registerSimpleBlockItem(block);
    }

    /*archive
    public static final  DeferredBlock<Block> NeverBlock = BLOCS.register("neverkey",()->new Block( BlockBehaviour.Properties.of()));
    private static <T extends Block> <Item> registerMyBlockItem(){}
    */
}
