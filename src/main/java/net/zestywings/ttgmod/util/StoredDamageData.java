package net.zestywings.ttgmod.util;

import net.minecraft.nbt.NbtCompound;

public class StoredDamageData {
    public static float addStoredDamage(IEntityDataSaver entity, float amount){
        NbtCompound nbt = entity.getPersistentData();
        float damageTotal = nbt.getFloat("storedDamage");
        damageTotal += amount;
        damageTotal = Math.max(0, damageTotal);

        nbt.putDouble("storedDamage", damageTotal);
        //sync data

        return damageTotal;

    }

    public static float removeStoredDamage(IEntityDataSaver entity, float amount){
        NbtCompound nbt = entity.getPersistentData();
        float damageTotal = nbt.getFloat("storedDamage");
        damageTotal -= amount;
        damageTotal = Math.max(0, damageTotal);

        nbt.putDouble("storedDamage", damageTotal);
        //sync data

        return damageTotal;
    }

    public static float getStoredDamage(IEntityDataSaver entity){
        NbtCompound nbt = entity.getPersistentData();
        return nbt.getFloat("storedDamage");
    }

    public static void resetStoredDamage(IEntityDataSaver entity){
        NbtCompound nbt = entity.getPersistentData();
        nbt.putFloat("storedDamage",0);
    }


}
