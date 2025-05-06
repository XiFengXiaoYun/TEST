package com.xifeng.tinkersidea.Weapons;

import com.xifeng.tinkersidea.RandomTic;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.TinkerRegistryClient;
import slimeknights.tconstruct.library.client.ToolBuildGuiInfo;
import slimeknights.tconstruct.library.tools.ToolCore;


public class Weapon {
    //public static CommonProxy proxy;
    public static ToolCore glaive;

    //public static ToolPart long_blade;

    public Weapon() {
    }

    public static void init(IForgeRegistry<Item> register) {
        glaive = new Glaive();
        register.register(glaive);
        TinkerRegistry.registerToolForgeCrafting(glaive);
        RandomTic.proxy.registerToolModel(glaive);
        //TinkerRegistry.registerStencilTableCrafting((ItemStack) glaive);
    }

    public static void initGUI() {
        glaive = new Glaive();
        ToolBuildGuiInfo glaiveInfo = new ToolBuildGuiInfo(glaive);
        glaiveInfo.addSlotPosition(12, 55);
        glaiveInfo.addSlotPosition(42, 27);
        glaiveInfo.addSlotPosition(27, 41);
        TinkerRegistryClient.addToolBuilding(glaiveInfo);
    }
}
