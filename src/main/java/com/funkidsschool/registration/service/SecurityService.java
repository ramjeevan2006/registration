import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityService {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Security> getAllSecurities() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Security", Security.class).list();
        }
    }

    // Other service methods can go here
}
