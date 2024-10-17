import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.samda.exception.InvalidUUIDException;

public class InvalidUUIDExceptionTest {

    @Test
    void testConstructorWithMessage() {
        // Arrange
        String message = "Invalid UUID format provided";

        // Act
        InvalidUUIDException exception = new InvalidUUIDException(message);

        // Assert
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
    }
}
