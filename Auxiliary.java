public class Auxiliary {

    private String arrayType;

    private String Type;

    private String Size;

    private String UpperBound;

    private String LowerBound;

    // getters

    public String getArrayType() {
        return arrayType;
    }

    public String getSymbol() {
        return UpperBound;
    }

    public String getType() {
        return Type;
    }

    public String getSize() {
        return Size;
    }

    public String getMemoryLocation() {
        return LowerBound;
    }

    // setters

    public void setArrayType(String at) {
        this.arrayType = at;
    }

    public void setSymbol(String ub) {
        this.UpperBound = ub;
    }

    public void setType(String type) {
        this.Type = type;
    }

    public void setSize(String size) {
        this.Size = size;
    }

    public void setMemoryLocation(String lb) {
        this.LowerBound = lb;
    }
}
