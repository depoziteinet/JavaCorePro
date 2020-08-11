package lesson07.client;

@FunctionalInterface
public interface Callback {
    void callback(String... args);
}