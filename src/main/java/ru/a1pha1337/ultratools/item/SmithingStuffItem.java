package ru.a1pha1337.ultratools.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.SmithingTableBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static ru.a1pha1337.ultratools.block.Blocks.CRIOSMITHING_TABLE;

public class SmithingStuffItem extends Item {
    private static final Settings SETTINGS = new FabricItemSettings().maxCount(1);

    public SmithingStuffItem() {
        this(SETTINGS);
    }

    public SmithingStuffItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);

        if (blockState.getBlock() instanceof SmithingTableBlock) {
            world.setBlockState(blockPos, CRIOSMITHING_TABLE.getDefaultState());

            if (!world.isClient) {
                world.playSound(null, blockPos, SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.BLOCKS, 1f, 1f);
                world.addParticle(ParticleTypes.EXPLOSION, blockPos.getX(), blockPos.getY() + 2, blockPos.getZ(), 0f, 2f, 0f);
            }

            context.getStack().decrement(1);
        }

        return super.useOnBlock(context);
    }

//    @Override
//    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
//        HitResult hitResult = user.raycast(3, 0, false);
//
//        if (hitResult.getType() == HitResult.Type.BLOCK){
//            BlockPos blockPos = new BlockPos(hitResult.getPos());
//            BlockState blockState = world.getBlockState(blockPos);
//            Block block = blockState.getBlock();
//
//            if (blockState.getBlock() instanceof SmithingTableBlock) {
//                world.breakBlock(blockPos, false);
//                world.setBlockState(blockPos, CRIOSMITHING_TABLE.getDefaultState());
//                world.playSound(null, blockPos, SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.BLOCKS, 1f, 1f);
//            }
//        }
//        return super.use(world, user, hand);
//    }
}
