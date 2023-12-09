package net.zestywings.ttgmod.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.Identifier;
import net.zestywings.ttgmod.TTGMod;


public class ModEffects {
    public static StatusEffect INVINCIBLE;
    public static StatusEffect IMMOBILE;
    public static StatusEffect ALLGOOD;
    public static StatusEffect BUZZED;
    public static StatusEffect CHORUSCOUNTDOWN;
    public static StatusEffect COOKIE;
    public static StatusEffect DRUNK;
    public static StatusEffect FALSEINVINCIBLE;
    public static StatusEffect FLORAL;
    public static StatusEffect GLOW;
    public static StatusEffect HEAD_CURSE;
    public static Team HEAD_CURSE_TEAM = null;
    public static StatusEffect IGNITE;
    public static StatusEffect LOWGRAV;
    public static StatusEffect PHOTOSYNTHETIC;
    public static StatusEffect SHROOMBOOST;
    public static StatusEffect STOUT;
    public static StatusEffect THORNY;
    public static StatusEffect BUOYANT;
    public static StatusEffect HUNGRYHEARTS;
    public static StatusEffect SUGARHIGH;



    public static StatusEffect registerStatusEffect(String name, StatusEffect effect){
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(TTGMod.MOD_ID, name), effect);
    }

    public static void registerEffects(){
        ALLGOOD = registerStatusEffect("all_good_status_effect", new AllGoodStatusEffect((StatusEffectCategory.BENEFICIAL), 16730955));
        BUOYANT = registerStatusEffect("buoyant_status_effect", new BuoyantStatusEffect((StatusEffectCategory.BENEFICIAL), 7697379));
        BUZZED = registerStatusEffect("buzzed_status_effect", new BuzzedStatusEffect(StatusEffectCategory.NEUTRAL, 16729454));
        CHORUSCOUNTDOWN = registerStatusEffect("chorus_countdown_status_effect", new ChorusCountdownStatusEffect(StatusEffectCategory.NEUTRAL, 11141290));
        COOKIE = registerStatusEffect("cookie_status_effect", new CookieStatusEffect(StatusEffectCategory.BENEFICIAL, 6631190));
        DRUNK = registerStatusEffect("drunk_status_effect", new DrunkStatusEffect(StatusEffectCategory.HARMFUL, 10944316 ));
        FALSEINVINCIBLE = registerStatusEffect("false_invincible_status_effect", new FalseInvincibleStatusEffect((StatusEffectCategory.BENEFICIAL), 16755200));
        FLORAL = registerStatusEffect("floral_status_effect", new FloralStatusEffect((StatusEffectCategory.BENEFICIAL), 5810688));
        GLOW = registerStatusEffect("glow_status_effect", new GlowStatusEffect(StatusEffectCategory.NEUTRAL, 16776960));
        HEAD_CURSE = registerStatusEffect("headcurse_status_effect", new HeadCurseStatusEffect((StatusEffectCategory.NEUTRAL), 16742656));
        HUNGRYHEARTS = registerStatusEffect("hungry_hearts_status_effect", new HungryHeartsStatusEffect((StatusEffectCategory.NEUTRAL), 11869214));
        IGNITE = registerStatusEffect("ignite_status_effect", new IgniteStatusEffect((StatusEffectCategory.BENEFICIAL), 16722432));
        IMMOBILE = registerStatusEffect("immobile_status_effect", new ImmobileStatusEffect(StatusEffectCategory.HARMFUL, 9013641));
        INVINCIBLE = registerStatusEffect("invincible_status_effect", new InvincibleStatusEffect(StatusEffectCategory.BENEFICIAL, 16755200));
        LOWGRAV = registerStatusEffect("low_grav_status_effect", new LowGravStatusEffect((StatusEffectCategory.BENEFICIAL), 13158600));
        PHOTOSYNTHETIC = registerStatusEffect("photosynthetic_status_effect", new PhotosyntheticStatusEffect((StatusEffectCategory.BENEFICIAL), 30976));
        SHROOMBOOST = registerStatusEffect("shroomboost_status_effect", new ShroomBoostStatusEffect((StatusEffectCategory.BENEFICIAL), 13828096));
        STOUT = registerStatusEffect("stout_status_effect", new StoutStatusEffect((StatusEffectCategory.BENEFICIAL), 9327360));
        SUGARHIGH = registerStatusEffect("sugar_high_status_effect", new SugarHighStatusEffect((StatusEffectCategory.BENEFICIAL), 16777215 ));
        THORNY = registerStatusEffect("thorny_status_effect", new ThornyStatusEffect((StatusEffectCategory.BENEFICIAL), 9830144));


    }

}
