package net.zestywings.ttgmod.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;


public class LowGravStatusEffect extends StatusEffect {

    double velX = 0;
    double velZ = 0;

    protected LowGravStatusEffect(StatusEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        entity.setNoDrag(true);
        super.onApplied(entity, attributes, amplifier);
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        entity.setNoDrag(false);
        super.onRemoved(entity, attributes, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(entity.isOnGround()){
                velX = entity.getVelocity().getX();
                velZ = entity.getVelocity().getZ();
        }

        if(entity.getVelocity().getY() != 0){
            entity.setVelocity(velX, (entity.getVelocity().getY()+0.06) , velZ);
            entity.velocityDirty=true;
        }
        entity.fallDistance = (float)(entity.fallDistance * 0.25);

        super.applyUpdateEffect(entity, amplifier);
    }



}
