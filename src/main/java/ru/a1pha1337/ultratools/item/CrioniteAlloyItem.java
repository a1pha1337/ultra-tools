package ru.a1pha1337.ultratools.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;

public class CrioniteAlloyItem extends Item {
    private static final Settings SETTINGS = new FabricItemSettings();

    public CrioniteAlloyItem() {
        this(SETTINGS);
    }

    public CrioniteAlloyItem(Settings settings) {
        super(settings);
    }
}
