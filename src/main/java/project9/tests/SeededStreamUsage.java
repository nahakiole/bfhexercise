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


        SeededStream<Rectangle> rectangleSeededStream = new SeededStream<>(
                new Rectangle((int) (Math.random() * 8)+2, (int) (Math.random() * 8)+2),
                x -> new Rectangle(
                        (int) (x.width + Math.random() * 4) + 2,
                        (int) (x.height + Math.random() * 4) + 2),
                x -> x.height < 100);

        System.out.println(rectangleSeededStream
                .filter(x -> x.height % 2 == 0 && x.width % 2 == 0)
                .map(x -> "[height: " + x.height + " width: " + x.width + "]\n")
                .reduce((x, y) -> y + x));

    }
}
