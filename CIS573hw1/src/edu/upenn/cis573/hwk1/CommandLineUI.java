package edu.upenn.cis573.hwk1;

/**
 * CommandLineUI class, printr out the result in console.
 * @author paulhsu
 *
 */
public class CommandLineUI extends Outputer{

	@Override
	public void output(int correct, int incorrect) {
		System.out.println(correct + " correct, " + incorrect + " incorrect");
	}
}
