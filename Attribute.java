public class Attribute {

    private String symbolType;

    private String dataType;

    private String tokenClass;

    private Auxiliary AuxPointer;

    // getters

    public String getSymbolType() {
        return symbolType;
    }

    public String getDataType() {
        return dataType;
    }

    public String getTokenClass() {
        return tokenClass;
    }

    public Auxiliary getAuxPointer() {
        return AuxPointer;
    }

    // setters

    public void setSymbolType(String symbol) {
        this.symbolType = symbol;
    }

    public void setDataType(String type) {
        this.dataType = type;
    }

    public void setTokenClass(String tc) {
        this.tokenClass = tc;
    }

    public void setAuxPointer(Auxiliary aux) {
        this.AuxPointer = aux;
    }
}
