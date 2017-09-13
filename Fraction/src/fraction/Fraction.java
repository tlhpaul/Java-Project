package fraction;


/**
 * @author Tse-Lun Hsu
 *
 */
public class Fraction implements Comparable<Object>{
	private int numerator;
	private int denominator;
	
	
	/**
	 * Constructs numerator and denominator if user inputs two integers. 
	 * 
	 * @param numerator The integer to be numerator. 
	 * @param denominator The integer to be denominator. 
	 * @exception ArithmeticException if denominator is 0.
	 */
	public Fraction(int numerator, int denominator){
		int x;
		if (denominator == 0) throw new ArithmeticException();
		if (numerator < 0 && denominator < 0) {
			numerator = numerator * -1;
			denominator = denominator * -1;
		}
		x = greatestCommonDivisor(numerator, denominator);
		this.numerator = numerator / x;
		this.denominator = denominator / x;	
	}
	
	
	/**
	 * Constructs numerator and denominator. If user only inputs one number, 
	 * that number will be assigned to numerator. And assigns 1 to denominator.  
	 * 
	 * @param wholeNumber The integer to be numerator. 
	 */
	public Fraction(int wholeNumber){
		this.numerator = wholeNumber;
		this.denominator = 1;
	}
	
	/**
	 * Constructs numerator and denominator. If user inputs a string fraction. 
	 * 
	 * @param fraction The first integer in the fraction is numerator, 
	 * the second integer in the fraction is denominator.
	 * @exception ArithmeticException if denominator is 0.
	 */
	public Fraction(String fraction){
		int x1;
		int x2;
		int x3;
		String a = fraction.replaceAll(" ", "");
		if (a.indexOf('/') >= 0) {
			String[] s = a.split("/");
			x1 = Integer.parseInt(s[0]);				
			x2 = Integer.parseInt(s[1]);
			if (x2 == 0) throw new ArithmeticException();
			if (x1 < 0 && x2 < 0) {
				x1 = x1 * -1;
				x2 = x2 * -1;
				}
			int x = greatestCommonDivisor(x1, x2);	
			this.numerator = x1 / x;
			this.denominator = x2 / x;
			}
		else {
			x3 = Integer.parseInt(a);
			this.numerator = x3;
			this.denominator = 1;
			}	
		}
	
	/**
	 * Adds this fraction to Fraction F and returns the sum 
	 * of this Fraction and the Fraction f.  
	 * 
	 * @param f The Fraction to be added to this one.
	 * @return The sum of the two Fractions.
	 */
	public Fraction add(Fraction f){
		int a = numerator;
		int b = denominator;
		int c = 0;
		int d = 1;
		c = numeratorFromOtherFraction(c, f);
		d = denominatorFromOtherFraction(d, f);
		int x = a*d + b*c;
		int y = b*d;
		return new Fraction(x, y);
	}
	
	/**
	 * Subtracts Fraction F from this Fraction. Returns a new Fraction 
	 * that is the difference of this Fraction and the Fraction F.
	 * 
	 * @param f The Fraction to be subtracted to this Fraction.
	 * @return The difference of this Fraction and the Fraction F.
	 */
	public Fraction subtract(Fraction f){
		int a = numerator;
		int b = denominator;
		int c = 0;
		int d = 1;
		c = numeratorFromOtherFraction(c, f);
		d = denominatorFromOtherFraction(d, f);
		int x = a*d - b*c;
		int y = b*d;
		return new Fraction(x, y);
	}
	
	/**
	 * Multiplys this Fraction by Fraction F. Returns a new Fraction 
	 * that is the product of this and the Fraction f. 
	 * 
	 * @param f The Fraction to be multiplied to this one.
	 * @return The product of this fraction and the Fraction f.
	 */
	public Fraction multiply(Fraction f){
		int a = numerator;
		int b = denominator;
		int c = 0;
		int d = 1;
		c = numeratorFromOtherFraction(c, f);
		d = denominatorFromOtherFraction(d, f);
		int x = a*c;
		int y = b*d;
		return new Fraction(x, y);
	}
		
	/**
	 * Divides this Fraction by Fraction F. Returns a new Fraction 
	 * that is the quotient of dividing this by the Fraction F.
	 * 
	 * @param f The Fraction to be divided by this one.
	 * @return The quotient of dividing this Fraction by the Fraction F.
	 */
	public Fraction divide(Fraction f){
		int a = this.numerator;
		int b = this.denominator;
		int c = 0;
		int d = 1;
		c = numeratorFromOtherFraction(c, f);
		d = denominatorFromOtherFraction(d, f);
		int x = a*d;
		int y = b*c;
		return new Fraction(x, y);
	}
	
