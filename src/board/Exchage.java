package board;

public class Exchage {
    private double eurorate;
    private double dolarrate;
    
    public Exchage(){
        eurorate=0.92;
        dolarrate=1.09;
    }
    public Exchage(double eurorate, double dolarrate){
        this.eurorate=eurorate;
        this.dolarrate=dolarrate;
    }
    public int[] ExchageUSDtoEUR(double amount, int howmuch){
        int[] tab=new int[2];
        // tab[0]=ile trzeba dodać euro do Eurobalance
        // tab[1]=ile trzeba odjąć dolarów od Dolarbalance
        if(howmuch*eurorate>amount){
            tab[0]=0;
            tab[1]=0;
        } else{
            tab[0]=howmuch;
            tab[1]=howmuch*eurorate;
        }
        return tab;
    }
    public int[] ExchageEURtoUSD(double amount, int howmuch){
        int[] tab=new int[2];
        // tab[0]=ile trzeba dodać dolarów do Dolarbalance
        // tab[1]=ile trzeba odjąć euro od Eurobalance
        if(howmuch*doalrrate>amount){
            tab[0]=0;
            tab[1]=0;
        } else{
            tab[0]=howmuch;
            tab[1]=howmuch*dolarrate;
        }
        return tab;
    }
    public double getEurorate(){
        return eurorate;
    }
    public void setEurorate(double eurorate){
        this.eurorate=eurorate;
    }
    public double getDolarrate(){
        return dolarrate;
    }
    public void setDolarrate(double dolarrate){
        this.dolarrate=dolarrate;
    }
}
