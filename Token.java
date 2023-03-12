public class Token {

    private String source;
    private String type;
    private Symbol symbolTablePointer;

    public void setSource(String s) {
        source = s;
    }

    public void setType(String t) {
        type = t;
    }

    public void setSymbolTablePointer(Symbol pointer){
        symbolTablePointer = pointer;
    }

    public String getSource() {
        return source;
    }

    public String getType() {
        return type;
    }

    public Symbol getSymbolTablePointer(){
        return symbolTablePointer;
    }

}
