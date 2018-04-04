package project9.tests;

import project9.classes.ArrayStream;
import project9.interfaces.Stream;

import java.util.Arrays;

public class ArrayStreamUsage {

    public static void main(String[] args) {
        ArrayStream<Integer> arrayStream = new ArrayStream<>(1, 4, 1, 3, 4, 1, 4, 12, 1, 2);
        System.out.println(Arrays.toString(arrayStream.filter(x -> x > 10).toList().toArray()));
        System.out.println(Arrays.toString(arrayStream.map(x -> x > 10).toList().toArray()));
        System.out.println(arrayStream.reduce((x, y) -> x + y));
        System.out.println(arrayStream.filter(x -> x > 10).countAll());

        ArrayStream<String> stringArrayStream = new ArrayStream<>("Bob", "Alice", "Eve", "Fred", "Markus");
        System.out.println(Arrays.toString(stringArrayStream.toList().toArray()));
        System.out.println(Arrays.toString(stringArrayStream.filter(x -> x.matches(".*e.*")).toList().toArray()));
        System.out.println(Arrays.toString(stringArrayStream.filter(x -> x.matches("A.*")).toList().toArray()));
        System.out.println(Arrays.toString(stringArrayStream.map(String::length).toList().toArray()));

        Stream<String> a = stringArrayStream.filter(x -> x.matches(".*e.*")).map(String::toUpperCase);
        String joined = stringArrayStream.filter(x -> x.matches(".*e.*")).map(String::toUpperCase).reduce((x, y) -> y+x);
        System.out.println(Arrays.toString(a.toList().toArray()));
        System.out.println(joined);
    }

}
