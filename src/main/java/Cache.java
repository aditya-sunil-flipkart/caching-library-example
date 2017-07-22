import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Cache {
    private int maxSize;
    private DataStore dataStore;
    private EvictionPolicy evictionPolicy;

    public Cache(int maxSize) {
        this(maxSize,new DataStoreDefaultImpl());
    }

    public Cache(int maxSize, DataStore dataStore) {
        this(maxSize,dataStore,new EvictionPolicyDefaultImpl());
    }

    public Cache(int maxSize, EvictionPolicy evictionPolicy) {
        this(maxSize,new DataStoreDefaultImpl(),evictionPolicy);
    }

    public Cache(int maxSize, DataStore dataStore, EvictionPolicy evictionPolicy) {
        this.maxSize = maxSize;
        this.dataStore = dataStore;
        this.evictionPolicy = evictionPolicy;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void add(String key, String value) {
        if(dataStore.getCurrentSize() < maxSize){
            dataStore.put(key,value);
        }
        else{
            while(dataStore.getCurrentSize() >= maxSize){
                KeyInformation[] keyInformations = dataStore.getKeyInformation();
                String keyToBeEvicted = evictionPolicy.getEvictedKey(keyInformations);
                dataStore.remove(keyToBeEvicted);
            }
            dataStore.put(key, value);
        }
    }
    public void add(Object key,Object value){
            String keyString = key.toString();
            String valueString = value.toString();
            add(keyString,valueString);
    }   //TODO can use objectMapper to serialize

    public String get(String key) {
        return dataStore.get(key);
    }
    public String get(Object key) {
        String keyString = key.toString();
        return dataStore.get(keyString);
    }   //TODO can use objectMapper to serialize

    public void remove(String key) {
        dataStore.remove(key);
    }

    public int getCurrentSize() {
        return dataStore.getCurrentSize();
    }


}
