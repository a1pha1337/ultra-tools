package ru.a1pha1337.ultratools.item;

import net.minecraft.item.ToolMaterial;
import ru.a1pha1337.ultratools.item.strategy.ThreeByThreeMiningStrategy;

public class UltraDiamondPickaxeItem extends UltraPickaxeItem {
    public UltraDiamondPickaxeItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings, new ThreeByThreeMiningStrategy());
    }
}
