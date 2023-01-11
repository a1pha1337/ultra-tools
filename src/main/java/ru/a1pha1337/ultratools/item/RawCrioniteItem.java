package ru.a1pha1337.ultratools.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;

public class RawCrioniteItem extends Item {
    private static final Settings SETTINGS = new FabricItemSettings();

    public RawCrioniteItem() {
        this(SETTINGS);
    }

    public RawCrioniteItem(Settings settings) {
        super(settings);
    }
}
