package ca.bc.gov.educ.grad.report.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SerializableMap<K, V> extends HashMap<K, V> implements Serializable {

    public SerializableMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public SerializableMap(int initialCapacity) {
        super(initialCapacity);
    }

    public SerializableMap() {
        super();
    }

    public SerializableMap(Map m) {
        super(m);
    }
}
