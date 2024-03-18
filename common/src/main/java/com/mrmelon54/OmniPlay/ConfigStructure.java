package com.mrmelon54.OmniPlay;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "omniplay")
@Config.Gui.Background("minecraft:textures/block/dirt.png")
public class ConfigStructure implements ConfigData {
    public boolean modeEnabled = true;
}
