package player;

import java.util.ArrayList;

public class Player {
    private int okrazenie;
    private double saldoEuro;
    private double saldoDolar;
    private boolean czyAuto;
    private ArrayList<Integer> PosiadanePola = new ArrayList<Integer>();
    private int pole; 

    public Player(){
        this.saldoEuro = 50000;
        this.saldoDolar = 50000;
        this.czyAuto = false;
        this.PosiadanePola.clear(); 
        this.pole = 0;
        this.okrazenie = 0;
    }

    //gettery settery
    public double getSaldoEuro() {
        return saldoEuro;
    }

    public void setSaldoEuro(double saldoEuro) {
        this.saldoEuro = saldoEuro;
    }

    public double getSaldoDolar() {
        return saldoDolar;
    }

    public void setSaldoDolar(double saldoDolar) {
        this.saldoDolar = saldoDolar;
    }

    public boolean GetCzyAuto() {
        return czyAuto;
    }

    public void setCzyAuto(boolean czyAuto) {
        this.czyAuto = czyAuto;
    }

    public int getPole() {
        return pole;
    }

    public void setPole(int pole) {
        this.pole = (pole%36);
    }

    public int getOkrazenie() {
        return okrazenie;
    }

    public void setOkrazenie(int okrazenie) {
        this.okrazenie = okrazenie;
    }

    //nie lepiej zamiast całej klasy kostka tutaj to zrobic ? (jak coś to to zmieć)
    public int rzutKostka(){
        return (int) ((Math.random()*6) + 1); 
    }
}