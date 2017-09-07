package UnitTest.JUnit.trainning.Example.Assume;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

public class JunitAssumptionTest {

	/**
	 * Thực hiện kiểm tra version hệ thống (giả lập là lấy dc giá trị 7)
	 * Trường hợp nếu version của hệ thống là 7 thì các testcase được phép thực hiện,
	 * nếu không sẽ bị disable
	 */
	@Before
	public void setUp() {
		String versionNumber = "7"; // Get it from configuration on runtime
		Assume.assumeTrue(Integer.valueOf(versionNumber) == 7);
	}

	@Test
	public void testIfVersioonGreaterThan4() {
		System.out.println("Test executed");
	}
}
