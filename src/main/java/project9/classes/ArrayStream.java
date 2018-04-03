package project9.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class ArrayStream<E> extends LazyStream<E> {

    private ArrayList<E> elements = new ArrayList<>();

    public ArrayStream(E... array) {
        elements.addAll(Arrays.asList(array));
    }

    @Override
    public Iterator<E> iterator() {
        return elements.iterator();
    }
}
