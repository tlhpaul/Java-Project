import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Tester {
	static String tag = "";
	static String beggingTag = "";

	public static void main(String[] args) throws IOException{
		HTMLValidator htmlValidator = new HTMLValidator();
		while(true){
			try {
				Scanner scanner = new Scanner(System.in); 
				System.out.print("Please input file Name: ");
				File inputFile = new File(scanner.nextLine().trim());
		    	Scanner scan = new Scanner(inputFile);
		    	beggingTag = scan.nextLine();
			    while(scan.hasNext()){
			    	tag += scan.nextLine();
			    }
			    if(htmlValidator.matchBeginner(beggingTag)) htmlValidator.validHTMLfile(tag);
			    scan.close();
			    scanner.close();
			    break;
			} catch (IOException e) {
				System.out.println("Can't find file.");
			} catch (NoSuchElementException e) {
				System.out.println("Wrong file.");
			}
		} 
	}
}
