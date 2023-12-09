package net.zestywings.ttgmod.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvents;

public class HungryHeartsStatusEffect extends StatusEffect {

    protected HungryHeartsStatusEffect(StatusEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        entity.playSound(SoundEvents.ENTITY_PLAYER_BURP,3,1);
        super.onApplied(entity, attributes, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration % (125 >> amplifier) == 0;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(entity.getWorld().isClient) {
            if (entity.isPlayer()) {
                PlayerEntity player = (PlayerEntity) entity;
                if (player.getHungerManager().getFoodLevel() > 6 && player.getHealth() != player.getMaxHealth()) {
                    player.getHungerManager().add(-4, -2);
                    player.heal(6);
                }
            } else entity.heal(4);
        }
        super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onRemoved(entity, attributes, amplifier);
    }
}
