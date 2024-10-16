import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import com.samda.controller.SecurityController;
import com.samda.service.SecuritiesService;
import com.samda.util.AuthenticationUtility;
import com.samda.headers.SchwabHeaders;

import java.util.List;

public class SecurityControllerTest {

    @Mock
    private SecuritiesService securitiesService;

    @Mock
    private AuthenticationUtility authenticationUtility;

    @Mock
    private SchwabHeaders schwabHeaders;

    @InjectMocks
    private SecurityController securityController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllSecurities() {
        // Arrange
        when(authenticationUtility.authorize(schwabHeaders, "poc")).thenReturn(true);
        when(securitiesService.getAllSecurities()).thenReturn(mock(List.class));

        // Act
        ResponseEntity<List<Security>> result = securityController.getAllSecurities();

        // Assert
        assertNotNull(result);
        assertEquals(200, result.getStatusCodeValue());
        verify(securitiesService, times(1)).getAllSecurities();
    }
}
