import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.samda.repository.SecurityRepository;
import com.samda.repository.SecurityServiceJpa;

public class SecurityServiceJpaTest {

    @Mock
    private SecurityRepository securityRepository;

    @InjectMocks
    private SecurityServiceJpa securityServiceJpa;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllSecurities() {
        // Arrange
        when(securityRepository.findAll()).thenReturn(mock(List.class));

        // Act
        List result = securityServiceJpa.getAllSecurities();

        // Assert
        assertNotNull(result);
        verify(securityRepository, times(1)).findAll();
    }
}
