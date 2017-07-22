import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataStoreDefaultImpl implements DataStore {

    Map<String,String> storage;
    List<KeyInformation> keyInformations;

    public DataStoreDefaultImpl(){
        storage = new ConcurrentHashMap<String, String>();
        keyInformations = new ArrayList<KeyInformation>();
    }

    public void put(String key, String value) {
        storage.put(key, value);
        KeyInformation keyInformation = new KeyInformation(key,value,0);
        keyInformations.add(keyInformation);
    }

    public String get(String key) {
        String value =  storage.get(key);
        for(KeyInformation keyInformation: keyInformations){
            if(keyInformation.getKey().equals(key)){
                keyInformation.incrementCount();
            }
        }
        return value;
    }

    public void remove(String key) {
        storage.remove(key);
    }

    public int getCurrentSize() {
        return storage.size();
    }

    public KeyInformation[] getKeyInformation() {
        return keyInformations.toArray(new KeyInformation[keyInformations.size()]);
    }

}
