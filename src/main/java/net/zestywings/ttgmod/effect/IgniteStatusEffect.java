package net.zestywings.ttgmod.effect;



import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;


public class IgniteStatusEffect extends StatusEffect {

    public IgniteStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }


    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(!entity.getWorld().isClient()) {

            if(entity.getLastAttackTime()-entity.age==-1 && entity.getAttacking()!=null){
                entity.getAttacking().setOnFireFor(3);
            }
        }
        super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}