package board;

import java.util.Random;

public class Exchage extends Field{
    private double eurorate;
    private double dolarrate;
    
    public Exchage(){
        eurorate=1.09;
        dolarrate=0.92;
    }
    public Exchage(String name, double eurorate, double dolarrate){
        super(name);
        this.eurorate=eurorate;
        this.dolarrate=dolarrate;
    }
    public int[] ExchageUSDtoEUR(int amount, int howMuch){
        // amount-bilans gracza w dolarach, howMuch-liczba euro jakie gracz chce wymienić
        int[] tab=new int[2];
        // tab[0]=ile trzeba dodać do balance[0] gracza
        // tab[1]=ile trzeba dodać do balance[1] gracza
        int pom=(int) Math.round(howMuch*eurorate);
        if(pom<=amount){
            tab[0]=howMuch;
            tab[1]=-pom;
        }
        return tab;
    }
    public void update() {
        Random generator = new Random();
        setEurorate(getEurorate() + generator.nextFloat(-0.5F,0.5F));
        setDolarrate(getDolarrate() + generator.nextFloat(-0.5F,0.5F));
    }
    public int[] ExchageEURtoUSD(int amount, int howMuch){
        // amount-bilans gracza w euro, howMuch-liczba dolarów jakie gracz chce wymienić
        int[] tab=new int[2];
        int pom=(int) Math.round(howMuch*dolarrate);
        // tab[0]=ile trzeba dodać do balance[0] gracza (EURO)
        // tab[1]=ile trzeba dodać do balance[1] gracza (DOLARY)
        if(pom<=amount){
            tab[0]=-pom;
            tab[1]=howMuch;
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
