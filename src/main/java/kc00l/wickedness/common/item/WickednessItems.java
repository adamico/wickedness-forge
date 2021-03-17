package kc00l.wickedness.common.item;

import kc00l.wickedness.Reference;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class WickednessItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);
    public static final RegistryObject<Item> DUST = ITEMS.register("item_wickedness_dust", WickednessDust::new);
}
