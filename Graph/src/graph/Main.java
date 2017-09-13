package graph;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main class (tester class)
 * @author paulhsu
 *
 */
public class Main {

	public static void main(String[] args) {
		AdjancentList adjancentList = new AdjancentList();
		AdjancentMatrix adjancentMatrix = new AdjancentMatrix();
		FileInputer fileInputer = new FileInputer();
		while(true) {
			try {
				ArrayList<Node> friendList = new ArrayList<>();
				int[][] friendList1 = new int[4039][4039];
				Scanner scan = fileInputer.input();
				while(scan.hasNextLine()){
					String input = scan.nextLine();
					friendList = adjancentList.makeAdjancentList(friendList, input);
					friendList1 = adjancentMatrix.makeAdjacentMatrix(friendList1, input);
				}
				Question question = new Question(friendList);
				question.answerQuestion();
		        scan.close();
		        break;
			} catch(FileNotFoundException e){
				System.out.println("File not found");
			} catch (NumberFormatException e){
				System.out.println("File not found");
			}
		}
	}
}
