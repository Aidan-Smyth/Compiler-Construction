public class Auxiliary {

    private Symbol UpperBound;

    private Symbol LowerBound;

   
    public Symbol getSymbol() {
        return UpperBound;
    }

   

    public Symbol getMemoryLocation() {
        return LowerBound;
    }

    // setters

    

    public void setSymbol(Symbol ub) {
        this.UpperBound = ub;
    }

}
