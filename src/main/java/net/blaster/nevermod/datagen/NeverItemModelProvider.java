package net.blaster.nevermod.datagen;

import net.blaster.nevermod.Item.ModItems;
import net.blaster.nevermod.NeverMod;
import net.blaster.nevermod.blocks.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

public class NeverItemModelProvider extends ItemModelProvider {

    public NeverItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, NeverMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
       basicItem(ModItems.AncientGold.get());
       basicItem(ModItems.RawAncientGold.get());
       basicItem(ModItems.PowerGem.get());
       basicItem(ModItems.LinkingGem.get());
       basicItem(ModItems.TeleportGem.get());
       basicItem(ModItems.DimensionGem.get());
       basicItem(ModItems.NeverKey.get());
       //registerAllItems();

        basicItem(ModBlocks.NeverDoor.asItem());
    }
    private void registerAllItems(){
        for(DeferredHolder<Item, ? extends Item> item: ModItems.ITEMS.getEntries()){
           // if(item.){basicItem(item.get());}

        }
    }
}
