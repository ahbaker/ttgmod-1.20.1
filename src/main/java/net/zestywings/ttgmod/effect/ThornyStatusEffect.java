package net.zestywings.ttgmod.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageRecord;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.zestywings.ttgmod.damage.ModDamageTypes;

import java.util.Objects;

public class ThornyStatusEffect extends StatusEffect {

    private DamageRecord damageRecord = null;


    protected ThornyStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(!entity.getWorld().isClient()){
            if(entity.getDamageTracker().getTimeSinceLastAttack() <= 10){
                if(entity.getAttacker().isAlive() ){
                    Objects.requireNonNull(entity.getAttacker()).damage(ModDamageTypes.of(entity.getWorld(), ModDamageTypes.THORN_DAMAGE), 1f);
                }
            }
        }
        super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

}


