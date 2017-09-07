package UnitTest.JUnit.trainning.Example;

import junit.framework.TestCase;
import static org.junit.Assert.assertArrayEquals;

public class TestJunitExample1 extends TestCase {

	public void testAdd() {
		// test data
		int num = 5;
		String str = "Abc";

		// check for equality
		assertEquals("Abc", str);

		// check for not null value
		assertNotNull(str);
	}

	public void testAssert() {

		// Variable declaration
		String string1 = "Junit";
		String string2 = "Junit";
		String string3 = "test";
		String string4 = "test";
		String string5 = null;
		int variable1 = 1;
		int variable2 = 2;
		int[] airethematicArrary1 = { 1, 2, 3 };
		int[] airethematicArrary2 = { 1, 2, 3 };

		// Assert statements
		assertEquals(string1, string2);
		assertSame(string3, string4);
		assertNotSame(string1, string3);
		assertNotNull(string1);
		assertNull(string5);
		assertTrue(variable1 < variable2);
		assertArrayEquals(airethematicArrary1, airethematicArrary2);
	}
}
