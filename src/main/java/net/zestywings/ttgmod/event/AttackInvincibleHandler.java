package net.zestywings.ttgmod.event;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.zestywings.ttgmod.effect.ModEffects;
import net.zestywings.ttgmod.util.IEntityDataSaver;
import net.zestywings.ttgmod.util.StoredDamageData;


public class AttackInvincibleHandler implements ServerLivingEntityEvents.AllowDamage {

    @Override
    public boolean allowDamage(LivingEntity entity, DamageSource source, float amount) {
        if (entity.hasStatusEffect(ModEffects.INVINCIBLE)) {
            return false;
        }


        if (entity.hasStatusEffect(ModEffects.FALSEINVINCIBLE)){
            StoredDamageData.addStoredDamage(((IEntityDataSaver) entity), amount);
            return false;
        }

        return true;
    }
}
