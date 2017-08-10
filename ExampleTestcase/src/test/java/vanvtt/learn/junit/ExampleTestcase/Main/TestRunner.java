package vanvtt.learn.junit.ExampleTestcase.Main;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import vanvtt.learn.junit.ExampleTestcase.MyTestCase;

public class TestRunner {

	public static void main(String[] args) {
		Result rs = JUnitCore.runClasses(MyTestCase.class);

		for (Failure fail : rs.getFailures()) {
			System.out.println(fail.toString());
		}
		System.out.println(rs.wasSuccessful());
	}

}
