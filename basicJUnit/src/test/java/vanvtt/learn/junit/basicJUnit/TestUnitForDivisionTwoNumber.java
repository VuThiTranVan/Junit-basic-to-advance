package vanvtt.learn.junit.basicJUnit;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestUnitForDivisionTwoNumber {

	public DivisionNumber setUp() {
		return new DivisionNumber();
	}

	@Test
	public void whenInputNumber1IsZeroThenResultIsZero() {
		assertEquals(0, setUp().divisionTwoNumber(0, 2));
	}

	// Ngoài cách dùng annotation thì còn có các cách khác như dùng try-catch, @Rule...
	/* Tham khảo link:
	http://blog.codeleak.pl/2013/07/3-ways-of-handling-exceptions-in-junit.html
	https://blog.goyello.com/2015/10/01/different-ways-of-testing-exceptions-in-java-and-junit/
	*/
	@Test(expected = ArithmeticException.class)
	public void whenInputNumber2ZeroThenReturnException() {
		setUp().divisionTwoNumber(3, 0);
	}
}
