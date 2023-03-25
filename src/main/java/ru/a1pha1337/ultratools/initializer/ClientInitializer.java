package ru.a1pha1337.ultratools.initializer;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import ru.a1pha1337.ultratools.registrar.ClientRegistrar;


@Environment(EnvType.CLIENT)
public class ClientInitializer implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientRegistrar.getInstance().register();
    }
}
