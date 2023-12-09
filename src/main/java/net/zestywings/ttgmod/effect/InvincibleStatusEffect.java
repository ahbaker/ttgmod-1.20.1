package net.zestywings.ttgmod.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.math.BlockPos;

public class InvincibleStatusEffect extends StatusEffect {
    private BlockPos location;

    public InvincibleStatusEffect(StatusEffectCategory category, int color){
        super(category, color);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        location = entity.getBlockPos();
        super.onApplied(entity, attributes, amplifier);

    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(!entity.getWorld().isClient()){
            if(entity.getBlockPos().getSquaredDistance(location)> 3){
                entity.removeStatusEffect(ModEffects.INVINCIBLE);
            }
            super.applyUpdateEffect(entity,amplifier);
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onRemoved(entity, attributes, amplifier);
    }
}
