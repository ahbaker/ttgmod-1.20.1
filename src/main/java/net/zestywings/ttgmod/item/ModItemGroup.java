package net.zestywings.ttgmod.item;


import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.zestywings.ttgmod.TTGMod;
import net.zestywings.ttgmod.block.ModBlocks;

public class ModItemGroup {

    public static void registerItemGroups(){
        TTGMod.LOGGER.info("Registering Item Groups for " + TTGMod.MOD_ID);
    }
    public static final ItemGroup TTG = Registry.register(Registries.ITEM_GROUP,
            new Identifier(TTGMod.MOD_ID, "ttg"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.ttg"))
                    .icon(() -> new ItemStack(ModItems.OBSIDIAN_SCROLL)).entries((displayContext, entries) -> {
                        entries.add(ModItems.GOLD_SCROLL);
                        entries.add(ModItems.IRON_SCROLL);
                        entries.add(ModItems.COPPER_SCROLL);
                        entries.add(ModItems.OBSIDIAN_SCROLL);
                        entries.add(ModItems.RED_SCROLL);
                        entries.add(ModItems.ORANGE_SCROLL);
                        entries.add(ModItems.YELLOW_SCROLL);
                        entries.add(ModItems.GREEN_SCROLL);
                        entries.add(ModItems.BLUE_SCROLL);
                        entries.add(ModItems.VIOLET_SCROLL);
                        entries.add(ModItems.PINK_SCROLL);

                        entries.add(ModItems.EMPTY_GLASS_PINT);

                        entries.add(ModItems.APPLE_CIDER_PINT);
                        entries.add(ModItems.BEET_RED_ALE_PINT);
                        entries.add(ModItems.CHOCO_STOUT_PINT);
                        entries.add(ModItems.CHORUS_LAGER_PINT);
                        entries.add(ModItems.COOKIE_PORTER_PINT);
                        entries.add(ModItems.FLORAL_PILSNER_PINT);
                        entries.add(ModItems.GLOW_BERRY_SOUR_PINT);
                        entries.add(ModItems.INDIA_PALE_ALE_PINT);
                        entries.add(ModItems.KELPBIER_PINT);
                        entries.add(ModItems.MEAD_PINT);
                        entries.add(ModItems.MILK_STOUT_PINT);
                        entries.add(ModItems.MUSHROOM_ALE_PINT);
                        entries.add(ModItems.PUMPKIN_ALE_PINT);
                        entries.add(ModItems.SWEET_BERRY_LAMBIC_PINT);

                        entries.add(ModItems.EMPTY_WOOD_MUG);

                        entries.add(ModItems.APPLE_CIDER_MUG);
                        entries.add(ModItems.BEET_RED_ALE_MUG);
                        entries.add(ModItems.CHOCO_STOUT_MUG);
                        entries.add(ModItems.CHORUS_LAGER_MUG);
                        entries.add(ModItems.COOKIE_PORTER_MUG);
                        entries.add(ModItems.FLORAL_PILSNER_MUG);
                        entries.add(ModItems.GLOW_BERRY_SOUR_MUG);
                        entries.add(ModItems.INDIA_PALE_ALE_MUG);
                        entries.add(ModItems.KELPBIER_MUG);
                        entries.add(ModItems.MEAD_MUG);
                        entries.add(ModItems.MILK_STOUT_MUG);
                        entries.add(ModItems.MUSHROOM_ALE_MUG);
                        entries.add(ModItems.PUMPKIN_ALE_MUG);
                        entries.add(ModItems.SWEET_BERRY_LAMBIC_MUG);


                        entries.add(ModItems.PRETZEL);

                        entries.add(ModItems.HOPS_ITEM);
                        entries.add(ModItems.HOPS_SEEDS);

                        entries.add(ModBlocks.PRETZEL_BOWL_BLOCK);
                        entries.add(ModBlocks.BREW_KEG_BLOCK);


                        //list items to add to group: entries.add(ModItems.ITEM_NAME);

                        TTGMod.LOGGER.info("Added Items to group for " + TTGMod.MOD_ID);

                    }).build());



}
