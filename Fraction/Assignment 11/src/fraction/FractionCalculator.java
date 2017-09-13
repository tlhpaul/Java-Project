package fraction;
import java.util.Scanner;

/**
 * @author Tse-Lun Hsu
 *
 */
public class FractionCalculator {
	/** Used for getting input from the user. */
    Scanner scanner = new Scanner(System.in);
    public int n = 0;
    public int m = 1;
	
	/**
	 * Calls run method and start this program. 
	 * 
	 * @param args Arguments for main method.
	 */
	public static void main(String[] args) {
		FractionCalculator f = new FractionCalculator();
		f.run();
	}
	
	
	/**
	 * Prints the instruction and asks user to input instruction and number.
	 *  
	 */
	public void run(){
		String x;
		Fraction f2 = new Fraction(n, m);
		while (true){
			try {
			System.out.println("The current number of calculator: " + f2.toString());
			System.out.println();
            x = getInput("Your instruction?");
            if (x.equals("q")) break;
            else if (x.equals("a")) f2 = f2.abs();
            else if (x.equals("c")) f2 = f2.multiply(new Fraction((0)));
            else if (x.equals("i")) f2 = f2.inverse();
            else if (x.equals("add")) f2 = f2.add(new Fraction(n, m));
            else if (x.equals("sub")) f2 = f2.subtract(new Fraction(n, m));
            else if (x.equals("mul")) f2 = f2.multiply(new Fraction(n, m));
            else if (x.equals("div")) f2 = f2.divide(new Fraction(n, m));
            else if (x.equals("dis")) {
            	f2 = f2.multiply(new Fraction((0)));
            	f2 = f2.add(new Fraction(n, m));
            	}   
			}
			catch (ArithmeticException e1){
				System.out.println("ArithmeticException: Denominator should not be 0.");	
			}
			catch (NumberFormatException e2){
				System.out.println("NumberFormatException: Invalid input.");
				System.out.println("Your enter is invalid. PLease try again.");
			}
		}
		System.out.println("Bye!");
	}

	
	/**
	 * Gets the input from user and corresponds the input to the instructions.
	 * 
	 * @param question The prompt.
	 * @return The string to indicate which instruction user enters.
	 */
	public String getInput(String question){
		String answer;
		printInstruction();
		while (true) {
            System.out.print(question + "  ");
            answer = scanner.nextLine();
            	if (answer.equals("a")) return "a";
                if (answer.equals("c")) return "c";
                if (answer.equals("i")) return "i";
                if (answer.equals("q")) return "q";
                if (answer.length() > 1) {
                	String b = answer.replaceAll(" ", "");
                	String c = b.substring(0, 1);
                	String d = b.substring(1);
                	if (d.indexOf('/') >= 0) {
            			String[] s = d.split("/");
            			n = Integer.parseInt(s[0]);				
            			m = Integer.parseInt(s[1]);
            		}
            		else {
            			n = Integer.parseInt(d);
            			m = 1;
            		}
                	if (c.equals("+")) return "add";
                	if (c.equals("-")) return "sub";
                	if (c.equals("*")) return "mul";
                	if (c.equals("/")) return "div";
                	if (c.equals("s")) return "dis";
                	else throw new NumberFormatException();                      
                }
                else throw new NumberFormatException();
            }
        }     
        

	/**
	 * Prints the instructions for user. 
	 * 
	 */
	void printInstruction(){
		System.out.println("PLease enter the following commands:");
		System.out.println("Enter \"a\" to take the absolute value of the number currently displayed.");
		System.out.println("Enter \"c\" to clear (reset to zero) the calculator.");
		System.out.println("Enter \"i\" to invert the number currently displayed.");
		System.out.println("Enter \"s n\" to discard the number currently displayed and replace it with n.");
		System.out.println("Enter \"+ n\" to add n to the number currently displayed.");
		System.out.println("Enter \"- n\" to subtract n from the number currently displayed.");
		System.out.println("Enter \"* n\" to multiply the number currently displayed by n.");
		System.out.println("Enter \"/ n\" to divide the number currently displayed by n.");
		System.out.println("Enter \"q\" to quit the program.");
	}
}
