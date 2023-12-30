package board;

import java.util.Random;

public class Exchage extends Field{
    private float eurorate;
    private float dolarrate;
    
    public Exchage(){
        eurorate=1.09F;
        dolarrate=0.92F;
    }
    public Exchage(String name, float eurorate, float dolarrate){
        super(name);
        this.eurorate=eurorate;
        this.dolarrate=dolarrate;
    }
    public int[] ExchageUSDtoEUR(int amount, int howMuch){
        // amount-bilans gracza w dolarach, howMuch-liczba euro jakie gracz chce wymienić
        int[] tab=new int[2];
        // tab[0]=ile trzeba dodać do balance[0] gracza
        // tab[1]=ile trzeba dodać do balance[1] gracza
        int pom=Math.round(howMuch*eurorate);
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
        int[] tab=new int[2];
        int pom=Math.round(howMuch*dolarrate);
        // tab[0]=ile trzeba dodać do balance[0] gracza (EURO)
        // tab[1]=ile trzeba dodać do balance[1] gracza (DOLARY)
        if(pom<=amount){
            tab[0]=-pom;
            tab[1]=howMuch;
        }
        return tab;
    }
    public float getEurorate(){
        return eurorate;
    }
    public void setEurorate(float eurorate){
        this.eurorate=eurorate;
    }
    public float getDolarrate(){
        return dolarrate;
    }
    public void setDolarrate(float dolarrate){
        this.dolarrate=dolarrate;
    }
}
