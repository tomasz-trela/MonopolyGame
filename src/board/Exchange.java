package board;

import java.util.Random;

import observer.AffluenceObserver;

public class Exchange extends Field{
    private double euroRate;
    private double dolarRate;

    AffluenceObserver affluenceObserver = new AffluenceObserver();
    
    public Exchange(){
        euroRate=1.09;
        dolarRate=0.92;
    }
    public Exchange(String name, double euroRate, double dolarRate){
        super(name);
        this.euroRate=euroRate;
        this.dolarRate=dolarRate;
    }
    public int[] ExchangeUSDtoEUR(int amount, int howMuch){
        // amount-bilans gracza w dolarach, howMuch-liczba euro jakie gracz chce wymienić
        int[] tab=new int[2];
        // tab[0]=ile trzeba dodać do balance[0] gracza
        // tab[1]=ile trzeba dodać do balance[1] gracza
        int pom=(int) Math.round(howMuch*euroRate);
        if(pom<=amount){
            tab[0]=howMuch;
            tab[1]=-pom;
            affluenceObserver.updateEuro(euroRate);
        }
        return tab;
    }
    public void update() {
        Random generator = new Random();
        setEuroRate(getEuroRate() + generator.nextDouble(-0.5,0.5));
        setDolarRate(getDolarRate() + generator.nextDouble(-0.5,0.5));
    }
    public int[] ExchangeEURtoUSD(int amount, int howMuch){
        // amount-bilans gracza w euro, howMuch-liczba dolarów jakie gracz chce wymienić
        int[] tab=new int[2];
        int pom=(int) Math.round(howMuch*dolarRate);
        // tab[0]=ile trzeba dodać do balance[0] gracza (EURO)
        // tab[1]=ile trzeba dodać do balance[1] gracza (DOLARY)
        if(pom<=amount){
            tab[0]=-pom;
            tab[1]=howMuch;
            affluenceObserver.updateDollar(dolarRate);
        }
        return tab;
    }
    public double getEuroRate(){
        return euroRate;
    }
    public void setEuroRate(double euroRate){
        this.euroRate=euroRate;
    }
    public double getDolarRate(){
        return dolarRate;
    }
    public void setDolarRate(double dolarRate){
        this.dolarRate=dolarRate;
    }
}
