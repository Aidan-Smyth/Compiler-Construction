import java.util.HashMap;

public class AttributeTable {

    private HashMap<String, Attribute> objectMap;

    public AttributeTable() {
        objectMap = new HashMap<String, Attribute>();
    }

    public void addSymbol(String key, Attribute object) {
        objectMap.put(key, object);
    }

    public Object getSymbol(String key) {
        return objectMap.get(key);
    }

    public void removeSymbol(String key) {
        objectMap.remove(key);
    }

    public boolean containsSymbol(String key) {
        return objectMap.containsKey(key);
    }

    public int size() {
        return objectMap.size();
    }

}
