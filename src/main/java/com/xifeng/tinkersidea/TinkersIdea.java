package com.xifeng.tinkersidea;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = Tags.MOD_ID,
        name = Tags.MOD_NAME,
        version = Tags.VERSION,
        dependencies = "required-after:tconstruct"
)
public class TinkersIdea {
    public static final Logger LOGGER = LogManager.getLogger(Tags.MOD_NAME);


    public TinkersIdea() {}

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        LOGGER.info("POST!");
    }
}
