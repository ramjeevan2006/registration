import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MyCustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        // Perform health check logic here
        boolean isHealthy = checkSomeCriticalComponent();
        if (isHealthy) {
            return Health.up().withDetail("customIndicator", "Everything is OK!").build();
        } else {
            return Health.down().withDetail("customIndicator", "Something is wrong!").build();
        }
    }

    private boolean checkSomeCriticalComponent() {
        // Logic to check the health of some critical component
        return true;
    }
}
