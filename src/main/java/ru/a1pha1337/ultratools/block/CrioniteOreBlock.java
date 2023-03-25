package ru.a1pha1337.ultratools.block;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import ru.a1pha1337.ultratools.entity.CrioniteSlimeEntity;

import java.util.Random;

import static ru.a1pha1337.ultratools.entity.Entities.CRIONITE_SLIME;

public class CrioniteOreBlock extends Block implements PlayerBlockBreakEvents.Before {
    private static final Settings SETTINGS = FabricBlockSettings.of(Material.STONE).hardness(30f).requiresTool();

    private static final int SLIME_CHANCE = 25;

    public CrioniteOreBlock() {
        this(SETTINGS);
    }

    public CrioniteOreBlock(Settings settings) {
        super(settings);
    }

    @Override
    public boolean beforeBlockBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity) {
        if (state.getBlock() == this) {
            Random rand = new Random();

            if (rand.nextInt(100) + 1 <= SLIME_CHANCE) {
                CrioniteSlimeEntity slimeEntity = new CrioniteSlimeEntity(CRIONITE_SLIME, world);

                slimeEntity.setSize(1, true);
                slimeEntity.setPos(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);

                world.breakBlock(pos, false);
                world.spawnEntity(slimeEntity);
            }
        }

        return true;
    }
}
