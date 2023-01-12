package ru.a1pha1337.ultratools.item.material;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

import static ru.a1pha1337.ultratools.item.Items.CRIONITE_PLATING;

public class CrioniteToolMaterial implements ToolMaterial {
    public static final CrioniteToolMaterial INSTANCE = new CrioniteToolMaterial();

    @Override
    public int getDurability() {
        return 6000;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 8f;
    }

    @Override
    public float getAttackDamage() {
        return 0;
    }

    @Override
    public int getMiningLevel() {
        return 5;
    }

    @Override
    public int getEnchantability() {
        return 10;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(CRIONITE_PLATING);
    }
}
