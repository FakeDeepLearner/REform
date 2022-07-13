package RealEstate.entities;

import java.util.HashMap;

public abstract class Container<K, V> extends HashMap<K, V> {
    @Override
    public V get(Object key) throws IllegalArgumentException {
        if (this.containsKey(key)) {
            return super.get(key);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        for (V value : this.values()) {
            str.append(value.toString()).append('\n');
        }

        return str.toString();
    }

    public String getInfo(K key) {
        V value = get(key);

        return value.toString();
    }
}