package net.zestywings.ttgmod.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.text.Text;

import java.util.Objects;


public class SugarHighStatusEffect extends StatusEffect {

    private static final float speedBoost = 2.0f;
    private int startDuration = -1;


    protected SugarHighStatusEffect(StatusEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        this.startDuration = Objects.requireNonNull(entity.getStatusEffect(this)).getDuration();
        super.onApplied(entity, attributes, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(Objects.requireNonNull(entity.getStatusEffect(this)).getDuration() > startDuration/2 ){
            //entity.setVelocity(Math.min(entity.getVelocity().getX()*speedBoost, 0.5f) , entity.getVelocity().getY(),Math.min(entity.getVelocity().getZ()*speedBoost, 0.5f));
            entity.sendMessage(Text.literal(entity.getVelocity().getX() + " " + entity.getVelocity().getZ()));
        }else{
            entity.setVelocity(Math.min(entity.getVelocity().getX()/speedBoost, 0.01f), entity.getVelocity().getY(), Math.min(entity.getVelocity().getZ()/speedBoost, 0.4f));
        }
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onRemoved(entity, attributes, amplifier);
    }

}
