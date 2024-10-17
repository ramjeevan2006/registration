import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import com.samda.headers.InterceptorsConfig;
import com.samda.headers.SchwabHeaders;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

public class InterceptorsConfigTest {

    @Mock
    private InterceptorRegistry interceptorRegistry;

    @Mock
    private SchwabHeaders schwabHeaders;

    @InjectMocks
    private InterceptorsConfig interceptorsConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddInterceptors() {
        // Act
        interceptorsConfig.addInterceptors(interceptorRegistry);

        // Assert
        verify(interceptorRegistry, times(1)).addInterceptor(any());
    }

    @Test
    void testSchwabHeadersBeanCreation() {
        // Act
        SchwabHeaders result = interceptorsConfig.schawbHeaders();

        // Assert
        assertNotNull(result);
        assertTrue(result instanceof SchwabHeaders);
    }

    @Test
    void testSchwabHeadersScopeAnnotation() throws NoSuchMethodException {
        // Reflectively check the @Scope annotation
        Scope scope = InterceptorsConfig.class.getMethod("schawbHeaders").getAnnotation(Scope.class);

        assertNotNull(scope);
        assertEquals(WebApplicationContext.SCOPE_REQUEST, scope.value());
        assertEquals(Scope.PROXY_MODE_TARGET_CLASS, scope.proxyMode());
    }
}
