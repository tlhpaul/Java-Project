import java.util.EmptyStackException;

/**
 * MyStack using the Doubly Linked List from my other class.
 * @author paulhsu
 *
 * @param <E> E element
 */
public class MyStack<E> {
	private DoublyLinkedList<E> doublyLinkedList;
	
	/**
	 * MyStack constructor
	 * @param item E item
	 */
	public MyStack(E item) {
		GenericNode<E> head = new GenericNode<E>(item);
		doublyLinkedList = new DoublyLinkedList<E>(head);
	}
	
	/**
	 * Tests if this stack is empty.
	 * @return true if and only if this stack contains no items; false otherwise.
	 */
	boolean empty(){
		return doublyLinkedList.isEmpty();
	}
	
	/**
	 * Looks at the object at the top of this stack without removing it from the stack.
	 * @return the object at the top of this stack (the last item of the Vector object).
	 */
	E peek(){
		if (empty()) throw new EmptyStackException();
		return doublyLinkedList.getHead().value;
	}
	
	/**
	 * Removes the object at the top of this stack and returns that object as the value of this function.
	 * @return
	 */
	E pop(){
		if(empty()) throw new EmptyStackException();
		E item = doublyLinkedList.remove(0);
		return item;
	}
	
	/**
	 * Pushes an item onto the top of this stack.
	 * @param item
	 * @return the item argument.
	 */
	E push(E item){
		doublyLinkedList.add(0, item);
		return item;
	}
}
