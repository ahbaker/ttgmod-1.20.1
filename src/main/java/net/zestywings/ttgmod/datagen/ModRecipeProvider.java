package net.zestywings.ttgmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.zestywings.ttgmod.block.ModBlocks;
import net.zestywings.ttgmod.block.custom.BrewKegBlock;
import net.zestywings.ttgmod.item.ModItems;

import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {


        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.BREW_KEG_BLOCK, 1)
                .pattern("SIS")
                .pattern("SBI")
                .pattern("SIS")
                .input('S', ItemTags.SLABS)
                .input('B', Items.BARREL)
                .criterion(hasItem(ModItems.HOPS_ITEM), conditionsFromItem(ModItems.HOPS_ITEM))
                .criterion(hasItem(ModItems.HOPS_SEEDS), conditionsFromItem(ModItems.HOPS_SEEDS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.BREW_KEG_BLOCK)));



    }
}
