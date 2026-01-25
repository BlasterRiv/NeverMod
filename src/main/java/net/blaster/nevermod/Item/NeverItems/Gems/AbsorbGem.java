package net.blaster.nevermod.Item.NeverItems.Gems;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class AbsorbGem extends Item implements GemsItemInterface {
  public AbsorbGem(Properties pProperties) {
    super(pProperties);
  }

  public int registerGem(ItemStack stack) {
    return -1;
  }

}
