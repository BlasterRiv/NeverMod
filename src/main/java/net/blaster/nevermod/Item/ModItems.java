package net.blaster.nevermod.Item;

import net.blaster.nevermod.Item.NeverItems.NeverKeyItem;
import net.blaster.nevermod.NeverMod;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModItems {
    //register
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(NeverMod.MOD_ID);
    //items

    private static final Item.Properties deffPropertiesRareSinglet =new Item.Properties().stacksTo(1).rarity(Rarity.EPIC);

    public static final DeferredHolder<Item,Item> NeverKey = ITEMS.register(
            "neverkey", () -> new NeverKeyItem(new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.EPIC)
        ));

    public static final DeferredItem<Item> PowerGem = ITEMS.registerItem("power_gem",gemItem -> new Item(deffPropertiesRareSinglet));
    public static final DeferredItem<Item> DimensionGem = ITEMS.register("dimension_gem",()->new Item(deffPropertiesRareSinglet));
    public static final DeferredItem<Item> TeleportGem = ITEMS.registerSimpleItem("teleport_gem", deffPropertiesRareSinglet);
    public static final DeferredItem<Item> LinkingGem = ITEMS.registerSimpleItem("linking_gem", deffPropertiesRareSinglet);


    public static final DeferredItem<Item> AncientGold = ITEMS.registerSimpleItem("ancient_gold",new Item.Properties().rarity(Rarity.EPIC));
    public static final DeferredItem<Item> RawAncientGold = ITEMS.registerSimpleItem("raw_ancient_gold",new Item.Properties());

}
/*
*
*
*
*
*
* */
