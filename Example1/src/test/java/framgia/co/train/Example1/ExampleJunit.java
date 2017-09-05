package framgia.co.train.Example1;

import junit.framework.TestCase;

public class ExampleJunit extends TestCase {
	// If not extends Testcase, you using Annotation @Testcase
	public void testAdd() {
		String str = "Junit is working fine";
		assertEquals("Junit is working fine", str);
	}
	
	public void testaa() {
		String str = "Junit is working fine";
		assertEquals("Junit is working fine111", str);
	}
}
