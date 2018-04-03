package project9.classes;

import project9.interfaces.Mapping;
import project9.interfaces.Predicate;

import java.util.Iterator;

public class SeededStream<E> extends LazyStream<E> {

    /**
     * Original seed from which all elements are created
     */
    private final E seed;

    /**
     * Method which transforms seed into next element
     */
    private final Mapping<E, E> update;

    /**
     * Condition to check for when generating new elements
     */
    private Predicate<E> condition;

    /**
     * Creates infinite list without condition
     *
     * @param seed   Seed from which all elements are created.
     * @param update Method which transforms seed into next element
     */
    public SeededStream(E seed, Mapping<E, E> update) {
        this.seed = seed;
        this.update = update;
    }


    /**
     * Creates finit list with condition
     *
     * @param seed      Seed from which all elements are created.
     * @param update    Method which transforms seed into next element
     * @param condition Condition to check for when generating new elements
     */
    public SeededStream(E seed, Mapping<E, E> update, Predicate<E> condition) {
        this.seed = seed;
        this.update = update;
        this.condition = condition;
    }

    /**
     * Creates iterator for ArrayStream
     * @return iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            boolean first = true;
            E lastElement = seed;

            @Override
            public boolean hasNext() {
                if (condition == null || condition.test(update.apply(lastElement))) {
                    return true;
                }
                return false;
            }

            @Override
            public E next() {
                if (first) {
                    first = false;
                    return seed;
                }
                lastElement = update.apply(lastElement);
                return lastElement;
            }
        };
    }
}
