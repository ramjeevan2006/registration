import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SecurityService {

    @Autowired
    private SecurityRepository securityRepository;

    public List<Security> getAllSecurities() {
        return securityRepository.findAll();
    }
}
