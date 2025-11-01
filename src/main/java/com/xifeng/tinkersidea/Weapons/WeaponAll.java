package com.xifeng.tinkersidea.Weapons;

import com.xifeng.tinkersidea.Registry;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;

public class WeaponAll {
    public static GreatSword greatSword;

    public static void initWeapon(RegistryEvent.Register<Item> event) {
        greatSword = new GreatSword();
        Registry.initForgeTool(greatSword, event);
    }
}
