

/**
 * This is the generic node class
 * @author paulhsu
 *
 * @param <E> E element 
 */
public class GenericNode<E> {
	public E value;
	public GenericNode<E> next;
	public GenericNode<E> previous;

	/**
	 * Constructor for thsi class
	 * @param value E value
	 */
	public GenericNode(E value) {
		this.value = value;
		next = null;
		previous = null;
	}
	
}
