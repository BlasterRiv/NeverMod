package net.blaster.nevermod.Item;

import net.blaster.nevermod.NeverMod;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModItems {
    //register
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(NeverMod.MOD_ID);
    //items
    public static final DeferredHolder<Item,Item> NeverKey = ITEMS.register(
            "neverkey", () -> new Item(new Item.Properties()
                    .stacksTo(1)
        )
    );

    public static final DeferredItem<Item> AncientGold = ITEMS.registerSimpleItem("ancient_gold",new Item.Properties().rarity(Rarity.EPIC));
    public static final DeferredItem<Item> RawAncientGold = ITEMS.registerSimpleItem("raw_ancient_gold",new Item.Properties());

}
