package net.zestywings.ttgmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.zestywings.ttgmod.block.ModBlocks;
import net.zestywings.ttgmod.screen.BrewKegScreen;
import net.zestywings.ttgmod.screen.ModScreenHandlers;

public class TTGModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HOPS_CROP_BLOCK, RenderLayer.getCutout());

        HandledScreens.register(ModScreenHandlers.BREW_KEG_SCREEN_HANDLER, BrewKegScreen::new);

    }
}
