package ru.a1pha1337.ultratools.registrar;

public final class ClientRegistrar implements ModRegistrar {

    @Override
    public void register() {

    }

    // Singleton
    private static final ModRegistrar INSTANCE  = new ClientRegistrar();

    private ClientRegistrar() {
    }

    public static ModRegistrar getInstance() {
        return INSTANCE;
    }
}
