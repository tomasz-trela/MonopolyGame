package player;

import java.util.Random;

public class Cube {
    public static int Roll(){
        Random random = new Random();
        int value1 = random.nextInt(6)+1;
        int value2 = random.nextInt(6)+1;
        return value1 + value2;
    }



}