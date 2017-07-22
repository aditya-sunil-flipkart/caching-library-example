
public interface DataStore {
    void put(String key, String value);

    String get(String key);

    void remove(String key);

    int getCurrentSize();

    KeyInformation[] getKeyInformation();

}
