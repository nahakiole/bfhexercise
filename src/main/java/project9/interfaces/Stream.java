package project9.interfaces;

import java.util.List;

/**
 * An interface for streams.
 *
 * @param <E> the type of elements in this stream
 */
public interface Stream<E> extends Iterable<E> {

    /**
     * Checks if a condition matches all the elements in the stream.
     *
     * @param predicate Condition to check for
     * @return Whether condition matches all elements
     */
    public boolean matchAll(Predicate<? super E> predicate);

    /**
     * Checks if a condition matches any of the elements in the stream.
     *
     * @param predicate Condition to check for
     * @return Whether condition matches any element
     */
    public boolean matchAny(Predicate<? super E> predicate);

    /**
     * Counts all the elements in the stream.
     *
     * @return Element count
     */
    public int countAll();

    /**
     * Count elements that match a condition.
     *
     * @param predicate Condition to match
     * @return Element count that matches condition
     */
    public int count(Predicate<? super E> predicate);

    /**
     * Get element with index from stream.
     *
     * @param index Index of element
     * @return Element at index
     * @throws IndexOutOfBoundsException When invalid index is passed
     */
    public E get(int index) throws IndexOutOfBoundsException;

    /**
     * Finds first element that matches condition.
     *
     * @param predicate Condition to look for
     * @return Element that matches condition
     */
    public E find(Predicate<? super E> predicate);

    /**
     * Reduce stream to a single element with a operator method.
     *
     * @param operator Operator method
     * @return Reduced Element
     */
    public E reduce(Operator<E> operator);

    /**
     * Convert elements in stream to a list.
     *
     * @return List with elements
     */
    public List<E> toList();

    /**
     * Sets upper limit for elements in stream.
     *
     * @param n Upper limit
     * @return Stream with limited elements
     * @throws IllegalArgumentException When limit is negative
     */
    public Stream<E> limit(int n) throws IllegalArgumentException;

    /**
     * Sets lower limit for elements in stream.
     *
     * @param n lower limit
     * @return Stream with limited elements
     * @throws IllegalArgumentException When limit is negative
     */
    public Stream<E> skip(int n) throws IllegalArgumentException;

    /**
     * Create new stream with filtered elements
     *
     * @param predicate Condition to filter for
     * @return filtered stream
     */
    public Stream<E> filter(Predicate<? super E> predicate);

    /**
     * Create new stream with other type of element by transformation
     *
     * @param mapping Transformation method
     * @param <F> New element type
     * @return Mapped stream
     */
    public <F> Stream<F> map(Mapping<? super E, ? extends F> mapping);

}
