package project9.classes;

import project9.interfaces.Mapping;
import project9.interfaces.Operator;
import project9.interfaces.Predicate;
import project9.interfaces.Stream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class LazyStream<E> implements Stream<E> {

    /**
     * Checks if a condition matches all the elements in the stream.
     *
     * @param predicate Condition to check for
     * @return Whether condition matches all elements
     */
    @Override
    public boolean matchAll(Predicate<? super E> predicate) {
        for (E element : this) {
            if (!predicate.test(element)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a condition matches any of the elements in the stream.
     *
     * @param predicate Condition to check for
     * @return Whether condition matches any element
     */
    @Override
    public boolean matchAny(Predicate<? super E> predicate) {
        for (E element : this) {
            if (predicate.test(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Counts all the elements in the stream.
     *
     * @return Element count
     */
    @Override
    public int countAll() {
        int count = 0;
        for (E element : this) {
            count++;
        }
        return count;
    }

    /**
     * Count elements that match a condition.
     *
     * @param predicate Condition to match
     * @return Element count that matches condition
     */
    @Override
    public int count(Predicate<? super E> predicate) {
        int count = 0;
        for (E element : this) {
            if (predicate.test(element)) {
                count++;
            }
        }
        return count;
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
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        int count = 0;
        for (E element : this) {
            if (count == index) {
                return element;
            }
            count++;
        }
        throw new IndexOutOfBoundsException();
    }

    /**
     * Finds first element that matches condition.
     *
     * @param predicate Condition to look for
     * @return Element that matches condition
     */
    @Override
    public E find(Predicate<? super E> predicate) {
        for (E element : this) {
            if (predicate.test(element)) {
                return element;
            }
        }
        return null;
    }

    /**
     * Reduce stream to a single element with a operator method.
     *
     * @param operator Operator method
     * @return Reduced Element
     */
    @Override
    public E reduce(Operator<E> operator) {
        E result = null;
        Iterator<E> iterator = iterator();
        if (iterator.hasNext()) {
            result = iterator.next();
            while (iterator.hasNext()) {
                E element = iterator.next();
                result = operator.apply(element, result);
            }
        }
        return result;
    }

    /**
     * Convert elements in stream to a list.
     *
     * @return List with elements
     */
    @Override
    public List<E> toList() {
        ArrayList<E> elementList = new ArrayList<>();
        for (E element : this) {
            elementList.add(element);
        }
        return elementList;
    }

    /**
     * Sets upper limit for elements in stream.
     *
     * @param n Upper limit
     * @return Stream with limited elements
     * @throws IllegalArgumentException
     */
    @Override
    public Stream<E> limit(int n) throws IllegalArgumentException {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        LazyStream<E> lazyStream = this;
        return new LazyStream<E>() {
            @Override
            public Iterator<E> iterator() {
                return new Iterator<E>() {
                    int i = 0;
                    int limit = n;

                    Iterator<E> innerIterator = lazyStream.iterator();

                    @Override
                    public boolean hasNext() {
                        if (i == limit) {
                            return false;
                        }
                        return innerIterator.hasNext();
                    }

                    @Override
                    public E next() {
                        i++;
                        return innerIterator.next();
                    }
                };
            }
        };
    }

    /**
     * Sets lower limit for elements in stream.
     *
     * @param n lower limit
     * @return Stream with limited elements
     * @throws IllegalArgumentException
     */
    @Override
    public Stream<E> skip(int n) throws IllegalArgumentException {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        LazyStream<E> lazyStream = this;
        return new LazyStream<E>() {
            @Override
            public Iterator<E> iterator() {
                return new Iterator<E>() {
                    int i = 0;
                    int skip = n;
                    Iterator<E> innerIterator = lazyStream.iterator();

                    @Override
                    public boolean hasNext() {
                        while (i < skip && innerIterator.hasNext()) {
                            next();
                        }
                        return innerIterator.hasNext();
                    }

                    @Override
                    public E next() {
                        i++;
                        return innerIterator.next();
                    }
                };
            }
        };
    }

    /**
     * Create new stream with filtered elements
     *
     * @param predicate Condition to filter for
     * @return filtered stream
     */
    @Override
    public Stream<E> filter(Predicate<? super E> predicate) {
        LazyStream<E> lazyStream = this;
        return new LazyStream<E>() {
            @Override
            public Iterator<E> iterator() {
                return new Iterator<E>() {
                    Iterator<E> innerIterator = lazyStream.iterator();

                    E nextElement = null;
                    E currentElement = null;

                    @Override
                    public boolean hasNext() {
                        if (nextElement != null){
                            if (predicate.test(nextElement)){
                                return true;
                            }
                        }
                        while (innerIterator.hasNext()){
                            nextElement = innerIterator.next();
                            if (predicate.test(nextElement)){
                                return true;
                            }
                        }
                        return false;
                    }

                    @Override
                    public E next() {
                        if (hasNext()){
                            currentElement = nextElement;
                            nextElement = null;
                            return currentElement;
                        }
                        return null;
                    }
                };
            }
        };
    }

    /**
     * Create new stream with other type of element by transformation
     *
     * @param mapping Transformation method
     * @return Mapped stream
     */
    @Override
    public <F> Stream<F> map(Mapping<? super E, ? extends F> mapping) {
        LazyStream<E> lazyStream = this;
        return new LazyStream<F>() {
            @Override
            public Iterator<F> iterator() {
                return new Iterator<F>() {
                    Iterator<E> innerIterator = lazyStream.iterator();

                    @Override
                    public boolean hasNext() {
                        return innerIterator.hasNext();
                    }

                    @Override
                    public F next() {
                        E element = innerIterator.next();
                        if (element == null) {
                            return null;
                        }
                        return mapping.apply(element);
                    }
                };
            }
        };
    }
}
