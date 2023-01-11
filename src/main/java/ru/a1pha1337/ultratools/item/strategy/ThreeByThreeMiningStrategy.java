package ru.a1pha1337.ultratools.item.strategy;

import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class ThreeByThreeMiningStrategy implements MiningStrategy {
    private static final int[][] UP_DOWN_OFFSETS = {
            {1, 0, -1},
            {1, 0, 0},
            {1, 0, 1},
            {-1, 0, -1},
            {-1, 0, 0},
            {-1, 0, 1},
            {0, 0, -1},
            {0, 0, 1}
    };

    private static final int[][] EAST_WEST_OFFSETS = {
            {0, 1, -1},
            {0, 1, 0},
            {0, 1, 1},
            {0, -1, -1},
            {0, -1, 0},
            {0, -1, 1},
            {0, 0, -1},
            {0, 0, 1}
    };

    private static final int[][] NORTH_SOUTH_OFFSETS = {
            {-1, 1, 0},
            {0, 1, 0},
            {1, 1, 0},
            {-1, -1, 0},
            {0, -1, 0},
            {1, -1, 0},
            {-1, 0, 0},
            {1, 0, 0}
    };

    private static final int[][] DEFAULT_OFFSETS = {
            {0, 0, 0}
    };

    @Override
    public void breakBlocks(BlockPos centerBlock, Direction direction, ServerPlayerInteractionManager manager) {
        int[][] offsets;

        switch (direction) {
            case UP, DOWN -> offsets = UP_DOWN_OFFSETS;
            case EAST, WEST -> offsets = EAST_WEST_OFFSETS;
            case NORTH, SOUTH -> offsets = NORTH_SOUTH_OFFSETS;

            default -> offsets = DEFAULT_OFFSETS;
        }

        for (int[] offset : offsets) {
            manager.tryBreakBlock(centerBlock.add(offset[0], offset[1], offset[2]));
        }
    }
}
