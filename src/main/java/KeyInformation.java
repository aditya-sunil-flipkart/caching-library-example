
public class KeyInformation {       //TODO can also add timer,didn't get time to code up
    private String key;
    private String value;
    private int count;

    public KeyInformation(String key, String value,int count) {
        this.key = key;
        this.value = value;
        this.count = count;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public int getCount() {
        return count;
    }

    public void incrementCount() {
        count++;
    }
}
