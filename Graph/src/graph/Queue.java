package graph;


import java.util.ArrayList;

/**
 * Queue class for DFS and BFS
 * @author paulhsu
 *
 */
public class Queue {
	private int head;
	private int size;
	private ArrayList<Integer> queue;
	
	/**
	 * Queue constructor
	 */
	public Queue() {
		head = 0;
		size = 0;
		queue = new ArrayList<>();
	}
	
	/**
	 * Enqueue the value at the end of queue.
	 * @param value enqueued into queue
	 */
	public void enqueue(int value) {
		queue.add(value);
		size ++;
	}
	
	/**
	 * Dequeue the first element out of queue
	 * @return the value dequeued
	 */
	public int dequeue() {
		int value = queue.get(head);
		head ++;
		size --;
		return value;
	}
	
	/**
	 * Check the queue is empty
	 * @return
	 */
	public boolean isEmpty() {
		return (size == 0);
	}
	
	/**
	 * Check given value is in queue
	 * @param value the value would like to be checked
	 * @return true if given value is in the queue
	 */
	public boolean contains(int value) {
		return queue.contains(value);
	}
	
	/**
	 * Get the last value in the queue
	 * @return the last value
	 */
	public int getLastValue() {
		return queue.get(queue.size() - 1);
	}
	
}
