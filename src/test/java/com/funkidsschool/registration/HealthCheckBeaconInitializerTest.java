import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.samda.config.HealthCheckBeaconInitializer;
import com.samda.config.ArcataClient;

public class HealthCheckBeaconInitializerTest {

    @Mock
    private ArcataClient arcataClient;

    @InjectMocks
    private HealthCheckBeaconInitializer healthCheckBeaconInitializer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testInit() {
        // Act
        healthCheckBeaconInitializer.initializeAndStartHealthBeacon();

        // Assert
        verify(arcataClient, times(1)).start();
    }
}
