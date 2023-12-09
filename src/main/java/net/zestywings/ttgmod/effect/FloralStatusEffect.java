package net.zestywings.ttgmod.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;


public class FloralStatusEffect extends StatusEffect {

    public FloralStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {

        int x = entity.getBlockX();
        int y = entity.getBlockY();
        int z = entity.getBlockZ();
        int radius = 1+amplifier; //range of effect
        ItemStack tempBoneMealStack = new ItemStack(Items.BONE_MEAL, 64);

        for(int i = x-radius;i <= x+radius;i++){
            for(int j = y-radius;j <= y+radius;j++){
                for(int k = z-radius;k <= z+radius;k++){
                    BlockPos blockPos = new BlockPos(i,j,k);

                    if (BoneMealItem.useOnFertilizable(tempBoneMealStack, entity.getWorld(), blockPos)) {
                        if (!entity.getWorld().isClient) {
                            entity.getWorld().syncWorldEvent(1505, blockPos, 0);
                        }
                    }
                }
            }
        }


        super.onApplied(entity, attributes, amplifier);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return false;
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onRemoved(entity, attributes, amplifier);
    }
}
