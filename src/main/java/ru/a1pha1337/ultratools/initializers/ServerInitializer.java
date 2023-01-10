package ru.a1pha1337.ultratools.initializers;

import net.fabricmc.api.DedicatedServerModInitializer;
import ru.a1pha1337.ultratools.utils.Logger;

public class ServerInitializer implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        Logger.LOGGER.info("Client initializer!");
    }
}
