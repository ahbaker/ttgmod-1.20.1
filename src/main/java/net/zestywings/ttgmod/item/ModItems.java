package net.zestywings.ttgmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.zestywings.ttgmod.TTGMod;
import net.zestywings.ttgmod.block.ModBlocks;
import net.zestywings.ttgmod.item.custom.*;


public class ModItems {



    public static final Item GOLD_SCROLL = registerItem("gold_scroll",
            new Item(new FabricItemSettings()));
    public static final Item IRON_SCROLL = registerItem("iron_scroll",
            new Item(new FabricItemSettings()));
    public static final Item COPPER_SCROLL = registerItem("copper_scroll",
            new Item(new FabricItemSettings()));
    public static final Item OBSIDIAN_SCROLL = registerItem("obsidian_scroll",
            new Item(new FabricItemSettings()));
    public static final Item RED_SCROLL = registerItem("red_scroll",
            new Item(new FabricItemSettings()));
    public static final Item ORANGE_SCROLL = registerItem("orange_scroll",
            new Item(new FabricItemSettings()));
    public static final Item YELLOW_SCROLL = registerItem("yellow_scroll",
            new Item(new FabricItemSettings()));
    public static final Item GREEN_SCROLL = registerItem("green_scroll",
            new Item(new FabricItemSettings()));
    public static final Item BLUE_SCROLL = registerItem("blue_scroll",
            new Item(new FabricItemSettings()));
    public static final Item VIOLET_SCROLL = registerItem("violet_scroll",
            new Item(new FabricItemSettings()));
    public static final Item PINK_SCROLL = registerItem("pink_scroll",
            new Item(new FabricItemSettings()));


    public static final Item PRETZEL = registerSnack("pretzel", null, 1, 0);

    public static final Item EMPTY_WOOD_MUG = registerItem("empty_wood_mug",
            new Item(new FabricItemSettings()));
    public static final Item EMPTY_GLASS_PINT = registerItem("empty_glass_pint",
            new Item(new FabricItemSettings()));

    public static final Item HOPS_SEEDS = registerItem("hops_seeds",
            new AliasedBlockItem(ModBlocks.HOPS_CROP_BLOCK, new FabricItemSettings()));

    public static final Item HOPS_ITEM = registerItem("hops_item",
            new Item(new FabricItemSettings().food(
                    new FoodComponent.Builder().hunger(0).saturationModifier(0).statusEffect(
                            new StatusEffectInstance(StatusEffects.POISON, 300, 0), 1).build())));



