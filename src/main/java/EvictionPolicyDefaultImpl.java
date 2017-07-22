public class EvictionPolicyDefaultImpl implements EvictionPolicy {
    public String getEvictedKey(KeyInformation[] keyInformations) {
        return keyInformations[0].getKey();
    }
}
