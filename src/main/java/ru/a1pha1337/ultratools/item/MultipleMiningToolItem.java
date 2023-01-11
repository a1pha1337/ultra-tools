package ru.a1pha1337.ultratools.item;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.tag.TagKey;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import ru.a1pha1337.ultratools.item.strategy.MiningStrategy;

public class MultipleMiningToolItem extends MiningToolItem implements PlayerBlockBreakEvents.Before {
    /**
     * Prevents handling breaking events for adjacent blocks, if target block was broken
     */
    private boolean stopHandlingEvents = false;

    private final MiningStrategy miningStrategy;

    public MultipleMiningToolItem(float attackDamage, float attackSpeed, ToolMaterial material,
                                  TagKey<Block> effectiveBlocks, Settings settings, MiningStrategy miningStrategy) {
        super(attackDamage, attackSpeed, material, effectiveBlocks, settings);

        this.miningStrategy = miningStrategy;
    }

    @Override
    public boolean beforeBlockBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity) {
        if (state.getBlock() == Blocks.BEDROCK && !player.isCreative()) {
            return false;
        }

        if (!stopHandlingEvents && player.getMainHandStack().getItem() instanceof MultipleMiningToolItem) {
            ServerPlayerInteractionManager manager = new ServerPlayerInteractionManager((ServerPlayerEntity) player);
            HitResult hitResult = player.raycast(20, 0, false);

            this.stopHandlingEvents = true;

            if (hitResult.getType() == HitResult.Type.BLOCK) {
                BlockHitResult blockHitResult = (BlockHitResult)hitResult;

                if (isSuitableFor(state)) {
                    breakBlocks(pos, blockHitResult.getSide(), manager);
                }
            }

            this.stopHandlingEvents = false;
        }

        return true;
    }

    private void breakBlocks(BlockPos centerBlock, Direction direction, ServerPlayerInteractionManager manager) {
        miningStrategy.breakBlocks(centerBlock, direction, manager);
    }
}
