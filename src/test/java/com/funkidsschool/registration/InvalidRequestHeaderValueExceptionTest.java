import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.samda.exception.InvalidRequestHeaderValueException;

public class InvalidRequestHeaderValueExceptionTest {

    @Test
    void testConstructorWithHeader() {
        // Arrange
        String header = "Authorization-Header";
        String expectedMessage = "Authorization-Header is not provided.";

        // Act
        InvalidRequestHeaderValueException exception = new InvalidRequestHeaderValueException(header);

        // Assert
        assertNotNull(exception);
        assertEquals(expectedMessage, exception.getMessage());
    }
}
