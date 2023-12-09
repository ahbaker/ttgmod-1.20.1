package net.zestywings.ttgmod.effect;


import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.zestywings.ttgmod.damage.ModDamageTypes;

import java.util.List;

public class ShroomBoostStatusEffect extends StatusEffect {

    public ShroomBoostStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(!entity.getWorld().isClient()) {
            if(entity.getVelocity().y < -0.09) {
                Vec3d blockBelow = new Vec3d(entity.getPos().getX(), entity.getPos().getY()-1, entity.getPos().getZ());
                Box targetArea = new Box(blockBelow.x + 0.5, blockBelow.y + 0.5, blockBelow.z + 0.5, blockBelow.x - 0.5, blockBelow.y - 0.5, blockBelow.z - 0.5);

                List<Entity> targets = entity.getWorld().getOtherEntities(entity, targetArea);
                if (!targets.isEmpty()) {
                    entity.setInvulnerable(true);

                    PlayerEntity player = (PlayerEntity) entity;
                    player.setVelocityClient(entity.getVelocity().getX(), 0.6, entity.getVelocity().getZ());
                    player.velocityModified=true;
                    player.velocityDirty=true;

                    targets.forEach(target -> target.damage(ModDamageTypes.of(entity.getWorld(), ModDamageTypes.STOMP_DAMAGE), 2f));
                    entity.setInvulnerable(false);
                }
            }
        }
        super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST,12000, 1, false, false, false));
        super.onApplied(entity, attributes, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
