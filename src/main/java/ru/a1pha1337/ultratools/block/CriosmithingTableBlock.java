package ru.a1pha1337.ultratools.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class CriosmithingTableBlock extends Block implements BlockEntityProvider {
    private static final Settings SETTINGS = FabricBlockSettings.of(Material.METAL).hardness(10f).requiresTool();

    public CriosmithingTableBlock() {
        this(SETTINGS);
    }

    public CriosmithingTableBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CriosmithingTableBlockEntity(pos, state);
    }
}
