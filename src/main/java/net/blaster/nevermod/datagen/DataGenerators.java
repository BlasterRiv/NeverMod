package net.blaster.nevermod.datagen;

import net.blaster.nevermod.NeverMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@EventBusSubscriber(modid = NeverMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {
  @SubscribeEvent
  public static void gatherData(GatherDataEvent event) {
    DataGenerator gen = event.getGenerator();
    PackOutput packOutput = gen.getPackOutput();
    ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
    CompletableFuture<HolderLookup.Provider> lookupPtovidrt = event.getLookupProvider();

    BlockTagsProvider blockTagsProvider = new NeverBlockTagProvider(packOutput, lookupPtovidrt, existingFileHelper);

    gen.addProvider(event.includeServer(),
        new LootTableProvider(
            packOutput,
            Collections.emptySet(),
            List.of(
                new LootTableProvider.SubProviderEntry(NeverBlockLootTableProvider::new, LootContextParamSets.BLOCK)),
            lookupPtovidrt));
    gen.addProvider(event.includeServer(),
        new NeverBlockStateProvider(packOutput, existingFileHelper));
    gen.addProvider(event.includeServer(),
        new NeverItemModelProvider(packOutput, existingFileHelper));
    gen.addProvider(event.includeServer(),
        new NeverRecipeProvider(packOutput, lookupPtovidrt));
    gen.addProvider(event.includeServer(),
        blockTagsProvider);
    gen.addProvider(event.includeServer(),
        new NeverItemTagProvider(packOutput, lookupPtovidrt, blockTagsProvider.contentsGetter(), existingFileHelper));
    // gen.addProvider(event.includeServer(), new
    // NeverDataMapProvider(packOutput,lookupPtovidrt));
  }

}
