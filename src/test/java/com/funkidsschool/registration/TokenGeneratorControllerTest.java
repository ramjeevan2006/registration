import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.samda.controller.TokenGeneratorController;
import com.samda.generator.CamTokenGenerator; // Assume this is the correct package for CamTokenGenerator

public class TokenGeneratorControllerTest {

    @Mock
    private CamTokenGenerator camTokenGenerator;

    @InjectMocks
    private TokenGeneratorController tokenGeneratorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); // Initialize mocks
    }

    @Test
    void testGenerateTokenSuccess() throws Exception {
        // Arrange
        String mockToken = "mockToken";
        when(camTokenGenerator.generate()).thenReturn(mockToken);

        // Act
        ResponseEntity<String> response = tokenGeneratorController.generateToken();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockToken, response.getBody());

        // Verify that the generate method was called once
        verify(camTokenGenerator, times(1)).generate();
    }

    @Test
    void testGenerateTokenFailure() throws Exception {
        // Arrange
        String errorMessage = "Token generation failed";
        when(camTokenGenerator.generate()).thenThrow(new RuntimeException(errorMessage));

        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            tokenGeneratorController.generateToken();
        });

        assertEquals(errorMessage, thrown.getMessage());

        // Verify that the generate method was called once
        verify(camTokenGenerator, times(1)).generate();
    }
}
