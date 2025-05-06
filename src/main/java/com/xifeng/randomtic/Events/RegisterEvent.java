package com.xifeng.randomtic.Events;

import com.xifeng.randomtic.Tags;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.events.MaterialEvent;
import slimeknights.tconstruct.library.materials.Material;

@Mod.EventBusSubscriber(modid = Tags.MOD_ID)
public class RegisterEvent {
    @SubscribeEvent
    public void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(Tags.MOD_ID))
        {
            ConfigManager.sync(Tags.MOD_ID, Config.Type.INSTANCE);
        }
    }
/*
    @SubscribeEvent
    public void onMaterialRegistry(MaterialEvent.MaterialRegisterEvent event) {
        Material material = event.material;
        event.setCanceled(false);
    }

 */
}
