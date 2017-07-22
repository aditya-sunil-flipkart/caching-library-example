import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CacheTest {

    Cache cache;
    DataStore dataStore;
    EvictionPolicy evictionPolicy;

    @Before
    public void setUp(){
        dataStore = new DataStoreDefaultImpl();
        evictionPolicy = new EvictionPolicyDefaultImpl();
        cache = new Cache(5,dataStore);
    }

    @Test
    public void testInitCacheWithSizeOnly(){
        Assert.assertEquals(5,cache.getMaxSize());
    }

    @Test
    public void testAddingOfKeyToCache(){
        String key = "key1";
        String value = "value1";
        cache.add(key,value);
        Assert.assertEquals("value1",cache.get(key));
    }

    @Test
    public void testRemovingOfKeyToCache(){
        String key1 = "key1"; String value1 = "value1";
        String key2 = "key2"; String value2= "value2";
        cache.add(key1,value1);
        cache.add(key2,value2);
        cache.remove(key1);
        Assert.assertEquals(null,cache.get(key1));
        Assert.assertEquals("value2",cache.get(key2));
    }

    @Test
    public void testInitCacheWithSizeAndDataStore(){
        String key = "key1";
        String value = "value1";
        cache.add(key,value);
        Assert.assertEquals("value1",cache.get(key));
    }

    @Test
    public void checkCurrentSizeOfCache(){

        String key1 = "key1"; String value1 = "value1";
        String key2 = "key2"; String value2 = "value2";
        String key3 = "key3"; String value3 = "value3";
        String key4 = "key4"; String value4 = "value4";

        cache.add(key1,value1);
        cache.add(key2,value2);
        cache.add(key3,value3);
        cache.add(key4,value4);
        Assert.assertEquals(4,cache.getCurrentSize());
        cache.remove(key1);
        Assert.assertEquals(3,cache.getCurrentSize());
    }

    @Test
    public void testIfEntriesDoNotExceedMaxLimit(){

        String key1 = "key1"; String value1 = "value1";
        String key2 = "key2"; String value2 = "value2";
        String key3 = "key3"; String value3 = "value3";
        String key4 = "key4"; String value4 = "value4";
        String key5 = "key5"; String value5 = "value5";
        String key6 = "key6"; String value6 = "value6";

        cache.add(key1,value1);
        cache.add(key2,value2);
        cache.add(key3,value3);
        cache.add(key4,value4);
        cache.add(key5,value5);
        cache.add(key6,value6);

        Assert.assertEquals(5,cache.getCurrentSize());
    }

    @Test
    public void testInitCacheWithSizeAndEvictionPolicy(){

        Cache cache = new Cache(5,evictionPolicy);
        Assert.assertEquals(5,cache.getMaxSize());
    }

    @Test
    public void testInitCacheWithSizeDataStoreAndEvictionPolicy(){

        Cache cache = new Cache(5,dataStore,evictionPolicy);
        Assert.assertEquals(5,cache.getMaxSize());
    }

    @Test
    public void testIfNewKeyIsAddedWheSizeHasExceeded(){

        String key1 = "key1"; String value1 = "value1";
        String key2 = "key2"; String value2 = "value2";
        String key3 = "key3"; String value3 = "value3";
        String key4 = "key4"; String value4 = "value4";
        String key5 = "key5"; String value5 = "value5";
        String key6 = "key6"; String value6 = "value6";

        cache.add(key1,value1);
        cache.add(key2,value2);
        cache.add(key3,value3);
        cache.add(key4,value4);
        cache.add(key5,value5);
        cache.add(key6,value6);

        Assert.assertEquals("value6",cache.get(key6));

    }

    @Test
    public void testAdditionToCacheForNonStringType(){
        Cache cache= new Cache(5);
        cache.add(new Integer(1),"value1");
        Assert.assertEquals("value1",cache.get(new Integer(1)));
    }
}
