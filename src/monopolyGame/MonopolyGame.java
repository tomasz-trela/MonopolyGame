package monopolyGame;
import player.*;

public class MonopolyGame {
    public static void main(String[] args) {
     
        new GameFrame();


        // Na dole jest implementacja ruchu gracza w konsoli
        // teraz to jeszcze graficznie trza
        
        /*Player gracz1 = new Player();

        while(gracz1.getOkrazenie() != 2){
            int tmp = gracz1.getPole();
            System.out.println("Gracz jest na: " + gracz1.getPole() + " okrazenie " + gracz1.getOkrazenie());
            
            gracz1.setPole(gracz1.getPole() + gracz1.rzutKostka() + gracz1.rzutKostka() );
            if(tmp>gracz1.getPole())
                gracz1.setOkrazenie(gracz1.getOkrazenie()+1);
        }
        
        System.out.println("Gracz jest na: " + gracz1.getPole() + " okrazenie " + gracz1.getOkrazenie());
        */
    }
}