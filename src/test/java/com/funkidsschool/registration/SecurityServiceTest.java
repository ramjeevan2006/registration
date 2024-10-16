import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.samda.service.SecurityService;
import com.samda.repository.SecurityServiceJpa;

public class SecurityServiceTest {

    @Mock
    private SecurityServiceJpa securityServiceJpa;

    @InjectMocks
    private SecurityService securityService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllSecurities() {
        // Arrange
        when(securityServiceJpa.getAllSecurities()).thenReturn(mock(List.class));

        // Act
        List result = securityService.getAllSecurities();

        // Assert
        assertNotNull(result);
        verify(securityServiceJpa, times(1)).getAllSecurities();
    }
}
