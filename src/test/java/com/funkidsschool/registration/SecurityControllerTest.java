import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import com.samda.controller.SecurityController;
import com.samda.service.SecurityService;

public class SecurityControllerTest {

    @Mock
    private SecurityService securityService;

    @InjectMocks
    private SecurityController securityController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllSecurities() {
        // Arrange
        when(securityService.getAllSecurities()).thenReturn(mock(List.class));

        // Act
        ResponseEntity<List> result = securityController.getAllSecurities();

        // Assert
        assertNotNull(result);
        verify(securityService, times(1)).getAllSecurities();
    }
}
