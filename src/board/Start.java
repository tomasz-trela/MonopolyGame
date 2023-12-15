package board;

public class Start extends Field {
    public int startMoney;
    public Start(String name, int startMoney) {
        super(name);
        this.startMoney = startMoney;
    }
    public Start() {
        super("start");
        this.startMoney = 100;
    }
    public int getStartMoney() {
        return startMoney;
    }
    public void setStartMoney(int startMoney) {
        this.startMoney = startMoney;
    }

}
