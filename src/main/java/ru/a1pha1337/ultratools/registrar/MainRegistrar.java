package ru.a1pha1337.ultratools.registrar;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import ru.a1pha1337.ultratools.ModSettings;
import ru.a1pha1337.ultratools.block.Blocks;
import ru.a1pha1337.ultratools.block.CriosmithingTableBlockEntity;
import ru.a1pha1337.ultratools.item.*;
import ru.a1pha1337.ultratools.utils.Utils;

import java.util.*;

import static net.minecraft.item.Items.EMERALD;
import static ru.a1pha1337.ultratools.entity.Entities.CRIONITE_SLIME;
import static ru.a1pha1337.ultratools.utils.Logger.LOGGER;
import static ru.a1pha1337.ultratools.item.Items.*;
import static ru.a1pha1337.ultratools.block.Blocks.*;

public final class MainRegistrar implements ModRegistrar {

    // Ore generation objects
    private static final ConfiguredFeature<?, ?> OVERWORLD_CRIONITE_ORE_CONFIGURED_FEATURE =
            new ConfiguredFeature(Feature.ORE, new OreFeatureConfig(
                    OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES,
                    DEEPSLATE_CRIONITE_ORE.getDefaultState(),
                    3
            ));

    private static final PlacedFeature OVERWORLD_CRIONITE_ORE_PLACED_FEATURE = new PlacedFeature(
            RegistryEntry.of(OVERWORLD_CRIONITE_ORE_CONFIGURED_FEATURE),
            Arrays.asList(
                    CountPlacementModifier.of(4),
                    HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(-32))
            ));

    public static final BlockEntityType<CriosmithingTableBlockEntity> CRIOSMITHING_TABLE_BLOCK_ENTITY = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            new Identifier(ModSettings.NAMESPACE, "criosmithing_table_entity"),
            FabricBlockEntityTypeBuilder.create(CriosmithingTableBlockEntity::new, CRIOSMITHING_TABLE).build()
    );

    @Override
    public void register() {
        LOGGER.info("Starting mod objects registration...");

        // ItemStacks for ItemGroup
        List<ItemStack> itemStacks = new ArrayList<>();

        // Items
        Map<String, Item> items = Utils.getFieldsFromClass(Items.class, Item.class);
        for (Map.Entry<String, Item> itemEntry : items.entrySet()) {
            Registry.register(Registry.ITEM, new Identifier(ModSettings.NAMESPACE, itemEntry.getKey()), itemEntry.getValue());

            itemStacks.add(new ItemStack(itemEntry.getValue()));
        }

        // Blocks
        Map<String, Block> blocks = Utils.getFieldsFromClass(Blocks.class, Block.class);
        for (Map.Entry<String, Block> blockEntry : blocks.entrySet()) {
            Registry.register(Registry.BLOCK, new Identifier(ModSettings.NAMESPACE, blockEntry.getKey()), blockEntry.getValue());
            Registry.register(Registry.ITEM, new Identifier(ModSettings.NAMESPACE, blockEntry.getKey()),
                    new BlockItem(blockEntry.getValue(), new FabricItemSettings()));

            itemStacks.add(new ItemStack(blockEntry.getValue()));
        }

        // Entities
        Registry.register(Registry.ENTITY_TYPE, new Identifier(ModSettings.NAMESPACE, "crionite_slime"),
                CRIONITE_SLIME);

        // Iteam group
        FabricItemGroupBuilder.create(new Identifier(ModSettings.NAMESPACE, "base"))
                .icon(() -> new ItemStack(CRIONITE_PICKAXE))
                .appendItems(consumer -> consumer.addAll(itemStacks))
                .build();

        // Ore generation
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
                new Identifier(ModSettings.NAMESPACE, "overworld_deepslate_crionite_ore"),
                OVERWORLD_CRIONITE_ORE_CONFIGURED_FEATURE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE,
                new Identifier(ModSettings.NAMESPACE, "overworld_deepslate_crionite_ore"),
                OVERWORLD_CRIONITE_ORE_PLACED_FEATURE);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY,
                        new Identifier(ModSettings.NAMESPACE, "overworld_deepslate_crionite_ore")));

        FabricDefaultAttributeRegistry.register(CRIONITE_SLIME,
                SlimeEntity.createMobAttributes().add(EntityAttributes.GENERIC_ATTACK_DAMAGE));

        // Events
        PlayerBlockBreakEvents.BEFORE.register(CRIONITE_PICKAXE);
        PlayerBlockBreakEvents.BEFORE.register((PlayerBlockBreakEvents.Before)DEEPSLATE_CRIONITE_ORE);

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.TOOLSMITH, 5,
                factories -> factories.add(((entity, random) ->
                        new TradeOffer(new ItemStack(EMERALD, 20), new ItemStack(SMITHING_STUFF),
                        1, 1, 0.02f))));

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
