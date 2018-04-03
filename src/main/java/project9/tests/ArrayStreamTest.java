package project9.tests;

import project9.classes.ArrayStream;
import project9.classes.SeededStream;

import java.util.Arrays;

public class ArrayStreamTest {

    public static void main(String[] args) {
        ArrayStream<Integer> arrayStream = new ArrayStream<>(1, 4, 1, 3, 4, 1, 4, 12, 1, 2);
        System.out.println(Arrays.toString(arrayStream.filter(x -> x > 10).toList().toArray()));
        System.out.println(Arrays.toString(arrayStream.map(x -> x > 10).toList().toArray()));
        System.out.println(arrayStream.reduce((x, y) -> x + y));
        System.out.println(arrayStream.filter(x -> x > 10).countAll());

        SeededStream<Integer> seededStream = new SeededStream<>(1, x -> x * 2, x -> x < 50);
        System.out.println(seededStream.countAll());
        System.out.println(Arrays.toString(seededStream.toList().toArray()));

        ArrayStream<String> stringArrayStream = new ArrayStream<>("Bob", "Alice", "Eve", "Fred", "Markus");
        System.out.println(Arrays.toString(stringArrayStream.filter(x -> x.matches(".*e.*")).toList().toArray()));

    }

}
