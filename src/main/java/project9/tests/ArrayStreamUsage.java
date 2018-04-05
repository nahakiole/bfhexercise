package project9.tests;

import project9.classes.ArrayStream;
import project9.interfaces.Stream;

import java.awt.*;
import java.util.Arrays;

public class ArrayStreamUsage {

    public static void main(String[] args) {
        ArrayStream<Integer> arrayStream = new ArrayStream<>(1,2,3,4,5,6,7,8,9,10);

        System.out.println(Arrays.toString(arrayStream.filter(x -> x > 5).toList().toArray()));

        System.out.println(Arrays.toString(arrayStream.map(x -> x > 5).toList().toArray()));

        System.out.println(arrayStream.reduce((x, y) -> x + y));

        System.out.println(arrayStream.filter(x -> x > 10).countAll());

        ArrayStream<Rectangle> rectangleArrayStream = new ArrayStream<>(
                new Rectangle(13, 55, 10, 10),
                new Rectangle(55, 24, 10, 10),
                new Rectangle(10, 56, 10, 10),
                new Rectangle(23, 10, 10, 10),
                new Rectangle(15, 10, 10, 10)
        );

        System.out.println(
                rectangleArrayStream
                        .filter(x -> x.x > 12 && x.y > 12)
                        .map(x -> "[x:" + x.x + " y:" + x.y + "]")
                        .reduce((x, y) -> x + " " + y)
        );


        ArrayStream<String> stringArrayStream = new ArrayStream<>("Bob", "Alice", "Eve", "Fred", "Markus");
        System.out.println(Arrays.toString(stringArrayStream.toList().toArray()));
        System.out.println(Arrays.toString(stringArrayStream.filter(x -> x.matches(".*e.*")).toList().toArray()));
        System.out.println(Arrays.toString(stringArrayStream.filter(x -> x.matches("A.*")).toList().toArray()));
        System.out.println(Arrays.toString(stringArrayStream.map(String::length).toList().toArray()));

        Stream<String> a = stringArrayStream.filter(x -> x.matches(".*e.*")).map(String::toUpperCase);
        String joined = stringArrayStream.filter(x -> x.matches(".*e.*")).map(String::toLowerCase).reduce((x, y) -> x + ", " + y);
        System.out.println(Arrays.toString(a.toList().toArray()));
        System.out.println(joined);
    }

}
