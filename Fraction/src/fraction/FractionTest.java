package fraction;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FractionTest {
	Fraction f;
	Fraction f2;
	Fraction f3;
	Fraction f4;
	Fraction f5;
	Fraction f6, f10;
	Fraction zero, quarter, half, one, two, negHalf, negQuarter, negOne;
	Fraction threeQuarters, negThreeQuarters, five, negTwo, negFive;
	
	
	

	
	@Before
	public void setUp() throws Exception{
		f = new Fraction(1, 2);
		f2 = new Fraction(1, -2);
		f3 = new Fraction(1, 2);
		f4 = new Fraction(4);
		f5 = new Fraction(-1, -2);
		f6 = new Fraction(4, 1);
		f10 = new Fraction(-1, 2);
		zero = new Fraction(0, 1);
		quarter = new Fraction(1, 4);
		half = new Fraction(1, 2);
		one = new Fraction(1, 1);
		two = new Fraction(2, 1);
		negHalf = new Fraction(-1, 2);
		negQuarter = new Fraction(-1, 4);
		negOne = new Fraction(-1, 1);
		threeQuarters = new Fraction(3, 4);
		negThreeQuarters = new Fraction(-3, 4);
		five = new Fraction(5, 1);
		negTwo = new Fraction(-2, 1);
		negFive = new Fraction(-5, 1);
	}

	@Test
	public void testAdd() {
		assertEquals("5/6", (f.add(new Fraction(1, 3)).toString()));
		assertEquals("3/4", (f.add(new Fraction(1, 4)).toString()));
		assertNotEquals("6/10", (f.add(new Fraction(1, 5)).toString()));
		assertNotEquals("14/20", (f.add(new Fraction(1, 5)).toString()));
	}
	
	@Test(expected=ArithmeticException.class)
	public void testDivideByZero() {
		Fraction f7 = new Fraction(1, 0);
		Fraction f8 = new Fraction(0 ,0);
		f7.add(f8);
		f2.subtract(f7);
	}
	
	@Test(expected=ClassCastException.class)
	public void neitherIntegerNorFraction() {
		f.compareTo("jjj");
		f.compareTo("kokoe9e9");
	}
	
	@Test
	public void testSubtract() {
		assertEquals("1/6", (f.subtract(new Fraction(1, 3)).toString()));
		assertEquals("1/4", (f.subtract(new Fraction(1, 4)).toString()));
		assertNotEquals("-7    /2", (f.subtract(new Fraction("4")).toString()));
		assertNotEquals("2/4", (f.subtract(new Fraction(1, 4)).toString()));
		assertNotEquals("4/10", (f.subtract(new Fraction(1, 5)).toString()));
	}

	@Test
	public void testMultiply() {
		assertEquals("1/6", (f.multiply(new Fraction("1/3")).toString()));
		assertEquals("1", (f.multiply(new Fraction("2/1")).toString()));
		assertEquals("1", (f.multiply(new Fraction("2")).toString()));
		assertEquals("1", (f.multiply(new Fraction(2)).toString()));
		assertEquals("0", (f.multiply(new Fraction(0)).toString()));
		assertEquals("-1/4", (f.multiply(f2).toString()));
		assertNotEquals("-1   /4", (f.multiply(f2).toString()));
		assertNotEquals("1/-4", (f.multiply(f2).toString()));
	}

	@Test
	public void testDivide() {
		assertEquals("3/2", (f.divide(new Fraction("1/3")).toString()));
		assertNotEquals("2/1", f.divide(new Fraction(1, 4)).toString());
	}

	@Test
	public void testAbs() {
		assertEquals("1/2", f2.abs().toString());
		assertNotEquals("1/-2", f2.abs().toString());
		assertNotEquals("-1/2", f2.abs().toString());
	}

	@Test
	public void testNegate() {
		assertEquals("-1/2", f.negate().toString());
		assertNotEquals("1/-2", f.negate().toString());
	}

	@Test
	public void testInverse() {
		assertEquals("2", f.inverse().toString());
		assertNotEquals("2/1", f.inverse().toString());
	}

	@Test
	public void testEqualsObject() {
		assertFalse(f2.equals(f3));
		assertTrue(f.equals(f5));
		assertTrue(f.equals(f3));
		assertTrue(f4.equals(f6));
		assertTrue(f2.equals(f10));
		assertTrue(negOne.equals(new Fraction(5, -5)));
		//assertEquals(negOne, new Fraction(5, -5));
		//assertEquals(negHalf, new Fraction("1/-2"));
		//assertEquals(negHalf, quarter.divide(negHalf));
		//assertEquals(negHalf, new Fraction(1, -2));
		//assertEquals(negTwo, negHalf.inverse());
	}

	@Test
	public void testCompareTo() {
		assertTrue(f.compareTo(f2) == 0);
		assertTrue(f4.compareTo(f5) > 0);
		assertTrue(f5.compareTo(f6) < 0);
		//assertTrue(quarter.compareTo(half) < 0);
		
	}

	@Test
	public void testToString() {
		assertEquals("1/2", f.toString());
		assertNotEquals("1/   2", f.toString());
		assertNotEquals("1      /2", f.toString());
		System.out.println(f2.toString());
		System.out.println(f10.toString());
	}

}
