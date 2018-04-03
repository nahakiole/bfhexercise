package project9.interfaces;

@FunctionalInterface
public interface Predicate<E> {

	/**
	 * Test whether element E meets certain conditions.
	 * @param element Element E
	 * @return Whether element E meets the condition.
	 */
	public boolean test(E element);

}
