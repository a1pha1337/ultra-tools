package ru.a1pha1337.ultratools.initializer;

import ru.a1pha1337.ultratools.item.IronRodItem;
import ru.a1pha1337.ultratools.item.MultipleMiningToolItem;
import ru.a1pha1337.ultratools.item.UltraDiamondPickaxeItem;
import ru.a1pha1337.ultratools.item.material.UltraDiamondToolMaterial;
import ru.a1pha1337.ultratools.ModSettings;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MainInitializer implements ModInitializer {
	private static final Item ITEM_IRON_ROD = new IronRodItem(new FabricItemSettings());
	private static final MultipleMiningToolItem ULTRA_DIAMOND_PICKAXE = new UltraDiamondPickaxeItem(UltraDiamondToolMaterial.INSTANCE,
			7, -2.8f, new FabricItemSettings());

	@Override
	public void onInitialize() {

		PlayerBlockBreakEvents.BEFORE.register(ULTRA_DIAMOND_PICKAXE);

		Registry.register(Registry.ITEM, new Identifier(ModSettings.NAMESPACE, "iron_rod"),
				ITEM_IRON_ROD);
		Registry.register(Registry.ITEM, new Identifier(ModSettings.NAMESPACE, "ultra_diamond_pickaxe"),
				ULTRA_DIAMOND_PICKAXE);

		FabricItemGroupBuilder.create(new Identifier(ModSettings.NAMESPACE, "base"))
			.icon(() -> new ItemStack(ITEM_IRON_ROD))
			.appendItems(itemStacks -> {
				itemStacks.add(new ItemStack(ITEM_IRON_ROD));
				itemStacks.add(new ItemStack(ULTRA_DIAMOND_PICKAXE));
			})
			.build();
	}
}
