package ru.a1pha1337.ultratools.registrar;

public final class ServerRegistrar implements ModRegistrar {

    @Override
    public void register() {

    }

    // Singleton
    private static final ModRegistrar INSTANCE  = new ServerRegistrar();

    private ServerRegistrar() {
    }

    public static ModRegistrar getInstance() {
        return INSTANCE;
    }
}
