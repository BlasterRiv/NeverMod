package net.blaster.nevermod.datagen;

import net.blaster.nevermod.Item.ModItems;
import net.blaster.nevermod.NeverMod;
import net.blaster.nevermod.util.NeverTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class NeverItemTagProvider extends ItemTagsProvider {
  public NeverItemTagProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider,
      CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
    super(pOutput, pLookupProvider, pBlockTags, NeverMod.MOD_ID, existingFileHelper);
  }

  @Override
  protected void addTags(HolderLookup.Provider pProvider) {
    tag(NeverTags.Items.GemItems).add(ModItems.AbsorbGem.get()).add(ModItems.LinkingGem.get())
        .add(ModItems.TeleportGem.get()).add(ModItems.DimensionGem.get());
  }
}
