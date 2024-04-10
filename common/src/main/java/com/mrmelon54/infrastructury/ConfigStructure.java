package com.mrmelon54.infrastructury;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "infrastructury")
@Config.Gui.Background("minecraft:textures/block/dirt.png")
public class ConfigStructure implements ConfigData {
    public boolean modeEnabled = true;
}
