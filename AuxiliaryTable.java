import java.util.HashMap;

public class AuxiliaryTable {

    private HashMap<String, Auxiliary> objectMap;

    public void SymbolTable() {
        objectMap = new HashMap<String, Auxiliary>();
    }

    public void addSymbol(String key, Auxiliary object) {
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
