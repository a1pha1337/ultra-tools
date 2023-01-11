package ru.a1pha1337.ultratools.item;

import net.minecraft.item.ToolMaterial;
import net.minecraft.tag.BlockTags;
import ru.a1pha1337.ultratools.item.strategy.MiningStrategy;

public class MultiPickaxeItem extends MultipleMiningToolItem {
    public MultiPickaxeItem(ToolMaterial material, float attackDamage, float attackSpeed,
                            Settings settings, MiningStrategy miningStrategy) {
        super(attackDamage, attackSpeed, material, BlockTags.PICKAXE_MINEABLE, settings, miningStrategy);
    }
}
