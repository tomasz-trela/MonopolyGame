package player;

import java.util.Random;

public class Dice {
    
    public static int Roll(){

        //rzut kostka

        Random random = new Random();
        int value1 = random.nextInt(6)+1;
        return value1;
    }
}
