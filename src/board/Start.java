package board;

public class Start extends Field {
    public int startMoney;
    public Start(String name, int index, int startMoney) {
        super(name, index);
        this.startMoney = startMoney;
    }
    public Start() {
        setIndex(0);
        this.startMoney = 100;
    }
    public int getStartMoney() {
        return startMoney;
    }
    public void setStartMoney(int startMoney) {
        this.startMoney = startMoney;
    }

}