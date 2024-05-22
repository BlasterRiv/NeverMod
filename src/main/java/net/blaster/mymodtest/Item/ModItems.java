package net.blaster.mymodtest.Item;

import net.blaster.mymodtest.MyModTest;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;


public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.createItems(MyModTest.MOD_ID);
    public static final DeferredHolder<Item,Item> NeverKey = ITEMS.register(
            "NeverKeyItem", () -> new Item(new Item.Properties(

            ).stacksTo(1)
        )
    );
}
