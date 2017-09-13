package graph;

import java.util.ArrayList;

/**
 * Stack class for DFS.
 * @author paulhsu
 *
 */
public class Stack {
	private int size;
	private int tail;
	private ArrayList<Integer> stack;
	
	/**
	 * Stack constructor.
	 */
	public Stack() {
		size = 0;
		tail = 0;
		stack = new ArrayList<>();
	}
	
	/**
	 * Push value on top of the stack.
	 * @param value put into stack
	 */
	public void push(int value) {
		if (stack.size() > size) {
			stack.set(tail, value);
		} else {
			stack.add(value);	
		}
		size++;
		tail++;
	}
	
	/**
	 * Pop the first element out of stack
	 * @return the element pop out from stck
	 */
	public int pop() {
		int value = stack.get(tail-1);
		size --;
		tail --;
		return value;
	}
	
	/**
	 * Check the stack is empty
	 * @return true if stack is empty
	 */
	public boolean isEmpty() {
		return (size == 0);
	}
	
}
