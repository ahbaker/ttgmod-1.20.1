package net.zestywings.ttgmod.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import net.zestywings.ttgmod.TTGMod;
import net.zestywings.ttgmod.block.custom.BrewKegBlock;
import net.zestywings.ttgmod.block.custom.HopsCropBlock;
import net.zestywings.ttgmod.block.custom.pretzel_bowl_block;


public class ModBlocks {

    public static final Block PRETZEL_BOWL_BLOCK = registerBlock("pretzel_bowl_block",
            new pretzel_bowl_block(FabricBlockSettings.create().breakInstantly().nonOpaque()));

    public static final Block HOPS_CROP_BLOCK = Registry.register(Registries.BLOCK, new Identifier(TTGMod.MOD_ID, "hops_crop_block"),
            new HopsCropBlock(FabricBlockSettings.copyOf(Blocks.WHEAT)));

    public static final Block BREW_KEG_BLOCK = registerBlock("brew_keg_block",
            new BrewKegBlock(FabricBlockSettings.create().nonOpaque().mapColor(MapColor.OAK_TAN).instrument(Instrument.BASS).strength(2.5f).sounds(BlockSoundGroup.WOOD).burnable()));






    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(TTGMod.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM, new Identifier(TTGMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    private static Block registerBlockWithoutItem(String name, Block block){
        return Registry.register(Registries.BLOCK, new Identifier(TTGMod.MOD_ID, name), block);
    }

    public static void registerModBlocks(){
        TTGMod.LOGGER.debug("Registering ModBlocks for " + TTGMod.MOD_ID);
    }
}
