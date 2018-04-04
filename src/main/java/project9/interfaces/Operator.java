package project9.interfaces;

@FunctionalInterface
public interface Operator<E> {

    /**
     * Reduce two element E into one element E.
     *
     * @param element1 first element E
     * @param element2 second element E
     * @return Element E
     */
    public E apply(E element1, E element2);

}
