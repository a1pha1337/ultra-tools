package ru.a1pha1337.ultratools.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;

public class CrioniteOreBlock extends Block {
    private static final Settings SETTINGS = FabricBlockSettings.of(Material.STONE).hardness(30f).requiresTool();

    public CrioniteOreBlock() {
        this(SETTINGS);
    }

    public CrioniteOreBlock(Settings settings) {
        super(settings);
    }
}
