package board;
import observer.*;

public class Exchange extends Field{
    private int entryFee;

    public Exchange(String name){
        super(name);
    }
    public String toString() {
        return "Kantor";
    }

    public int getEntryFee() {
        return entryFee;
    }

    public void setEntryFee(int entryFee) {
        this.entryFee = entryFee;
    }
}