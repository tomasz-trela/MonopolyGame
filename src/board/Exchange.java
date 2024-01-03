package board;
import observer.*;

public class Exchange extends Field{
    private double euroRate;
    private double dolarRate;

    Subject subjects = new Subject();
    
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
            subjects.notifyObserversEuro(); // Aktualizujemy kurs waluty Euro
        }
        return tab;
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
            subjects.notifyObserversDollar(); // Aktualizujemy kurs waluty Dollara
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