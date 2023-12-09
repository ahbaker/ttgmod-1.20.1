package net.zestywings.ttgmod.event;

import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.zestywings.ttgmod.effect.ModEffects;


public class LogoutProtectionHandler implements ServerPlayConnectionEvents.Disconnect {

    @Override
    public void onPlayDisconnect(ServerPlayNetworkHandler handler, MinecraftServer server) {

        PlayerEntity player = handler.getPlayer();
        if(player.hasStatusEffect(ModEffects.INVINCIBLE)){
            player.removeStatusEffect(ModEffects.INVINCIBLE);
        }

    }
}






