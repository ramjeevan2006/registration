import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.samda.interceptor.HeadersInterceptor;
import com.samda.headers.SchwabHeaders;

public class HeadersInterceptorTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private Object handler;

    @Mock
    private SchwabHeaders schwabHeaders;

    @InjectMocks
    private HeadersInterceptor headersInterceptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testPreHandleWithClassificationPath() {
        // Arrange
        when(request.getServletPath()).thenReturn("/classifications");

        // Act
        boolean result = headersInterceptor.preHandle(request, response, handler);

        // Assert
        assertTrue(result);
        verify(schwabHeaders, never()).setSchwabHeaders(any(HttpServletRequest.class));
    }

    @Test
    void testPreHandleWithFundRatingsPath() {
        // Arrange
        when(request.getServletPath()).thenReturn("/ratings/funds");

        // Act
        boolean result = headersInterceptor.preHandle(request, response, handler);

        // Assert
        assertTrue(result);
        verify(schwabHeaders, never()).setSchwabHeaders(any(HttpServletRequest.class));
    }

    @Test
    void testPreHandleWithEquityRatingsPath() {
        // Arrange
        when(request.getServletPath()).thenReturn("/ratings/equities");

        // Act
        boolean result = headersInterceptor.preHandle(request, response, handler);

        // Assert
        assertTrue(result);
        verify(schwabHeaders, never()).setSchwabHeaders(any(HttpServletRequest.class));
    }

    @Test
    void testPreHandleWithTempEndPointPath() {
        // Arrange
        when(request.getServletPath()).thenReturn("/api/fof/securities");

        // Act
        boolean result = headersInterceptor.preHandle(request, response, handler);

        // Assert
        assertTrue(result);
        verify(schwabHeaders, never()).setSchwabHeaders(any(HttpServletRequest.class));
    }

    @Test
    void testPreHandleWithOtherPath() {
        // Arrange
        when(request.getServletPath()).thenReturn("/other/path");

        // Act
        boolean result = headersInterceptor.preHandle(request, response, handler);

        // Assert
        assertTrue(result);
        verify(schwabHeaders, times(1)).setSchwabHeaders(request);
    }
}
