package data.damage_type;


import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.zestywings.ttgmod.TTGMod;


public class ModDamageTypes implements DamageTypes {

    public static RegistryKey<DamageType> STOMP_DAMAGE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(TTGMod.MOD_ID, "stomp_damage_type"));
    public static RegistryKey<DamageType> BRAVADO_DAMAGE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(TTGMod.MOD_ID, "bravado_damage_type"));

    public static RegistryKey<DamageType> THORN_DAMAGE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(TTGMod.MOD_ID, "thorns_damage_type"));

    public static DamageSource of(World world, RegistryKey<DamageType> key) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
    }

}
