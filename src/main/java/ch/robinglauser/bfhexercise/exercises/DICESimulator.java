package ch.robinglauser.bfhexercise.exercises;

import java.util.Random;

public class DICESimulator {

    Random rand;

    public DICESimulator() {
        rand = new Random();
    }

    public int throwDice(){
        return rand.nextInt(6)+1;
    }
}
