package vanvtt.learn.junit.basicJUnit.main;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import vanvtt.learn.junit.basicJUnit.TestUnitForDivisionTwoNumber;

public class TestRunner {

	// Class này hỗ trợ việc đọc cách message lỗi.
	// Vẫn không cần class này cũng có thể run junit tại class TestUnitForDivisionTwoNumber
	public static void main(String[] args) {
		Result rs = JUnitCore.runClasses(TestUnitForDivisionTwoNumber.class);
		
		for (Failure fail : rs.getFailures()) {
			System.out.println(fail.toString());
		}

		System.out.println(rs.wasSuccessful());
	}

}
