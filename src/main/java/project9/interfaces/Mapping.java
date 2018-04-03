package project9.interfaces;

@FunctionalInterface
public interface Mapping<E, F> {

	/**
	 * Method to convert element E to element F
	 * @param element E
	 * @return Element F
	 */
	public F apply(E element);

}
