package com.maxgavr.feeder.items;

import com.maxgavr.feeder.Feeder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemBase extends Item {
    public ItemBase() {
        super(new Item.Properties().group(Feeder.TAB));
    }
}
