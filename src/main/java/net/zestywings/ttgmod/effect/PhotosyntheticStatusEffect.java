package net.zestywings.ttgmod.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;


public class PhotosyntheticStatusEffect extends StatusEffect {

    public PhotosyntheticStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }


    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(!entity.getWorld().isClient()){
            if(entity.getWorld().isDay() && !entity.getWorld().isThundering() && entity.getWorld().getDimension().hasSkyLight()){
                if(entity.isPlayer()){
                    ((PlayerEntity) entity).getHungerManager().setFoodLevel(
                            ((PlayerEntity) entity).getHungerManager().getFoodLevel() + 1);
                    ((PlayerEntity) entity).getHungerManager().setSaturationLevel(
                            ((PlayerEntity) entity).getHungerManager().getSaturationLevel() + 2);
                }else{
                    entity.heal(4);
                }
            }
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int i = 60 >> amplifier;
        if (i > 0) {
            return duration % i == 0;
        } else {
            return true;
        }
    }

}
