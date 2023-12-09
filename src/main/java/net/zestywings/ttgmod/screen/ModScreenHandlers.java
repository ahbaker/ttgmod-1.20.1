package net.zestywings.ttgmod.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.zestywings.ttgmod.TTGMod;


public class ModScreenHandlers {
    public static final ScreenHandlerType<BrewKegScreenHandler> BREW_KEG_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(TTGMod.MOD_ID, "brewing"),
                    new ExtendedScreenHandlerType<>(BrewKegScreenHandler::new));


    public static void registerAllScreenHandlers() {
      TTGMod.LOGGER.info("Registering Screen Handlers for " + TTGMod.MOD_ID);
    }
}

