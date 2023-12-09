package net.zestywings.ttgmod.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.zestywings.ttgmod.damage.ModDamageTypes;
import net.zestywings.ttgmod.util.IEntityDataSaver;
import net.zestywings.ttgmod.util.StoredDamageData;

import java.util.Objects;

public class FalseInvincibleStatusEffect extends StatusEffect {

    protected FalseInvincibleStatusEffect(StatusEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onApplied(entity, attributes, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {

        if(entity.hasStatusEffect(ModEffects.FALSEINVINCIBLE)){
            if(entity.isPlayer()){

                if(Objects.requireNonNull(entity.getStatusEffect(ModEffects.FALSEINVINCIBLE)).getDuration() == 60){
                    entity.getWorld().playSound(null, entity.getBlockPos(), SoundEvent.of(SoundEvents.BLOCK_NOTE_BLOCK_CHIME.value().getId()), SoundCategory.MASTER, 1.5f, 1.0f);
                } else if (Objects.requireNonNull(entity.getStatusEffect(ModEffects.FALSEINVINCIBLE)).getDuration() == 40) {
                    entity.getWorld().playSound(null, entity.getBlockPos(), SoundEvent.of(SoundEvents.BLOCK_NOTE_BLOCK_CHIME.value().getId()), SoundCategory.MASTER, 1.5f, 1.0f);
                } else if (Objects.requireNonNull(entity.getStatusEffect(ModEffects.FALSEINVINCIBLE)).getDuration() == 20) {
                    entity.getWorld().playSound(null, entity.getBlockPos(), SoundEvent.of(SoundEvents.BLOCK_NOTE_BLOCK_CHIME.value().getId()), SoundCategory.MASTER, 1.5f, 1.0f);
                }
            }
        }
        super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        float damageTotal = StoredDamageData.getStoredDamage(((IEntityDataSaver) entity));
        entity.damage(ModDamageTypes.of(entity.getWorld(), ModDamageTypes.BRAVADO_DAMAGE), damageTotal/2);
        StoredDamageData.resetStoredDamage(((IEntityDataSaver) entity));
        super.onRemoved(entity, attributes, amplifier);
    }

}
