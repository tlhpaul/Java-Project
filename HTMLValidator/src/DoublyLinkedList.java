
/**
 * This is the doubly linked list class
 * @author paulhsu
 *
 * @param <E> E element 
 */
public class DoublyLinkedList<E> {
	
	private int size;
	private GenericNode<E> head;
	private GenericNode<E> tail;
	
	/**
	 * Doubly linked list constructor
	 * @param head Input the head to initialize doubly linked list. 
	 */
	public DoublyLinkedList(GenericNode<E> head) {
		size = 1;
		this.head = head;
		this.tail = head;
	}
	
	/**
	 * Appends the specified element to the end of this list. 
	 * @param e
	 * @return true if this doubly linked list changed as a result of the call
	 */
	boolean add(E e){	
		if(e == null) return false;
		GenericNode<E> node = new GenericNode<E>(e);
		if(isEmpty()){
			this.head = node;
			GenericNode<E> i = head;
			while(i.next != null){
				i = i.next;
			}
			tail = i;
		}
		tail.next = node;
		node.previous = tail;
		tail = node;
		size++;
		return true;
	}

	/**
	 * Inserts the specified element at the specified position in this list. 
	 * @param index User needs to specify the index 
	 * @param element E element 
	 */
	void add(int index, E element){
		GenericNode<E> node = head;
		GenericNode<E> node1;
		GenericNode<E> newNode = new GenericNode<E>(element);
		if(element == null) throw new NullPointerException();
		if(index >= size && size != 0) throw new IndexOutOfBoundsException();
		if(index == 0 && size == 0) {
			this.head = newNode;
			GenericNode<E> i = head;
			while(i.next != null){
				i = i.next;
			}
			tail = i;
		}
		else if (index == 0 && size != 0) {
			newNode.next = head;
			head.previous = newNode;
			head = newNode;
		} 
		else {
			for (int i = 0; i < index -1; i++){
				node = node.next;
			}
			node1 = node.next;
			newNode.next = node1;
			node1.previous = newNode;
			node.next = newNode;
			newNode.previous = node;
		}
		size++;
	}
	
	/**
	 * Removes all of the elements from this list.
	 */
	void clear(){
		head = null;
		tail = null;
		size = 0;
	}
	
	/**
	 * Returns true if this list contains no elements.
	 * @return true if this list contains no elements
	 */
	boolean isEmpty(){
		return size() == 0;
	}
	
	/**
	 * Removes the element at the specified position in this list
	 * @param index
	 * @return E the element previously at the specified position
	 */
	E remove(int index){
		if(index > size - 1) throw new IndexOutOfBoundsException();
		GenericNode<E> node = head;
		GenericNode<E> node1;
		GenericNode<E> nodeToReturn;
		if(size == 1){
			nodeToReturn = head;
			clear();
		} else if (index == 0 && size != 0){
			nodeToReturn = head;
			head = head.next;
			head.previous = null;
			size --;
		} else if (index == size - 1 && size != 0) {
			nodeToReturn = tail;
			tail = tail.previous;
			tail.next = null;
			size --;
		}
		else {
			for(int i = 0; i < index -1; i++){
				node = node.next;
			}
			node1 = node.next.next;
			nodeToReturn = node.next;
			node.next = node1;
			node1.previous = node;
			size --;
		}
		return nodeToReturn.value;
	}
	
	/**
	 * Removes the first occurrence of the specified element from this list, if it is present.
	 * @param o
	 * @return true if this list contained the specified element
	 */
	boolean remove(Object o){
		if (o == null) throw new NullPointerException();
		GenericNode<E> node = head;
		for(int i = 0; i < size; i++){
			if (node.value.equals(o)) {
				remove(i);
				return true;
			} node = node.next;
		}
		return false;
	}
	
	/**
	 * Returns the number of elements in this list.
	 * @return size
	 */
	int size(){
		return size;
	}
	
	/**
	 * Return head in linked list 
	 * @return head
	 */
	public GenericNode<E> getHead(){
		return head;
	}
	
	/**
	 * Return tail in linked list 
	 * @return tail
	 */
	public GenericNode<E> getTail(){
		return tail;
	}
}
