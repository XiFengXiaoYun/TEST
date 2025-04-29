package com.xifeng.randomtic.Common;


import slimeknights.mantle.pulsar.config.ForgeCFG;

@net.minecraftforge.common.config.Config(
        modid = "randomtic",
        category = ""
)
public class Config {
    @net.minecraftforge.common.config.Config.Ignore
    public static ForgeCFG pulseConfig = new ForgeCFG("random_tic", "a test");

    public Config() {}
}
