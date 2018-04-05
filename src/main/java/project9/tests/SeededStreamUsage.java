package project9.tests;

import project9.classes.SeededStream;

import java.awt.*;
import java.util.Arrays;

public class SeededStreamUsage {

    public static void main(String[] args) {
        SeededStream<Integer> seededStream = new SeededStream<>(1, x -> x * 2, x -> x <= 1024);
        System.out.println(seededStream.countAll());
        System.out.println(Arrays.toString(seededStream.toList().toArray()));

        seededStream = new SeededStream<>(1, x -> x + 2, x -> x <= 10);
        System.out.println(seededStream.countAll());
        System.out.println(Arrays.toString(seededStream.toList().toArray()));


        seededStream = new SeededStream<>(2, x -> x * x, x -> x <= 50);
        System.out.println(seededStream.countAll());
        System.out.println(Arrays.toString(seededStream.toList().toArray()));


        SeededStream<Rectangle> rectangleSeededStream = new SeededStream<>(new Rectangle(1, 1), x -> new Rectangle(x.width *2 , x.height * 2), x -> x.height < 100);

        System.out.println(rectangleSeededStream.map(x -> "[height: " + x.height + " width: " + x.width + "]\n")
                .reduce((x, y) -> y+x));

    }
}
