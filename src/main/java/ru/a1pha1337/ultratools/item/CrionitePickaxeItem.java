package ru.a1pha1337.ultratools.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ToolMaterial;
import ru.a1pha1337.ultratools.item.material.CrioniteToolMaterial;
import ru.a1pha1337.ultratools.item.strategy.ThreeByThreeMiningStrategy;

public class CrionitePickaxeItem extends MultiPickaxeItem {
    private static final int ATTACK_DAMAGE = 7;
    private static final float ATTACK_SPEED = -2.8f;
    private static final ToolMaterial TOOL_MATERIAL = CrioniteToolMaterial.INSTANCE;
    private static final Settings SETTINGS = new FabricItemSettings();

    public CrionitePickaxeItem() {
        super(TOOL_MATERIAL, ATTACK_DAMAGE, ATTACK_SPEED, SETTINGS, new ThreeByThreeMiningStrategy());
    }

    public CrionitePickaxeItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings, new ThreeByThreeMiningStrategy());
    }
}
