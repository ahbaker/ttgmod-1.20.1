package net.zestywings.ttgmod.block.entity;


import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.zestywings.ttgmod.TTGMod;
import net.zestywings.ttgmod.block.ModBlocks;

public class ModBlockEntities {

    public static final BlockEntityType<BrewKegBlockEntity> BREW_KEG_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(TTGMod.MOD_ID, "brew_keg_be"),
                    FabricBlockEntityTypeBuilder.create(BrewKegBlockEntity::new,
                            ModBlocks.BREW_KEG_BLOCK).build());

    public static void registerBlockEntities(){
        TTGMod.LOGGER.info("Registering Block Entities for " + TTGMod.MOD_ID);
    }

}
