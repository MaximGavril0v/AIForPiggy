package com.maxgavr.feeder.util;

import com.maxgavr.feeder.Feeder;
import com.maxgavr.feeder.blocks.BlockItemBase;
import com.maxgavr.feeder.blocks.FeederBlock;
import com.maxgavr.feeder.items.ItemBase;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Feeder.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Feeder.MOD_ID);

    public static void init(){
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    // Items
    public static final RegistryObject<Item> FEEDER = ITEMS.register("feeder", ItemBase::new);

    // Blocks
    public static final RegistryObject<Block> FEEDER_BLOCK = BLOCKS.register("feeder_block", FeederBlock::new);

    // Block Items
    public static final RegistryObject<Item> FEEDER_BLOCK_ITEM = ITEMS.register("feeder_block", () -> new BlockItemBase(FEEDER_BLOCK.get()));

}
