package engine.model;

import java.util.HashMap;
import java.util.Set;

public class DataRecord {

    private HashMap<String,  Object> values = new HashMap<>();

    public DataRecord() {
    }

    public void addValue(String key, Object value) {
        this.values.put(key, value);
    }
    public Object getValue(String key) {
        return this.values.get(key);
    }
    public int countValue() {
        return this.values.size();
    }
    public Set<String> getKeys() {
        return this.values.keySet();
    }

    @Override
    public String toString() {
        return "DataRecord{" +
                "values=" + values +
                '}';
    }
}
