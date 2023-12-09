package net.zestywings.ttgmod.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;


public class AllGoodStatusEffect extends StatusEffect {

    int origDuration = 0;


    protected AllGoodStatusEffect(StatusEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        entity.removeStatusEffect(this);
        super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        StatusEffectInstance[] statuses = entity.getStatusEffects().toArray(new StatusEffectInstance[0]);
        for (StatusEffectInstance instance: statuses)
        {
            if(!instance.getEffectType().isBeneficial()) {
                entity.removeStatusEffect(instance.getEffectType());
            }
        }
        super.onApplied(entity, attributes, amplifier);
    }


    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        if(origDuration==0){origDuration = duration;}
        return origDuration == duration + 60;
    }
}
