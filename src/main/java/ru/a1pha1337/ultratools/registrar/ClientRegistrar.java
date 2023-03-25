package ru.a1pha1337.ultratools.registrar;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SlimeEntityModel;
import net.minecraft.util.Identifier;
import ru.a1pha1337.ultratools.ModSettings;
import ru.a1pha1337.ultratools.entity.CrioniteSlimeEntityRenderer;

import static ru.a1pha1337.ultratools.entity.Entities.CRIONITE_SLIME;

public final class ClientRegistrar implements ModRegistrar {
    public static final EntityModelLayer MODEL_SLIME_LAYER_1 = new EntityModelLayer(
            new Identifier(ModSettings.NAMESPACE, "crionite_slime"),
            "layer_1"
    );

    public static final EntityModelLayer MODEL_SLIME_LAYER_2 = new EntityModelLayer(
            new Identifier(ModSettings.NAMESPACE, "crionite_slime"),
            "layer_2"
    );

    @Override
    public void register() {
        EntityRendererRegistry.register(CRIONITE_SLIME, CrioniteSlimeEntityRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(MODEL_SLIME_LAYER_1, SlimeEntityModel::getOuterTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(MODEL_SLIME_LAYER_2, SlimeEntityModel::getInnerTexturedModelData);
    }

    // Singleton
    private static final ModRegistrar INSTANCE  = new ClientRegistrar();

    private ClientRegistrar() {
    }

    public static ModRegistrar getInstance() {
        return INSTANCE;
    }
}
