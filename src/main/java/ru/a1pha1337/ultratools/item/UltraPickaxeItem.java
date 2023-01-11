package ru.a1pha1337.ultratools.item;

import net.minecraft.item.ToolMaterial;
import net.minecraft.tag.BlockTags;
import ru.a1pha1337.ultratools.item.strategy.MiningStrategy;

public class UltraPickaxeItem extends MultipleMiningToolItem {
    public UltraPickaxeItem(ToolMaterial material, float attackDamage, float attackSpeed,
                            Settings settings, MiningStrategy miningStrategy) {
        super(attackDamage, attackSpeed, material, BlockTags.PICKAXE_MINEABLE, settings, miningStrategy);
    }
}
