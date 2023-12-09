package net.zestywings.ttgmod.event;


import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.zestywings.ttgmod.effect.ModEffects;


public class LoginProtectionHandler implements ServerPlayConnectionEvents.Join {
    @Override
    public void onPlayReady(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        PlayerEntity player = handler.getPlayer();
        if(player.hasStatusEffect(ModEffects.INVINCIBLE)) {
            player.removeStatusEffect(ModEffects.INVINCIBLE);
        }
        player.addStatusEffect(new StatusEffectInstance(ModEffects.INVINCIBLE, 1200));

    }

}





