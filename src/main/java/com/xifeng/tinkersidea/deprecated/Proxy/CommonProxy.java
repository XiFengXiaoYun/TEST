package com.xifeng.tinkersidea.deprecated.Proxy;
/*
import com.xifeng.tinkersidea.TinkersIdea;
import com.xifeng.tinkersidea.Tags;
import com.xifeng.tinkersidea.Weapons.Weapon;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import slimeknights.tconstruct.library.tools.IToolPart;
import slimeknights.tconstruct.library.tools.ToolCore;

@Mod.EventBusSubscriber(modid = Tags.MOD_ID)
public class CommonProxy {

    public void init(FMLInitializationEvent e) {
    }

    public void postInit(FMLPostInitializationEvent e) {
    }


    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        Weapon.init(registry);
    }


    public void registerModels() {
        if(Loader.instance().hasReachedState(LoaderState.INITIALIZATION)) {
            TinkersIdea.LOGGER.error(
                    "Proxy.registerModels has to be called during preInit. Otherwise the models wont be found on first load.");
        }
    }


    public <T extends Item & IToolPart> void registerToolPartModel(T part) {
    }

    public void registerToolModel(ToolCore toolCore) {
    }
}

 */
