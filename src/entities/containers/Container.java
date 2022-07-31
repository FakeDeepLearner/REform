package entities.containers;

import java.util.HashMap;

public abstract class Container<K, V> extends HashMap<K, V> {
    /**
     * @param key the key whose associated value is to be returned
     * @return the value associated with key
     * @throws IllegalArgumentException if no object is associated with key in the hashmap
     */
    @Override
    public V get(Object key) throws IllegalArgumentException {
        if (this.containsKey(key)) {
            return super.get(key);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * @return the string representation of every object in the hashmap
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        for (V value : this.values()) {
            str.append(value.toString()).append('\n');
        }

        return str.toString();
    }

    /**
     * @param key the key whose associated value's string representation is desired
     * @return the string representation of the value associated with key
     */
    public String getInfo(K key) {
        V value = get(key);

        return value.toString();
    }
}