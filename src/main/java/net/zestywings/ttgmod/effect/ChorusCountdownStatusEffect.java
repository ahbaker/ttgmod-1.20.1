package net.zestywings.ttgmod.effect;

import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.damage.DamageRecord;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.TeleportTarget;

import java.util.Objects;


public class ChorusCountdownStatusEffect extends StatusEffect {

    public ChorusCountdownStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    Vec3d returnLocation;
    DamageRecord damageRecord = null;
    ServerWorld returnWorld;


    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        if(entity.getHealth()<6) {
            entity.setHealth(6.0f);
        }
        returnLocation = entity.getPos();
        returnWorld = Objects.requireNonNull(entity.getServer()).getWorld(entity.getWorld().getRegistryKey());
        super.onApplied(entity, attributes, amplifier);
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        if(!entity.getWorld().isClient) {
            TeleportTarget target = new TeleportTarget(returnLocation, entity.getVelocity(), entity.getYaw(), entity.getPitch());
            FabricDimensions.teleport(entity, returnWorld, target);
            entity.getWorld().playSound(null, entity.getBlockPos(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.MASTER, 1.0f, 1.0f);
        }
        super.onRemoved(entity, attributes, amplifier);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(!entity.getWorld().isClient()) {

            if(entity.hasStatusEffect(ModEffects.CHORUSCOUNTDOWN)){
                if(entity.isPlayer()){
                    if(Objects.requireNonNull(entity.getStatusEffect(ModEffects.CHORUSCOUNTDOWN)).getDuration() == 60){
                        entity.getWorld().playSound(null, entity.getBlockPos(), SoundEvent.of(SoundEvents.BLOCK_NOTE_BLOCK_CHIME.value().getId()), SoundCategory.MASTER, 1.5f, 1.0f);
                    } else if (Objects.requireNonNull(entity.getStatusEffect(ModEffects.CHORUSCOUNTDOWN)).getDuration() == 40) {
                        entity.getWorld().playSound(null, entity.getBlockPos(), SoundEvent.of(SoundEvents.BLOCK_NOTE_BLOCK_CHIME.value().getId()), SoundCategory.MASTER, 1.5f, 1.0f);
                    } else if (Objects.requireNonNull(entity.getStatusEffect(ModEffects.CHORUSCOUNTDOWN)).getDuration() == 20) {
                        entity.getWorld().playSound(null, entity.getBlockPos(), SoundEvent.of(SoundEvents.BLOCK_NOTE_BLOCK_CHIME.value().getId()), SoundCategory.MASTER, 1.5f, 1.0f);
                    }
                }
            }


            if(entity.getHealth() <= 6){
                entity.removeStatusEffect(this);
            }
        }
        super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

}