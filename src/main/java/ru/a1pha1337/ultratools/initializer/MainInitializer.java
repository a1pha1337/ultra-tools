package ru.a1pha1337.ultratools.initializer;

import net.fabricmc.api.ModInitializer;
import ru.a1pha1337.ultratools.registrar.MainRegistrar;

public class MainInitializer implements ModInitializer {
	@Override
	public void onInitialize() {
		MainRegistrar.getInstance().register();
	}
}
