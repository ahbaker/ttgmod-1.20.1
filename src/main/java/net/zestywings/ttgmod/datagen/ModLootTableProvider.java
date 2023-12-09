package net.zestywings.ttgmod.datagen;


import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.predicate.StatePredicate;
import net.zestywings.ttgmod.block.ModBlocks;
import net.zestywings.ttgmod.block.custom.HopsCropBlock;
import net.zestywings.ttgmod.item.ModItems;


public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.BREW_KEG_BLOCK);
        addDrop(ModBlocks.PRETZEL_BOWL_BLOCK);

        BlockStatePropertyLootCondition.Builder hopsBuilder = BlockStatePropertyLootCondition.builder(ModBlocks.HOPS_CROP_BLOCK)
                .properties(StatePredicate.Builder.create().exactMatch(HopsCropBlock.AGE, 7));
        addDrop(ModBlocks.HOPS_CROP_BLOCK, cropDrops(ModBlocks.HOPS_CROP_BLOCK, ModItems.HOPS_ITEM, ModItems.HOPS_SEEDS, hopsBuilder));


    }
}