package net.zestywings.ttgmod.effect;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ItemScatterer;

public class CookieStatusEffect extends StatusEffect {

    private long lastProcTime = 0;

    public CookieStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }


    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(!entity.getWorld().isClient()){
            int procLimit;
            procLimit = 300 - (amplifier * 60);


            if(entity.getDamageTracker().getTimeSinceLastAttack()<20){
                if(entity.getWorld().getTime() - lastProcTime >= procLimit) {
                    this.lastProcTime = entity.getWorld().getTime();

                    int margin = 3;
                    double x = entity.getX() + (Math.random() * margin * 2) - margin;
                    double y = entity.getY() + 0.5;
                    double z = entity.getZ() + (Math.random() * margin * 2) - margin;
                    entity.getWorld().playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ENTITY_CHICKEN_EGG, SoundCategory.BLOCKS, 2f, 1f);
                    ItemScatterer.spawn(entity.getWorld(), x, y, z, new ItemStack(Items.COOKIE, 1));

                    super.applyUpdateEffect(entity, amplifier);
                }
            }
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
