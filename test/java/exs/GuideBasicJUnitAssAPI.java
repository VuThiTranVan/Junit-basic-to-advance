

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class GuideBasicJUnitAssAPI {
	// Assertions
	@Test
	void lambdaExpressions() {
		List<Integer> numbers = Arrays.asList(1, 2, 3);
		assertTrue(numbers.stream().mapToInt(Integer::intValue).sum() > 5, "Sum should be greater than 5");
	}

	@Disabled("test to show MultipleFailuresError")
	@Test
	void groupAssertions() {
		int[] numbers = { 0, 1, 2, 3, 4 };
		assertAll("numbers", () -> assertEquals(numbers[0], 1), () -> assertEquals(numbers[3], 3),
				() -> assertEquals(numbers[4], 1));
	}

	// Assumptions
	@Test
	public void trueAssumption() {
		assumeTrue(5 > 1, () -> "5 is greater the 1");
		assertEquals(5 + 2, 7);
	}

	@Test
	public void falseAssumption() {
		assumeFalse(5 < 1, () -> "5 is less then 1");
		assertEquals(5 + 2, 7);
	}

	@Test
	public void assumptionThat() {
		String someString = "Just a string";
		assumingThat(someString.equals("Just a string"), () -> assertEquals(2 + 2, 4));
	}

}
