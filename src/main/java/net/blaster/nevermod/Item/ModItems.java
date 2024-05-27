package net.blaster.nevermod.Item;

import net.blaster.nevermod.NeverMod;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.createItems(NeverMod.MOD_ID);
    public static final DeferredHolder<Item,Item> NeverKey = ITEMS.register(
            "neverkey", () -> new Item(new Item.Properties()
                    .stacksTo(1)
        )
    );
}
