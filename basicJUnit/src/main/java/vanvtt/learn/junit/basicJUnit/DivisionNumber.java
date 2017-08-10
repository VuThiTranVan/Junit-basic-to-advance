package vanvtt.learn.junit.basicJUnit;

/**
 * Hello world!
 *
 */
public class DivisionNumber {

	public int divisionTwoNumberNoneCheck(int num1, int num2) {
		return num1 / num2;
	}

	public int divisionTwoNumber(int num1, int num2) {

		if (num2 == 0)
			throw new ArithmeticException("Can't be divided by Zero");

		if (num1 == 0)
			return 0;

		return num1 / num2;
	}

	public void displayResult(int num1, int num2) {
		System.out.println("result is " + divisionTwoNumber(num1, num2));
	}
}
