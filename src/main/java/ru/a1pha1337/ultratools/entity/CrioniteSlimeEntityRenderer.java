package ru.a1pha1337.ultratools.entity;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.SlimeEntityRenderer;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.util.Identifier;
import ru.a1pha1337.ultratools.ModSettings;

public class CrioniteSlimeEntityRenderer extends SlimeEntityRenderer {

    public CrioniteSlimeEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(SlimeEntity slimeEntity) {
        return new Identifier(ModSettings.NAMESPACE, "textures/entity/crionite_slime/crionite_slime.png");
    }
}
