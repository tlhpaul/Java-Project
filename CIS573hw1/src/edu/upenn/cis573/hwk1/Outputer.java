package edu.upenn.cis573.hwk1;

/**
 * Outputer class, handles different type of output.
 * @author paulhsu
 *
 */
public abstract class Outputer {
	
	/**
	 * Output the result
	 * @param correct
	 * @param incorrect
	 */
	abstract public void output(int correct, int incorrect);
	
	
}
