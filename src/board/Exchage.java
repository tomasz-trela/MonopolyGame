package board;

import java.util.Random;

public class Exchage extends Field{
    private double eurorate;
    private double dolarrate;
    
    public Exchage(){
        eurorate=0.92;
        dolarrate=1.09;
    }
    public Exchage(String name, double eurorate, double dolarrate){
        super(name);
        this.eurorate=eurorate;
        this.dolarrate=dolarrate;
    }
    public double[] ExchageUSDtoEUR(double amount, int howMuch){
        double[] tab=new double[2];
        // tab[0]=ile trzeba dodać euro do Eurobalance
        // tab[1]=ile trzeba odjąć dolarów od Dolarbalance
        if(howMuch*eurorate>amount){
            tab[0]=0;
            tab[1]=0;
        } else{
            tab[0]=howMuch;
            tab[1]=howMuch*eurorate;
        }
        return tab;
    }
    public void update() {
        Random generator = new Random();
        setEurorate(getEurorate() + generator.nextDouble(-0.5,0.5));
        setDolarrate(getDolarrate() + generator.nextDouble(-0.5,0.5));
    }
    public double[] ExchageEURtoUSD(double amount, int howMuch){
        double[] tab=new double[2];
        // tab[0]=ile trzeba dodać dolarów do Dolarbalance
        // tab[1]=ile trzeba odjąć euro od Eurobalance
        if(howMuch*dolarrate>amount){
            tab[0]=0;
            tab[1]=0;
        } else{
            tab[0]=howMuch;
            tab[1]=howMuch*dolarrate;
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
