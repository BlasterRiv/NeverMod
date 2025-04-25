package net.blaster.nevermod.util;

import net.blaster.nevermod.NeverMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class NeverTags {
    public static class Blocks{



        private static TagKey<Block> createTag(String name){
            return BlockTags.create(ResourceLocation.tryBuild(NeverMod.MOD_ID,name));
        }
    }
    public static class Items{

        public static final TagKey<Item> GemItems= createTag("gem_items");


        private static TagKey<Item> createTag(String name){
            return ItemTags.create(ResourceLocation.tryBuild(NeverMod.MOD_ID,name));
        }
    }
}
