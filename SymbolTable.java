import java.util.HashMap;
import java.util.Map.Entry;

public class SymbolTable {

    private HashMap<String, Symbol> objectMap;

    private AttributeTable attributeTable;

    public SymbolTable() {

        // creating the symbol table
        this.objectMap = new HashMap<String, Symbol>();

        // creating the attribute table
        this.attributeTable = new AttributeTable();

        // Adding standard data types

        // setting the attribute
        Attribute programAttr = new Attribute();
        programAttr.setSymbolType("tokprogram");
        programAttr.setDataType("none");
        programAttr.setTokenClass("keyword");

        // adding the attribute to the attribute table
        attributeTable.addSymbol("tokprogram", programAttr);

        // setting the symbol
        Symbol programSymbol = new Symbol("program", "tokprogram", "keyword", "none", "0", "global", programAttr);

        // adding the symbol to the table
        this.addSymbol("program", programSymbol);

        Attribute integerAttr = new Attribute();
        integerAttr.setSymbolType("tokint");
        integerAttr.setDataType("integer");
        integerAttr.setTokenClass("keyword");
        attributeTable.addSymbol("tokint", integerAttr);
        Symbol integerSymbol = new Symbol("integer", "tokint", "keyword", "integer", "0", "global", integerAttr);
        this.addSymbol("integer", integerSymbol);

        Attribute semiColonAttr = new Attribute();
        semiColonAttr.setSymbolType("toksemicolon");
        semiColonAttr.setDataType("none");
        semiColonAttr.setTokenClass("operator");
        attributeTable.addSymbol("toksemicolon", semiColonAttr);
        Symbol semiColonSymbol = new Symbol(";", "toksemicolon", "operator", "none", "0", "global", semiColonAttr);
        this.addSymbol(";", semiColonSymbol);

        Attribute constAttr = new Attribute();
        constAttr.setSymbolType("tokword");
        constAttr.setDataType("none");
        constAttr.setTokenClass("keyword");
        attributeTable.addSymbol("tokword", constAttr);
        Symbol constSymbol = new Symbol("const", "tokword", "keyword", "none", "0", "global", constAttr);
        this.addSymbol("const", constSymbol);

        Attribute equalsAttr = new Attribute();
        equalsAttr.setSymbolType("tokequal");
        equalsAttr.setDataType("none");
        equalsAttr.setTokenClass("operator");
        attributeTable.addSymbol("tokequal", equalsAttr);
        Symbol equalsSymbol = new Symbol("=", "tokequal", "operator", "none", "0", "global", equalsAttr);
        this.addSymbol("=", equalsSymbol);

        Attribute beginAttr = new Attribute();
        beginAttr.setSymbolType("tokbegin");
        beginAttr.setDataType("none");
        beginAttr.setTokenClass("keyword");
        attributeTable.addSymbol("tokbegin", beginAttr);
        Symbol beginSymbol = new Symbol("begin", "tokbegin", "keyword", "none", "0", "global", beginAttr);
        this.addSymbol("begin", beginSymbol);

        Attribute endAttr = new Attribute();
        endAttr.setSymbolType("tokend");
        endAttr.setDataType("none");
        endAttr.setTokenClass("keyword");
        attributeTable.addSymbol("tokend", endAttr);
        Symbol endSymbol = new Symbol("end", "tokend", "keyword", "none", "0", "global", endAttr);
        this.addSymbol("end", endSymbol);

        Attribute periodAttr = new Attribute();
        periodAttr.setSymbolType("tokperiod");
        periodAttr.setDataType("none");
        periodAttr.setTokenClass("operator");
        attributeTable.addSymbol("tokperiod", periodAttr);
        Symbol periodSymbol = new Symbol(".", "tokperiod", "operator", "none", "0", "global", periodAttr);
        this.addSymbol(".", periodSymbol);

        Attribute rightParenAttr = new Attribute();
        rightParenAttr.setSymbolType("tokrightparen");
        rightParenAttr.setDataType("none");
        rightParenAttr.setTokenClass("operator");
        attributeTable.addSymbol("tokrightparen", periodAttr);
        Symbol rightParenSymbol = new Symbol(")", "tokrightparen", "operator", "none", "0", "global", rightParenAttr);
        this.addSymbol(")", rightParenSymbol);

        Attribute leftParenAttr = new Attribute();
        leftParenAttr.setSymbolType("tokleftparen");
        leftParenAttr.setDataType("none");
        leftParenAttr.setTokenClass("operator");
        attributeTable.addSymbol("tokleftparen", periodAttr);
        Symbol leftParenSymbol = new Symbol("(", "tokleftparen", "operator", "none", "0", "global", leftParenAttr);
        this.addSymbol("(", leftParenSymbol);

        Attribute commaAttr = new Attribute();
        commaAttr.setSymbolType("tokcomma");
        commaAttr.setDataType("none");
        commaAttr.setTokenClass("operator");
        attributeTable.addSymbol("tokcomma", commaAttr);
        Symbol commaSymbol = new Symbol(",", "tokcomma", "operator", "none", "0", "global", commaAttr);
        this.addSymbol(",", commaSymbol);

    }

    public SymbolTable(SymbolTable other) {
        this.objectMap = new HashMap<String, Symbol>(other.objectMap);
    }

    public void addSymbol(String key, Symbol object) {
        this.objectMap.put(key, object);
    }

    public Symbol getSymbol(String key) {
        return this.objectMap.get(key);
    }

    public void removeSymbol(String key) {
        this.objectMap.remove(key);
    }

    public boolean containsSymbol(String key) {
        return this.objectMap.containsKey(key);
    }

    public int size() {
        return this.objectMap.size();
    }

    public void printSymbolTable() {
        System.out.printf("%-10s %-15s %-15s %-15s %-10s %-10s %-20s\n", "Key", "Name", "Token", "Type", "Value",
                "Scope", "Attribute");
        System.out
                .println("--------------------------------------------------------------------------------------");
        for (Entry<String, Symbol> entry : this.objectMap.entrySet()) {
            Symbol symbol = entry.getValue();
            System.out.printf("%-10s %-15s %-15s %-15s %-10s %-10s %-20s\n", entry.getKey(), symbol.getLexeme(),
                    symbol.getTokenClass(), symbol.getSymbolType(), symbol.getValue(), symbol.getScope(),
                    symbol.getAttributePointer());
        }
    }

    public void addToAttributeTable(String key, Attribute object) {
        attributeTable.addSymbol(key, object);
    }
}
