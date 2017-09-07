package testing.trainning.example_testing;

public class MyClass {

	// Step 1
	// public Float divideTowNumber(int num1, int num2) {
	// return (float) (num1 / num2);
	// }

	// step 2
	// public Float divideTowNumber(int num1, int num2) {
	// if (num2 == 0)
	// return null;
	//
	// return (float) (num1 / num2);
	// }

	// step 3
	public Float divideTowNumber(int num1, int num2) {
		if (num2 == 0)
			return null;

		if (num1 == 0)
			return 0.0f;

		return (float) (num1 / num2);
	}

}