	/**
	 * Makes this Fraction to be absolute number. Returns a new Fraction 
	 * that is the absolute value of this fraction.
	 * 
	 * @return The absolute value of this Fraction. 
	 */
	public Fraction abs(){
		int a = numerator;
		int b = denominator;
		if (a*b > 0){
			return new Fraction(a, b);
		} 
		return new Fraction(-a, b);
	}
	
	/**
	 * Makes the opposite sign of this Fraction.  
	 * 
	 * @return The opposite sign of this Fraction. 
	 */
	public Fraction negate(){
		int a = numerator;
		int b = denominator;
		return new Fraction(-a, b);
	}
		
	/**
	 * Inverses this Fraction.
	 * 
	 * @return The inverse of this fraction. 
	 */
	public Fraction inverse(){
		int a = numerator;
		int b = denominator;
		return new Fraction(b, a);
	}
	
	/**
	 * Checks Object o is a Fraction. 
	 * If it is a fraction, checks this Fraction is equal to Object o. 
	 * 
	 * @param o Object o.
	 * @return boolean. Returns true if o is a Fraction equal to this, and false in all other cases.
	 */
	@Override
	public boolean equals(Object o){
		if (o instanceof Fraction){
			Fraction f = (Fraction) o; 
			if (this.numerator == f.numerator && this.denominator == f.denominator) return true;
		}
		return false;
	}
	
	/**
	 * Checks Object o is a Fraction or an int. If it is, checks this Fraction 
	 * is smaller, bigger or equal to Object o.
	 * 
	 * @param o Object o.
	 * @return A negative int if this is less than o. Zero if this is equal to o. 
	 * A positive int if this is greater than o.
	 * @exception ClassCastException If o is neither a Fraction nor an int.
	 * 
	 */
	@Override 
	public int compareTo(Object o) {
		int a = this.numerator;
		int b = this.denominator;
		int x = a/b;
		if (o instanceof Fraction){
			Fraction f = (Fraction) o; 
			int c = f.numerator;
			int d = f.denominator;
			int y = c/d;
			return x-y;
		}
		else if (o instanceof Integer){
			Integer f = (Integer) o;
			return x-f;
		}
		else throw new ClassCastException();
	}
	
	/**
	 * Converts illegitimate format of Fraction to legitimate format, n/d, 
	 * where n is the numerator and d is the denominator.
	 * For example, if d is 1, just return n (as a String) and 
	 * the returned String should not contain any blanks.
	 * If the fraction represents a negative number, a minus sign should precede the n.
	 * 
	 * @return The legitimate format of Fraction.
	 */
	@Override
	public String toString(){
		if (numerator * denominator > 0){
			if (denominator == 1) return "" + numerator;
			}
		else {
			if (denominator == -1) return "" + -numerator;
			else if (denominator == 1) return "" + numerator;
			else if (denominator < 0) return + -numerator + "/" + -denominator;
			}
		return numerator + "/" + denominator;
	}
			
	/**
	 * Finds the greatest common divisor of integer x and integer y.
	 * 
	 * @param x The first number. 
	 * @param y The second number. 
	 * @return The greatest common divisor of integer x and integer y.
	 */
	private int greatestCommonDivisor(int x, int y){
		if (x < 0) x = x * (-1);
		if (y < 0) y = y * (-1);
		while(x != 0 && y != 0){
			if (x > y) x = x % y;
			else if (y > x) y = y % x;
			else if (x == y) y = 0;		
		} 
		if (x == 0) return y; 
		else return x;
	}
	
	/**
	 * Converts output of Fraction f through toString method to integer. 
	 * And returns the numerator. 
	 * 
	 * @param c The integer assigned to be the numerator. 
	 * @param f The other fraction.
	 * @return The numerator of fraction f.
	 */
	private int numeratorFromOtherFraction(int c, Fraction f){
		String f1 = f.toString();
		if (f1.indexOf('/') >= 0){
			String[] f2 = f1.split("/");
			c = Integer.parseInt(f2[0]);				
			return c;
		} 
		c = Integer.parseInt(f1);
		return c;
	}
	
	/**
	 * Converts output of Fraction f through toString method to integer. 
	 * And returns the denominator. 
	 * 
	 * @param d The integer assigned to be the denominator. 
	 * @param f The other fraction.
	 * @return The denominator of fraction f.
	 */
	private int denominatorFromOtherFraction(int d, Fraction f){
		String f1 = f.toString();
		if (f1.indexOf('/') >= 0){
			String[] f2 = f1.split("/");				
			d = Integer.parseInt(f2[1]);
			return d;
		} 
		d = 1;
		return d;
	}
}
