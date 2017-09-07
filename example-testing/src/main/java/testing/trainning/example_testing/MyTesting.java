package testing.trainning.example_testing;

public class MyTesting {

	MyClass myClass = new MyClass();

	public Float testMethodDivide() {
		int num1 = 10;
		int num2 = 0;
		
		return myClass.divideTowNumber(num1, num2);
	}
	
	public boolean testMethodDivide2() {
		int num1 = 0;
		int num2 = 5;
		System.out.println(myClass.divideTowNumber(num1, num2));
		if (myClass.divideTowNumber(num1, num2) == 0.0f) {
			return true;
		}
		return false;
	}

}
