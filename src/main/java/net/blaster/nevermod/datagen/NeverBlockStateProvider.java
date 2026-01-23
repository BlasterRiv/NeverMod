package net.blaster.nevermod.datagen;

import net.blaster.nevermod.NeverMod;
import net.blaster.nevermod.blocks.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.function.BiConsumer;

public class NeverBlockStateProvider extends BlockStateProvider {

  public NeverBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
    super(output, NeverMod.MOD_ID, exFileHelper);
  }

  @Override
  protected void registerStatesAndModels() {
    simpleBlockWithItem(ModBlocks.NeverStone.get(), cubeAll(ModBlocks.NeverStone.get()));
    doorBlockWithRenderType(ModBlocks.NeverDoor.get(), ModBlocks.NeverDoor.get().getName().getString(),
        modLoc(blockTexture(ModBlocks.NeverStone.get()).getPath().replaceFirst("stone", "door_bottom")),
        modLoc(blockTexture(ModBlocks.NeverStone.get()).getPath().replaceFirst("stone", "door_top")),
        "minecraft:cutout");
  }
}
