import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HandleException {
	@Test
	void assertThrowsException() {
		String str = null;
		assertThrows(IllegalArgumentException.class, () -> {
			Integer.valueOf(str);
		});
	}

	@Test
	public void whenExceptionThrown_thenAssertionSucceeds() {
		Exception exception = assertThrows(NumberFormatException.class, () -> {
			Integer.parseInt("1a");
		});

		String expectedMessage = "For input string";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void whenDerivedExceptionThrown_thenAssertionSucceeds() {
		Exception exception = assertThrows(RuntimeException.class, () -> {
			Integer.parseInt("1a");
		});

		String expectedMessage = "For input string";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
}
