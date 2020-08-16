package lesson08.client;

@FunctionalInterface
public interface Callback {
    void callback(String... args);
}