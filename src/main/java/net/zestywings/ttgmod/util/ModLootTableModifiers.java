package net.zestywings.ttgmod.util;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import net.zestywings.ttgmod.item.ModItems;

public class ModLootTableModifiers {

    private static final Identifier SHIPWRECK_SUPPLY_ID = new Identifier("minecraft", "chests/shipwreck_supply");



    public static void modifyLootTables(){
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
                if (SHIPWRECK_SUPPLY_ID == id){
                    LootPool.Builder poolBuilder = LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1))
                            .conditionally(RandomChanceLootCondition.builder(.5f))
                            .with(ItemEntry.builder(ModItems.HOPS_SEEDS))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f,2f)).build());
                    tableBuilder.pool(poolBuilder.build());
                }
        });
    }
}
