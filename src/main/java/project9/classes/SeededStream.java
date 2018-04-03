package project9.classes;

import project9.interfaces.Mapping;
import project9.interfaces.Predicate;

import java.util.Iterator;

public class SeededStream<E> extends LazyStream<E> {


    private final E seed;
    private final Mapping<E, E> update;
    private Predicate<E> condition;

    public SeededStream(E seed, Mapping<E, E> update) {
        this.seed = seed;
        this.update = update;
    }


    public SeededStream(E seed, Mapping<E, E> update, Predicate<E> condition) {
        this.seed = seed;
        this.update = update;
        this.condition = condition;
    }

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
                if (first){
                    first = false;
                    return seed;
                }
                lastElement = update.apply(lastElement);
                return lastElement;
            }
        };
    }
}
