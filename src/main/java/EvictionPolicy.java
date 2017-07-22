public interface EvictionPolicy {
    String getEvictedKey(KeyInformation[] keyInformations);
}
