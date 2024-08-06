import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SnowflakeService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> queryView(String viewName) {
        String sql = "SELECT * FROM " + viewName;
        return jdbcTemplate.queryForList(sql);
    }
}
