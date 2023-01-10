package ru.a1pha1337.ultratools.items.materials;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class UltraDiamondToolMaterial implements ToolMaterial {
    public static final UltraDiamondToolMaterial INSTANCE = new UltraDiamondToolMaterial();

    @Override
    public int getDurability() {
        return 1500;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 8F;
    }

    @Override
    public float getAttackDamage() {
        return 0;
    }

    @Override
    public int getMiningLevel() {
        return 4;
    }

    @Override
    public int getEnchantability() {
        return 10;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.DIAMOND_BLOCK);
    }
}
