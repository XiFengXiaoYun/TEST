package com.xifeng.tinkersidea.materials;

import com.xifeng.tinkersidea.Weapons.wizardry.MagicMaterialStats;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.tools.TinkerMaterials;

public class MagicMaterials {
    public static void initMagicMaterials() {
        Material.UNKNOWN.addStats(new MagicMaterialStats(1.0f, 100));
        TinkerRegistry.addMaterialStats(TinkerMaterials.wood, new MagicMaterialStats(1.5f, 200));
        TinkerRegistry.addMaterialStats(TinkerMaterials.manyullyn, new MagicMaterialStats(10.0f, 1500));
        TinkerRegistry.addMaterialStats(TinkerMaterials.bone, new MagicMaterialStats(2.0f, 500));
    }
}
