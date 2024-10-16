import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.samda.config.ArcataHealthCheck;
import com.samda.config.HealthCheck;

public class ArcataHealthCheckTest {

    private ArcataHealthCheck arcataHealthCheck;

    @BeforeEach
    void setUp() {
        arcataHealthCheck = new ArcataHealthCheck();
    }

    @Test
    void testGetStatus() {
        // Act
        HealthCheck.AvailabilityReport result = arcataHealthCheck.getStatus();

        // Assert
        assertNotNull(result);
        assertTrue(result.isPassed());
    }
}