    public static final Item APPLE_CIDER_PINT = registerItem("apple_cider_pint", new AppleCiderPintItem(new FabricItemSettings().food(ModFoodComponents.APPLE_CIDER_PINT)));
    public static final Item BEET_RED_ALE_PINT = registerItem("beet_red_ale_pint", new BeetRedAlePintItem(new FabricItemSettings().food(ModFoodComponents.BEET_RED_ALE_PINT)));
    public static final Item CHOCO_STOUT_PINT = registerItem("choco_stout_pint", new ChocoStoutPintItem(new FabricItemSettings().food(ModFoodComponents.CHOCO_STOUT_PINT)));
    public static final Item CHORUS_LAGER_PINT = registerItem("chorus_lager_pint", new ChorusLagerPintItem(new FabricItemSettings().food(ModFoodComponents.CHORUS_LAGER_PINT)));
    public static final Item COOKIE_PORTER_PINT = registerItem("cookie_porter_pint", new CookiePorterPintItem(new FabricItemSettings().food(ModFoodComponents.COOKIE_PORTER_PINT)));
    public static final Item FLORAL_PILSNER_PINT = registerItem("floral_pilsner_pint", new FloralPilsnerPintItem(new FabricItemSettings().food(ModFoodComponents.FLORAL_PILSNER_PINT)));
    public static final Item GLOW_BERRY_SOUR_PINT = registerItem("glow_berry_sour_pint", new GlowBerrySourPintItem(new FabricItemSettings().food(ModFoodComponents.GLOW_BERRY_SOUR_PINT)));
    public static final Item INDIA_PALE_ALE_PINT = registerItem("india_pale_ale_pint", new IndiaPaleAlePintItem(new FabricItemSettings().food(ModFoodComponents.INDIA_PALE_ALE_PINT)));
    public static final Item KELPBIER_PINT = registerItem("kelpbier_pint", new KelpbierPintItem(new FabricItemSettings().food(ModFoodComponents.KELPBIER_PINT)));
    public static final Item MEAD_PINT = registerItem("mead_pint", new MeadPintItem(new FabricItemSettings().food(ModFoodComponents.MEAD_PINT)));
    public static final Item MILK_STOUT_PINT = registerItem("milk_stout_pint", new MilkStoutPintItem(new FabricItemSettings().food(ModFoodComponents.MILK_STOUT_PINT)));
    public static final Item MUSHROOM_ALE_PINT = registerItem("mushroom_ale_pint", new MushroomAlePintItem(new FabricItemSettings().food(ModFoodComponents.MUSHROOM_ALE_PINT)));
    public static final Item PUMPKIN_ALE_PINT = registerItem("pumpkin_ale_pint", new PumpkinAlePintItem(new FabricItemSettings().food(ModFoodComponents.PUMPKIN_ALE_PINT)));
    public static final Item SWEET_BERRY_LAMBIC_PINT = registerItem("sweet_berry_lambic_pint", new SweetBerryLambicPintItem(new FabricItemSettings().food(ModFoodComponents.SWEET_BERRY_LAMBIC_PINT)));
    public static final Item APPLE_CIDER_MUG = registerItem("apple_cider_mug", new AppleCiderMugItem(new FabricItemSettings().food(ModFoodComponents.APPLE_CIDER_MUG)));
    public static final Item BEET_RED_ALE_MUG = registerItem("beet_red_ale_mug", new BeetRedAleMugItem(new FabricItemSettings().food(ModFoodComponents.BEET_RED_ALE_MUG)));
    public static final Item CHOCO_STOUT_MUG = registerItem("choco_stout_mug", new BeetRedAleMugItem(new FabricItemSettings().food(ModFoodComponents.CHOCO_STOUT_MUG)));
    public static final Item CHORUS_LAGER_MUG = registerItem("chorus_lager_mug", new ChorusLagerMugItem(new FabricItemSettings().food(ModFoodComponents.CHORUS_LAGER_MUG)));
    public static final Item COOKIE_PORTER_MUG = registerItem("cookie_porter_mug", new CookiePorterMugItem(new FabricItemSettings().food(ModFoodComponents.COOKIE_PORTER_MUG)));
    public static final Item FLORAL_PILSNER_MUG = registerItem("floral_pilsner_mug", new FloralPilsnerMugItem(new FabricItemSettings().food(ModFoodComponents.FLORAL_PILSNER_MUG)));
    public static final Item GLOW_BERRY_SOUR_MUG = registerItem("glow_berry_sour_mug", new GlowBerrySourMugItem(new FabricItemSettings().food(ModFoodComponents.GLOW_BERRY_SOUR_MUG)));
    public static final Item INDIA_PALE_ALE_MUG = registerItem("india_pale_ale_mug", new IndiaPaleAleMugItem(new FabricItemSettings().food(ModFoodComponents.INDIA_PALE_ALE_MUG)));
    public static final Item KELPBIER_MUG = registerItem("kelpbier_mug", new KelpbierMugItem(new FabricItemSettings().food(ModFoodComponents.KELPBIER_MUG)));
    public static final Item MEAD_MUG = registerItem("mead_mug", new MeadMugItem(new FabricItemSettings().food(ModFoodComponents.MEAD_MUG)));
    public static final Item MILK_STOUT_MUG = registerItem("milk_stout_mug", new MilkStoutMugItem(new FabricItemSettings().food(ModFoodComponents.MILK_STOUT_MUG)));
    public static final Item MUSHROOM_ALE_MUG = registerItem("mushroom_ale_mug", new MushroomAleMugItem(new FabricItemSettings().food(ModFoodComponents.MUSHROOM_ALE_MUG)));
    public static final Item PUMPKIN_ALE_MUG = registerItem("pumpkin_ale_mug", new PumpkinAleMugItem(new FabricItemSettings().food(ModFoodComponents.PUMPKIN_ALE_MUG)));
    public static final Item SWEET_BERRY_LAMBIC_MUG = registerItem("sweet_berry_lambic_mug", new SweetBerryLambicMugItem(new FabricItemSettings().food(ModFoodComponents.SWEET_BERRY_LAMBIC_MUG)));









    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(TTGMod.MOD_ID, name), item);
    }

    private static Item registerSnack(String name, Item remainder, int hunger, float saturation){
        return Registry.register(Registries.ITEM, new Identifier(TTGMod.MOD_ID, name),
                new Item(new FabricItemSettings().recipeRemainder(remainder)
                        .food(new FoodComponent.Builder()
                            .hunger(hunger).saturationModifier(saturation)
                            .snack().build())));
    }

    public static void registerModItems(){
        TTGMod.LOGGER.info("Registering Mod Items for " + TTGMod.MOD_ID);

    }

}
