package net.zestywings.ttgmod.item.custom;


import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.zestywings.ttgmod.effect.ModEffects;
import net.zestywings.ttgmod.item.ModItems;

import java.util.Objects;


public class CookiePorterMugItem extends Item {
    private static final int MAX_USE_TIME = 40;

    public CookiePorterMugItem(Settings settings) {
        super(settings);
    }

    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        super.finishUsing(stack, world, user);
        if (user instanceof ServerPlayerEntity serverPlayerEntity) {
            Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        }

        if (!world.isClient) {
            user.addStatusEffect(new StatusEffectInstance(ModEffects.COOKIE, 3600));
            if (user.hasStatusEffect(ModEffects.BUZZED)) {
                user.removeStatusEffect(ModEffects.BUZZED);
                user.addStatusEffect(new StatusEffectInstance(ModEffects.DRUNK, 6000, 0));

            } else if (user.hasStatusEffect(ModEffects.DRUNK)) {
                user.addStatusEffect(new StatusEffectInstance(ModEffects.DRUNK, 6000, Math.min((Objects.requireNonNull(user.getStatusEffect(ModEffects.DRUNK)).getAmplifier() + 1), 4 )));
            } else {
                user.addStatusEffect(new StatusEffectInstance(ModEffects.BUZZED, 6000));
            }
        }


        if (stack.isEmpty()) {
            return new ItemStack(ModItems.EMPTY_WOOD_MUG);
        } else {
            if (user instanceof PlayerEntity && !((PlayerEntity) user).getAbilities().creativeMode) {
                ItemStack itemStack = new ItemStack(ModItems.EMPTY_WOOD_MUG);
                PlayerEntity playerEntity = (PlayerEntity) user;
                if (!playerEntity.getInventory().insertStack(itemStack)) {
                    playerEntity.dropItem(itemStack, false);
                }
            }
            return stack;
        }
    }


    public int getMaxUseTime(ItemStack stack) {
        return MAX_USE_TIME;
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    public SoundEvent getDrinkSound() {
        return SoundEvents.ITEM_HONEY_BOTTLE_DRINK;
    }

    public SoundEvent getEatSound() {
        return SoundEvents.ITEM_HONEY_BOTTLE_DRINK;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }
}
