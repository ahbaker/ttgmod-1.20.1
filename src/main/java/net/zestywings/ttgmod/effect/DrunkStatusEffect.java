package net.zestywings.ttgmod.effect;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

import java.util.Objects;


public class DrunkStatusEffect extends StatusEffect{

    private static final int MAX_AMPLIFIER = 4;
    private static final int[] DEFAULT_DURATIONS = {3600, 2400, 2400, 2400, 1200};

    Boolean passedOut = false;
    long outTime = 0;
    int tempDur;

    public DrunkStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }


    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        passedOut = false;
        if(entity.isPlayer()){
        //    ((PlayerEntity) entity).incrementStat (TTGMod.TIMES_DRUNK);
        }
        if(entity.hasStatusEffect(ModEffects.BUZZED)){
            entity.removeStatusEffect(ModEffects.BUZZED);
        }
        super.onApplied(entity, attributes, amplifier);

    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        if(amplifier == MAX_AMPLIFIER && entity.isSleeping()){
            entity.wakeUp();
        }
        super.onRemoved(entity, attributes, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(entity.getWorld().isClient()) {
            if (entity.isPlayer()) {
                tempDur = Objects.requireNonNull(entity.getStatusEffect(ModEffects.DRUNK)).getDuration();
            }
            if (tempDur > 200 && entity.isPlayer()) {
                int t = tempDur;
                float x = (float) Math.cos(t / 20f) / (400f - (10 * (float) (Math.pow(2d, (double) amplifier + 1))));
                float z = (float) Math.sin(t / 30f) / (400f - (10 * (float) (Math.pow(2d, (double) amplifier + 1))));


                Vec3d tempVec = new Vec3d(x, 0, z);


                entity.setVelocity(tempVec.add(entity.getVelocity()));
                entity.velocityModified = true;
                entity.velocityDirty = true;


            }

            if (tempDur < 200 && tempDur > 3 && !passedOut && amplifier == MAX_AMPLIFIER) {
                PlayerEntity player = (PlayerEntity) entity;
                if (player.getWorld().isClient) {
                    player.detach();
                    player.sleep(player.getBlockPos());
                    //player.incrementStat(TTGMod.NUM_BLACKOUTS);
                    outTime = player.getWorld().getTime();
                    PlayerEntity[] players = entity.getWorld().getPlayers().toArray(new PlayerEntity[0]);
                    String msg = getPassedOutMessage((PlayerEntity) entity);
                    for (PlayerEntity playerEntity : players) {
                        playerEntity.sendMessage(Text.of(msg));
                    }


                }

                passedOut = true;

            }

            if (Objects.requireNonNull(entity.getStatusEffect(ModEffects.DRUNK)).getDuration() <= 3 && passedOut) {
                entity.wakeUp();
                passedOut = false;
                if (entity.isPlayer()) {
                //    ((PlayerEntity) entity).increaseStat(TTGMod.TIME_PASSED_OUT, ((int) (entity.world.getTime() - outTime)));
                }
            }
            if (Objects.requireNonNull(entity.getStatusEffect(ModEffects.DRUNK)).getDuration() == 1) {
                entity.addStatusEffect(new StatusEffectInstance(ModEffects.DRUNK, 300, 0));
            }
        }


        super.applyUpdateEffect(entity, amplifier);
    }

    private static String getPassedOutMessage(PlayerEntity player){
        int rando = (int)(Math.random() * 10);

        return switch (rando) {
            case 0 -> ("§D§L" + player.getEntityName() + "§r§D has passed out!");
            case 1 -> ("§D§L" + player.getEntityName() + "§r§D drank too much!");
            case 2 -> ("§D§L" + player.getEntityName() + "§r§D passed the eff out!");
            case 3 -> ("§D§L" + player.getEntityName() + "§r§D is druuuuuuunk!");
            case 4 -> ("§D§L" + player.getEntityName() + "§r§D partied too hard!");
            case 5 -> ("§DToo much booze for§L " + player.getEntityName());
            case 6 -> ("§D§L" + player.getEntityName() + "§r§D says hello to the ground");
            case 7 -> ("§DMaybe §L" + player.getEntityName() + "§r§D had one too many drinks!");
            case 8 -> ("§D§L" + player.getEntityName() + "§r§D has been hitting the bottle a little to much!");
            case 9 -> ("§D§L" + player.getEntityName() + "§r§D passed out hard, someone draw something on their face!");
            default -> ("§D§L" + player.getEntityName() + "§r§Dis druuuuunk!");
        };

    }

    public int getMaxAmplifier(){
        return MAX_AMPLIFIER;
    }

}