import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.samda.exception.AuthorizationException;

public class AuthorizationExceptionTest {

    @Test
    void testConstructorWithException() {
        // Arrange
        String expectedMessage = "Authentication Failed. The token passed is expired or not valid.";
        Exception cause = new Exception("Cause");

        // Act
        AuthorizationException exception = new AuthorizationException(cause);

        // Assert
        assertNotNull(exception);
        assertEquals(expectedMessage, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testConstructorWithHeader() {
        // Arrange
        String header = "Authorization-Token";
        String expectedMessage = "Authorization-Token is not provided. Please authorize to continue";

        // Act
        AuthorizationException exception = new AuthorizationException(header);

        // Assert
        assertNotNull(exception);
        assertEquals(expectedMessage, exception.getMessage());
    }
}
