import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * It is a HTML validator class to validate HTML is valid or not.
 * @author paulhsu
 *
 */
public class HTMLValidator {
	Pattern pattern;
	Matcher matcher;
	String tagPattern = "<(\"[^\"]*\"|'[^']*'|[^'\">])*>";
	MyStack<String> myStack;
	
	/**
	 * Class constructor.
	 */
	public HTMLValidator() {
		pattern = Pattern.compile(tagPattern);
		myStack = new MyStack<String>("!doctypehtml");
	}
	
	/**
	 * Validate the tag is valid or not.
	 * @param tag String tag to be tested.
	 * @return
	 */
	public boolean validHTMLfile(String tag){
	matcher = pattern.matcher(tag);
	while(matcher.find()){
		String s1 = matcher.group();
		if (! isSelfClosingTag(s1) && ! match(s1)) {
			System.out.println("Not a valid html file.");
			return false;
		} 	
	}
	System.out.println("It is a valid html file.");
	return true;
	}
	
	/**
	 * Checks tag is open tag or not.
	 * @param tag
	 * @return true if it is a open tag.
	 */
	public boolean isOpenTag(String tag){
		if (tag.substring(1, 2).equals("/")) return false;
		return true;
	}
	
	/**
	 * Checks tag is self-closing tag or not.
	 * @param tag
	 * @return true if it is a self-closing tag. 
	 */
	public boolean isSelfClosingTag(String tag){
		if (tag.substring(tag.length()-2, tag.length()).equals("/>")) return true;
		return false;
	}
	
	/**
	 * If it an open tag, push the tag into stack. 
	 * If it is a closing tag, peek the stack
	 * @param tag
	 * @return true if it is an open tag or it is a closing tag which is the same as the top tag on stack.
	 */
	public boolean match(String tag){
		if(isOpenTag(tag)) {
			if(tag.contains(" ")) myStack.push(tag.substring(1, tag.indexOf(" ")));
			else myStack.push(tag.substring(1, tag.length()-1));
			return true;
		}
		else {
			if(tag.substring(2, tag.length()-1).equals(myStack.peek())) {
				myStack.pop();
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Specifically check <!DOCTYPE html> 
	 * @param tag String tag
	 * @return true if page has <!DOCTYPE html> at the beginning of file.
	 */
	public boolean matchBeginner(String tag){
		if(tag.contains(" ")){
			String[] tempArray = tag.split(" ");
			String tempTag = tempArray[0].toLowerCase() + tempArray[1].toLowerCase();
			String newTag = tempTag.replaceAll("<", "");
			newTag = newTag.replaceAll(">", "");
			if(newTag.equals(myStack.peek())) {
				return true;
			}
		}
		System.out.println("Missing <!DOCTYPE HTML>");
		return false;
	}
}
