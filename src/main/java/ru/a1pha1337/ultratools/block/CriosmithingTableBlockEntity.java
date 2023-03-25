package ru.a1pha1337.ultratools.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import static ru.a1pha1337.ultratools.registrar.MainRegistrar.CRIOSMITHING_TABLE_BLOCK_ENTITY;

public class CriosmithingTableBlockEntity extends BlockEntity {
    public CriosmithingTableBlockEntity(BlockPos pos, BlockState state) {
        super(CRIOSMITHING_TABLE_BLOCK_ENTITY, pos, state);
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }
}
