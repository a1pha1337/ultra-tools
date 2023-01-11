package ru.a1pha1337.ultratools.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;

public class CrionitePlatingItem extends Item {
    private static final Settings SETTINGS = new FabricItemSettings();

    public CrionitePlatingItem() {
        this(SETTINGS);
    }

    public CrionitePlatingItem(Settings settings) {
        super(settings);
    }
}
