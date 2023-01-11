package ru.a1pha1337.ultratools.item.strategy;

import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public interface MiningStrategy {
    void breakBlocks(BlockPos centerBlock, Direction direction, ServerPlayerInteractionManager manager);
}
