import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import javax.servlet.http.HttpServletRequest;
import com.samda.headers.SchwabHeaders;
import com.samda.exception.InvalidInputRequestException;

import java.util.Map;
import java.util.UUID;

public class SchwabHeadersTest {

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private SchwabHeaders schwabHeaders;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSetSchwabHeaders_Success() {
        // Arrange
        when(request.getHeader("authorization")).thenReturn("validAuth");
        when(request.getHeader("schwab-client-correlid")).thenReturn(UUID.randomUUID().toString());
        when(request.getHeader("schwab-client-appid")).thenReturn("validAppId");
        when(request.getHeader("schwab-resource-version")).thenReturn("2");

        // Act
        schwabHeaders.setSchwabHeaders(request);

        // Assert
        assertEquals("validAuth", schwabHeaders.getAuthorizationString());
        assertNotNull(schwabHeaders.getCorrelationId());
        assertEquals("validAppId", schwabHeaders.getAppId());
        assertEquals(2, schwabHeaders.getResourceVersion());
    }

    @Test
    void testSetSchwabHeaders_InvalidInputException() {
        // Arrange
        when(request.getHeader("authorization")).thenReturn(null); // Simulate missing header

        // Act & Assert
        assertThrows(InvalidInputRequestException.class, () -> schwabHeaders.setSchwabHeaders(request));
    }

    @Test
    void testToMap_Success() {
        // Arrange
        UUID correlationId = UUID.randomUUID();
        schwabHeaders.setAuthorizationString("validAuth");
        schwabHeaders.setCorrelationId(correlationId);
        schwabHeaders.setAppId("validAppId");
        schwabHeaders.setResourceVersion(2);

        // Act
        Map<String, Object> result = schwabHeaders.toMap();

        // Assert
        assertNotNull(result);
        assertEquals(correlationId, result.get("schwab-client-correlid"));
        assertEquals("validAppId", result.get("schwab-client-appid"));
        assertEquals(2, result.get("schwab-resource-version"));
    }
}
