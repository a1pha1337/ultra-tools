package ru.a1pha1337.ultratools.items;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class UltraPickaxeItem extends PickaxeItem implements PlayerBlockBreakEvents.Before {

    public UltraPickaxeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean beforeBlockBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity) {
        HitResult hitResult = MinecraftClient.getInstance().cameraEntity.raycast(20, 0, false);

        if (hitResult.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockHitResult = (BlockHitResult)hitResult;

            if (this.isSuitableFor(state)) {
                breakBlocks(world, pos, blockHitResult.getSide());
            }
        }

        return true;
    }

    private void breakBlocks(World world, BlockPos centerBlock, Direction direction) {
        switch (direction) {
            case UP, DOWN -> {
                world.breakBlock(centerBlock.add(1, 0, -1), true);
                world.breakBlock(centerBlock.add(1, 0, 0), true);
                world.breakBlock(centerBlock.add(1, 0, 1), true);
                world.breakBlock(centerBlock.add(-1, 0, -1), true);
                world.breakBlock(centerBlock.add(-1, 0, 0), true);
                world.breakBlock(centerBlock.add(-1, 0, 1), true);
                world.breakBlock(centerBlock.add(0, 0, -1), true);
                world.breakBlock(centerBlock.add(0, 0, 1), true);
            }
            case EAST, WEST -> {
                world.breakBlock(centerBlock.add(0, 1, -1), true);
                world.breakBlock(centerBlock.add(0, 1, 0), true);
                world.breakBlock(centerBlock.add(0, 1, 1), true);
                world.breakBlock(centerBlock.add(0, -1, -1), true);
                world.breakBlock(centerBlock.add(0, -1, 0), true);
                world.breakBlock(centerBlock.add(0, -1, 1), true);
                world.breakBlock(centerBlock.add(0, 0, -1), true);
                world.breakBlock(centerBlock.add(0, 0, 1), true);
            }
            case NORTH, SOUTH -> {
                world.breakBlock(centerBlock.add(-1, 1, 0), true);
                world.breakBlock(centerBlock.add(0, 1, 0), true);
                world.breakBlock(centerBlock.add(1, 1, 0), true);
                world.breakBlock(centerBlock.add(-1, -1, 0), true);
                world.breakBlock(centerBlock.add(0, -1, 0), true);
                world.breakBlock(centerBlock.add(1, -1, 0), true);
                world.breakBlock(centerBlock.add(-1, 0, 0), true);
                world.breakBlock(centerBlock.add(1, 0, 0), true);
            }
        }
    }
}
