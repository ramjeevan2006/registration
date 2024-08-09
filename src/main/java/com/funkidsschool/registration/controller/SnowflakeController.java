import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/snowflake")
public class SnowflakeController {

    @Autowired
    private SnowflakeService snowflakeService;

    @GetMapping("/view/{viewName}")
    public List<Map<String, Object>> getViewData(@PathVariable String viewName) {
        return snowflakeService.queryView(viewName);
    }
}
