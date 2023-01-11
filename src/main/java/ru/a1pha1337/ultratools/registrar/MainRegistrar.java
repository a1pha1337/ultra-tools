package ru.a1pha1337.ultratools.registrar;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import ru.a1pha1337.ultratools.ModSettings;
import ru.a1pha1337.ultratools.block.CrioniteOreBlock;
import ru.a1pha1337.ultratools.item.*;

import java.util.Arrays;

import static ru.a1pha1337.ultratools.utils.Logger.LOGGER;

public final class MainRegistrar implements ModRegistrar {
    public static final MultipleMiningToolItem CRIONITE_PICKAXE = new CrionitePickaxeItem();

    public static final CrioniteOreBlock CRIONITE_ORE_BLOCK = new CrioniteOreBlock();
    public static final Item RAW_CRIONITE = new RawCrioniteItem();
    public static final Item CRIONITE_INGOT = new CrioniteIngotItem();
    public static final Item CRIONITE_ALLOY = new CrioniteAlloyItem();
    public static final Item CRIONITE_PLATING = new CrionitePlatingItem();

    private static final ConfiguredFeature<?, ?> OVERWORLD_CRIONITE_ORE_CONFIGURED_FEATURE =
            new ConfiguredFeature(Feature.ORE, new OreFeatureConfig(
                    OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES,
                    CRIONITE_ORE_BLOCK.getDefaultState(),
                    3
            ));

    private static final PlacedFeature OVERWORLD_CRIONITE_ORE_PLACED_FEATURE = new PlacedFeature(
            RegistryEntry.of(OVERWORLD_CRIONITE_ORE_CONFIGURED_FEATURE),
            Arrays.asList(
                    CountPlacementModifier.of(4),
                    HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(-32))
            ));


    @Override
    public void register() {
        LOGGER.info("Starting mod objects registration...");

        // Events
        PlayerBlockBreakEvents.BEFORE.register(CRIONITE_PICKAXE);

        // Items
        Registry.register(Registry.ITEM, new Identifier(ModSettings.NAMESPACE, "crionite_pickaxe"),
                CRIONITE_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier(ModSettings.NAMESPACE, "raw_crionite"),
                RAW_CRIONITE);
        Registry.register(Registry.ITEM, new Identifier(ModSettings.NAMESPACE, "crionite_ingot"),
                CRIONITE_INGOT);
        Registry.register(Registry.ITEM, new Identifier(ModSettings.NAMESPACE, "crionite_alloy"),
                CRIONITE_ALLOY);
        Registry.register(Registry.ITEM, new Identifier(ModSettings.NAMESPACE, "crionite_plating"),
                CRIONITE_PLATING);

        // Blocks
        Registry.register(Registry.BLOCK, new Identifier(ModSettings.NAMESPACE, "deepslate_crionite_ore"),
                CRIONITE_ORE_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(ModSettings.NAMESPACE, "deepslate_crionite_ore"),
                new BlockItem(CRIONITE_ORE_BLOCK, new FabricItemSettings()));

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
                new Identifier(ModSettings.NAMESPACE, "overworld_deepslate_crionite_ore"), OVERWORLD_CRIONITE_ORE_CONFIGURED_FEATURE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE,
                new Identifier(ModSettings.NAMESPACE, "overworld_deepslate_crionite_ore"), OVERWORLD_CRIONITE_ORE_PLACED_FEATURE);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY,
                        new Identifier(ModSettings.NAMESPACE, "overworld_deepslate_crionite_ore")));

        // Iteam group
        FabricItemGroupBuilder.create(new Identifier(ModSettings.NAMESPACE, "base"))
                .icon(() -> new ItemStack(CRIONITE_PICKAXE))
                .appendItems(itemStacks -> {
                    itemStacks.add(new ItemStack(CRIONITE_PICKAXE));
                    itemStacks.add(new ItemStack(CRIONITE_ORE_BLOCK));
                    itemStacks.add(new ItemStack(RAW_CRIONITE));
                    itemStacks.add(new ItemStack(CRIONITE_INGOT));
                    itemStacks.add(new ItemStack(CRIONITE_ALLOY));
                    itemStacks.add(new ItemStack(CRIONITE_PLATING));
                })
                .build();

        LOGGER.info("Mod objects registration completed successfully!");
    }

    // Singleton
    private static final ModRegistrar INSTANCE  = new MainRegistrar();

    private MainRegistrar() {
    }

    public static ModRegistrar getInstance() {
        return INSTANCE;
    }
}
