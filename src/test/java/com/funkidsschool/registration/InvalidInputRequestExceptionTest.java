import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.samda.exception.InvalidInputRequestException;

public class InvalidInputRequestExceptionTest {

    @Test
    void testConstructorWithException() {
        // Arrange
        Exception cause = new Exception("Invalid input provided");

        // Act
        InvalidInputRequestException exception = new InvalidInputRequestException(cause);

        // Assert
        assertNotNull(exception);
        assertEquals("Please check the input type passed. Invalid input provided", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}
