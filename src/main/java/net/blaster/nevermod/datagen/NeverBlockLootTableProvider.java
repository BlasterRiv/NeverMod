package net.blaster.nevermod.datagen;

import net.blaster.nevermod.blocks.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public class NeverBlockLootTableProvider extends BlockLootSubProvider implements LootTableSubProvider {
    //HolderLookup.Provider registries
    protected NeverBlockLootTableProvider() {
        super(Set.of(ModBlocks.NeverStone.asItem()), FeatureFlags.REGISTRY.allFlags());
    }



    @Override
    protected void generate() {
        dropSelf(ModBlocks.NeverStone.get());


    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCS.getEntries().stream().map(Holder::value)::iterator;
    }

}
