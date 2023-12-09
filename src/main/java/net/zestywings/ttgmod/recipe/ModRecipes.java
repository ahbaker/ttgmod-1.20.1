package net.zestywings.ttgmod.recipe;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.zestywings.ttgmod.TTGMod;

public class ModRecipes {

    // Define the recipe serializer
    public static final RecipeSerializer<BrewKegRecipe> BREW_KEG_SERIALIZER = new BrewKegRecipe.Serializer();
    // Define the recipe type
    public static final RecipeType<BrewKegRecipe> BREW_KEG_TYPE = new BrewKegRecipe.Type();




    public static void registerRecipes(){
        TTGMod.LOGGER.info("Registering Mod Recipes for " + TTGMod.MOD_ID);

        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(TTGMod.MOD_ID, BrewKegRecipe.Serializer.ID),BrewKegRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(TTGMod.MOD_ID, BrewKegRecipe.Type.ID), BrewKegRecipe.Type.INSTANCE);
    }
}
