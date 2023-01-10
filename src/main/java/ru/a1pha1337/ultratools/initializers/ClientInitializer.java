package ru.a1pha1337.ultratools.initializers;

import net.fabricmc.api.ClientModInitializer;
import ru.a1pha1337.ultratools.utils.Logger;


public class ClientInitializer implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Logger.LOGGER.info("Client initializer!");
    }
}
