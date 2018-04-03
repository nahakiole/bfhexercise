package project9.classes;

import project9.interfaces.Mapping;
import project9.interfaces.Operator;
import project9.interfaces.Predicate;
import project9.interfaces.Stream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class LazyStream<E> implements Stream<E> {

    @Override
    public boolean matchAll(Predicate<? super E> predicate) {
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            E element = iterator.next();
            if (!predicate.test(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean matchAny(Predicate<? super E> predicate) {
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            E element = iterator.next();
            if (predicate.test(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int countAll() {
        Iterator<E> iterator = iterator();
        int count = 0;
        while (iterator.hasNext()) {
            E element = iterator.next();
            if (element != null) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int count(Predicate<? super E> predicate) {
        Iterator<E> iterator = iterator();
        int count = 0;
        while (iterator.hasNext()) {
            E element = iterator.next();
            if (predicate.test(element)) {
                count++;
            }
        }
        return count;
    }

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

    @Override
    public E find(Predicate<? super E> predicate) {
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            E element = iterator.next();
            if (predicate.test(element)) {
                return element;
            }
        }
        return null;
    }

    @Override
    public E reduce(Operator<E> operator) {
        Iterator<E> iterator = iterator();
        E result = null;
        if (iterator.hasNext()) {
            result = iterator.next();
            while (iterator.hasNext()) {
                E element = iterator.next();
                result = operator.apply(element, result);
            }
        }
        return result;
    }

    @Override
    public List<E> toList() {
        ArrayList<E> elementList = new ArrayList<>();
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            E element = iterator.next();
            elementList.add(element);
        }
        return elementList;
    }

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

    @Override
    public Stream<E> filter(Predicate<? super E> predicate) {
        LazyStream<E> lazyStream = this;
        return new LazyStream<E>() {
            @Override
            public Iterator<E> iterator() {
                return new Iterator<E>() {
                    Iterator<E> innerIterator = lazyStream.iterator();

                    @Override
                    public boolean hasNext() {
                        return innerIterator.hasNext();
                    }

                    @Override
                    public E next() {
                        E element;
                        while (hasNext()) {
                            element = innerIterator.next();
                            if (predicate.test(element)) {
                                return element;
                            }
                        }
                        return null;
                    }
                };
            }
        };
    }

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
                        return mapping.apply(innerIterator.next());
                    }
                };
            }
        };
    }
}
