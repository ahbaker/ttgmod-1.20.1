package net.zestywings.ttgmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.zestywings.ttgmod.block.ModBlocks;
import net.zestywings.ttgmod.block.custom.HopsCropBlock;
import net.zestywings.ttgmod.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerCrop(ModBlocks.HOPS_CROP_BLOCK, HopsCropBlock.AGE, 0,1,2,3,4,5,6,7);

        blockStateModelGenerator.registerSimpleState(ModBlocks.BREW_KEG_BLOCK);

        blockStateModelGenerator.registerSimpleState(ModBlocks.PRETZEL_BOWL_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.BLUE_SCROLL, Models.GENERATED);
        itemModelGenerator.register(ModItems.COPPER_SCROLL, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLD_SCROLL, Models.GENERATED);
        itemModelGenerator.register(ModItems.GREEN_SCROLL, Models.GENERATED);
        itemModelGenerator.register(ModItems.IRON_SCROLL, Models.GENERATED);
        itemModelGenerator.register(ModItems.OBSIDIAN_SCROLL, Models.GENERATED);
        itemModelGenerator.register(ModItems.ORANGE_SCROLL, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_SCROLL, Models.GENERATED);
        itemModelGenerator.register(ModItems.RED_SCROLL, Models.GENERATED);
        itemModelGenerator.register(ModItems.VIOLET_SCROLL, Models.GENERATED);
        itemModelGenerator.register(ModItems.YELLOW_SCROLL, Models.GENERATED);



    }
}
