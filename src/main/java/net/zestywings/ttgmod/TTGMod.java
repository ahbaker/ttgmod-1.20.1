package net.zestywings.ttgmod;

import net.fabricmc.api.ModInitializer;
import net.zestywings.ttgmod.block.ModBlocks;
import net.zestywings.ttgmod.block.entity.ModBlockEntities;
import net.zestywings.ttgmod.item.ModItems;
import net.zestywings.ttgmod.effect.ModEffects;
import net.zestywings.ttgmod.item.ModItemGroup;
import net.zestywings.ttgmod.recipe.ModRecipes;
import net.zestywings.ttgmod.screen.ModScreenHandlers;
import net.zestywings.ttgmod.util.ModCustomTrades;
import net.zestywings.ttgmod.util.ModLootTableModifiers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TTGMod implements ModInitializer {
	public static final String MOD_ID = "ttgmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItemGroup.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModRecipes.registerRecipes();

		ModLootTableModifiers.modifyLootTables();
		ModCustomTrades.registerCustomTrades();

		ModEffects.registerEffects();
		ModBlockEntities.registerBlockEntities();

		ModScreenHandlers.registerAllScreenHandlers();







		LOGGER.info("TTGMod loading?");

	}


}
