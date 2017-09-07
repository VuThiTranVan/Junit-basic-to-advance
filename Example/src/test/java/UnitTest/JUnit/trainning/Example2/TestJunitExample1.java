package UnitTest.JUnit.trainning.Example2;

import junit.framework.TestCase;

public class TestJunitExample1 extends TestCase {

	public void testAdd() {
		// test data
		int num = 5;
		String str = "Abc";

		// check for equality
		assertEquals("Abc", str);

		// check for false condition
		assertFalse(num > 6);

		// check for not null value
		assertNotNull(str);
	}
}
