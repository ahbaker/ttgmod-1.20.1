package net.zestywings.ttgmod.effect;

import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.scoreboard.Team;
import net.minecraft.text.Text;

import java.util.Objects;

import static net.minecraft.util.Formatting.LIGHT_PURPLE;


public class HeadCurseStatusEffect extends StatusEffect {

    Text oldName;
    final String trickName = "TRICKED! ";
    PlayerEntity player;
    ItemStack oldHat;
    ItemStack newHat = ItemStack.EMPTY;


    public HeadCurseStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }



    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {

        super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {

        if(ModEffects.HEAD_CURSE_TEAM == null){
            ModEffects.HEAD_CURSE_TEAM = new Team(entity.getWorld().getScoreboard(), trickName);
        }

        if(entity.isPlayer()) {

            player = (PlayerEntity) entity;
            oldName = player.getName();

            if(!player.getInventory().getStack(39).isEmpty()){
                oldHat = ItemStack.EMPTY;
            }
            oldHat = player.getInventory().getStack(39);
            newHat.setCount(1);

            String[] tempNames = Objects.requireNonNull(player.getServer()).getPlayerManager().getPlayerNames();
            Text[] playerNames = new Text[tempNames.length];
            int playerIndex = 0;
            for(int i=0;i<tempNames.length;i++){
                playerNames[i] = Text.of(tempNames[i]);
                if(playerNames[i] == oldName){
                    playerIndex = i;
                }
            }
            entity.sendMessage(Text.of(String.valueOf(playerIndex)));

            int rando = (int)(Math.random() * playerNames.length);


            if (rando == playerIndex) {
                switch ((int) Math.floor(Math.random() * 3)) {
                    case 0:
                        newHat = setZombieHead();
                        break;
                    case 1:
                        newHat = setCreeperHead();
                        break;
                    case 2:
                        newHat = setPumpkinHead();
                    default:
                        newHat = setSkellyHead();
                }
            } else {
                newHat.getOrCreateNbt().putString("SkullOwner", (playerNames[rando]).getString());
                newHat.addEnchantment(Enchantments.BINDING_CURSE, 80);
            }

            ((PlayerEntity) entity).getInventory().setStack(39, newHat);

            ModEffects.HEAD_CURSE_TEAM.setColor(LIGHT_PURPLE);
            ModEffects.HEAD_CURSE_TEAM.setPrefix(Text.of(trickName));


            player.getWorld().getScoreboard().addPlayerToTeam(oldName.getString(), ModEffects.HEAD_CURSE_TEAM);

        }
        super.onApplied(entity, attributes, amplifier);
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        if(entity.isPlayer()){
            ((PlayerEntity) entity).getInventory().setStack(39, oldHat);
        }
        entity.getWorld().getScoreboard().removePlayerFromTeam(entity.getEntityName(), ModEffects.HEAD_CURSE_TEAM);
        super.onRemoved(entity, attributes, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    private ItemStack setZombieHead(){

        ItemStack zombieHat = new ItemStack(Items.ZOMBIE_HEAD);
        zombieHat.addEnchantment(Enchantments.BINDING_CURSE,80);
        return(zombieHat);
    }
    private ItemStack setCreeperHead(){

        ItemStack creeperHat = new ItemStack(Items.CREEPER_HEAD);
        creeperHat.addEnchantment(Enchantments.BINDING_CURSE,80);
        return(creeperHat);
    }
    private ItemStack setSkellyHead(){

        ItemStack skellyHat = new ItemStack(Items.SKELETON_SKULL);
        skellyHat.addEnchantment(Enchantments.BINDING_CURSE,80);
        return(skellyHat);
    }
    private ItemStack setPumpkinHead(){

        ItemStack pumpkinHat = new ItemStack(Items.CARVED_PUMPKIN);
        pumpkinHat.addEnchantment(Enchantments.BINDING_CURSE,80);
        return(pumpkinHat);
    }
}
