package project9.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class ArrayStream<E> extends LazyStream<E> {

    /**
     * List with array elements.
     */
    private ArrayList<E> elements = new ArrayList<>();

    /**
     * Create ArrayStream from string array.
     *
     * @param array Initial array
     */
    public ArrayStream(E... array) {
        elements.addAll(Arrays.asList(array));
    }


    /**
     * Get element with index from stream.
     *
     * @param index Index of element
     * @return Element at index
     * @throws IndexOutOfBoundsException
     */
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        return elements.get(index);
    }

    /**
     * Creates iterator for ArrayStream
     *
     * @return iterator
     */
    @Override
    public Iterator<E> iterator() {
        return elements.iterator();
    }
}
