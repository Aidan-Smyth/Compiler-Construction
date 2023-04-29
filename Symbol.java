public class Symbol {

    private String lexeme;

    private String tokenClass;

    private String symbolType;

    private String dataType;

    private String value;

    private String scope;

    private Attribute AttributePointer;

   

    public Symbol(String l, String tc, String st, String dt, String v, String s, Attribute a) {
        lexeme = l;
        tokenClass = tc;
        symbolType = st;
        dataType = dt;
        value = v;
        scope = s;
        AttributePointer = a;
    }

    public Symbol(Symbol symbolToCopy) {
        this.lexeme = symbolToCopy.lexeme;
        this.tokenClass = symbolToCopy.tokenClass;
        this.symbolType = symbolToCopy.symbolType;
        this.dataType = symbolToCopy.dataType;
        this.value = symbolToCopy.value;
        this.scope = symbolToCopy.scope;
        this.AttributePointer = symbolToCopy.AttributePointer;
    }

    // getters
    public String getLexeme() {
        return lexeme;
    }

    public String getTokenClass() {
        return tokenClass;
    }

    public String getSymbolType() {
        return symbolType;
    }

    public String getDataType() {
        return dataType;
    }

    public String getValue() {
        return value;
    }

    public String getScope() {
        return scope;
    }

    public Attribute getAttributePointer() {
        return AttributePointer;
    }

  

    // setters
    public void setLexeme(String l) {
        this.lexeme = l;
    }

    public void setToken(String symbol) {
        this.tokenClass = symbol;
    }

    public void setType(String type) {
        this.symbolType = type;
    }

    public void setDataType(String dt) {
        this.dataType = dt;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setAttributePointer(Attribute attribute) {
        this.AttributePointer = attribute;
    }

   
}
