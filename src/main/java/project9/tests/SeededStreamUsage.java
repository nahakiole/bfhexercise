package project9.tests;

import project9.classes.SeededStream;

import java.util.Arrays;

public class SeededStreamUsage {

    public static void main(String[] args) {
        SeededStream<Integer> seededStream = new SeededStream<>(1, x -> x * 2, x -> x <= 1024);
        System.out.println(seededStream.countAll());
        System.out.println(Arrays.toString(seededStream.toList().toArray()));

        seededStream = new SeededStream<>(1, x -> x+2, x -> x <= 10);
        System.out.println(seededStream.countAll());
        System.out.println(Arrays.toString(seededStream.toList().toArray()));


        seededStream = new SeededStream<>(2, x -> x*x, x -> x <= 50);
        System.out.println(seededStream.countAll());
        System.out.println(Arrays.toString(seededStream.toList().toArray()));

    }
}
