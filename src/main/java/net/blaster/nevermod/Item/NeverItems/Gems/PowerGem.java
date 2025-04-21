package net.blaster.nevermod.Item.NeverItems.Gems;
import net.blaster.nevermod.util.NeverItemProp;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class PowerGem extends Item implements GemsItemInterface {
    public PowerGem(Properties pProperties) {
        super(pProperties);
    }
    public int registerGem(ItemStack stack){
        return -1;
    }


}
