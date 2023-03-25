package ru.a1pha1337.ultratools.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
public final class Entities {
    public static final EntityType<CrioniteSlimeEntity> CRIONITE_SLIME =
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, CrioniteSlimeEntity::new).build();
}
