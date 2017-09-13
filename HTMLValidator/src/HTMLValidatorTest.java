import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HTMLValidatorTest {
	HTMLValidator htmlValidator;

	@Before
	public void setUp() throws Exception {
		htmlValidator = new HTMLValidator();
	}

	@Test
	public void testIsOpenTag() {
		String tag = "<head>";
		String tag1 = "</style>";
		assertTrue(htmlValidator.isOpenTag(tag));
		assertFalse(htmlValidator.isOpenTag(tag1));
	}

	@Test
	public void testIsSelfClosingTag() {
		String tag = "<head>";
		String tag1 = "</style>";
		String tag2 = "<img src=”cat.jpeg” />";
		assertFalse(htmlValidator.isSelfClosingTag(tag));
		assertFalse(htmlValidator.isSelfClosingTag(tag1));
		assertTrue(htmlValidator.isSelfClosingTag(tag2));
	}

	@Test
	public void testMatch() {
		String tag = "<head>";
		String tag1 = "<p class=\"color\">";
		String tag2 = "</style>";
		String tag3 = "</body>";
		String tag4 = "</p>";
		assertTrue(htmlValidator.match(tag));
		assertTrue(htmlValidator.match(tag1));
		assertFalse(htmlValidator.match(tag2));
		assertFalse(htmlValidator.match(tag3));
		assertTrue(htmlValidator.match(tag4));
	}

	@Test
	public void testMatchBeginner() {
		String tag = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";
		String tag1 = "<!DOCTYPE html>";
		String tag2 = "<head>";
		String tag3 = "<img src=”cat.jpeg” />";
		assertTrue(htmlValidator.matchBeginner(tag));
		assertTrue(htmlValidator.matchBeginner(tag1));
		assertFalse(htmlValidator.matchBeginner(tag2));
		assertFalse(htmlValidator.matchBeginner(tag3));
	}
}
